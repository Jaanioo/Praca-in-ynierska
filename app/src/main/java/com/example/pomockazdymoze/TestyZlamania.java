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

        quizModalArrayList.add(new Pytanie("Co należy zrobić w przypadku podejrzenia zwichnięcia stawu kolanowego?",
                "Rozmasować staw.",
                "Nakazać poszkodowanemu wykonywanie delikatnych ruchów uszkodzoną kończyną.",
                "Unieruchomić staw w pozycji fizjologicznej (prawidłowej). ",
                "Unieruchomić staw kolanowy w pozycji zastanej.",
                "Unieruchomić staw kolanowy w pozycji zastanej." ));

        quizModalArrayList.add(new Pytanie("Które kości są najbardziej narażone na złamania?",
                "Strzemiączko.",
                "Obojczyk.",
                "Miednica.",
                "Strzałkowa.",
                "Strzałkowa." ));

        quizModalArrayList.add(new Pytanie("Które z wymienionych objawów nie są objawami złamania?",
                "Pełna ruchomość kończyny.",
                "Ograniczona ruchomość kończyny.",
                "Ból nasilający się przy ruchu.",
                "Nienaturalne ułożenie kończyny.",
                "Pełna ruchomość kończyny." ));

        quizModalArrayList.add(new Pytanie("Zwichnięcie to:",
                "Wzajemne przemieszczenie się względem siebie powierzchni stawowych kości.",
                "Zamknięte uszkodzenie wewnętrznej struktury tkanki powstałe w wyniku urazu mechanicznego polegające na zgnieceniu komórek.",
                "Przerwanie ciągłości tkanki kostnej.",
                "Przerwanie naczynia tętniczego lub krwionośnego.",
                "Wzajemne przemieszczenie się względem siebie powierzchni stawowych kości." ));

        quizModalArrayList.add(new Pytanie("Co jest pierwszym objawem skręcenia?",
                "Pełna ruchomość kończyny.",
                "Ból.",
                "Krwiak.",
                "Obrzęk.",
                "Ból." ));

        quizModalArrayList.add(new Pytanie("Prostym i skutecznym sposobem unieruchomienia kończyny dolnej jest:",
                "Przymocowanie jej do kończyny zdrowej.",
                "Założenie chusty trójkątnej.",
                "Wyprostowanie uszkodzonych kończyn i zabandażowanie.",
                "Żadne z powyższych.",
                "Przymocowanie jej do kończyny zdrowej." ));

        quizModalArrayList.add(new Pytanie("Przy zwichnięciu lub skręceniu należy unieruchomić:",
                "Tylko uszkodzony staw.",
                "Uszkodzony staw wraz z dwiema sąsiadującymi kośćm.",
                "Kość i dwa sąsiednie stawy.",
                "Żadne z powyższych.",
                "Uszkodzony staw wraz z dwiema sąsiadującymi kośćmi." ));

        quizModalArrayList.add(new Pytanie("Urazy kręgosłupa należy podejrzewać przy:",
                "Wypadkach komunikacyjnych.",
                "Upadku z dużej wysokości.",
                "Skokach do wody na \"główkę\"",
                "Wszystkie odpowiedzi są poprawne.",
                "Wszystkie odpowiedzi są poprawne." ));

        quizModalArrayList.add(new Pytanie("Chłopiec po upadku z drabiny z wysokości 3 m leży na ziemi i jęczy. Co należy zrobić w pierwszej kolejności:",
                "Odciągnąć go w bezpieczne miejsce.",
                "Próbować postawić na nogi.",
                "Nie ruszać do czasu przybycia zespoły ratowniczego.",
                "Podać środek p/bólowy w tabletce.",
                "Nie ruszać do czasu przybycia zespoły ratowniczego." ));

        quizModalArrayList.add(new Pytanie("W przypadku obrażeń kości i stawów możemy zmniejszyć obrzęk w następujący sposób:",
                "Bandażując opatrunkiem elastycznym.",
                "Stosując ciepły kompres.",
                "Stosując zimny okład.",
                "Polewając wodą przez 10 minut.",
                "Stosując zimny okład." ));

        quizModalArrayList.add(new Pytanie("Przy podejrzeniu złamania kończyny należy:",
                "Nastawić kości w osi kończyny.",
                "Podać doustnie lek p/bólowy o silnym działaniu.",
                "Unieruchomić w zastanej pozycji.",
                "Obandażować kończynę w celu zmniejszenia obrzęku.",
                "Unieruchomić w zastanej pozycji." ));

        quizModalArrayList.add(new Pytanie("Typowy objawurazu kręgosłupa to:",
                "Obrzęk w okolicy uszkodzenia.",
                "Mimowolne oddawanie moczu i kału.",
                "Sińce lub krwiaki w okolicy uszkodzenia.",
                "Wszystkie odpowiedzi są poprawne.",
                "Mimowolne oddawanie moczu i kału." ));

    }
}