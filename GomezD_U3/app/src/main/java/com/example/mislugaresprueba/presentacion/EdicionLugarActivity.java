package com.example.mislugaresprueba.presentacion;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.RatingBar;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mislugaresprueba.Aplicacion;
import com.example.mislugaresprueba.R;
import com.example.mislugaresprueba.casos_uso.CasosUsoLugar;
import com.example.mislugaresprueba.databinding.EdicionLugarBinding;
import com.example.mislugaresprueba.databinding.VistaLugarBinding;
import com.example.mislugaresprueba.datos.RepositorioLugares;
import com.example.mislugaresprueba.modelo.Lugar;
import com.example.mislugaresprueba.modelo.TipoLugar;

import java.text.DateFormat;
import java.util.Date;

public class EdicionLugarActivity extends AppCompatActivity {
    private RepositorioLugares lugares;
    private CasosUsoLugar usoLugar;
    private int pos;
    private Lugar lugar;
    private EdicionLugarBinding binding;
    private Uri uriUltimaFoto;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = EdicionLugarBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        lugares = ((Aplicacion) getApplication()).lugares;
        Bundle extras = getIntent().getExtras();
        pos = extras.getInt("pos", 0);
        lugar = lugares.elemento(pos);
        //usoLugar = new CasosUsoLugar(this, lugares);
        actualizaVistas();
    }

    public void actualizaVistas() {
        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, TipoLugar.getNombres());
        adaptador.setDropDownViewResource(android.R.layout.
                simple_spinner_dropdown_item);
        binding.tipo.setAdapter(adaptador);
        binding.tipo.setSelection(lugar.getTipo().ordinal());
        /*
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
                    @Override
                    public void onRatingChanged(RatingBar ratingBar,
                                                float valor, boolean fromUser) {
                        lugar.setValoracion(valor);
                    }
                });
         */


        //Si el telefono es 0 se esconde el texto y el icono
       /*
        if (lugar.getTelefono() == 0) {
            binding.telefono.setVisibility(View.GONE);
            binding.logoTelefono.setVisibility(View.GONE);

        } else {
            binding.telefono.setVisibility(View.VISIBLE);

        */
            binding.telefono.setText(Integer.toString(lugar.getTelefono()));
        //}

        //Si la direccion está vacía se esconde el texto y el icono
        /*
        if (lugar.getDireccion().isEmpty()) {
            binding.direccion.setVisibility(View.GONE);
            binding.logoDireccion.setVisibility(View.GONE);
        } else {
            binding.direccion.setVisibility(View.VISIBLE);

         */
            binding.direccion.setText(lugar.getDireccion());
        //}


        //Si la url está vacía se esconde el texto y el icono
        /*
        if (lugar.getUrl().isEmpty()) {
            binding.url.setVisibility(View.GONE);
            binding.logoUrl.setVisibility(View.GONE);
        } else {
            binding.url.setVisibility(View.VISIBLE);

         */
            binding.url.setText(lugar.getUrl());
        //}

        //Si el comentario está vacío se esconde el texto y el icono
        /*
        if (lugar.getComentario().isEmpty()) {
            binding.comentario.setVisibility(View.GONE);
            binding.logoComentario.setVisibility(View.GONE);
        } else {
            binding.comentario.setVisibility(View.VISIBLE);

         */
            binding.comentario.setText(lugar.getComentario());
        //}

    }


    @Override public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.editar_menu, menu);
        return true;
    }

    @Override public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.accion_cancelar:
                cancelar(lugar);
                return true;
            case R.id.accion_guardar:
                guardar(lugar, pos);
                return true;
            case R.id.accion_editar:
                CasosUsoLugar.editar(pos);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void guardar(Lugar lugar, int pos){
        lugar.setNombre(binding.nombre.getText().toString());
        lugar.setTipo(TipoLugar.values()[binding.tipo.getSelectedItemPosition()]);
        lugar.setDireccion(binding.direccion.getText().toString());
        lugar.setTelefono(Integer.parseInt(binding.telefono.getText().toString()));
        lugar.setUrl(binding.url.getText().toString());
        lugar.setComentario(binding.comentario.getText().toString());
        usoLugar.guardar(pos, lugar);
        finish();
    }

    public void cancelar(Lugar lugar){
        finish();
    }

}
