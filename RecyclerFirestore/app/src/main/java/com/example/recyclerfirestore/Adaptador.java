package com.example.recyclerfirestore;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.reflect.Array;
import java.util.ArrayList;



public class Adaptador extends RecyclerView.Adapter<Adaptador.ViewHolder> {

    Context context;
    ArrayList<Paquete> paquetesArrayList;

    //Constructor
    public Adaptador(Context context, ArrayList<Paquete> paquetesArrayList) {
        this.context = context;
        this.paquetesArrayList = paquetesArrayList;
    }

    //Métodos
    @NonNull
    @Override
    public Adaptador.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context).inflate(R.layout.item, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Adaptador.ViewHolder holder, int position) {
        Paquete paquete = paquetesArrayList.get(position);

        holder.nombre.setText(paquete.nombre);
        holder.fecha.setText(paquete.fecha);
    }

    @Override
    public int getItemCount() {
        return paquetesArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView nombre, fecha;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nombre = itemView.findViewById(R.id.NombrePaquete);
            fecha = itemView.findViewById(R.id.fecha);
        }
    }
}
