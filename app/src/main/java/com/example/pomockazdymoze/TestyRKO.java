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

public class TestyRKO extends AppCompatActivity {

    private TextView trescPytania, liczbaPytan;
    private Button option1Btn, option2Btn, option3Btn, option4Btn, cofnijBigBtn;
    private ImageView napisLogo, duzeLogo;
    private ArrayList<Pytanie> queList;
    Random random;
    int wynik = 0, numerPytania = 1, aktualnePytanie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testy_rko);

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
                Intent intent = new Intent(TestyRKO.this, TestyLista.class);
                startActivity(intent);
                finish();
            }
        });

        option1Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (queList.get(aktualnePytanie).getAnswer().trim().toLowerCase().equals(option1Btn.getText().toString().trim().toLowerCase())) {
                    wynik++;
                }
                numerPytania++;
                aktualnePytanie = random.nextInt(queList.size());
                wyswietlaniePytan(aktualnePytanie);
            }
        });

        option2Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (queList.get(aktualnePytanie).getAnswer().trim().toLowerCase().equals(option2Btn.getText().toString().trim().toLowerCase())) {
                    wynik++;
                }
                numerPytania++;
                aktualnePytanie = random.nextInt(queList.size());
                wyswietlaniePytan(aktualnePytanie);
            }
        });

        option3Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (queList.get(aktualnePytanie).getAnswer().trim().toLowerCase().equals(option3Btn.getText().toString().trim().toLowerCase())) {
                    wynik++;
                }
                numerPytania++;
                aktualnePytanie = random.nextInt(queList.size());
                wyswietlaniePytan(aktualnePytanie);
            }
        });

        option4Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (queList.get(aktualnePytanie).getAnswer().trim().toLowerCase().equals(option4Btn.getText().toString().trim().toLowerCase())) {
                    wynik++;
                }
                numerPytania++;
                aktualnePytanie = random.nextInt(queList.size());
                wyswietlaniePytan(aktualnePytanie);
            }
        });

    }

    private void pokazDolneMenu() {

        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(TestyRKO.this);
        View bottomSheetView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.score_bottom_sheet, (LinearLayout) findViewById(R.id.idLLScore));
        TextView scoreTV = bottomSheetView.findViewById(R.id.idWynikTestu);
        Button restartQuizBtn = bottomSheetView.findViewById(R.id.idBtnRestart);
        Button cofnijBtn = bottomSheetView.findViewById(R.id.idCofnijBtn);
        scoreTV.setText("Your score is: \n" + wynik + "/" + queList.size());

        cofnijBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TestyRKO.this, TestyLista.class);
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
        } else {
            trescPytania.setText(queList.get(currentPos).getQuestion());
            option1Btn.setText(queList.get(currentPos).getOption1());
            option2Btn.setText(queList.get(currentPos).getOption2());
            option3Btn.setText(queList.get(currentPos).getOption3());
            option4Btn.setText(queList.get(currentPos).getOption4());
        }
    }

    private void pytaniaQuizu(ArrayList<Pytanie> quizModalArrayList) {

        quizModalArrayList.add(new Pytanie("Rozwi?? skr??t RKO.",
                "Resuscytacja kr????eniowo-oddechowa.",
                "Resuscytacja kr????eniowo-og??lna.",
                "Resutacja kr????eniowo-oddechowa.",
                "Reanimacja kolano-odwrotowa",
                "Resuscytacja kr????eniowo-oddechowa."));

        quizModalArrayList.add(new Pytanie("Ile wynosi stosunek uci??nie?? do wdech??w?",
                "25:2",
                "30:2",
                "40:2",
                "30:3",
                "30:2"));

        quizModalArrayList.add(new Pytanie("Co to jest AED?",
                "Automatyczny defibrulator zewn??trzny.",
                "Automatyczny defibrylator zewn??trznokom??rkowy",
                "Automatyczny defibrylator zewn??trzny.",
                "Automatyczny defibrylator wewn??trzny",
                "Automatyczny defibrylator zewn??trzny."));

        quizModalArrayList.add(new Pytanie("Co oznacza skr??t NZK?",
                "Nag??e zatrzymanie kr????enia.",
                "Natychmiasowe zatrzymanie kr????enia",
                "Nag??e zwolnienie kr????enia",
                "Natychmiastowe zatrzymanie ko??ci.",
                "Nag??e zatrzymanie kr????enia."));

        quizModalArrayList.add(new Pytanie("Gdzie nale??y umie??ci?? r??ce do robienia ucisk??w?",
                "2 cm nad p??pkiem.",
                "??rodek mostka.",
                "Tchawica.",
                "Na lewej stronie klatki piersiowej.",
                "??rodek mostka."));

        quizModalArrayList.add(new Pytanie("Co nale??y zrobi?? przed wprowadzeniem oddech??w ratowniczych?",
                "Zablokowa?? drogi oddechowe.",
                "Udro??ni?? drogi oddechowe poszkodowanego.",
                "Nie zatka?? nosa.",
                "??adne z powy??szych.",
                "Udro??ni?? drogi oddechowe poszkodowanego."));

        quizModalArrayList.add(new Pytanie("Jaka jest prawid??owa cz??stotliwo???? ucisk??w?",
                "100-120 ucisk??w na minut??.",
                "160-190 ucisk??w na minut??.",
                "130-150 ucisk??w na minut??.",
                "80-120 ucisk??w na minut??.",
                "100-120 ucisk??w na minut??."));

    }
}