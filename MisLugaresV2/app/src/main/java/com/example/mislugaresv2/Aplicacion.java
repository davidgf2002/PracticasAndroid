package com.example.mislugaresv2;

import android.app.Application;
import android.content.SharedPreferences;

public class Aplicacion extends Application {
    private int saldo;
    @Override public void onCreate() {
        super.onCreate();
        SharedPreferences pref = getSharedPreferences("pref", MODE_PRIVATE);
        saldo = pref.getInt("saldo_inicial", 0);
    }
    public int getSaldo(){
        return saldo;
    }
    public void setSaldo(int saldo){
        this.saldo=saldo;
    }
}
