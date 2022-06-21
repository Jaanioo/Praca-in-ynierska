package com.example.pomockazdymoze;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ZmianaHaslaFragment extends Fragment {

    private Button zmianaHaslaBtn;
    private EditText noweHaslo;
    FirebaseAuth mAuth;
    ProgressDialog dialog;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ZmianaHaslaFragment() {
        // Required empty public constructor
    }

    public static ZmianaHaslaFragment newInstance(String param1, String param2) {
        ZmianaHaslaFragment fragment = new ZmianaHaslaFragment();
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
        View view = inflater.inflate(R.layout.fragment_zmiana_hasla, container, false);

        zmianaHaslaBtn = view.findViewById(R.id.zmienHasloBtn);
        noweHaslo = view.findViewById(R.id.noweHasloEditText);
        mAuth = FirebaseAuth.getInstance();
        dialog = new ProgressDialog(getActivity());

        zmianaHaslaBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                zmianaHasla();
            }
        });

        return view;
    }

    public void zmianaHasla() {

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {

            String zHNoweHaslo = noweHaslo.getText().toString().trim();

            dialog.setMessage("Trwa zmiana hasła, proszę czekać...");
            dialog.show();

            if (zHNoweHaslo.isEmpty()) {
                noweHaslo.setError("Wprowadź nowe hasło!");
                noweHaslo.requestFocus();
                return;
            }

            if (!walidacja(zHNoweHaslo)) {
                noweHaslo.setError("Zły format hasła! ");
                noweHaslo.requestFocus();
                return;
            }

            //Zmiana hasła
            user.updatePassword(noweHaslo.getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()) {
                        dialog.dismiss();
                        Toast.makeText(getActivity(), "Pomyślnie zmieniono hasło!", Toast.LENGTH_SHORT).show();
                        mAuth.signOut();
                        Intent intent = new Intent(getActivity(), Login.class);
                        startActivity(intent);
                        getActivity().finish();
                    }else {
                        dialog.dismiss();
                        Toast.makeText(getActivity(), "Błąd.", Toast.LENGTH_SHORT).show();
                    }
                }
            });
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
}