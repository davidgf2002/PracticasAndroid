package com.example.recyclerview;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListAdapter;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recyclerview.databinding.ActivityMainBinding;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.ListenerRegistration;
import com.google.firebase.firestore.Query;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<Paquete> elementos;
    FirebaseFirestore db;
    ListenerRegistration registration;

    private ActivityMainBinding binding; //si no está
    public static AdaptadorPaquetesFirestoreUI adaptador;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        Log.d("Firestore", "activado el escuchador");
        registration = db.collection("coleccion").document("documento").addSnapshotListener(
                new EventListener<DocumentSnapshot>() {
                    @Override
                    public void onEvent(@Nullable DocumentSnapshot snapshot,
                                        @Nullable FirebaseFirestoreException e){
                        if (e != null) {
                            Log.e("Firestore", "Error al leer", e);
                        } else if (snapshot == null || !snapshot.exists()) {
                            Log.e("Firestore", "Error: documento no encontrado ");
                        } else {
                            Log.d("Firestore", "datos:" + snapshot.getData());
                        }
                    }
                });

        elementos = new ArrayList<Paquete>();

        init();
    }

    public void init(){
        elementos = new ArrayList<>();
        elementos.add(new Paquete("paquete1", "15/01/01"));
        elementos.add(new Paquete("paquete2", "15/01/01"));
        elementos.add(new Paquete("paquete3", "15/01/01"));
        elementos.add(new Paquete("paquete4", "15/01/01"));
        elementos.add(new Paquete("paquete5", "15/01/01"));
        elementos.add(new Paquete("paquete6", "15/01/01"));
        elementos.add(new Paquete("paquete7", "15/01/01"));
        elementos.add(new Paquete("paquete8", "15/01/01"));
        elementos.add(new Paquete("paquete9", "15/01/01"));
        elementos.add(new Paquete("paquete10","15/01/01"));

        AdaptadorPaquetes listAdapter = new AdaptadorPaquetes(elementos, this, new AdaptadorPaquetes.OnItemClickListener() {
            @Override
            public void onItemClick(Paquete item) {
                moveToDescription(item);
            }
        });
        RecyclerView recyclerView = findViewById(R.id.recyclerID);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(listAdapter);
    }

    public void moveToDescription(Paquete item){
        Intent intent = new Intent(this, DescripcionPaquete.class);
        intent.putExtra("ListElement", item);
        startActivity(intent);
    }
}
