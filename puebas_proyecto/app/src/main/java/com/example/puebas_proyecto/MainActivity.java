package com.example.puebas_proyecto;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tab2);
    }


    public void verPaquete(View view){
        Intent myIntent = new Intent(view.getContext(), Paquete1.class);
        startActivity(myIntent);
    }
}