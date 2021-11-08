package com.example.mislugaresprueba.presentacion;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import com.example.mislugaresprueba.Aplicacion;
import com.example.mislugaresprueba.R;
import com.example.mislugaresprueba.casos_uso.CasosUsoLugar;
import com.example.mislugaresprueba.modelo.GeoPunto;
import com.example.mislugaresprueba.modelo.Lugar;
import com.example.mislugaresprueba.datos.RepositorioLugares;
import com.example.mislugaresprueba.databinding.VistaLugarBinding;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;

public class VistaLugarActivity extends AppCompatActivity {
    private RepositorioLugares lugares;
    private CasosUsoLugar usoLugar;
    private int pos;
    private Lugar lugar;
    private VistaLugarBinding binding;
    private Uri uriUltimaFoto;


    ActivityResultLauncher<Intent> galeriaLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        lugar.setFoto(result.getData().getDataString());
                        ponerFoto(binding.foto, lugar.getFoto());
                    } else {
                        Toast.makeText(VistaLugarActivity.this,
                                "Foto no cargada", Toast.LENGTH_LONG).show();
                    }
                }
            });

    ActivityResultLauncher<Intent> tomarFotoLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode()==Activity.RESULT_OK && uriUltimaFoto!=null) {
                        lugar.setFoto(uriUltimaFoto.toString());
                        ponerFoto(binding.foto, lugar.getFoto());
                    } else {
                        Toast.makeText(VistaLugarActivity.this,
                                "Error en captura", Toast.LENGTH_LONG).show();
                    }
                }
            });




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = VistaLugarBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        lugares = ((Aplicacion) getApplication()).lugares;
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
        //Si el telefono es 0 se esconde el texto y el icono
        if (lugar.getTelefono() == 0) {
            binding.telefono.setVisibility(View.GONE);
            binding.logoTelefono.setVisibility(View.GONE);

        } else {
            binding.telefono.setVisibility(View.VISIBLE);
            binding.telefono.setText(Integer.toString(lugar.getTelefono()));
        }

        //Si la direccion está vacía se esconde el texto y el icono
        if (lugar.getDireccion().isEmpty()) {
            binding.direccion.setVisibility(View.GONE);
            binding.logoDireccion.setVisibility(View.GONE);
        } else {
            binding.direccion.setVisibility(View.VISIBLE);
            binding.direccion.setText(lugar.getDireccion());
        }


        //Si la url está vacía se esconde el texto y el icono
        if (lugar.getUrl().isEmpty()) {
            binding.url.setVisibility(View.GONE);
            binding.logoUrl.setVisibility(View.GONE);
        } else {
            binding.url.setVisibility(View.VISIBLE);
            binding.url.setText(lugar.getUrl());
        }

        //Si el comentario está vacío se esconde el texto y el icono
        if (lugar.getComentario().isEmpty()) {
            binding.comentario.setVisibility(View.GONE);
            binding.logoComentario.setVisibility(View.GONE);
        } else {
            binding.comentario.setVisibility(View.VISIBLE);
            binding.comentario.setText(lugar.getComentario());
        }

    }

    @Override public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.vista_lugar, menu);
        return true;
    }
    @Override public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.accion_compartir:
                compartir(lugar);
                return true;
            case R.id.accion_llegar:
                verMapa(lugar);
                return true;
            case R.id.accion_editar:
                CasosUsoLugar.editar(pos);
                return true;
            case R.id.accion_borrar:
                new AlertDialog.Builder(this)
                        .setTitle("Borrar lugar")
                        .setMessage("¿Quieres eliminar este lugar?")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                usoLugar.borrar(pos);
                            }
                        })
                        .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        })
                        .show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    // INTENCIONES
    public void compartir(Lugar lugar) {
        Intent i = new Intent(Intent.ACTION_SEND);
        i.setType("text/plain");
        i.putExtra(Intent.EXTRA_TEXT,
                lugar.getNombre() + " - " + lugar.getUrl());
        this.startActivity(i);
    }
    public void llamarTelefono(Lugar lugar) {
        this.startActivity(new Intent(Intent.ACTION_DIAL,
                Uri.parse("tel:" + lugar.getTelefono())));
    }
    public void verPgWeb(Lugar lugar) {
        this.startActivity(new Intent(Intent.ACTION_VIEW,
                Uri.parse(lugar.getUrl())));
    }
    public final void verMapa(Lugar lugar) {
        double lat = lugar.getPosicion().getLatitud();
        double lon = lugar.getPosicion().getLongitud();
        Uri uri = lugar.getPosicion() != GeoPunto.SIN_POSICION
                ? Uri.parse("geo:" + lat + ',' + lon)
                : Uri.parse("geo:0,0?q=" + lugar.getDireccion());
        this.startActivity(new Intent("android.intent.action.VIEW", uri));
    }

    public void verMapa(View view) {
        verMapa(lugar);
    }
    public void llamarTelefono(View view) {
        llamarTelefono(lugar);
    }
    public void verPgWeb(View view) {
        verPgWeb(lugar);
    }


    public void galeria(View view) {
        String action;
        if (android.os.Build.VERSION.SDK_INT >= //API19
                android.os.Build.VERSION_CODES.KITKAT) {
            action = Intent.ACTION_OPEN_DOCUMENT;
        } else {
            action = Intent.ACTION_PICK;
        }
        Intent intent = new Intent(action,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("image/*");
        galeriaLauncher.launch(intent);
        //startActivityForResult(intent, RESULTADO_GALERIA);
    }

    protected void ponerFoto(ImageView imageView, String uri) {
        if (uri != null && !uri.isEmpty() && !uri.equals("null")) {
            imageView.setImageURI(Uri.parse(uri));
        } else {
            imageView.setImageBitmap(null);
        }
    }

    public void tomarFoto(View view) {
        try {
            File file = File.createTempFile(
                    "img_" + (System.currentTimeMillis()/ 1000), ".jpg" ,
                    getExternalFilesDir(Environment.DIRECTORY_PICTURES));
            if (Build.VERSION.SDK_INT >= 24) {
                uriUltimaFoto = FileProvider.getUriForFile(
                        this, "es.upv.dgomfer.mislugares.fileProvider", file);
            } else {
                uriUltimaFoto = Uri.fromFile(file);
            }
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            intent.putExtra (MediaStore.EXTRA_OUTPUT, uriUltimaFoto);
            tomarFotoLauncher.launch(intent);
        } catch (IOException ex) {
            Toast.makeText(this, "Error al crear fichero de imagen",
                    Toast.LENGTH_LONG).show();
        }
    }

    public void eliminarFoto(View view) {
        new AlertDialog.Builder(this)
                .setTitle("Borrar imagen")
                .setMessage("¿Quieres eliminar la imagen?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        lugar.setFoto("");
                        ponerFoto(binding.foto, "");
                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                })
                .show();
    }

}