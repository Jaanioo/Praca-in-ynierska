package com.example.pomockazdymoze;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CukierAdapter extends RecyclerView.Adapter<CukierAdapter.MyViewHolder> {

    Context context;

    ArrayList<WynikiCukier> listaCukier;

    public CukierAdapter(Context context, ArrayList<WynikiCukier> listaCukier) {
        this.context = context;
        this.listaCukier = listaCukier;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.wynik_cukier_layout, parent, false);

        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        WynikiCukier wynikiCukier1 = listaCukier.get(position);
        holder.cukier.setText(wynikiCukier1.getCukier());
        holder.dataWyniku.setText(wynikiCukier1.getDataWyniku());
    }

    @Override
    public int getItemCount() {
        return listaCukier.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView cukier, dataWyniku;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            cukier = itemView.findViewById(R.id.cukierWynikTextView);
            dataWyniku = itemView.findViewById(R.id.dataWynikuTextView);
        }
    }
}
