package com.example.firebase_authentication;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

public class Usuarios {
    public static void guardarUsuario(final FirebaseUser user) {
        Usuario usuario = new Usuario(user.getDisplayName(),user.getEmail());
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("Usuarios").document(user.getUid()).set(usuario);
    }
}
