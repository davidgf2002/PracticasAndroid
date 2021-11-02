package com.example.firebaserecyclerview;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;

import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerview;
    ArrayList<Paquete> listaPaquetes;
    MyAdapter myAdapter;
    FirebaseFirestore db;
    ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Fetching data...");
        progressDialog.show();

        recyclerview = findViewById(R.id.recyclerView);
        recyclerview.setHasFixedSize(true);
        recyclerview.setLayoutManager(new LinearLayoutManager(this));

        //Inicializamos firebase
        db = FirebaseFirestore.getInstance();
        listaPaquetes = new ArrayList<Paquete>();
        myAdapter = new MyAdapter(MainActivity.this,listaPaquetes);

        recyclerview.setAdapter(myAdapter);


        EventChangeListener();
    }

    private void EventChangeListener() {
        db.collection("Paquetes").orderBy("fecha", Query.Direction.ASCENDING)
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {

                /*
                    if(error != null){

                        if(progressDialog.isShowing())
                            progressDialog.dismiss();
                        Log.e("Firestore error", error.getMessage());
                        return;
                    }

                 */

                    for(DocumentChange dc : value.getDocumentChanges()){

                        if(dc.getType() == DocumentChange.Type.ADDED){
                            listaPaquetes.add(dc.getDocument().toObject(Paquete.class));

                        }
                        /*
                        myAdapter.notifyDataSetChanged();
                        if(progressDialog.isShowing())
                            progressDialog.dismiss();

                         */
                    }

            }
        });
    }
}