package com.example.pomockazdymoze;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Donate extends AppCompatActivity {

    Button wylaczButtonD, donateButtonD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donate);

        wylaczButtonD = (Button) findViewById(R.id.wylaczButtonD);
        donateButtonD = (Button) findViewById(R.id.donateButtonD);

        wylaczButtonD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        donateButtonD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), " Przekierowuję na stronę dostawcy płatności.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}