package com.example.mislugaresprueba;

import android.app.Application;
import android.content.SharedPreferences;

public class Aplicacion extends Application {
    public RepositorioLugares lugares = new LugaresLista();
    public AdaptadorLugares adaptador = new AdaptadorLugares(lugares);
    @Override public void onCreate() {
        super.onCreate();
    }
}
