// Generated by view binder compiler. Do not edit!
package com.example.mislugaresprueba.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.mislugaresprueba.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class EdicionLugarBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final EditText comentario;

  @NonNull
  public final EditText direccion;

  @NonNull
  public final EditText nombre;

  @NonNull
  public final TextView tComentario;

  @NonNull
  public final TextView tDireccion;

  @NonNull
  public final TextView tNombre;

  @NonNull
  public final TextView tTelefono;

  @NonNull
  public final TextView tTipo;

  @NonNull
  public final TextView tUrl;

  @NonNull
  public final EditText telefono;

  @NonNull
  public final Spinner tipo;

  @NonNull
  public final EditText url;

  private EdicionLugarBinding(@NonNull ConstraintLayout rootView, @NonNull EditText comentario,
      @NonNull EditText direccion, @NonNull EditText nombre, @NonNull TextView tComentario,
      @NonNull TextView tDireccion, @NonNull TextView tNombre, @NonNull TextView tTelefono,
      @NonNull TextView tTipo, @NonNull TextView tUrl, @NonNull EditText telefono,
      @NonNull Spinner tipo, @NonNull EditText url) {
    this.rootView = rootView;
    this.comentario = comentario;
    this.direccion = direccion;
    this.nombre = nombre;
    this.tComentario = tComentario;
    this.tDireccion = tDireccion;
    this.tNombre = tNombre;
    this.tTelefono = tTelefono;
    this.tTipo = tTipo;
    this.tUrl = tUrl;
    this.telefono = telefono;
    this.tipo = tipo;
    this.url = url;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static EdicionLugarBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static EdicionLugarBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.edicion_lugar, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static EdicionLugarBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.comentario;
      EditText comentario = ViewBindings.findChildViewById(rootView, id);
      if (comentario == null) {
        break missingId;
      }

      id = R.id.direccion;
      EditText direccion = ViewBindings.findChildViewById(rootView, id);
      if (direccion == null) {
        break missingId;
      }

      id = R.id.nombre;
      EditText nombre = ViewBindings.findChildViewById(rootView, id);
      if (nombre == null) {
        break missingId;
      }

      id = R.id.t_comentario;
      TextView tComentario = ViewBindings.findChildViewById(rootView, id);
      if (tComentario == null) {
        break missingId;
      }

      id = R.id.t_direccion;
      TextView tDireccion = ViewBindings.findChildViewById(rootView, id);
      if (tDireccion == null) {
        break missingId;
      }

      id = R.id.t_nombre;
      TextView tNombre = ViewBindings.findChildViewById(rootView, id);
      if (tNombre == null) {
        break missingId;
      }

      id = R.id.t_telefono;
      TextView tTelefono = ViewBindings.findChildViewById(rootView, id);
      if (tTelefono == null) {
        break missingId;
      }

      id = R.id.t_tipo;
      TextView tTipo = ViewBindings.findChildViewById(rootView, id);
      if (tTipo == null) {
        break missingId;
      }

      id = R.id.t_url;
      TextView tUrl = ViewBindings.findChildViewById(rootView, id);
      if (tUrl == null) {
        break missingId;
      }

      id = R.id.telefono;
      EditText telefono = ViewBindings.findChildViewById(rootView, id);
      if (telefono == null) {
        break missingId;
      }

      id = R.id.tipo;
      Spinner tipo = ViewBindings.findChildViewById(rootView, id);
      if (tipo == null) {
        break missingId;
      }

      id = R.id.url;
      EditText url = ViewBindings.findChildViewById(rootView, id);
      if (url == null) {
        break missingId;
      }

      return new EdicionLugarBinding((ConstraintLayout) rootView, comentario, direccion, nombre,
          tComentario, tDireccion, tNombre, tTelefono, tTipo, tUrl, telefono, tipo, url);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}