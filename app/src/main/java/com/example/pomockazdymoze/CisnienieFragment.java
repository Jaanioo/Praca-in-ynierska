package com.example.pomockazdymoze;

import static com.example.pomockazdymoze.R.drawable.zaokraglone_narozniki_blue;
import static com.example.pomockazdymoze.R.drawable.zaokraglone_narozniki_green;
import static com.example.pomockazdymoze.R.drawable.zaokraglone_narozniki_heavyred;
import static com.example.pomockazdymoze.R.drawable.zaokraglone_narozniki_orange;
import static com.example.pomockazdymoze.R.drawable.zaokraglone_narozniki_red;
import static com.example.pomockazdymoze.R.drawable.zaokraglone_narozniki_yellow;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
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

public class CisnienieFragment extends Fragment {

    private NumberPicker skurczowePicker, rozkurczowePicker, pulsPicker;
    private TextView interpretacjaSkurczoweTextView, interpretacjaRozkurczoweTextView;
    private Button zapiszBtn,twojeWynikiBtn, datePickerBtn;
    private int wynikSkurczowe, wynikRozkurczowe, wynikPuls;
    private String data = getTodayDate();
    Task<Void> databaseReference;
    private DatePickerDialog datePickerDialog;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public CisnienieFragment() {
        // Required empty public constructor
    }

