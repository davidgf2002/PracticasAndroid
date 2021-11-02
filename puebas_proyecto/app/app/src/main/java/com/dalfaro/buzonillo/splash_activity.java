package com.dalfaro.buzonillo;

import static android.content.pm.ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.widget.MediaController;
import android.widget.VideoView;

public class splash_activity extends Activity {

    private final int DURACION_SPLASH = 3000;

    //añadido
    VideoView videoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_splash);

        //añadido
        videoView=(VideoView) findViewById(R.id.videoView);
        Uri uri = Uri.parse("android.resource://" + getPackageName() + "/"+ R.raw.logovid);
        videoView.setVideoURI(uri);
        videoView.start();

        /*  //Funciones de reproductor, como es video automatico no hacen falta
        videoView.setMediaController(new MediaController(this));
        videoView.requestFocus();
        */

        new Handler().postDelayed(new Runnable() {

            public void run() {
                Intent intent = new Intent(splash_activity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            };
        },DURACION_SPLASH);
    }
}