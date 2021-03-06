package com.example.pomockazdymoze;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class TestyZlamania extends AppCompatActivity {

    private TextView trescPytania, liczbaPytan;
    private Button option1Btn, option2Btn, option3Btn, option4Btn, cofnijBigBtn;
    private ImageView napisLogo, duzeLogo;
    private ArrayList<Pytanie> queList;
    Random random;
    int wynik = 0, numerPytania = 1, aktualnePytanie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testy_zlamania);

        trescPytania = findViewById(R.id.idPytanie);
        liczbaPytan = findViewById(R.id.idLiczbaPytan);
        napisLogo = findViewById(R.id.logoNapis);
        duzeLogo = findViewById(R.id.logoDuze);

        option1Btn = findViewById(R.id.idBtnOption1);
        option2Btn = findViewById(R.id.idBtnOption2);
        option3Btn = findViewById(R.id.idBtnOption3);
        option4Btn = findViewById(R.id.idBtnOption4);
        cofnijBigBtn = findViewById(R.id.cofnijBtn);

        queList = new ArrayList<>();
        random = new Random();

        Collections.shuffle(queList);
        pytaniaQuizu(queList);
        aktualnePytanie = random.nextInt(queList.size());
        wyswietlaniePytan(aktualnePytanie);

        duzeLogo.setVisibility(View.GONE);
        napisLogo.setVisibility(View.VISIBLE);
        trescPytania.setVisibility(View.VISIBLE);
        liczbaPytan.setVisibility(View.VISIBLE);

        cofnijBigBtn.setVisibility(View.VISIBLE);
        option1Btn.setVisibility(View.VISIBLE);
        option2Btn.setVisibility(View.VISIBLE);
        option3Btn.setVisibility(View.VISIBLE);
        option4Btn.setVisibility(View.VISIBLE);

        cofnijBigBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TestyZlamania.this, TestyLista.class);
                startActivity(intent);
                finish();
            }
        });

        option1Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (queList.get(aktualnePytanie).getAnswer().trim().toLowerCase().equals(option1Btn.getText().toString().trim().toLowerCase())) {
                    wynik ++;
                }
                numerPytania ++;
                aktualnePytanie = random.nextInt(queList.size());
                wyswietlaniePytan(aktualnePytanie);
            }
        });

        option2Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (queList.get(aktualnePytanie).getAnswer().trim().toLowerCase().equals(option2Btn.getText().toString().trim().toLowerCase())) {
                    wynik ++;
                }
                numerPytania ++;
                aktualnePytanie = random.nextInt(queList.size());
                wyswietlaniePytan(aktualnePytanie);
            }
        });

        option3Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (queList.get(aktualnePytanie).getAnswer().trim().toLowerCase().equals(option3Btn.getText().toString().trim().toLowerCase())) {
                    wynik ++;
                }
                numerPytania ++;
                aktualnePytanie = random.nextInt(queList.size());
                wyswietlaniePytan(aktualnePytanie);
            }
        });

        option4Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (queList.get(aktualnePytanie).getAnswer().trim().toLowerCase().equals(option4Btn.getText().toString().trim().toLowerCase())) {
                    wynik ++;
                }
                numerPytania ++;
                aktualnePytanie = random.nextInt(queList.size());
                wyswietlaniePytan(aktualnePytanie);
            }
        });

    }

    private void pokazDolneMenu() {

        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(TestyZlamania.this);
        View bottomSheetView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.score_bottom_sheet, (LinearLayout)findViewById(R.id.idLLScore));
        TextView scoreTV = bottomSheetView.findViewById(R.id.idWynikTestu);
        Button restartQuizBtn = bottomSheetView.findViewById(R.id.idBtnRestart);
        Button cofnijBtn = bottomSheetView.findViewById(R.id.idCofnijBtn);
        scoreTV.setText("Your score is: \n" + wynik + "/" + queList.size());

        cofnijBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TestyZlamania.this, TestyLista.class);
                startActivity(intent);
                finish();
            }
        });

        restartQuizBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numerPytania = 1;
                aktualnePytanie = random.nextInt(queList.size());
                wyswietlaniePytan(aktualnePytanie);
                wynik = 0;
                bottomSheetDialog.dismiss();

                duzeLogo.setVisibility(View.GONE);
                napisLogo.setVisibility(View.VISIBLE);
                trescPytania.setVisibility(View.VISIBLE);
                liczbaPytan.setVisibility(View.VISIBLE);

                cofnijBigBtn.setVisibility(View.VISIBLE);
                option1Btn.setVisibility(View.VISIBLE);
                option2Btn.setVisibility(View.VISIBLE);
                option3Btn.setVisibility(View.VISIBLE);
                option4Btn.setVisibility(View.VISIBLE);
            }
        });
        bottomSheetDialog.setCancelable(false);
        bottomSheetDialog.setContentView(bottomSheetView);
        bottomSheetDialog.show();

    }

    private void schowajKomponenty() {

        duzeLogo.setVisibility(View.VISIBLE);
        napisLogo.setVisibility(View.GONE);
        trescPytania.setVisibility(View.GONE);
        liczbaPytan.setVisibility(View.GONE);

        cofnijBigBtn.setVisibility(View.GONE);
        option1Btn.setVisibility(View.GONE);
        option2Btn.setVisibility(View.GONE);
        option3Btn.setVisibility(View.GONE);
        option4Btn.setVisibility(View.GONE);

    }

    private void wyswietlaniePytan(int currentPos) {

        liczbaPytan.setText("Pytanie numer: " + numerPytania + "/" + queList.size());

        if (numerPytania == queList.size() + 1) {
            pokazDolneMenu();
            schowajKomponenty();
        }else {
            trescPytania.setText(queList.get(currentPos).getQuestion());
            option1Btn.setText(queList.get(currentPos).getOption1());
            option2Btn.setText(queList.get(currentPos).getOption2());
            option3Btn.setText(queList.get(currentPos).getOption3());
            option4Btn.setText(queList.get(currentPos).getOption4());
        }
    }

    private void pytaniaQuizu(ArrayList<Pytanie> quizModalArrayList) {

        quizModalArrayList.add(new Pytanie("Co nale??y zrobi?? w przypadku podejrzenia zwichni??cia stawu kolanowego?",
                "Rozmasowa?? staw.",
                "Nakaza?? poszkodowanemu wykonywanie delikatnych ruch??w uszkodzon?? ko??czyn??.",
                "Unieruchomi?? staw w pozycji fizjologicznej (prawid??owej). ",
                "Unieruchomi?? staw kolanowy w pozycji zastanej.",
                "Unieruchomi?? staw kolanowy w pozycji zastanej." ));

        quizModalArrayList.add(new Pytanie("Kt??re ko??ci s?? najbardziej nara??one na z??amania?",
                "Strzemi??czko.",
                "Obojczyk.",
                "Miednica.",
                "Strza??kowa.",
                "Strza??kowa." ));

        quizModalArrayList.add(new Pytanie("Kt??re z wymienionych objaw??w nie s?? objawami z??amania?",
                "Pe??na ruchomo???? ko??czyny.",
                "Ograniczona ruchomo???? ko??czyny.",
                "B??l nasilaj??cy si?? przy ruchu.",
                "Nienaturalne u??o??enie ko??czyny.",
                "Pe??na ruchomo???? ko??czyny." ));

        quizModalArrayList.add(new Pytanie("Zwichni??cie to:",
                "Wzajemne przemieszczenie si?? wzgl??dem siebie powierzchni stawowych ko??ci.",
                "Zamkni??te uszkodzenie wewn??trznej struktury tkanki powsta??e w wyniku urazu mechanicznego polegaj??ce na zgnieceniu kom??rek.",
                "Przerwanie ci??g??o??ci tkanki kostnej.",
                "Przerwanie naczynia t??tniczego lub krwiono??nego.",
                "Wzajemne przemieszczenie si?? wzgl??dem siebie powierzchni stawowych ko??ci." ));

        quizModalArrayList.add(new Pytanie("Co jest pierwszym objawem skr??cenia?",
                "Pe??na ruchomo???? ko??czyny.",
                "B??l.",
                "Krwiak.",
                "Obrz??k.",
                "B??l." ));

        quizModalArrayList.add(new Pytanie("Prostym i skutecznym sposobem unieruchomienia ko??czyny dolnej jest:",
                "Przymocowanie jej do ko??czyny zdrowej.",
                "Za??o??enie chusty tr??jk??tnej.",
                "Wyprostowanie uszkodzonych ko??czyn i zabanda??owanie.",
                "??adne z powy??szych.",
                "Przymocowanie jej do ko??czyny zdrowej." ));

        quizModalArrayList.add(new Pytanie("Przy zwichni??ciu lub skr??ceniu nale??y unieruchomi??:",
                "Tylko uszkodzony staw.",
                "Uszkodzony staw wraz z dwiema s??siaduj??cymi ko????m.",
                "Ko???? i dwa s??siednie stawy.",
                "??adne z powy??szych.",
                "Uszkodzony staw wraz z dwiema s??siaduj??cymi ko????mi." ));

        quizModalArrayList.add(new Pytanie("Urazy kr??gos??upa nale??y podejrzewa?? przy:",
                "Wypadkach komunikacyjnych.",
                "Upadku z du??ej wysoko??ci.",
                "Skokach do wody na \"g????wk??\"",
                "Wszystkie odpowiedzi s?? poprawne.",
                "Wszystkie odpowiedzi s?? poprawne." ));

        quizModalArrayList.add(new Pytanie("Ch??opiec po upadku z drabiny z wysoko??ci 3 m le??y na ziemi i j??czy. Co nale??y zrobi?? w pierwszej kolejno??ci:",
                "Odci??gn???? go w bezpieczne miejsce.",
                "Pr??bowa?? postawi?? na nogi.",
                "Nie rusza?? do czasu przybycia zespo??y ratowniczego.",
                "Poda?? ??rodek p/b??lowy w tabletce.",
                "Nie rusza?? do czasu przybycia zespo??y ratowniczego." ));

        quizModalArrayList.add(new Pytanie("W przypadku obra??e?? ko??ci i staw??w mo??emy zmniejszy?? obrz??k w nast??puj??cy spos??b:",
                "Banda??uj??c opatrunkiem elastycznym.",
                "Stosuj??c ciep??y kompres.",
                "Stosuj??c zimny ok??ad.",
                "Polewaj??c wod?? przez 10 minut.",
                "Stosuj??c zimny ok??ad." ));

        quizModalArrayList.add(new Pytanie("Przy podejrzeniu z??amania ko??czyny nale??y:",
                "Nastawi?? ko??ci w osi ko??czyny.",
                "Poda?? doustnie lek p/b??lowy o silnym dzia??aniu.",
                "Unieruchomi?? w zastanej pozycji.",
                "Obanda??owa?? ko??czyn?? w celu zmniejszenia obrz??ku.",
                "Unieruchomi?? w zastanej pozycji." ));

        quizModalArrayList.add(new Pytanie("Typowy objawurazu kr??gos??upa to:",
                "Obrz??k w okolicy uszkodzenia.",
                "Mimowolne oddawanie moczu i ka??u.",
                "Si??ce lub krwiaki w okolicy uszkodzenia.",
                "Wszystkie odpowiedzi s?? poprawne.",
                "Mimowolne oddawanie moczu i ka??u." ));

    }
}