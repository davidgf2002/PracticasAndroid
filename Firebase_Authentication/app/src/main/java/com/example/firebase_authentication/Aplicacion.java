package com.example.firebase_authentication;

import android.app.Application;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class Aplicacion extends Application {
    public LugaresAsinc lugares;
    public AdaptadorLugaresFirestoreUI adaptador;
    @Override public void onCreate() {
        super.onCreate();
        lugares = new LugaresFirestore();
        Query query = FirebaseFirestore.getInstance()
                .collection("lugares")
                .limit(50);
        FirestoreRecyclerOptions<Lugar> opciones =new FirestoreRecyclerOptions
                .Builder<Lugar>().setQuery(query, Lugar.class).build();
        adaptador = new AdaptadorLugaresFirestoreUI(opciones, this);
    }
}
