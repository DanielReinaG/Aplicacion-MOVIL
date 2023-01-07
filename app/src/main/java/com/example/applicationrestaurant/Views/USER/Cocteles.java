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

import com.example.applicationrestaurant.Adapter.USER.CoctelesAdapterUser;
import com.example.applicationrestaurant.DB.DBFirebase;
import com.example.applicationrestaurant.R;
import com.example.applicationrestaurant.Servicios.CoctelesService;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class Cocteles extends AppCompatActivity {
    private ListView listViewCocteles;
    private ArrayList<com.example.applicationrestaurant.Entities.Cocteles> arrayCoctelesUser;
    private CoctelesAdapterUser coctelesAdapterUser;
    private SearchView searchViewBuscar;
    private CoctelesService coctelesService;
    private DBFirebase dbFirebase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cocteles);

        arrayCoctelesUser = new ArrayList<>();
        listViewCocteles = (ListView) findViewById(R.id.listViewCocteles);
        searchViewBuscar = (SearchView) findViewById(R.id.searchViewBuscar);
        BottomNavigationView navigation = findViewById(R.id.bottom_navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);


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

        try{
            dbFirebase = new DBFirebase();
            coctelesService = new CoctelesService();
        }catch (Exception e){
            Log.e("DB", e.toString());
        }

        coctelesAdapterUser = new CoctelesAdapterUser(this, arrayCoctelesUser);
        listViewCocteles.setAdapter(coctelesAdapterUser);
        dbFirebase.getDataCoctelesUser(coctelesAdapterUser, arrayCoctelesUser);

    }

    private void filterList(String text) {
        ArrayList<com.example.applicationrestaurant.Entities.Cocteles> filteredList = new ArrayList<>();
        for (com.example.applicationrestaurant.Entities.Cocteles cocteles : arrayCoctelesUser){
            if(cocteles.getName().toLowerCase().contains(text.toLowerCase())){
                filteredList.add(cocteles);
            }
        }
        if(filteredList.isEmpty()){
            Toast.makeText(this,"No existe registro", Toast.LENGTH_SHORT).show();
        }else{
            coctelesAdapterUser.setFilteredListCoc(filteredList);
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