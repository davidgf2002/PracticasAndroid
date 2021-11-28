package com.example.practicadavid_gomez;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private static final int SOLICITUD_PERMISO_WRITE_CALL_LOG = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button b1 = findViewById(R.id.b1);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (v.getContext(), Segunda_Actividad.class);
                startActivity(intent);
            }
        });



        Button b3 = findViewById(R.id.b3);
        b3.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            public void onClick(View view) {

                         Context context = getApplicationContext();
                Intent intent = new Intent(MainActivity.this, SensorDistancia.class);
                context.startForegroundService(intent);


                startService(new Intent(MainActivity.this,
                        SensorDistancia.class));
            }
        });

    }

    public void llamarTelefono(View view) {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission
                .CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
            Intent intent = new Intent(Intent.ACTION_CALL,
                    Uri.parse("tel:5555"));
            startActivity(intent);
        } else {
            solicitarPermiso(Manifest.permission.CALL_PHONE, "Sin el permiso" +
                            " No puedo realizar la llamada",
                    SOLICITUD_PERMISO_WRITE_CALL_LOG, this);
        }
    }

    public static void solicitarPermiso(final String permiso, String
            justificacion, final int requestCode, final Activity actividad) {
        if (ActivityCompat.shouldShowRequestPermissionRationale(actividad,
                permiso)) {
            new AlertDialog.Builder(actividad)
                    .setTitle("Solicitud de permiso")
                    .setMessage(justificacion)
                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {
                            ActivityCompat.requestPermissions(actividad,
                                    new String[]{permiso}, requestCode);
                        }
                    }).show();
        } else {
            ActivityCompat.requestPermissions(actividad,
                    new String[]{permiso}, requestCode);
        }
    }

}