package com.example.pomockazdymoze;

import static android.view.View.GONE;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Leki extends AppCompatActivity {

    private Button wyjdzBtn, dodajLekBtn;
    private EditText nazwaLekuEditText;
    private String lNazwaLeku, lCzestotliwosc, lDawkowanieLeku;

    RecyclerView recyclerView;
    DatabaseReference database;
    Task<Void> mDatabaseReference;
    LekiAdapter lekiAdapter;
    ArrayList<LekiModel> list;
    //LekiModel lekiModel;
    Spinner czestotliwoscSpinner, dawkaSpinner;
    String[] elementyCzestotliwosc = {"1 dziennie", "2 dziennie", "3 dziennie", "4 dziennie", "1 tygodniowo", "2 tygodniowo", "3 tygodniowo"};
    String[] elementyDawka = {"1 tabletka", "2 tabletki", "3 tabletki", "5 ml", "10 ml", "15 ml", "20 ml", "1 porcja", "2 porcje", "3 porcje"};

        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leki);

        wyjdzBtn = findViewById(R.id.wyjdzBtn);
        dodajLekBtn = findViewById(R.id.dodajLekBtn);
        nazwaLekuEditText = findViewById(R.id.nazwaLekuEditText);

        czestotliwoscSpinner = (Spinner)findViewById(R.id.czestotliwoscSpinner);
        dawkaSpinner = (Spinner)findViewById(R.id.dawkaSpinner);

        recyclerView = findViewById(R.id.listaLekow);
        database = FirebaseDatabase.getInstance().getReference("Users")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("Leki");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        list = new ArrayList<>();
        lekiAdapter = new LekiAdapter(this, list);
        recyclerView.setAdapter(lekiAdapter);
        //lekiModel = new LekiModel();


            //lNazwaLeku = nazwaLekuEditText.getText().toString();
            dodanieSpinneraCzestotliwosc();
            dodanieSpinneraDawka();

            //Wyświetlanie leków z bazy danych w liście
                database.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        for (DataSnapshot dataSnapshot : snapshot.getChildren()) {

                            LekiModel lekiModel = dataSnapshot.getValue(LekiModel.class);
                            list.add(lekiModel);

                        }
                        lekiAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });


            //Przypisanie funkcji przyciskom
        wyjdzBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Profile.class);
                startActivity(intent);
                finish();
            }
        });

        dodajLekBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                lNazwaLeku = nazwaLekuEditText.getText().toString().trim();

                if (lNazwaLeku.isEmpty()){

                    nazwaLekuEditText.setError("Wprowadź nazwę leku!");
                    nazwaLekuEditText.requestFocus();
                }else {

                    list.clear();
                    wgranieLeku();
                    nazwaLekuEditText.setText("");
                }
            }
        });

    }

    public void dodanieSpinneraDawka() {

        ArrayAdapter<String> dawkaAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, elementyDawka);
        dawkaSpinner.setAdapter(dawkaAdapter);


        //Obsługa spinnera częstotliwości przyjmowania leku
        dawkaSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int id, long position) {

                switch((int)position)
                {
                    case 0:
                        lDawkowanieLeku = dawkaSpinner.getSelectedItem().toString();
                        break;
                    case 1:
                        lDawkowanieLeku = dawkaSpinner.getSelectedItem().toString();
                        break;
                    case 2:
                        lDawkowanieLeku = dawkaSpinner.getSelectedItem().toString();
                        break;
                    case 3:
                        lDawkowanieLeku = dawkaSpinner.getSelectedItem().toString();
                        break;
                    case 4:
                        lDawkowanieLeku = dawkaSpinner.getSelectedItem().toString();
                        break;
                    case 5:
                        lDawkowanieLeku = dawkaSpinner.getSelectedItem().toString();
                        break;
                    case 6:
                        lDawkowanieLeku = dawkaSpinner.getSelectedItem().toString();
                        break;
                    case 7:
                        lDawkowanieLeku = dawkaSpinner.getSelectedItem().toString();
                        break;
                    case 8:
                        lDawkowanieLeku = dawkaSpinner.getSelectedItem().toString();
                        break;
                    case 9:
                        lDawkowanieLeku = dawkaSpinner.getSelectedItem().toString();
                        break;
                    case 10:
                        lDawkowanieLeku = dawkaSpinner.getSelectedItem().toString();
                        break;
                }

                Toast.makeText(Leki.this, "Wybrano opcję: " + lDawkowanieLeku, Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {

            }
        });
    }

    public void dodanieSpinneraCzestotliwosc() {

            ArrayAdapter<String> czestotliwoscAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, elementyCzestotliwosc);
            czestotliwoscSpinner.setAdapter(czestotliwoscAdapter);


        //Obsługa spinnera częstotliwości przyjmowania leku
        czestotliwoscSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int id, long position) {

                switch((int)position)
                {
                    case 0:
                        lCzestotliwosc = czestotliwoscSpinner.getSelectedItem().toString();
                        break;
                    case 1:
                        lCzestotliwosc = czestotliwoscSpinner.getSelectedItem().toString();
                        break;
                    case 2:
                        lCzestotliwosc = czestotliwoscSpinner.getSelectedItem().toString();
                        break;
                    case 3:
                        lCzestotliwosc = czestotliwoscSpinner.getSelectedItem().toString();
                        break;
                    case 4:
                        lCzestotliwosc = czestotliwoscSpinner.getSelectedItem().toString();
                        break;
                    case 5:
                        lCzestotliwosc = czestotliwoscSpinner.getSelectedItem().toString();
                        break;
                    case 6:
                        lCzestotliwosc = czestotliwoscSpinner.getSelectedItem().toString();
                        break;
                    case 7:
                        lCzestotliwosc = czestotliwoscSpinner.getSelectedItem().toString();
                        break;
                }

                Toast.makeText(Leki.this, "Wybrano opcję: " + lCzestotliwosc, Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {

            }
        });
    }

    //Dodawanie leku do bazy danych
    public void wgranieLeku() {

        String nazwaLeku = lNazwaLeku;
        String dawkowanieLeku = lDawkowanieLeku;
        String czestotliwosc = lCzestotliwosc;
        
        LekiModel lekiModel = new LekiModel(nazwaLeku, dawkowanieLeku, czestotliwosc);

        mDatabaseReference = FirebaseDatabase.getInstance().getReference("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .child("Leki")
                .child(lNazwaLeku)
                .setValue(lekiModel).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        Toast.makeText(getApplicationContext(), "Dodano lek!", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                        Toast.makeText(getApplicationContext(), "Błąd dodawania wyników! /n spróbuj ponownie!", Toast.LENGTH_SHORT).show();
                    }
                });
    }

}