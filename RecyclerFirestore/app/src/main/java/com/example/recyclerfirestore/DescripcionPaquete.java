package com.example.recyclerfirestore;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DescripcionPaquete extends AppCompatActivity {
    TextView nombreDescripcion;
    TextView fechaDescripcion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.descripcion_paquete);

        Paquete element = (Paquete) getIntent().getSerializableExtra("ListElement");

        nombreDescripcion = findViewById(R.id.descripcionPaquete);
        fechaDescripcion = findViewById(R.id.fechaDescripcion);

    }
}