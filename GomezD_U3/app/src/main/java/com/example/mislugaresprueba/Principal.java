package com.example.mislugaresprueba;

import com.example.mislugaresprueba.datos.LugaresLista;
import com.example.mislugaresprueba.datos.RepositorioLugares;

class Principal {
    public static void main(String[] main) {
        /**
        com.example.mislugaresprueba.modelo.Lugar lugar = new com.example.mislugaresprueba.modelo.Lugar("Escuela Politécnica Superior de Gandía",
                "C/ Paranimf, 1 46730 Gandia (SPAIN)", -0.166093, 38.995656,
                962849300, "http://www.epsg.upv.es",
                "Uno de los mejores lugares para formarse.", 3);
        System.out.println("Lugar " + lugar.toString());
    }
         **/
        RepositorioLugares lugares = new LugaresLista();
        for (int i=0; i<lugares.tamaño(); i++) {
            System.out.println(lugares.elemento(i).toString());
        }
    }
}
