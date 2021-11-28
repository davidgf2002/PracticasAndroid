package com.example.acelerometro;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button arrancar = findViewById(R.id.botonEmpezar);
        arrancar.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            public void onClick(View view) {

                Context context = getApplicationContext();
                Intent intent = new Intent(MainActivity.this, Acelerometro.class);
                context.startForegroundService(intent);


                startService(new Intent(MainActivity.this,
                        Acelerometro.class));
            }
        });
        Button detener = findViewById(R.id.botonTerminar);
        detener.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                stopService(new Intent(MainActivity.this,
                        Acelerometro.class));
            }
        });
    }

}