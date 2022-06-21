package com.example.pomockazdymoze;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.mikhaellopez.circularimageview.CircularImageView;
import com.squareup.picasso.Picasso;

public class Profile extends AppCompatActivity {

    Button profileMenu, sprawdzWiedze, homeMenu, donateMenu, infoMenu, zmianaZdjecia, wynikiBadan, wyloguj, zmianaDanych, wynikiBadanMenu;
    CircularImageView zdjecieProf;
    StorageReference storageReference;
    Uri zdjecieUri;
    ProgressDialog progressDialog;
    String nazwaPliku = FirebaseAuth.getInstance().getCurrentUser().getUid();

    FirebaseAuth mAuth;
    FirebaseStorage firebaseStorage;
    FirebaseUser firebaseUser;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        profileMenu = findViewById(R.id.profilMenuButton1);
        sprawdzWiedze = findViewById(R.id.sprawdzWiedzeButton);
        homeMenu = findViewById(R.id.glownaMenuButton1);
        donateMenu = findViewById(R.id.donateMenuButton1);
        infoMenu = findViewById(R.id.infoMenuButton1);
        zmianaZdjecia = findViewById(R.id.zmianaZdjeciaButton);
        wynikiBadan = findViewById(R.id.wynikiButton);
        wynikiBadanMenu = findViewById(R.id.wynikiMenuButton1);
        wyloguj = findViewById(R.id.wylogujBtn);
        zmianaDanych = findViewById(R.id.zmianaDanych);

        mAuth = FirebaseAuth.getInstance();
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        firebaseStorage = FirebaseStorage.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference("Users");

        zdjecieProf = findViewById(R.id.profileImage);
        final TextView nazwaUzytkownika = (TextView) findViewById(R.id.loginProfil);

        //Pobieranie zdjęcia z firebase do widoku
        storageReference = FirebaseStorage.getInstance().getReference()
                .child("zdjecieProfilowe")
                .child(nazwaPliku + ".jpeg");
        storageReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.with(Profile.this).load(uri).into(zdjecieProf);
            }
        });

        //Wyświetlanie nazwy użytkownika
        databaseReference.child(FirebaseAuth.getInstance().getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User userProfile = snapshot.getValue(User.class);

                if (userProfile != null) {
                    String login = userProfile.rULogin;
                    nazwaUzytkownika.setText(login);
                }else {
                    nazwaUzytkownika.setText("Błąd ładowania.");
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        //Przycisk zmiany zdjęcia profilowego
        zmianaZdjecia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                wybierzZdjecie();
            }
        });

        //Przycisk wylogowywania
        wyloguj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.getInstance().signOut();

                Intent intent = new Intent(Profile.this, MainActivity.class);
                startActivity(intent);
                Toast.makeText(Profile.this, " Wylogowano pomyślnie.", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        //Proste przejścia do innych aktywności
        infoMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), InfoAplikacja.class);
                startActivity(intent);
                finish();
            }
        });

        donateMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Donate.class);
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

        profileMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Profile.class);
                startActivity(intent);
                finish();
            }
        });

        sprawdzWiedze.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), TestyLista.class);
                startActivity(intent);
                finish();
            }
        });

        wynikiBadan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), WyborBadan.class);
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

        zmianaDanych.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ZmianaDanych.class);
                startActivity(intent);
                finish();
            }
        });
    }

    // Ustawianie zdjęcia profilowego wybranego z galerii
    private void wgrajZdjecie(Uri zdjecieUri){

        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Wgrywanie zdjęcia...");
        progressDialog.show();

        storageReference = FirebaseStorage.getInstance().getReference()
                .child("zdjecieProfilowe")
                .child(nazwaPliku + ".jpeg");

        //Przesyłanie zdjęcia do firebaseStorage
        storageReference.putFile(zdjecieUri)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                        //Wyświetlanie nowo wybranego zdjęcia poprzez pobranie go ze Storage
                        storageReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {

                                Picasso.with(Profile.this).load(uri).into(zdjecieProf);
                                Toast.makeText(Profile.this, "Powodzenie.", Toast.LENGTH_SHORT).show();
                            }
                        });
                        if (progressDialog.isShowing()) {
                            progressDialog.dismiss();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

                if (progressDialog.isShowing()){
                    progressDialog.dismiss();
                }
                Toast.makeText(Profile.this, "Błąd.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    //Wybieranie zdjęcia z galerii oraz wywołanie metody jego wgrywania na StorageFirebase
    private void wybierzZdjecie(){

        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, 10001);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 10001 && data != null && data.getData() != null) {
            if ( resultCode == Activity.RESULT_OK) {

                zdjecieUri = data.getData();
                wgrajZdjecie(zdjecieUri);
            }
        }
    }
}