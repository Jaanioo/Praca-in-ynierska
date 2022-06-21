package com.example.pomockazdymoze;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ZmianaDanych extends AppCompatActivity {

    private Button profileMenu, homeMenu, donateMenu, infoMenu, wynikiBadanMenu;
    private Button zmienHaslo;
    private static FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zmiana_danych);

        profileMenu = findViewById(R.id.profilMenuButton1);
        homeMenu = findViewById(R.id.glownaMenuButton1);
        donateMenu = findViewById(R.id.donateMenuButton1);
        infoMenu = findViewById(R.id.infoMenuButton1);
        wynikiBadanMenu = findViewById(R.id.wynikiMenuButton1);

        zmienHaslo = findViewById(R.id.zmianaHaslaBtn);

        fragmentManager = getSupportFragmentManager();

        zmienHaslo.setVisibility(View.VISIBLE);

        zmienHaslo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hasloZmiana();
                zmienHaslo.setVisibility(View.GONE);
            }
        });

        profileMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Profile.class);
                startActivity(intent);
                finish();
            }
        });

        homeMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        donateMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Donate.class);
                startActivity(intent);
                finish();
            }
        });

        infoMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), InfoAplikacja.class);
                startActivity(intent);
                finish();
            }
        });

        wynikiBadanMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), WyborBadan.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void hasloZmiana() {

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        ZmianaHaslaFragment zmianaHaslaFragment = new ZmianaHaslaFragment();
        fragmentTransaction.add(R.id.container, zmianaHaslaFragment, null);
        fragmentTransaction.commit();
        ZmianaDanych.fragmentManager.beginTransaction()
                .replace(R.id.container,
                        new ZmianaHaslaFragment(), null).addToBackStack(null).commit();
    }
}