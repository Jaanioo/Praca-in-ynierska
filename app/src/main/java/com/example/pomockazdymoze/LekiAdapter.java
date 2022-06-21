package com.example.pomockazdymoze;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class LekiAdapter extends RecyclerView.Adapter<LekiAdapter.MyViewHolder> {

    Context context;
    ArrayList<LekiModel> list;

    public LekiAdapter(Context context, ArrayList<LekiModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context).inflate(R.layout.item_layout, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        LekiModel lekiModel1 = list.get(position);
        holder.nazwaLeku.setText(lekiModel1.getNazwaLeku());
        holder.czestotliwosc.setText(lekiModel1.getCzestotliwosc());
        holder.dawkowanieLeku.setText(lekiModel1.getDawkowanieLeku());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView nazwaLeku, dawkowanieLeku, czestotliwosc;

       public MyViewHolder(@NonNull View itemView) {
           super(itemView);

           nazwaLeku = itemView.findViewById(R.id.nazwaLekuTextView);
           dawkowanieLeku = itemView.findViewById(R.id.dawkowanieLekuTextView);
           czestotliwosc = itemView.findViewById(R.id.czestotliwoscTextView);
       }
   }

}
