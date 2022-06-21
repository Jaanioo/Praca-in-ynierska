package com.example.pomockazdymoze;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.LinkedList;

public class ListaCisnienieFragment extends Fragment {

    private Button dodajWynikBtn;

    RecyclerView recyclerView;
    DatabaseReference database;
    CisnienieAdapter cisnienieAdapter;
    ArrayList<WynikiCisnienie> listaCisnienie;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public ListaCisnienieFragment() {
        // Required empty public constructor
    }

    public static ListaCisnienieFragment newInstance(String param1, String param2) {
        ListaCisnienieFragment fragment = new ListaCisnienieFragment();
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
        View view = inflater.inflate(R.layout.fragment_lista_cisnienie, container, false);

        dodajWynikBtn = view.findViewById(R.id.dodajWynikBtn);

        recyclerView = view.findViewById(R.id.listaWynikowCisnienia);
        database = FirebaseDatabase.getInstance().getReference("Users")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("Cisnienie");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));

        listaCisnienie = new ArrayList<>();
        cisnienieAdapter = new CisnienieAdapter(view.getContext(), listaCisnienie);
        recyclerView.setAdapter(cisnienieAdapter);

        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {

                    WynikiCisnienie wynikiCisnienie = dataSnapshot.getValue(WynikiCisnienie.class);
                    listaCisnienie.add(wynikiCisnienie);
                }
                cisnienieAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        dodajWynikBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                CisnienieFragment cisnienieFragment = new CisnienieFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.container, cisnienieFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        return view;
    }
}