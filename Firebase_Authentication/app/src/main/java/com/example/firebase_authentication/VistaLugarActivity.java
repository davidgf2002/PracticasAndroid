package com.example.firebase_authentication;

import android.os.Bundle;
import android.widget.RatingBar;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mislugaresprueba.databinding.VistaLugarBinding;

import java.text.DateFormat;
import java.util.Date;

public class VistaLugarActivity extends AppCompatActivity {
    private RepositorioLugares lugares;
    //private CasosUsoLugar usoLugar;
    private int pos;
    private Lugar lugar;
    private VistaLugarBinding binding;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = VistaLugarBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        lugares = (RepositorioLugares) ((Aplicacion) getApplication()).lugares;
        Bundle extras = getIntent().getExtras();
        pos = extras.getInt("pos", 0);
        lugar = lugares.elemento(pos);
        //usoLugar = new CasosUsoLugar(this, lugares);
        actualizaVistas();
    }

    public void actualizaVistas() {
        binding.nombre.setText(lugar.getNombre());
        binding.logoTipo.setImageResource(lugar.getTipo().getRecurso());
        binding.tipo.setText(lugar.getTipo().getTexto());
        binding.direccion.setText(lugar.getDireccion());
        binding.telefono.setText(Integer.toString(lugar.getTelefono()));
        binding.url.setText(lugar.getUrl());
        binding.comentario.setText(lugar.getComentario());
        binding.fecha.setText(DateFormat.getDateInstance().format(
                new Date(lugar.getFecha())));
        binding.hora.setText(DateFormat.getTimeInstance().format(
                new Date(lugar.getFecha())));
        binding.valoracion.setRating(lugar.getValoracion());
        binding.valoracion.setOnRatingBarChangeListener(
                new RatingBar.OnRatingBarChangeListener() {
                    @Override public void onRatingChanged(RatingBar ratingBar,
                                                          float valor, boolean fromUser) {
                        lugar.setValoracion(valor);
                    }
                });
    }
}