package com.example.pomockazdymoze;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class LoggedMain extends Fragment {

    private Button ratujBtn, profilBtn, mainBtn, donateBtn, infoBtn, wynikiBtn, wylogujBtn, quizBigBtn, lekiBigBtn;
    private FirebaseAuth mAuth;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public LoggedMain() {
        // Required empty public constructor
    }

    public static LoggedMain newInstance(String param1, String param2) {
        LoggedMain fragment = new LoggedMain();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_logged_main, container, false);

        ratujBtn = view.findViewById(R.id.ratuj);
        profilBtn = view.findViewById(R.id.profilMenuButton1);
        mainBtn = view.findViewById(R.id.glownaMenuButton1);
        donateBtn = view.findViewById(R.id.donateMenuButton1);
        infoBtn = view.findViewById(R.id.infoMenuButton1);
        wynikiBtn = view.findViewById(R.id.wynikiMenuButton1);
        wylogujBtn = view.findViewById(R.id.wylogujBtn);
        quizBigBtn = view.findViewById(R.id.quizBtn);
        lekiBigBtn = view.findViewById(R.id.lekiBtn);


        lekiBigBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), Leki.class);
                startActivity(intent);
                getActivity().finish();
            }
        });

        quizBigBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), TestyLista.class);
                startActivity(intent);
                getActivity().finish();

            }
        });

        wylogujBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                wylogujUzytkownika();
            }
        });

        ratujBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), Dolegliwosc.class);
                startActivity(intent);
                ((Activity) getActivity()).overridePendingTransition(0, 0);
            }
        });

        profilBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), Profile.class);
                startActivity(intent);
                ((Activity) getActivity()).overridePendingTransition(0, 0);
            }
        });

        mainBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
                ((Activity) getActivity()).overridePendingTransition(0, 0);
            }
        });

        donateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), Donate.class);
                startActivity(intent);
                ((Activity) getActivity()).overridePendingTransition(0 ,0);
            }
        });

        infoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), InfoAplikacja.class);
                startActivity(intent);
                ((Activity) getActivity()).overridePendingTransition(0, 0);
            }
        });

        wynikiBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), WyborBadan.class);
                startActivity(intent);
                ((Activity) getActivity()).overridePendingTransition(0, 0);
            }
        });

        return view;
    }

    public void wylogujUzytkownika() {

        mAuth.getInstance().signOut();

        Intent intent = new Intent(getActivity(), MainActivity.class);
        startActivity(intent);
        Toast.makeText(getActivity(), " Wylogowano pomyślnie.", Toast.LENGTH_SHORT).show();
        getActivity().finish();
    }
}