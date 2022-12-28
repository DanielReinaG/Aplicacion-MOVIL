package com.example.applicationrestaurant.Views.Products;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;


import com.example.applicationrestaurant.Adapter.ComidaAdapter;
import com.example.applicationrestaurant.DB.DBFirebase;
import com.example.applicationrestaurant.Entities.Comida;
import com.example.applicationrestaurant.R;
import com.example.applicationrestaurant.Servicios.ComidaService;
import com.example.applicationrestaurant.Views.Crear_Product;
import com.example.applicationrestaurant.Views.InicioMenu;

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


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Aqui se enlaza el menu que se creo
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.Menu:
                Intent intent2 = new Intent(getApplicationContext(), InicioMenu.class);
                startActivity(intent2);

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}