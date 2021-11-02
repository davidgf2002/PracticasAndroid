package com.example.puebas_proyecto;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Paquete1 extends Activity {
    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.paquete1);

        /*
        Button clp3 = findViewById(R.id.verGrabacion);
        clp3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                verCamara(view);
            }
        });
         */
    }
    /*
    public void verCamara(View view) {
        Intent myIntent = new Intent(view.getContext(), Camaras.class);
        startActivity(myIntent);
        Toast.makeText(view.getContext(), "Abriendo Cámaras", Toast.LENGTH_SHORT).show();
    }

     */
}
