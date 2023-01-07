package com.example.applicationrestaurant.Views.USER;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.applicationrestaurant.R;
import com.example.applicationrestaurant.Views.USER.Comida;

public class Inicio extends AppCompatActivity{
    private ImageView logoImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);
        logoImage = findViewById(R.id.logoImage);
        rotarImagen(logoImage);

        final int DURACION = 2700;

        new Handler().postDelayed(()->{
            //CODIGO QUE SE EJECUTARA
            Intent intent = new Intent(getApplicationContext(), Comida.class);
            startActivity(intent);
            finish();
        },DURACION);
    }
    //ANIMACION DE HACER ROTAR LA IMAGEN
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