package com.example.applicationrestaurant.Views.Products;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;

import com.example.applicationrestaurant.Adapter.ComidaAdapter;
import com.example.applicationrestaurant.DB.DBFirebase;
import com.example.applicationrestaurant.Entities.Comida;
import com.example.applicationrestaurant.R;
import com.example.applicationrestaurant.Servicios.ComidaService;
import com.example.applicationrestaurant.Views.Crear_Product;
import com.example.applicationrestaurant.Views.InicioMenu;
import com.example.applicationrestaurant.Views.Map;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class ListComida extends AppCompatActivity{
    private SearchView txtBuscar;
    private DBFirebase dbFirebase;
    private EditText searchViewBuscar;
    private ComidaService comidaService;
    private ListView listViewComida;
    private ComidaAdapter comidaAdapter;
    private ArrayAdapter<String> adapter;
    private ArrayList<Comida> arrayComida;
    private FloatingActionButton btnAgregarListComida;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_comida);
        arrayComida = new ArrayList<>();
        //Evento al dar clic al BOTON +
        btnAgregarListComida = (FloatingActionButton) findViewById(R.id.btnAgregarListComida);

        listViewComida = (ListView) findViewById(R.id.listViewComida);
        searchViewBuscar = (EditText) findViewById(R.id.searchViewBuscar);

        //adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, arrayComida);
        //listViewComida.setAdapter(adapter);

        //searchViewBuscar.addTextChangedListener(new TextWatcher() {

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



        //this.txtBuscar.setOnQueryTextListener(this);
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