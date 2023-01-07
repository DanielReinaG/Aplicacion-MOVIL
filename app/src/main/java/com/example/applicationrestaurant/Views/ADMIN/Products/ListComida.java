package com.example.applicationrestaurant.Views.ADMIN.Products;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.applicationrestaurant.Adapter.ComidaAdapter;
import com.example.applicationrestaurant.DB.DBFirebase;
import com.example.applicationrestaurant.Entities.Comida;
import com.example.applicationrestaurant.R;
import com.example.applicationrestaurant.Servicios.ComidaService;
import com.example.applicationrestaurant.Views.ADMIN.VISTAS.Crear_Product;
import com.example.applicationrestaurant.Views.USER.Register;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class ListComida extends AppCompatActivity{
    private ListView listViewComida;
    private ArrayList<Comida> arrayComida;
    private ComidaAdapter comidaAdapter;
    private SearchView searchViewBuscar;
    private DBFirebase dbFirebase;
    private ComidaService comidaService;
    private TextView btnAgregarListComida;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_comida);

        arrayComida = new ArrayList<>();
        //Evento al dar clic al BOTON +
        btnAgregarListComida = (TextView) findViewById(R.id.btnAgregarListComida);
        listViewComida = (ListView) findViewById(R.id.listViewComida);
        searchViewBuscar = (SearchView) findViewById(R.id.searchViewBuscar);
        BottomNavigationView navigation = findViewById(R.id.bottom_navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        //
        searchViewBuscar.clearFocus();
        searchViewBuscar.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterList(newText);
                return true;
            }
        });
        //
        try{
            dbFirebase = new DBFirebase();
            comidaService = new ComidaService();
        }catch (Exception e){
            Log.e("DB", e.toString());
        }

        comidaAdapter = new ComidaAdapter(this, arrayComida);
        listViewComida.setAdapter(comidaAdapter);


        //MUESTRA LOS DATOS CON GETDATA(OBTENER INFORMACION) DE LA BD
        dbFirebase.getDataComida(comidaAdapter, arrayComida);

        //AGREGA EL MENU CREADO
        btnAgregarListComida.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent inte = new Intent(getApplicationContext(), Crear_Product.class);
                startActivity(inte);
            }
        });

    }

    private void filterList(String text) {
        ArrayList<Comida> filteredList = new ArrayList<>();
        for (Comida comida :arrayComida){
            if(comida.getName().toLowerCase().contains(text.toLowerCase())){
                filteredList.add(comida);
            }
        }
        if(filteredList.isEmpty()){Toast.makeText(this,"No existe registro", Toast.LENGTH_SHORT).show();
        }else{
            comidaAdapter.setFilteredList(filteredList);

        }
    }

    private final BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    switch (item.getItemId()){
                        case R.id.nav_menu:
                            Intent intent = new Intent(getApplicationContext(), ListComida.class);
                            startActivity(intent);
                            return true;

                        case R.id.nav_coctel:
                            Intent intent2 = new Intent(getApplicationContext(), ListCocteles.class);
                            startActivity(intent2);
                            return true;

                        case R.id.nav_cerveza:
                            Intent intent3 = new Intent(getApplicationContext(), ListCervezas.class);
                            startActivity(intent3);
                            return true;

                        case R.id.nav_login:
                            Intent intent5 = new Intent(getApplicationContext(), com.example.applicationrestaurant.Views.USER.Comida.class);
                            startActivity(intent5);
                            return true;

                        case R.id.nav_newAdmin:
                            Intent intent6 = new Intent(getApplicationContext(), Register.class);
                            startActivity(intent6);
                            return true;


                    }
                    return false;
                }
            };
}