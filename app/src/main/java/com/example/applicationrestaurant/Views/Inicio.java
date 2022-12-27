package com.example.applicationrestaurant.Views;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.applicationrestaurant.R;
import com.example.applicationrestaurant.Views.Products.ListComida;

public class Inicio extends AppCompatActivity{
    private TextView textHome;
    private ImageView logoImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);
        logoImage = (ImageView) findViewById(R.id.logoImage);
        textHome = (TextView) findViewById(R.id.textHome);
        rotarImagen(logoImage);

        textHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (getApplicationContext(), InicioMenu.class);
                startActivity(intent);
            }
        });
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