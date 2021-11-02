package com.example.recyclerview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class DescripcionPaquete extends AppCompatActivity {
    TextView nombreDescripcion;
    TextView fechaDescripcion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_descripcion_paquete);

        Paquete element = (Paquete) getIntent().getSerializableExtra("ListElement");
        nombreDescripcion = findViewById(R.id.nombreDescripcion);
        fechaDescripcion = findViewById(R.id.fechaDescripcion);

        nombreDescripcion.setText(element.getNombre());
        fechaDescripcion.setText(element.getFecha());
    }
}