package com.example.applicationrestaurant.Views.USER;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.applicationrestaurant.R;
import com.example.applicationrestaurant.Views.ADMIN.VISTAS.InicioBienvenidaAdmin;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {
    private TextView btnLogin;
    private EditText editEmailLogin, editPassLogin;
    private BottomNavigationView bottom_navigation;
    private ImageView imageViewLogin;
    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        bottom_navigation = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        bottom_navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        btnLogin = (TextView) findViewById(R.id.btnLogin);
        editEmailLogin = (EditText) findViewById(R.id.editEmailLogin);
        editPassLogin = (EditText) findViewById(R.id.editPassLogin);

        imageViewLogin = (ImageView) findViewById(R.id.imageViewLogin);
        rotarImagen(imageViewLogin);
        final int DURACION = 3500;
        
        //METODO PARA GUARDAR SESION
        guardarEstadoButton();

        //EVENTO BOTONES
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = editEmailLogin.getText().toString();
                String pass = editPassLogin.getText().toString();
                FirebaseAuth mAuth;
                mAuth = FirebaseAuth.getInstance();
                mAuth.signInWithEmailAndPassword(email, pass)
                                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        if(task.isSuccessful()){
                                            Intent intent = new Intent(getApplicationContext(), InicioBienvenidaAdmin.class);
                                            startActivity(intent);
                                        }else{
                                            Toast.makeText(getApplicationContext(),"Intente nuevamente", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
            }
        });

    }

    private void guardarEstadoButton() {
        SharedPreferences preferences = getSharedPreferences("sesion", Context.MODE_PRIVATE);
        boolean estado = true;
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("estado_usu", estado);
        editor.commit();
    }


    private void rotarImagen(ImageView imageViewLogin) {
        RotateAnimation animation = new RotateAnimation(0, 360,
                RotateAnimation.RELATIVE_TO_SELF, 0.5f,
                RotateAnimation.RELATIVE_TO_SELF, 0.5f);

        animation.setDuration(2000);
        animation.setRepeatCount(Animation.INFINITE);
        animation.setRepeatMode(Animation.REVERSE);
        imageViewLogin.startAnimation(animation);
    }

    private final BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    switch (item.getItemId()){
                        case R.id.nav_menu:
                            Intent intent = new Intent(getApplicationContext(), Comida.class);
                            startActivity(intent);
                            return true;

                        case R.id.nav_coctel:
                            Intent intent2 = new Intent(getApplicationContext(), Cocteles.class);
                            startActivity(intent2);
                            return true;

                        case R.id.nav_cerveza:
                            Intent intent3 = new Intent(getApplicationContext(), Cervezas.class);
                            startActivity(intent3);
                            return true;

                        case R.id.nav_map:
                            Intent intent4 = new Intent(getApplicationContext(), Map.class);
                            startActivity(intent4);
                            return true;

                        case R.id.nav_login:
                            Intent intent5 = new Intent(getApplicationContext(), Login.class);
                            startActivity(intent5);
                            return true;

                    }
                    return false;
                }
            };
}