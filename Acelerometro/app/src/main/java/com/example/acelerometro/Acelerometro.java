package com.example.acelerometro;

import android.Manifest;
import android.app.Service;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.net.Uri;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import java.util.List;

public class Acelerometro extends Service implements SensorEventListener {
    private boolean primeraVez = true;
    private double anterior;

    @Override
    public void onCreate() {

    }//onCreate()


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onSensorChanged(SensorEvent evento) {
        if (primeraVez==true){
            anterior = evento.values[0];
            primeraVez=false;
        }else {
            if (Math.abs(anterior-evento.values[0]) > 2){
                for (int i = 0; i < 3; i++) {
                    if (evento.values[0]>30){
                        Log.d("Caidas", "Caidas " + i + ": " + evento.values[0]);
                        int permiso_llamada = ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE);
                        if (permiso_llamada == PackageManager.PERMISSION_GRANTED){
                            Intent x = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "643364675"));
                            x.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(x);
                        }
                    }
                }
            }
            anterior=evento.values[0];
        }

        anterior = evento.values[0];
    }//()
    //Log.d("Sensores", "Caídas " + i + ": " + evento.values[0]);

    @Override
    public void onAccuracyChanged(Sensor sensor, int precision) {
    }


    @Override
    public int onStartCommand(Intent intenc, int flags, int idArranque) {
        Toast.makeText(this, "Servicio creado", Toast.LENGTH_SHORT).show();

        SensorManager sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        List<Sensor> listaSensores = sensorManager.getSensorList(Sensor.TYPE_ACCELEROMETER);
        listaSensores = sensorManager.getSensorList(Sensor.TYPE_ACCELEROMETER);

        if (!listaSensores.isEmpty()) {
            Sensor acelerometerSensor = listaSensores.get(0);
            sensorManager.registerListener(this, acelerometerSensor, SensorManager.SENSOR_DELAY_UI);
        }

        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        Toast.makeText(this, "Servicio detenido", Toast.LENGTH_SHORT).show();

        SensorManager sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensorManager.unregisterListener(this);
    }


}//class
