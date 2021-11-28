package com.example.practicaclaseexamen;

import static com.example.practicaclaseexamen.Lectura.DIAMETRO;
import static com.example.practicaclaseexamen.Lectura.MINUTO;
import static com.example.practicaclaseexamen.Lectura.TIPO;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //Ejercicio 2 (Variables importadas desde Lectura.class)
        List<Lectura> lecturas = new ArrayList<>();
        for (int n = 0; n < TIPO.length; n++) {
            lecturas.add(
                    new Lectura(TIPO[n], DIAMETRO[n], MINUTO[n+1]-MINUTO[n])
            );
        }
        Log.d("Array de datos: ", lecturas.toString());


        //Ejercicio 3
        Set<String> tipos = new HashSet<>();
        for (Lectura lectura : lecturas) {
            tipos.add(lectura.getTipo());
        }
        Log.d("Tipos naranjas: ", tipos.toString());

        //Ejercicio 4
        for(String tipo : tipos){
            long total = 0;
            float volumen = 0;

            for(Lectura lectura : lecturas){
                if(lectura.getTipo().equals(tipo)){
                    total = lectura.getTiempo()*50;
                    volumen = (float) (total * (4/3) * Math.PI * Math.pow(lectura.getDiametro()/2, 3));
                }
            }

            Log.d("Total", tipo + " = " + total + "naranjas");
            Log.d("Volumen", tipo + " = " + volumen/1000 + " L");
        }

    }//main()
}