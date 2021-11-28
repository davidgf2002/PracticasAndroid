package com.example.practicaclaseexamen;


import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class Lectura {
    private String tipo;
    private int diametro;
    private long tiempo;

    static long MINUTO[]={ //minuto en que entra nueva variedad
            0L, 5L, 7L, 10L, 17L, 22L, 30L, 45L, 50L};
    static String TIPO[] = { //variedad que entra
            "valencia", "navel", "valencia", "clementina",
            "navel", "valencia", "clementina", "valencia"};
    static int DIAMETRO[] = { //diámetro medio en cm
            18, 19, 17, 13, 16, 19, 14, 16};



    //Constructor
    public Lectura(String tipo, int diametro, long tiempo) {
        this.tipo = tipo;
        this.diametro = diametro;
        this.tiempo = tiempo;
    }

    //Getters and Setters
    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getDiametro() {
        return diametro;
    }

    public void setDiametro(int diametro) {
        this.diametro = diametro;
    }

    public long getTiempo() {
        return tiempo;
    }

    public void setTiempo(long tiempo) {
        this.tiempo = tiempo;
    }

    @Override
    public String toString() {
        return "Lectura{" +
                "tipo='" + tipo + '\'' +
                ", diametro=" + diametro +
                ", tiempo=" + tiempo +
                '}';
    }
}//()
