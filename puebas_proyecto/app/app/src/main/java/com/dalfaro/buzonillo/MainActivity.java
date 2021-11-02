package com.dalfaro.buzonillo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;



import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class MainActivity extends AppCompatActivity {
    private String[] nombres = new String[]{"Sensores","Historial","Perfil"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity_layout);


        ViewPager2 viewPager = findViewById(R.id.viewpager);
        viewPager.setAdapter(new MiPagerAdapter(this));
        TabLayout tabs = findViewById(R.id.tabs);
        new TabLayoutMediator(tabs, viewPager,
                new TabLayoutMediator.TabConfigurationStrategy() {
                    @Override
                    public void onConfigureTab(@NonNull TabLayout.Tab tab, int position){
                        tab.setText(nombres[position]);
                    }
                }
        ).attach();


    }


    public class MiPagerAdapter extends FragmentStateAdapter {

        public MiPagerAdapter(FragmentActivity activity){
            super(activity);
        }
        @Override
        public int getItemCount() {
            return 3;
        }
        @Override @NonNull
        public Fragment createFragment(int position) {
            switch (position) {
                case 0: return new Tab1();
                case 1: return new Tab2();
                case 2: return new Tab3();
            }
            return null;
        }
    }

    public void sePulsa(View view){
        Toast.makeText(this, "Pulsado", Toast.LENGTH_SHORT).show();
    }


}