package com.example.applicationrestaurant.Views.ADMIN.Products;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.SearchView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.applicationrestaurant.Adapter.CoctelesAdapter;
import com.example.applicationrestaurant.DB.DBFirebase;
import com.example.applicationrestaurant.Entities.Cocteles;
import com.example.applicationrestaurant.R;
import com.example.applicationrestaurant.Servicios.CoctelesService;
import com.example.applicationrestaurant.Views.ADMIN.VISTAS.Crear_ProductCocteles;
import com.example.applicationrestaurant.Views.USER.Comida;
import com.example.applicationrestaurant.Views.USER.Register;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class ListCocteles extends AppCompatActivity {
    private ListView listViewCocteles;
    private ArrayList<Cocteles> arrayCocteles;
    private CoctelesAdapter coctelesAdapter;
    private SearchView searchViewBuscar;

    private CoctelesService coctelesService;
    private DBFirebase dbFirebase;
    private TextView btnAgregarListCocteles;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_cocteles);
        arrayCocteles = new ArrayList<>();
        listViewCocteles = (ListView) findViewById(R.id.listViewCocteles);
        btnAgregarListCocteles = (TextView) findViewById(R.id.btnAgregarListCocteles);
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
            coctelesService = new CoctelesService();
        }catch (Exception e){
            Log.e("DB", e.toString());
        }

        coctelesAdapter = new CoctelesAdapter(this, arrayCocteles);
        listViewCocteles.setAdapter(coctelesAdapter);
        dbFirebase.getDataCocteles(coctelesAdapter, arrayCocteles);

        btnAgregarListCocteles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Crear_ProductCocteles.class);
                startActivity(intent);
            }
        });
    }

    private void filterList(String text) {
        ArrayList<Cocteles> filteredList = new ArrayList<>();
        for (Cocteles cocteles : arrayCocteles){
            if(cocteles.getName().toLowerCase().contains(text.toLowerCase())){
                filteredList.add(cocteles);
            }
        }
        if(filteredList.isEmpty()){
            Toast.makeText(this,"No existe registro", Toast.LENGTH_SHORT).show();
        }else{
            coctelesAdapter.setFilteredListCoc(filteredList);
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