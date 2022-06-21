package com.example.pomockazdymoze;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TestyLista extends AppCompatActivity {

    Button profileMenu, homeMenu, donatemenu, infoMenu;
    Button zlamaniaBut, rkoButton, wypadekButton, omdlenieButton, krwotokButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testy_lista);

        rkoButton = findViewById(R.id.rkotestyButton);
        wypadekButton = findViewById(R.id.wypadekTestyButton);
        omdlenieButton = findViewById(R.id.omdlenieTestyButton);
        krwotokButton = findViewById(R.id.krwotokTestyButton);
        zlamaniaBut = findViewById(R.id.zlamaniaTestyButton);
        profileMenu = findViewById(R.id.profilMenuButton2);
        homeMenu = findViewById(R.id.glownaMenuButton2);
        donatemenu = findViewById(R.id.donateMenuButton2);
        infoMenu = findViewById(R.id.infoMenuButton2);

        profileMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Profile.class);
                startActivity(intent);
                finish();
            }
        });

        homeMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        donatemenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Donate.class);
                startActivity(intent);
                finish();
            }
        });

        infoMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), InfoAplikacja.class);
                startActivity(intent);
                finish();
            }
        });

        zlamaniaBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), TestyZlamania.class);
                startActivity(intent);
                finish();
            }
        });

        rkoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), TestyRKO.class);
                startActivity(intent);
                finish();
            }
        });

        wypadekButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), TestyWypadekDrogowy.class);
                startActivity(intent);
                finish();
            }
        });

        omdlenieButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), TestyOmdlenie.class);
                startActivity(intent);
                finish();
            }
        });

        krwotokButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), TestyKrwotok.class);
                startActivity(intent);
                finish();
            }
        });
    }
}