package com.example.firebaserecyclerview;

public class Paquete {

    String fecha, nombre;


    public Paquete(){}


    public Paquete(String fecha, String nombre) {
        this.fecha = fecha;
        this.nombre = nombre;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

}
