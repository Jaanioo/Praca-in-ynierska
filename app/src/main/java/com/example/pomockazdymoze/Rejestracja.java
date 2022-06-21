package com.example.pomockazdymoze;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Vibrator;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Rejestracja extends AppCompatActivity implements View.OnClickListener{

    private EditText  haslo;
    private EditText login, email;
    private Button rejestracja, anuluj, logoBtn;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rejestracja);

        login = (EditText) findViewById(R.id.loginRej);
        email = (EditText)findViewById(R.id.emailRej);
        haslo = (EditText) findViewById(R.id.hasloRej);

        rejestracja = (Button) findViewById(R.id.rejestracja);
        rejestracja.setOnClickListener(this);
        anuluj = findViewById(R.id.anuluj);
        anuluj.setOnClickListener(this);
        logoBtn = findViewById(R.id.logoBtn);
        logoBtn.setOnClickListener(this);

        mAuth = FirebaseAuth.getInstance();

    }

    ///////////////// NAJNOWSZY BPROBA

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.anuluj:
                Intent anulujIntent = new Intent(Rejestracja.this, Login.class);
                startActivity(anulujIntent);
                break;

            case R.id.rejestracja:

                    rejestracjaUzytkownika();
                break;

            case R.id.logoBtn:
                Intent logo = new Intent(Rejestracja.this, MainActivity.class);
                startActivity(logo);
                break;
        }

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

    private void rejestracjaUzytkownika() {
        String rULogin = login.getText().toString().trim();
        String rUHaslo = haslo.getText().toString().trim();
        String rUEmail = email.getText().toString().trim();

        //Sprawdzenie loginu
        if (rULogin.isEmpty()) {
            login.setError("Wprowadź login!");
            login.requestFocus();
            return;
        }
        //Sprawdzenie obecności hasła w polu hasła
        if (rUHaslo.isEmpty()) {
            haslo.setError("Wprowadź hasło!");
            haslo.requestFocus();
            return;
        }
        //Sprawdzenie poprawności hasła hasła
        if (!walidacja(rUHaslo)) {
            haslo.setError("Zły format hasła! ");
            haslo.requestFocus();
            return;
        }

        //Sprawdzenie emaila
        if (rUEmail.isEmpty()) {
            email.setError("Wprowadź email!");
            email.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(rUEmail).matches()) {
            email.setError("Wprowadź poprawny email!");
            email.requestFocus();
            return;
        }

        // Połączenie procesu rejestracji z firebase
        mAuth.createUserWithEmailAndPassword(rUEmail, rUHaslo)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            User user = new User(rULogin, rUEmail);

                            FirebaseDatabase.getInstance().getReference("Users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    Toast.makeText(Rejestracja.this, "Konto utworzone.", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(Rejestracja.this, Login.class);
                                    startActivity(intent);
                                }
                            });
                        } else {
                            Toast.makeText(Rejestracja.this, "Rejestracja nieudana. Spróbuj ponownie.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });//koniec rejestracji
    }
}
