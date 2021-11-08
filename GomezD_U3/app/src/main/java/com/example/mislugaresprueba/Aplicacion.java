package com.example.mislugaresprueba;

import android.app.Application;

import com.example.mislugaresprueba.datos.LugaresLista;
import com.example.mislugaresprueba.datos.RepositorioLugares;
import com.example.mislugaresprueba.presentacion.AdaptadorLugares;

public class Aplicacion extends Application {
    public RepositorioLugares lugares = new LugaresLista();
    public AdaptadorLugares adaptador = new AdaptadorLugares(lugares);
    @Override public void onCreate() {
        super.onCreate();
    }
}
