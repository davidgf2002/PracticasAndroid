package com.example.recyclerview;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListAdapter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<Paquete> elementos;
    FirebaseFirestore db;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = FirebaseFirestore.getInstance();

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
