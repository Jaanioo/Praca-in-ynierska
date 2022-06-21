package com.example.pomockazdymoze;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Vibrator;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Login extends AppCompatActivity implements View.OnClickListener{

    EditText email, haslo;
    Button zalogujBtn, rejestracjaBtn, logoBtn;
    static boolean isLogin = false;

    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();

        email = findViewById(R.id.email);
        haslo = findViewById(R.id.haslo);

        zalogujBtn = findViewById(R.id.zaloguj);
        zalogujBtn.setOnClickListener(Login.this);
        rejestracjaBtn = findViewById(R.id.rejestracja);
        rejestracjaBtn.setOnClickListener(Login.this);
        logoBtn = findViewById(R.id.logoBtn);
        logoBtn.setOnClickListener(Login.this);

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.rejestracja:
                Intent rejestracja = new Intent(Login.this, Rejestracja.class);
                startActivity(rejestracja);
                break;
            case R.id.zaloguj:
                zalogujUzytkownika();
                break;
            case R.id.logoBtn:
                Intent logo = new Intent(Login.this, MainActivity.class);
                startActivity(logo);
                break;
        }
    }

    private void zalogujUzytkownika() {
        String zUEmail = email.getText().toString().trim();
        String zUHaslo = haslo.getText().toString().trim();

        if (zUEmail.isEmpty()) {
            email.setError("Wprowadź email!");
            email.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(zUEmail).matches()) {
            email.setError("Wprowadź poprawny email!");
            email.requestFocus();
            return;
        }
        if (zUHaslo.isEmpty()) {
            haslo.setError("Wprowadź hasło!");
            haslo.requestFocus();
            return;
        }
        if (!walidacja(zUHaslo)) {
            haslo.setError("Wprowadzono błędne hasło!");
        }

        mAuth.signInWithEmailAndPassword(zUEmail, zUHaslo).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(Login.this, "Zalogowano!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Login.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }else {
                    Toast.makeText(Login.this, "Błędne dane logowania!", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private static boolean walidacja(String vHaslo) {

        String regex = "^(?=.*[0-9])"
                + "(?=.*[a-z])(?=.*[A-Z])"
                + "(?=.*[@#$%^&+=.,!])"
                + "(?=\\S+$).{6,20}$";

        Pattern p = Pattern.compile(regex);

        if (vHaslo == null) {
            return false;
        }

        Matcher m = p.matcher(vHaslo);
        return m.matches();
    }
}
