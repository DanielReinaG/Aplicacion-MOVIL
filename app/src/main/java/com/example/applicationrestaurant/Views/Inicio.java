package com.example.applicationrestaurant.Views;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.applicationrestaurant.R;
import com.example.applicationrestaurant.Views.Products.ListComida;

public class Inicio extends AppCompatActivity {
    private TextView textHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);
        textHome = (TextView) findViewById(R.id.textHome);

        textHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (getApplicationContext(), Login.class);
                startActivity(intent);
            }
        });
    }
}