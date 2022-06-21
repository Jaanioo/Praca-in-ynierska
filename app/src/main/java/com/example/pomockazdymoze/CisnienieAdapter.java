package com.example.pomockazdymoze;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CisnienieAdapter extends RecyclerView.Adapter<CisnienieAdapter.MyViewHolder> {

    Context context;

    ArrayList<WynikiCisnienie> listaCisnienie;

    public CisnienieAdapter(Context context, ArrayList<WynikiCisnienie> listaCisnienie) {
        this.context = context;
        this.listaCisnienie = listaCisnienie;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.wynik_cisnienie_layout, parent, false);

        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        WynikiCisnienie wynikiCisnienie = listaCisnienie.get(position);
        holder.skurczowe.setText(wynikiCisnienie.getSkurczowe());
        holder.rozkurczowe.setText(wynikiCisnienie.getRozkurczowe());
        holder.puls.setText(wynikiCisnienie.getPuls());
        holder.dataWyniku.setText(wynikiCisnienie.getDataWyniku());
    }

    @Override
    public int getItemCount() {
        return listaCisnienie.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView skurczowe, rozkurczowe, puls, dataWyniku;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            skurczowe = itemView.findViewById(R.id.skurczoweWynikTextView);
            rozkurczowe = itemView.findViewById(R.id.rozkurczoweWynikTextView);
            puls = itemView.findViewById(R.id.pulsWynikTextView);
            dataWyniku = itemView.findViewById(R.id.dataWynikuTextView);
        }
    }
}
