package com.example.firebase_authentication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.example.firebase_authentication.databinding.ElementoListaBinding;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

public class AdaptadorLugaresFirestoreUI extends
        FirestoreRecyclerAdapter<Lugar, AdaptadorLugares.ViewHolder> {
    protected View.OnClickListener onClickListener;
    protected Context context;
    public AdaptadorLugaresFirestoreUI(
            @NonNull FirestoreRecyclerOptions<Lugar> options, Context context){
        super(options);
        this.context = context;
    }
    @Override
    public AdaptadorLugares.ViewHolder onCreateViewHolder(
            ViewGroup parent, int viewType) {
        ElementoListaBinding v = ElementoListaBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false);
        v.getRoot().setOnClickListener(onClickListener);
        return new AdaptadorLugares.ViewHolder(v);
    }
    @Override
    protected void onBindViewHolder(@NonNull AdaptadorLugares
            .ViewHolder holder, int position, @NonNull Lugar lugar) {
        holder.personaliza(lugar);
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
