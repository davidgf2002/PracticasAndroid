package com.example.recyclerfirestore;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.reflect.Array;
import java.util.ArrayList;



public class Adaptador extends RecyclerView.Adapter<Adaptador.ViewHolder> implements View.OnClickListener{
    Context context;
    ArrayList<Paquete> paquetesArrayList;
    private View.OnClickListener listener;

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
        v.setOnClickListener(this);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Adaptador.ViewHolder holder, int position) {
        Paquete paquete = paquetesArrayList.get(position);

        holder.cv.setAnimation(AnimationUtils.loadAnimation(context, R.anim.fade_transition));
        holder.nombre.setText(paquete.nombre);
        holder.fecha.setText(paquete.fecha);
        holder.proveedorDescripcion.setText(paquete.proveedor);
    }

    @Override
    public int getItemCount() {
        return paquetesArrayList.size();
    }

    public void setOnClickListener(View.OnClickListener listener){
        this.listener = listener;
    }

    @Override
    public void onClick(View v) {
        if(listener != null){
            listener.onClick(v);
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView nombre, fecha, proveedorDescripcion;
        CardView cv;
        TextView descripcionPaquete, fechaDescripcion;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nombre = itemView.findViewById(R.id.NombrePaquete);
            fecha = itemView.findViewById(R.id.fecha);
            cv = itemView.findViewById(R.id.cv);
            proveedorDescripcion = itemView.findViewById(R.id.proveedorDescripcion);

        }
    }
}
