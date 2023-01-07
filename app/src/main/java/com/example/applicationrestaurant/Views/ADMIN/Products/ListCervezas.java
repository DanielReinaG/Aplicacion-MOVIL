package com.example.applicationrestaurant.Views.ADMIN.Products;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.applicationrestaurant.Adapter.CervezaAdapter;
import com.example.applicationrestaurant.DB.DBFirebase;
import com.example.applicationrestaurant.Entities.Cervezas;
import com.example.applicationrestaurant.R;
import com.example.applicationrestaurant.Servicios.CervezaService;
import com.example.applicationrestaurant.Views.ADMIN.VISTAS.CrearProductCerveza;
import com.example.applicationrestaurant.Views.USER.Comida;
import com.example.applicationrestaurant.Views.USER.Register;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class ListCervezas extends AppCompatActivity {
    private DBFirebase dbFirebase;
    private CervezaService cervezaService;
    private ListView listViewCervezas;
    private CervezaAdapter cervezaAdapter;
    private ArrayList<Cervezas> arrayCerveza;
    private TextView btnAgregarListCervezas;
    private BottomNavigationView bottom_navigation;
    private SearchView searchViewBuscar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_cervezas);
        arrayCerveza = new ArrayList<>();
        listViewCervezas = (ListView) findViewById(R.id.listViewCervezas);
        btnAgregarListCervezas = (TextView) findViewById(R.id.btnAgregarListCervezas);
        searchViewBuscar = (SearchView) findViewById(R.id.searchViewBuscar);
        bottom_navigation = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        bottom_navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

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
            cervezaService = new CervezaService();
        }catch (Exception e){
            Log.e("DB", e.toString());
        }

        cervezaAdapter = new CervezaAdapter(this, arrayCerveza);
        listViewCervezas.setAdapter(cervezaAdapter);
        dbFirebase.getDataCerveza(cervezaAdapter, arrayCerveza);

        btnAgregarListCervezas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), CrearProductCerveza.class);
                startActivity(intent);
            }
        });
    }

    private void filterList(String text) {
        ArrayList<Cervezas> filteredList = new ArrayList<>();
        for (Cervezas cervezas :arrayCerveza){
            if(cervezas.getName().toLowerCase().contains(text.toLowerCase())){
                filteredList.add(cervezas);
            }
        }
        if(filteredList.isEmpty()){
            Toast.makeText(this, "No existe registro", Toast.LENGTH_SHORT).show();
        }else{
            cervezaAdapter.setFilteredList(filteredList);
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
                            Intent intent5 = new Intent(getApplicationContext(), Comida.class);
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