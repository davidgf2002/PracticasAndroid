package com.example.recyclerfirestore;

public class Paquete {
    String nombre, fecha;

    //Constructor
    public Paquete(String nombre, String fecha) {
        this.nombre = nombre;
        this.fecha = fecha;
    }
    public Paquete(){}

    //Getters and Settes
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
}
