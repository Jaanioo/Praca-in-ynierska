package com.example.pomockazdymoze;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Dolegliwosc extends AppCompatActivity {

    Button wylacz, oddech, tetno, noga, reka, nos, wymioty, bolKlatki, omdlenie, dalej;
    static boolean oddechB = false, tetnoB = false, nogaB = false, rekaB = false,
            nosB = false, wymiotyB = false, bolKlatkiB = false, omdlenieB = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dolegliwosc);

        wylacz = (Button) findViewById(R.id.wylacz);
        oddech = (Button) findViewById(R.id.oddechButton);
        tetno = (Button) findViewById(R.id.tetnoButton);
        noga = (Button) findViewById(R.id.nogaButton);
        reka = (Button) findViewById(R.id.rekaButton);
        nos = (Button) findViewById(R.id.nosButton);
        wymioty = (Button) findViewById(R.id.wymiotyButton);
        bolKlatki = (Button) findViewById(R.id.bolKlatkiButton);
        omdlenie = (Button) findViewById(R.id.omdlenieButton);
        dalej = (Button) findViewById(R.id.przejdzDalejButton);

        wylacz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        oddech.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (oddechB == false) {

                    oddech.setBackground(getResources().getDrawable(R.drawable.oddechredbutton));
                    oddechB = true;
                }else {
                    oddech.setBackground(getResources().getDrawable(R.drawable.oddechbutton));
                    oddechB = false;
                }
            }
        });

        tetno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (tetnoB == false) {

                    tetno.setBackground(getResources().getDrawable(R.drawable.tetnoredbutton));
                    tetnoB = true;
                }else {
                    tetno.setBackground(getResources().getDrawable(R.drawable.tetnobutton));
                    tetnoB = false;
                }
            }
        });

        noga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (nogaB == false) {

                    noga.setBackground(getResources().getDrawable(R.drawable.nogaredbutton));
                    nogaB = true;
                }else {
                    noga.setBackground(getResources().getDrawable(R.drawable.nogabutton));
                    nogaB = false;
                }
            }
        });

        reka.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (rekaB == false) {

                    reka.setBackground(getResources().getDrawable(R.drawable.rekaredbutton));
                    rekaB = true;
                }else {
                    reka.setBackground(getResources().getDrawable(R.drawable.rekabutton));
                    rekaB = false;
                }
            }
        });

        nos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (nosB == false) {

                    nos.setBackground(getResources().getDrawable(R.drawable.nosredbutton));
                    nosB = true;
                }else {
                    nos.setBackground(getResources().getDrawable(R.drawable.nosbutton));
                    nosB = false;
                }
            }
        });

        wymioty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (wymiotyB == false) {

                    wymioty.setBackground(getResources().getDrawable(R.drawable.wymiotyredbutton));
                    wymiotyB = true;
                }else {
                    wymioty.setBackground(getResources().getDrawable(R.drawable.wymiotybutton));
                    wymiotyB = false;
                }
            }
        });

        bolKlatki.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (bolKlatkiB == false) {

                    bolKlatki.setBackground(getResources().getDrawable(R.drawable.bolklatkiredbutton));
                    bolKlatkiB = true;
                }else {
                    bolKlatki.setBackground(getResources().getDrawable(R.drawable.bolklatkibutton));
                    bolKlatkiB = false;
                }
            }
        });

        omdlenie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (omdlenieB == false) {

                    omdlenie.setBackground(getResources().getDrawable(R.drawable.omdlenieredbutton));
                    omdlenieB = true;
                }else {
                    omdlenie.setBackground(getResources().getDrawable(R.drawable.omdleniebutton));
                    omdlenieB = false;
                }
            }
        });

        dalej.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Instrukcje.class);
                finish();
                startActivity(intent);

            }
        });

    }
}