package com.example.mislugaresprueba.casos_uso;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;

import com.example.mislugaresprueba.datos.RepositorioLugares;
import com.example.mislugaresprueba.modelo.GeoPunto;
import com.example.mislugaresprueba.modelo.Lugar;
import com.example.mislugaresprueba.presentacion.EdicionLugarActivity;
import com.example.mislugaresprueba.presentacion.VistaLugarActivity;

public class CasosUsoLugar{
    private static Activity actividad;
    private RepositorioLugares lugares;


    public CasosUsoLugar(Activity actividad, RepositorioLugares lugares) {
        this.actividad = actividad;
        this.lugares = lugares;
    }
    // OPERACIONES BÁSICAS
    public void mostrar(int pos) {
        Intent i = new Intent(actividad, VistaLugarActivity.class);
        i.putExtra("pos", pos);
        actividad.startActivity(i);
    }


    public void borrar(int id) {
        lugares.borrar(id);
        actividad.finish();
    }

    public static void editar(int pos) {
        Intent i = new Intent(actividad, EdicionLugarActivity.class);
        i.putExtra("pos", pos);
        actividad.startActivity(i);
    }

    public void guardar(int id, Lugar nuevoLugar) {
        lugares.actualiza(id, nuevoLugar);
    }


}

