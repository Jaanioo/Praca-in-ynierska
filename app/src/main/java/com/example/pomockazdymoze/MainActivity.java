package com.example.pomockazdymoze;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    public static FragmentManager fragmentManager;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentManager = getSupportFragmentManager();
        mAuth = FirebaseAuth.getInstance();

        if(findViewById(R.id.fragment_container) != null){
            if(savedInstanceState != null){
                return;
            }
        }
    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser mFirebaseUserser = mAuth.getCurrentUser();
        if (mFirebaseUserser != null) {
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                LoggedMain loggedMain = new LoggedMain();
                fragmentTransaction.add(R.id.fragment_container, loggedMain, null);
                fragmentTransaction.commit();
                MainActivity.fragmentManager.beginTransaction()
                        .replace(R.id.fragment_container,
                                new LoggedMain(), null).addToBackStack(null).commit();
        }else {
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                NotLoggedMain notLoggedMain = new NotLoggedMain();
                fragmentTransaction.add(R.id.fragment_container, notLoggedMain, null);
                fragmentTransaction.commit();
        }
    }
}