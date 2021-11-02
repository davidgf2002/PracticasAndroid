package com.example.recyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


public class AdaptadorPaquetes extends RecyclerView.Adapter<AdaptadorPaquetes.ViewHolder> {
    private List<Paquete> mData;
    private LayoutInflater mInflater;
    private Context context;
    final AdaptadorPaquetes.OnItemClickListener listener;

    public interface OnItemClickListener{
        void onItemClick(Paquete item);
    }


    public AdaptadorPaquetes(List<Paquete> listaPaquetes, Context context, AdaptadorPaquetes.OnItemClickListener listener){
        this.mInflater = LayoutInflater.from(context);
        this.context = context;
        this.mData = listaPaquetes;
        this.listener = listener;
    }
    @Override
    public int getItemCount(){
        return mData.size();
    }
    @Override
    public AdaptadorPaquetes.ViewHolder onCreateViewHolder(ViewGroup parent, int viewTipe){
        View view = mInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);
        return new AdaptadorPaquetes.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final AdaptadorPaquetes.ViewHolder holder, final int position){
        holder.cv.setAnimation(AnimationUtils.loadAnimation(context, R.anim.fade_transition));
        holder.binData(mData.get(position));
    }

    public void setItems(List<Paquete> lista){
        mData = lista;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView nombre,fecha;
        CardView cv;

        ViewHolder(View itemView){
            super(itemView);
            nombre = itemView.findViewById(R.id.NombrePaquete);
            fecha = itemView.findViewById(R.id.fecha);
            cv = itemView.findViewById(R.id.cv);
        }

        void binData(final Paquete lista){
            nombre.setText(lista.getNombre());
            fecha.setText(lista.getFecha());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(lista);
                }
            });
        }
    }
}
