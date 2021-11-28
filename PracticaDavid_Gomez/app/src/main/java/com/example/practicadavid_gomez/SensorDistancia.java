package com.example.practicadavid_gomez;

import android.app.Service;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.List;

public class SensorDistancia extends Service implements SensorEventListener {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onSensorChanged(SensorEvent evento) {
        for (int i=0 ; i<3 ; i++) {
            Log.d("Sensores","Proximidad "+i+": "+evento.values[i]);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    public int onStartCommand(Intent intenc, int flags, int idArranque) {
        Toast.makeText(this, "Servicio creado", Toast.LENGTH_SHORT).show();

        SensorManager sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        List<Sensor> listaSensores = sensorManager.getSensorList(Sensor.TYPE_PROXIMITY);
        listaSensores = sensorManager.getSensorList(Sensor.TYPE_PROXIMITY);

        if (!listaSensores.isEmpty()) {
            Sensor proximitySensor = listaSensores.get(0);
            sensorManager.registerListener(this, proximitySensor, SensorManager.SENSOR_DELAY_NORMAL);
        }

        return START_STICKY;
    }
}
