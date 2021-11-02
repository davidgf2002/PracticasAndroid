package com.example.firebase_authentication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.ListenerRegistration;

public class MainActivity extends AppCompatActivity {

    ListenerRegistration registration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        /*
        LugaresLista listaLugares = new LugaresLista();00
        for (int id=0; id<listaLugares.tamaño(); id++){
            db.collection("lugares").add(listaLugares.elemento(id));
        }
         */

        Log.d("Firestore", "activado el escuchador");
        registration = db.collection("coleccion").document("documento").addSnapshotListener(
                new EventListener<DocumentSnapshot>() {
                    @Override
                    public void onEvent(@Nullable DocumentSnapshot snapshot,
                                        @Nullable FirebaseFirestoreException e){
                        if (e != null) {
                            Log.e("Firestore", "Error al leer", e);
                        } else if (snapshot == null || !snapshot.exists()) {
                            Log.e("Firestore", "Error: documento no encontrado ");
                        } else {
                            Log.d("Firestore", "datos:" + snapshot.getData());
                        }
                    }
                });




        Button cerrarSesion =(Button) findViewById(R.id.btn_cerrar_sesion);
        cerrarSesion.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                AuthUI.getInstance().signOut(MainActivity.this)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                Intent i = new Intent(MainActivity.this,LoginActivity.class);
                                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
                                        | Intent.FLAG_ACTIVITY_NEW_TASK
                                        | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(i);
                                MainActivity.this.finish();
                            }
                        });
            }
        });
        FirebaseUser usuario = FirebaseAuth.getInstance().getCurrentUser();
        TextView nombre = (TextView) findViewById(R.id.nombre);
        nombre.setText(usuario.getDisplayName());


    }
    protected void onDestroy() {
        super.onDestroy();
        Log.d("Firestore", "desactivado el escuchador");
        registration.remove();
    }
}