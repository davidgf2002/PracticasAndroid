package com.example.receptoranuncio;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.text_view);
        IntentFilter filtro = new IntentFilter(Intent.ACTION_POWER_DISCONNECTED);
        filtro.addCategory(Intent.CATEGORY_DEFAULT);
        registerReceiver(new ReceptorAnuncios(), filtro);

    }


    public class ReceptorAnuncios extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            textView.setText(textView.getText()+" DESCONECTADO");
        }
    }
}