package com.example.firebaserecyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    Context context;
    ArrayList<Paquete> listaPaquetes;


    //Constructor
    public MyAdapter(Context context, ArrayList<Paquete> listaPaquetes) {
        this.context = context;
        this.listaPaquetes = listaPaquetes;
    }


    @NonNull
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context).inflate(R.layout.activity_launcher, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.MyViewHolder holder, int position) {

        Paquete paquete = listaPaquetes.get(position);

        holder.fecha.setText(paquete.fecha);
        holder.nombre.setText(paquete.nombre);

    }

    @Override
    public int getItemCount() {
        return listaPaquetes.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView fecha, nombre;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            fecha = itemView.findViewById(R.id.fecha);
            nombre = itemView.findViewById(R.id.NombrePaquete);
        }
    }
}
