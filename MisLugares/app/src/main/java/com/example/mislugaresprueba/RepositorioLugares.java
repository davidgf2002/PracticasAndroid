package com.example.mislugaresprueba;

public interface RepositorioLugares {
    com.example.mislugaresprueba.Lugar elemento(int id); //Devuelve el elemento dado su id
    void añade(com.example.mislugaresprueba.Lugar lugar); //Añade el elemento indicado
    int nuevo(); //Añade un elemento en blanco y devuelve su id
    void borrar(int id); //Elimina el elemento con el id indicado
    int tamaño(); //Devuelve el número de elementos
    void actualiza(int id, com.example.mislugaresprueba.Lugar lugar); //Reemplaza un elemento
}
