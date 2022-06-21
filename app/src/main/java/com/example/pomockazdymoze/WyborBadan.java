package com.example.pomockazdymoze;

import static android.view.View.GONE;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class WyborBadan extends AppCompatActivity {

    private Button profilBtn, mainBtn, donateBtn, infoBtn, wynikiBtn;
    private Button glikemiaBtn, cisnienieBtn;
    private static FragmentManager fragmentManager;
    private ImageView logo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wybor_badan);

        profilBtn = findViewById(R.id.profilMenuButton1);
        mainBtn = findViewById(R.id.glownaMenuButton1);
        donateBtn = findViewById(R.id.donateMenuButton1);
        infoBtn = findViewById(R.id.infoMenuButton1);
        wynikiBtn = findViewById(R.id.wynikiMenuButton1);

        logo = findViewById(R.id.imageViewLogo);

        glikemiaBtn = findViewById(R.id.glikemiaBtn);
        cisnienieBtn = findViewById(R.id.cisnienieBtn);

        fragmentManager = getSupportFragmentManager();

        //Proste przypisanie przycisków
        glikemiaBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //glikemia();
                wykresGlikemia();
                glikemiaBtn.setVisibility(GONE);
                cisnienieBtn.setVisibility(GONE);
                logo.setVisibility(GONE);
            }
        });

        cisnienieBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //cisnienie();
                wykresCisnienie();
                glikemiaBtn.setVisibility(GONE);
                cisnienieBtn.setVisibility(GONE);
                logo.setVisibility(GONE);
            }
        });

        profilBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplication(), Profile.class);
                startActivity(intent);
                finish();
            }
        });

        mainBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplication(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        donateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplication(), Donate.class);
                startActivity(intent);
                finish();
            }
        });

        infoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplication(), InfoAplikacja.class);
                startActivity(intent);
                finish();
            }
        });

        wynikiBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplication(), WyborBadan.class);
                startActivity(intent);
                finish();
            }
        });

    }

    //Funkcje otwierania widoku konkretnego fragmentu
    private void cisnienie() {

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        CisnienieFragment cisnienieFragment = new CisnienieFragment();
        fragmentTransaction.add(R.id.container, cisnienieFragment, null);
        fragmentTransaction.commit();
        WyborBadan.fragmentManager.beginTransaction()
                .replace(R.id.container,
                        new CisnienieFragment(), null).addToBackStack(null).commit();
    }

    private void glikemia() {

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        CukierFragment glikemiaFragment = new CukierFragment();
        fragmentTransaction.add(R.id.container, glikemiaFragment, null);
        fragmentTransaction.commit();
        WyborBadan.fragmentManager.beginTransaction()
                .replace(R.id.container,
                        new CukierFragment(), null).addToBackStack(null).commit();
    }

    private void wykresCisnienie() {

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        ListaCisnienieFragment wykresCisnienieFragment = new ListaCisnienieFragment();
        fragmentTransaction.add(R.id.container, wykresCisnienieFragment, null);
        fragmentTransaction.commit();
        WyborBadan.fragmentManager.beginTransaction()
                .replace(R.id.container,
                        new ListaCisnienieFragment(), null).addToBackStack(null).commit();
    }

    private void wykresGlikemia() {

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        ListaCukierFragment wykresGlikemiaFragment = new ListaCukierFragment();
        fragmentTransaction.add(R.id.container, wykresGlikemiaFragment, null);
        fragmentTransaction.commit();
        WyborBadan.fragmentManager.beginTransaction()
                .replace(R.id.container,
                        new ListaCukierFragment(), null).addToBackStack(null).commit();
    }
}