package com.example.intenciones;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void compartir(View view) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT,
                "Mira al sitio que he encontrado http://www.androidcurso.com/" );
        startActivity(intent);
    }
    public void abrirPagina(View view) {
        Intent intent = new Intent(Intent.ACTION_VIEW,
                Uri.parse("http://www.androidcurso.com/"));
        startActivity(intent);
    }
    public void llamarTelefono(View view) {
        Intent intent = new Intent(Intent.ACTION_DIAL,
                Uri.parse("tel:962849347"));
        startActivity(intent);
    }
    public void verMapa(View view) {
        Intent intent = new Intent(Intent.ACTION_VIEW,
                Uri.parse("geo:38.995656,-0.166093?z=18"));
        startActivity(intent);
    }
    public void tomarFoto(View view) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivity(intent);
    }
    public void mandarCorreo(View view) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_SUBJECT, "asunto");
        intent.putExtra(Intent.EXTRA_TEXT, "texto del correo");
        intent.putExtra(Intent.EXTRA_EMAIL, new String[] {"jtomas@upv.es"});
        startActivity(intent);
    }
    public void streetview(View view) {
        Intent intent = new Intent(Intent.ACTION_VIEW,
                Uri.parse("google.streetview:cbll=38.996766,-0.1652696&cbp=0,250,0,0,0"));
        //cbll=latitud,longitud&cbp=0,azimut,0,zoom,altura
        startActivity(intent);
    }
}