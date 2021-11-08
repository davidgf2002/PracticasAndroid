package com.example.firebase_authentication;

import android.app.Activity;
import android.content.Intent;

public class CasosUsoLugar {
    private Activity actividad;
    private RepositorioLugares lugares;
    public CasosUsoLugar(Activity actividad, LugaresAsinc lugares) {
        this.actividad = actividad;
        this.lugares = (RepositorioLugares) lugares;
    }
    // OPERACIONES BÁSICAS
    public void mostrar(int pos) {
        Intent i = new Intent(actividad, VistaLugarActivity.class);
        i.putExtra("pos", pos);
        actividad.startActivity(i);
    }
}

