package com.example.pomockazdymoze;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class CukierFragment extends Fragment {

    private NumberPicker cukierPicker;
    private TextView interpretacjaCukierTextView;
    private Button zapiszBtn,twojeWynikiBtn, datePickerBtn;
    private int wynikCukier;
    private String data = getTodayDate();
    Task<Void> databaseReference;
    private DatePickerDialog datePickerDialog;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public CukierFragment() {
        // Required empty public constructor
    }

    public static CukierFragment newInstance(String param1, String param2) {
        CukierFragment fragment = new CukierFragment();
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
        View view = inflater.inflate(R.layout.fragment_cukier, container, false);

        interpretacjaCukierTextView = view.findViewById(R.id.interpteracjaWynikowCukruTextView);

        zapiszBtn = view.findViewById(R.id.zapiszWynikBtn);
        twojeWynikiBtn = view.findViewById(R.id.twojeWynikiBtn);
        datePickerBtn = view.findViewById(R.id.datePickerBtn);

        cukierPicker = view.findViewById(R.id.cukierPicker);

        datePickerBtn.setText(getTodayDate());

        //Wywo??ywanie funkcji przy tworzeniu widoku fragmentu
        initDatePicker();
        otworzDatePicker();
        cukierWybor();

        zapiszBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                wgraniePoziomuCukru();
            }
        });

        twojeWynikiBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ListaCukierFragment listacukierFragment = new ListaCukierFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.container, listacukierFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();;
            }
        });

        return view;
    }

    ////////////KALENDARZ////////////////
    //Inicjacja tera??niejszej daty
    private String getTodayDate() {

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        month = month + 1;
        int day = cal.get(Calendar.DAY_OF_MONTH);
        return makeDateString(day, month, year);
    }

    //Otwieranie kalendarza
    private void otworzDatePicker () {

        datePickerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePickerDialog.show();
            }
        });
    }

    private void initDatePicker () {

        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {

                month = month + 1;
                data = makeDateString(day, month, year);
                datePickerBtn.setText(data);
            }
        };

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        int style = AlertDialog.THEME_HOLO_LIGHT;

        datePickerDialog = new DatePickerDialog(getActivity(), dateSetListener, year, month, day);
        datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
    }

    //Konwercja formatu daty na String
    private String makeDateString(int day, int month, int year) {

        return getMonthFormat(month) + " " + day + " " + year;
    }

    //Zmiana formatu wy??wietlania miesi??ca na skr??t z liczby
    private String getMonthFormat(int month) {

        if (month == 1)
            return "STY";
        if (month == 2)
            return "LUT";
        if (month == 3)
            return "MAR";
        if (month == 4)
            return "KWI";
        if (month == 5)
            return "MAJ";
        if (month == 6)
            return "CZE";
        if (month == 7)
            return "LIP";
        if (month == 8)
            return "SIE";
        if (month == 9)
            return "WRZ";
        if (month == 10)
            return "PA??";
        if (month == 11)
            return "LIS";
        if (month == 12)
            return "GRU";

        //nigdy nie b??dzie u??yty
        return "STY";
    }

    ////////WYNIKI, PICKERY////////
    //Obs??uga picker??w
    private void cukierWybor() {

        cukierPicker.setMaxValue(300);
        cukierPicker.setMinValue(0);
        cukierPicker.setValue(80);

        wynikCukier = cukierPicker.getValue();

        cukierPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int i, int i1) {

                wynikCukier = i1;

                //interpretacja wynik??w
                if (i1 < 70) {
                    interpretacjaCukierTextView.setText("Cukier zbyt niski");
                    //interpretacjaCukierTextView.setBackgroundResource();
                }else if (i1 >= 70 && i1 < 100) {
                    interpretacjaCukierTextView.setText("Cukier prawid??owy");
                    //interpretacjaCukierTextView.setBackgroundResource();
                }else if (i1 >= 100 && i1 < 126) {
                    interpretacjaCukierTextView.setText("Stan \n przedcukrzycowy");
                }else {
                    interpretacjaCukierTextView.setText("Stan cukrzycowy");
                }
            }
        });
    }

    //funkcja dodaj??ca wyniki do bazydanych Firebase
    private void wgraniePoziomuCukru() {

        String cukier = Integer.toString(wynikCukier);
        String dataWyniku = data;

        WynikiCukier wynikiCukier = new WynikiCukier(cukier, dataWyniku);

        databaseReference = FirebaseDatabase.getInstance().getReference("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .child("Cukier")
                .child(data)
                .setValue(wynikiCukier).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {

                        Toast.makeText(getActivity(), "Dodano dzisiejszy wynik!", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                        Toast.makeText(getActivity(), "B????d dodawania wynik??w! /n spr??buj ponownie!", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}