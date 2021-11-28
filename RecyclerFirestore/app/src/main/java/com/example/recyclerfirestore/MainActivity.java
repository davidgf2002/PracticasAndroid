package com.example.recyclerfirestore;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<Paquete> paqueteArrayList;
    Adaptador adaptador;
    FirebaseFirestore db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        db = FirebaseFirestore.getInstance();
        paqueteArrayList = new ArrayList<Paquete>();
        adaptador = new Adaptador(MainActivity.this, paqueteArrayList);

        adaptador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getApplicationContext(), "Por lo menos te saca el nombre: " + paqueteArrayList.get(recyclerView.getChildAdapterPosition(v)).getNombre(),Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(v.getContext(), DescripcionPaquete.class);
                startActivity(intent);
            }
        });

        recyclerView.setAdapter(adaptador);

        EventChangeListener();
    }

    private void EventChangeListener() {
        db.collection("Paquetes").orderBy("fecha", Query.Direction.ASCENDING)
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {

                if(error != null){

                    Log.e("Firestore error", error.getMessage());
                    return;
                }

                for(DocumentChange dc: value.getDocumentChanges()){
                    if(dc.getType() == DocumentChange.Type.ADDED){
                        paqueteArrayList.add(dc.getDocument().toObject(Paquete.class));
                    }
                    adaptador.notifyDataSetChanged();
                }
            }
        });
    }
}