package com.example.pikku;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.blautic.pikkuAcademyLib.PikkuAcademy;
import com.blautic.pikkuAcademyLib.ScanInfo;
import com.blautic.pikkuAcademyLib.ble.gatt.ConnectionState;
import com.blautic.pikkuAcademyLib.callback.ConnectionCallback;
import com.blautic.pikkuAcademyLib.callback.ScanCallback;
import com.example.pikku.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private PikkuAcademy pikku;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        pikku = PikkuAcademy.getInstance(this);
        pikku.enableLog();
    }

    public void onClickScan(View view) {
        binding.textScan.setText("Pulsa el botón Pikku 1 para ser escaneado");
        pikku.scan(true, new ScanCallback() {
            @Override
            public void onScan(ScanInfo scanInfo) {
                pikku.saveDevice(scanInfo);
                // guardar dispositivo para futuras conexiones
                Log.d("Pikku", scanInfo.toString());
                binding.textScan.setText("Encontrado:" + pikku.getAddressDevice());
                binding.buttonConnect.setEnabled(true);
            }
        });
    }

    public void onClickConnect(View view) {
        binding.textConnect.setText("Conectando...");
        pikku.connect(new ConnectionCallback() {
            @Override
            public void onConnect(ConnectionState state) {
                if (state == ConnectionState.CONNECTED) {
                    binding.textConnect.setText("Conectado: " + pikku.getAddressDevice());

                    binding.buttonScan.setEnabled(false);
                    binding.buttonConnect.setText("Desconectar");
                }

                if (binding.buttonConnect.getText().equals("Desconectar")) {
                    pikku.disconnect();
                    binding.buttonConnect.setText("Conectar");
                    binding.buttonScan.setEnabled(true);
                    binding.buttonConnect.setEnabled(false);
                }

            }
        });
    }

    public void arrancarMotor(View view){
        if(binding.arrancarMotor.equals("Arrancar Motor")){
            pikku.startEngine();
            binding.arrancarMotor.setText("Apagar Motor");
        }
        else{
            pikku.stopEngine();
            binding.arrancarMotor.setText("Arrancar Motor");
        }
    }

    public void encenderLed(View view){
        if(binding.botonLed.equals("Encender LED")){
            pikku.turnOnLed();
            binding.botonLed.setText("Cambiar a Intermitente");
        }
        else if (binding.botonLed.equals("Cambiar a Intermitente")){
            pikku.flashingLed();
            binding.arrancarMotor.setText("Apagar LED");
        }
        else{
            pikku.turnOffLed();
            binding.botonLed.setText("Encender LED");
        }
    }

}