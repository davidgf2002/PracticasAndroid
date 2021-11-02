package com.dalfaro.buzonillo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity; //????


import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.ErrorCodes;
import com.firebase.ui.auth.IdpResponse;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Arrays;
import java.util.List;

public class LoginActivity extends AppCompatActivity{
    private static final int RC_SIGN_IN = 123;
    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        login();
    }
    private void login() {
        FirebaseUser usuario = FirebaseAuth.getInstance().getCurrentUser();
        if (usuario != null && usuario.isEmailVerified()) {
            Toast.makeText(this, "inicia sesión: "+usuario.getDisplayName()+
                    " - "+ usuario.getEmail(),Toast.LENGTH_LONG).show();
            Intent i = new Intent(this, MainActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
                    | Intent.FLAG_ACTIVITY_NEW_TASK
                    | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(i);
        }else {
            /** List<AuthUI.IdpConfig> providers = Arrays.asList(
             new AuthUI.IdpConfig.EmailBuilder().build(),
             new AuthUI.IdpConfig.GoogleBuilder().build());
             startActivityForResult(
             AuthUI.getInstance().createSignInIntentBuilder()
             .setAvailableProviders(providers)
             .setIsSmartLockEnabled(false)
             .build(),
             RC_SIGN_IN);**/
            if(usuario !=null) {
                usuario.sendEmailVerification();
                Toast.makeText(this, "Te hemos enviado un correo: ",Toast.LENGTH_LONG).show();
            }

            startActivityForResult(AuthUI.getInstance()
                    .createSignInIntentBuilder().setAvailableProviders(Arrays.asList(
                            new AuthUI.IdpConfig.EmailBuilder().setAllowNewAccounts(true)
                                    .build(),
                            new AuthUI.IdpConfig.GoogleBuilder().build()
                    )).build(), RC_SIGN_IN);
        }
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode,Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            if (resultCode == RESULT_OK) {
                login();
                //  finish();
            } else {
                /** String s = "";
                 IdpResponse response = IdpResponse.fromResultIntent(data);
                 if (response == null) s = "Cancelado";
                 else switch (response.getError().getErrorCode()) {case ErrorCodes.NO_NETWORK: s="Sin conexión a Internet"; break;
                 case ErrorCodes.PROVIDER_ERROR: s="Error en proveedor"; break;
                 case ErrorCodes.DEVELOPER_ERROR: s="Error desarrollador"; break;
                 default: s="Otros errores de autentificación";
                 }
                 Toast.makeText(this, s, Toast.LENGTH_LONG).show();
                 **/

                IdpResponse response = IdpResponse.fromResultIntent(data);
                if (response == null) {
                    Toast.makeText(this, "Cancelado", Toast.LENGTH_LONG).show();
                    return;
                } else if (response.getError().getErrorCode() ==
                        ErrorCodes.NO_NETWORK) {
                    Toast.makeText(this, "Sin conexión a Internet", Toast.LENGTH_LONG).show();
                    return;
                } else if (response.getError().getErrorCode() ==
                        ErrorCodes.UNKNOWN_ERROR) {
                    Toast.makeText(this, "Error desconocido", Toast.LENGTH_LONG).show();
                    return;
                }
            }
        }
    }
}
