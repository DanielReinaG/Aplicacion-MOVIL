package com.example.applicationrestaurant.Views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.applicationrestaurant.R;
import com.example.applicationrestaurant.Views.Products.ListComida;

public class InicioMenu extends AppCompatActivity {
    private TextView textViewComidaMenu,textViewCoctelesMenu,textViewCervezasMenu,textViewNosotrosMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio_menu);

        textViewNosotrosMenu = (TextView) findViewById(R.id.textViewNosotrosMenu);
        textViewComidaMenu = (TextView) findViewById(R.id.textViewComidaMenu);
        textViewCoctelesMenu = (TextView) findViewById(R.id.textViewCoctelesMenu);
        textViewCervezasMenu = (TextView) findViewById(R.id.textViewCervezasMenu);


        textViewNosotrosMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Map.class);
                startActivity(intent);
            }
        });


        textViewComidaMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ListComida.class);
                startActivity(intent);
            }
        });

        textViewCoctelesMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ListComida.class);
                startActivity(intent);
            }
        });

        textViewCervezasMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ListComida.class);
                startActivity(intent);
            }
        });

    }
}