package com.example.receptoranuncio;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;

public class ReceptorLlamadas extends BroadcastReceiver {
    @Override public void onReceive(Context context, Intent intent) {
        // Sacamos información de la intención
        String estado = "", numero = "";
        Bundle extras = intent.getExtras();
        if (extras != null) {
            estado = extras.getString(TelephonyManager.EXTRA_STATE);
            if (estado.equals(TelephonyManager.EXTRA_STATE_RINGING)) {
                numero = extras.getString(
                        TelephonyManager.EXTRA_INCOMING_NUMBER);
                String info = estado + " " + numero;
                Log.d("ReceptorAnuncio", info + " intent=" + intent);
                Toast.makeText(context, "Llamada: "+ numero,
                        Toast.LENGTH_SHORT).show();
            }
        }
    }
}

