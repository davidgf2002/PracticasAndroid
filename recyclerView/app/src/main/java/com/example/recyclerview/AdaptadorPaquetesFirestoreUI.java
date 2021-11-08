package com.example.recyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;


public class AdaptadorPaquetesFirestoreUI extends
        FirestoreRecyclerAdapter<Paquete, AdaptadorPaquetes.ViewHolder> {
    protected View.OnClickListener onClickListener;
    protected Context context;

    public AdaptadorPaquetesFirestoreUI(
            @NonNull FirestoreRecyclerOptions<Paquete> options, Context context) {
        super(options);
        this.context = context;
    }

    @Override
    public AdaptadorPaquetes.ViewHolder onCreateViewHolder(
            ViewGroup parent, int viewType) {
        ElementoListaBinding v = ElementoListaBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false);
        v.getRoot().setOnClickListener(onClickListener);
        return new AdaptadorPaquetes.ViewHolder(v);
    }

    @Override
    protected void onBindViewHolder(@NonNull AdaptadorPaquetes
            .ViewHolder holder, int position, @NonNull Paquete paquete) {
        holder.personaliza(paquete);
    }

    public void setOnItemClickListener(View.OnClickListener onClick) {
        onClickListener = onClick;
    }

    public String getKey(int pos) {
        return super.getSnapshots().getSnapshot(pos).getId();
    }

    public int getPos(String id) {
        int pos = 0;
        while (pos < getItemCount()) {
            if (getKey(pos).equals(id)) return pos;
            pos++;
        }
        return -1;
    }
}