    public static CisnienieFragment newInstance(String param1, String param2) {
        CisnienieFragment fragment = new CisnienieFragment();
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

        View view = inflater.inflate(R.layout.fragment_cisnienie, container, false);

        interpretacjaSkurczoweTextView = view.findViewById(R.id.interpteracjaWynikowSkurczoweTextView);
        interpretacjaRozkurczoweTextView = view.findViewById(R.id.interpteracjaWynikowRozkurczoweTextView);

        zapiszBtn = view.findViewById(R.id.zapiszWynikBtn);
        twojeWynikiBtn = view.findViewById(R.id.twojeWynikiBtn);
        datePickerBtn = view.findViewById(R.id.datePickerBtn);

        skurczowePicker = view.findViewById(R.id.skurczowePicker);
        rozkurczowePicker = view.findViewById(R.id.rozkurczowePicker);
        pulsPicker = view.findViewById(R.id.pulsPicker);


        datePickerBtn.setText(getTodayDate());

        //Wywoływanie funkcji przy tworzeniu widoku fragmentu
        initDatePicker();
        otworzDatePicker();

        cisnienieWybor();

        zapiszBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                wgranieCisnienia();
                //Toast.makeText(getActivity(), "Wgrano wynik")
            }
        });

        twojeWynikiBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ListaCisnienieFragment listacisnienieFragment = new ListaCisnienieFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.container, listacisnienieFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();;
            }
        });

        // Inflate the layout for this fragment
        return view;
    }

    ////////////KALENDARZ////////////////
    //Inicjacja teraźniejszej daty
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

    //Zmiana formatu wyświetlania miesiąca na skrót z liczby
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
            return "PAŹ";
        if (month == 11)
            return "LIS";
        if (month == 12)
            return "GRU";

        //nigdy nie będzie użyty
        return "STY";
    }

    ////////WYNIKI, PICKERY////////
    //Obsługa pickerów
    private void cisnienieWybor() {

        skurczowePicker.setMaxValue(300);
        skurczowePicker.setMinValue(0);
        skurczowePicker.setValue(110);

        wynikSkurczowe = skurczowePicker.getValue();

        skurczowePicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int i, int i1) {

                //wynikSkurczowe = skurczowePicker.getValue();
                wynikSkurczowe = i1;

                //interpretacja wyników
                if (i1 < 100 ) {
                    interpretacjaSkurczoweTextView.setText("Niedociśnienie");
                    interpretacjaSkurczoweTextView.setBackgroundResource(zaokraglone_narozniki_blue);
                }else if (i1 < 130) {
                    interpretacjaSkurczoweTextView.setText("Ciśnienie prawidłowe");
                    interpretacjaSkurczoweTextView.setBackgroundResource(zaokraglone_narozniki_green);
                }else if (i1 < 140) {
                    interpretacjaSkurczoweTextView.setText("Ciśnienie podwyższone");
                    interpretacjaSkurczoweTextView.setBackgroundResource(zaokraglone_narozniki_yellow);
                }else if (i1 < 160) {
                    interpretacjaSkurczoweTextView.setText("Umiarkowane nadciśnienie");
                    interpretacjaSkurczoweTextView.setBackgroundResource(zaokraglone_narozniki_orange);
                }else if (i1 < 180) {
                    interpretacjaSkurczoweTextView.setText("Średnie nadciśnienie");
                    interpretacjaSkurczoweTextView.setBackgroundResource(zaokraglone_narozniki_red);
                }else {
                    interpretacjaSkurczoweTextView.setText("Znaczne nadciśnienie");
                    interpretacjaSkurczoweTextView.setBackgroundResource(zaokraglone_narozniki_heavyred);
                }
            }
        });

        rozkurczowePicker.setMaxValue(250);
        rozkurczowePicker.setMinValue(0);
        rozkurczowePicker.setValue(80);

        wynikRozkurczowe = rozkurczowePicker.getValue();

        rozkurczowePicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int i, int i1) {

                wynikRozkurczowe = i1;

                if (i1 < 60 ) {
                    interpretacjaRozkurczoweTextView.setText("Niedociśnienie");
                    interpretacjaRozkurczoweTextView.setBackgroundResource(zaokraglone_narozniki_blue);
                }else if (i1 < 85) {
                    interpretacjaRozkurczoweTextView.setText("Ciśnienie prawidłowe");
                    interpretacjaRozkurczoweTextView.setBackgroundResource(zaokraglone_narozniki_green);
                }else if (i1 < 90) {
                    interpretacjaRozkurczoweTextView.setText("Ciśnienie podwyższone");
                    interpretacjaRozkurczoweTextView.setBackgroundResource(zaokraglone_narozniki_yellow);
                }else if (i1 < 100) {
                    interpretacjaRozkurczoweTextView.setText("Umiarkowane nadciśnienie");
                    interpretacjaRozkurczoweTextView.setBackgroundResource(zaokraglone_narozniki_orange);
                }else if (i1 < 110) {
                    interpretacjaRozkurczoweTextView.setText("Średnie nadciśnienie");
                    interpretacjaRozkurczoweTextView.setBackgroundResource(zaokraglone_narozniki_red);
                }else {
                    interpretacjaRozkurczoweTextView.setText("Znaczne nadciśnienie");
                    interpretacjaRozkurczoweTextView.setBackgroundResource(zaokraglone_narozniki_heavyred);
                }
            }
        });

        pulsPicker.setMaxValue(200);
        pulsPicker.setMinValue(0);
        pulsPicker.setValue(60);

        wynikPuls = pulsPicker.getValue();

        pulsPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int i, int i1) {

                wynikPuls = pulsPicker.getValue();
            }
        });
    }

    //funkcja dodająca wyniki do bazydanych Firebase
    private void wgranieCisnienia() {

        String skurczowe = Integer.toString(wynikSkurczowe);
        String rozkurczowe = Integer.toString(wynikRozkurczowe);
        String puls = Integer.toString(wynikPuls);
        String dataWyniku = data;

        WynikiCisnienie wynikiKrew = new WynikiCisnienie(skurczowe, rozkurczowe, puls, dataWyniku);

        databaseReference = FirebaseDatabase.getInstance().getReference("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .child("Cisnienie")
                .child(data)
                .setValue(wynikiKrew).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {

                        Toast.makeText(getActivity(), "Dodano dzisiejszy wynik!", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                        Toast.makeText(getActivity(), "Błąd dodawania wyników! /n spróbuj ponownie!", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}