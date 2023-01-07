package com.example.applicationrestaurant.Views.ADMIN.VISTAS;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

import com.example.applicationrestaurant.R;
import com.example.applicationrestaurant.Views.ADMIN.Products.ListComida;
import com.example.applicationrestaurant.Views.USER.Comida;
import com.example.applicationrestaurant.Views.USER.Login;

public class InicioBienvenidaAdmin extends AppCompatActivity {
    private ImageView logoImage;
    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio_bienvenida_admin);

        logoImage = findViewById(R.id.logoImage);
        rotarImagen(logoImage);

        final int DURACION = 4000;

        new Handler().postDelayed(()->{
            //CODIGO QUE SE EJECUTARA
            Intent intent = new Intent(getApplicationContext(), Comida.class);
            startActivity(intent);
            finish();
        },DURACION);

        //VALIDACION PARA MANTENER SESION
        SharedPreferences preferences = getSharedPreferences("sesion", Context.MODE_PRIVATE);
        if(preferences.getBoolean("estado_usu", false)==false){
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent ventanaLogin = new Intent(getApplicationContext(), Login.class);
                    startActivity(ventanaLogin);
                    finish();
                }
            }, 4000);
        }else{
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent ventanaMenu = new Intent(getApplicationContext(), ListComida.class);
                    startActivity(ventanaMenu);
                    finish();
                }
            }, 4000);
        }

    }

    private void rotarImagen(View view){
        RotateAnimation animation = new RotateAnimation(0, 360,
                RotateAnimation.RELATIVE_TO_SELF, 0.5f,
                RotateAnimation.RELATIVE_TO_SELF, 0.5f);

        animation.setDuration(2000);
        animation.setRepeatCount(Animation.INFINITE);
        animation.setRepeatMode(Animation.REVERSE);
        view.startAnimation(animation);
    }
}