package com.example.pomockazdymoze;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Podziekowania extends AppCompatActivity {

    Button glownaDzieki, infoDzieki, donateDzieki;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_podziekowania);

        glownaDzieki = (Button) findViewById(R.id.glownaDziekiButton);
        infoDzieki = (Button) findViewById(R.id.infoDziekiButton);
        donateDzieki = (Button) findViewById(R.id.donateDziekiButton);

        donateDzieki.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Donate.class);
                startActivity(intent);
                finish();
            }
        });

        infoDzieki.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), InfoAplikacja.class);
                startActivity(intent);
                finish();
            }
        });

        glownaDzieki.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}