package com.example.mislugaresprueba.presentacion;

import android.os.Bundle;
import android.widget.Toast;

import androidx.preference.EditTextPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;

import com.example.mislugaresprueba.R;

public class PreferenciasFragment extends PreferenceFragmentCompat {
    @Override
    public void onCreatePreferences(Bundle savedInstanceState,
                                    String rootKey) {
        setPreferencesFromResource(R.xml.preferencias, rootKey);


        final EditTextPreference maximo = (EditTextPreference)
                findPreference("maximo");
        maximo.setOnPreferenceChangeListener(
                new Preference.OnPreferenceChangeListener() {
                    @Override
                    public boolean onPreferenceChange(Preference preference, Object
                            newValue) {
                        int valor;
                        try {
                            valor = Integer.parseInt((String)newValue);
                        } catch(Exception e) {
                            Toast.makeText(getActivity(), "Ha de ser un número",
                                    Toast.LENGTH_SHORT).show();
                            return false;
                        }
                        if (valor>=0 && valor<=99) {
                            maximo.setSummary(
                                    "Limita en número de lugares que se muestran ("+valor+")");
                            return true;
                        } else {
                            Toast.makeText(getActivity(), "Valor Máximo 99",
                                    Toast.LENGTH_SHORT).show();
                            return false;
                        }
                    }
                });
    }
}

