package com.example.puebas_proyecto;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

public class Tab2 extends Fragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab2, container, false);

        ConstraintLayout clp = view.findViewById(R.id.constraintLayout13);
        clp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //verPaquete(view);
                Intent myIntent = new Intent(view.getContext(), Paquete1.class);
                startActivity(myIntent);
            }
        });

        return view;
    }

}
