package com.example.mislugaresprueba.presentacion;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.mislugaresprueba.modelo.Lugar;
import com.example.mislugaresprueba.R;
import com.example.mislugaresprueba.datos.RepositorioLugares;
import com.example.mislugaresprueba.databinding.ElementoListaBinding;

public class AdaptadorLugares extends
        RecyclerView.Adapter<AdaptadorLugares.ViewHolder> {

    protected View.OnClickListener onClickListener;
    public void setOnItemClickListener(View.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    protected RepositorioLugares lugares; // Lista de lugares a mostrar
    public AdaptadorLugares(RepositorioLugares lugares) {
        this.lugares = lugares;
    }
    //Creamos nuestro ViewHolder, con los tipos de elementos a modificar

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView nombre, direccion;
        public ImageView foto;
        public RatingBar valoracion;
        public ViewHolder(ElementoListaBinding itemView) {
            super(itemView.getRoot());
            nombre = itemView.nombre;
            direccion = itemView.direccion;
            foto = itemView.foto;
            valoracion = itemView.valoracion;
        }
// Personalizamos un ViewHolder a partir de un lugar
public void personaliza(Lugar lugar) {
    nombre.setText(lugar.getNombre());
    direccion.setText(lugar.getDireccion());
    int id = R.drawable.otros;
    switch(lugar.getTipo()) {
        case RESTAURANTE:id = R.drawable.restaurante; break;
        case BAR: id = R.drawable.bar; break;
        case COPAS: id = R.drawable.copas; break;
        case ESPECTACULO:id = R.drawable.espectaculos; break;
        case HOTEL: id = R.drawable.hotel; break;
        case COMPRAS: id = R.drawable.compras; break;
        case EDUCACION: id = R.drawable.educacion; break;
        case DEPORTE: id = R.drawable.deporte; break;
        case NATURALEZA: id = R.drawable.naturaleza; break;
        case GASOLINERA: id = R.drawable.gasolinera; break; }
    foto.setImageResource(id);
    foto.setScaleType(ImageView.ScaleType.FIT_END);
    valoracion.setRating(lugar.getValoracion());
}
    }
    // Creamos el ViewHolder con la vista de un elemento sin personalizar
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Inflamos la vista desde el xml
        ElementoListaBinding v = ElementoListaBinding.inflate(LayoutInflater.from(
                parent.getContext()), parent, false);
        v.getRoot().setOnClickListener(onClickListener);
        return new AdaptadorLugares.ViewHolder(v);

    }
    // Usando como base el ViewHolder y lo personalizamos
    @Override
    public void onBindViewHolder(ViewHolder holder, int posicion) {
        Lugar lugar = lugares.elemento(posicion);
        holder.personaliza(lugar);
    }
    // Indicamos el número de elementos de la lista
    @Override public int getItemCount() {
        return lugares.tamaño();
    }
}