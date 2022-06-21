package com.example.pomockazdymoze;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class Instrukcje extends AppCompatActivity {

    Button wylaczButtonI, podajDolegliwoscBtn, zakonczInstrButton;
    ImageView tekst, ilustracja;
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instrukcje);

        wylaczButtonI = findViewById(R.id.wylaczButtonI);
        podajDolegliwoscBtn = findViewById(R.id.podajDolegliwoscButton);
        zakonczInstrButton = findViewById(R.id.zakonczButton1);
        tekst = findViewById(R.id.instrukcjeText);
        ilustracja = findViewById(R.id.instrukcjeIlustracje);


            if (Dolegliwosc.oddechB == true || Dolegliwosc.tetnoB == true || Dolegliwosc.nogaB == true
                    || Dolegliwosc.rekaB == true || Dolegliwosc.nosB == true || Dolegliwosc.wymiotyB == true
                    || Dolegliwosc.bolKlatkiB == true || Dolegliwosc.omdlenieB == true) {

                //////Ustawienie stałych przycisków przy instrukcjach
                podajDolegliwoscBtn.setBackground(getResources().getDrawable(R.drawable.wezwijpomocbutton));

                wylaczButtonI.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                });

                podajDolegliwoscBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(getApplicationContext(), "Wzywam pomoc!", Toast.LENGTH_LONG).show();
                        podajDolegliwoscBtn.setVisibility(View.GONE);
                        zakonczInstrButton.setVisibility(View.VISIBLE);
                    }
                });

                zakonczInstrButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(getApplicationContext(), Podziekowania.class);
                        startActivity(intent);
                        finish();
                    }
                });
                ////////// Wyświetlanie instrukcji pomocy po wcześniejszym ich wyborze w klasie Dolegliwosc

                    if (Dolegliwosc.oddechB == true) {

                        tekst.setImageDrawable(getResources().getDrawable(R.drawable.instrukcjaoddech));
                        ilustracja.setImageDrawable(getResources().getDrawable(R.drawable.ilustracjaoddech));
                        Dolegliwosc.oddechB = false;

                    }
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            if (Dolegliwosc.tetnoB == true) {

                                tekst.setImageDrawable(getResources().getDrawable(R.drawable.tetnotext));
                                ilustracja.setImageDrawable(getResources().getDrawable(R.drawable.tetnoilustracja));
                                Dolegliwosc.tetnoB = false;
                            }
                        }
                    }, 5000);

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {

                            if (Dolegliwosc.nogaB == true) {

                                tekst.setImageDrawable(getResources().getDrawable(R.drawable.nogatext));
                                ilustracja.setImageDrawable(getResources().getDrawable(R.drawable.nogailustracja));
                                Dolegliwosc.nogaB = false;
                            }
                        }
                    }, 10000);

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {

                            if (Dolegliwosc.rekaB == true) {

                                tekst.setImageDrawable(getResources().getDrawable(R.drawable.rekatext));
                                ilustracja.setImageDrawable(getResources().getDrawable(R.drawable.rekailustracja));
                                Dolegliwosc.rekaB = false;
                            }
                        }
                    }, 15000);

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {

                            if (Dolegliwosc.nosB == true) {

                                tekst.setImageDrawable(getResources().getDrawable(R.drawable.nostext));
                                ilustracja.setVisibility(View.INVISIBLE);
                                Dolegliwosc.nosB = false;
                            }
                        }
                    }, 20000);

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {

                            if (Dolegliwosc.wymiotyB == true) {

                                tekst.setImageDrawable(getResources().getDrawable(R.drawable.wymiotytext));
                                ilustracja.setVisibility(View.INVISIBLE);
                                Dolegliwosc.wymiotyB = false;
                            }
                        }
                    }, 25000);

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {

                            if (Dolegliwosc.bolKlatkiB == true) {

                                tekst.setImageDrawable(getResources().getDrawable(R.drawable.bolklatkitext));
                                ilustracja.setImageDrawable(getResources().getDrawable(R.drawable.bolklatkiilustracja));
                                Dolegliwosc.bolKlatkiB = false;

                            }
                        }
                    }, 30000);

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {

                            if (Dolegliwosc.omdlenieB == true) {

                                tekst.setImageDrawable(getResources().getDrawable(R.drawable.omdlenietext));
                                ilustracja.setImageDrawable(getResources().getDrawable(R.drawable.omdlenieilustracja));
                                Dolegliwosc.omdlenieB = false;
                            }
                        }
                    }, 35000);

            } else {
                //////Ustawienie przycisków przy braku wyboru dolegliwości
                wylaczButtonI.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                });

                podajDolegliwoscBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(getApplicationContext(), Dolegliwosc.class);
                        startActivity(intent);
                        finish();
                    }
                });
            }
    }
}