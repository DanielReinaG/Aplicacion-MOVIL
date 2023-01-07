package com.example.applicationrestaurant.Views.USER;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.applicationrestaurant.Adapter.USER.CervezaAdapterUser;
import com.example.applicationrestaurant.DB.DBFirebase;
import com.example.applicationrestaurant.R;
import com.example.applicationrestaurant.Servicios.CervezaService;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class Cervezas extends AppCompatActivity {
    private DBFirebase dbFirebase;
    private CervezaService cervezaService;
    private ListView listViewCervezas;
    private CervezaAdapterUser cervezaAdapterUser;
    private ArrayList<com.example.applicationrestaurant.Entities.Cervezas> arrayCervezaUser;
    private BottomNavigationView bottom_navigation;
    private SearchView searchViewBuscar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cervezas);

        arrayCervezaUser = new ArrayList<>();

        listViewCervezas = (ListView) findViewById(R.id.listViewCervezas);
        searchViewBuscar = (SearchView) findViewById(R.id.searchViewBuscar);
        bottom_navigation = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        bottom_navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        searchViewBuscar.clearFocus();
        searchViewBuscar.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                filterList(s);
                return false;
            }
        });

        try {
            dbFirebase = new DBFirebase();
            cervezaService = new CervezaService();
        }catch (Exception e){
            Log.e("DB", e.toString());
        }

        cervezaAdapterUser = new CervezaAdapterUser(this, arrayCervezaUser);
        listViewCervezas.setAdapter(cervezaAdapterUser);
        dbFirebase.getDataCervezaUser(cervezaAdapterUser,arrayCervezaUser);

    }

    private void filterList(String text) {
        ArrayList<com.example.applicationrestaurant.Entities.Cervezas> filteredList = new ArrayList<>();
        for (com.example.applicationrestaurant.Entities.Cervezas cervezas :arrayCervezaUser){
            if(cervezas.getName().toLowerCase().contains(text.toLowerCase())){
                filteredList.add(cervezas);
            }
        }
        if(filteredList.isEmpty()){
            Toast.makeText(this, "No existe registro", Toast.LENGTH_SHORT).show();
        }else{
            cervezaAdapterUser.setFilteredList(filteredList);
        }
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