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

import com.example.applicationrestaurant.Adapter.ComidaAdapter;
import com.example.applicationrestaurant.DB.DBFirebase;
import com.example.applicationrestaurant.Entities.Comida;
import com.example.applicationrestaurant.R;
import com.example.applicationrestaurant.Servicios.ComidaService;
import com.example.applicationrestaurant.Views.Crear_Product;
import com.example.applicationrestaurant.Views.Map;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class ListComida extends AppCompatActivity {
    private DBFirebase dbFirebase;
    private ComidaService comidaService;
    private ListView listViewComida;
    private ComidaAdapter comidaAdapter;
    private ArrayList<Comida> arrayComida;
    private FloatingActionButton btnAgregarListProducts;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_comida);
        arrayComida = new ArrayList<>();
        listViewComida = (ListView) findViewById(R.id.listViewComida);
        //Evento al dar clic al BOTON +
        btnAgregarListProducts = (FloatingActionButton) findViewById(R.id.btnAgregarListProducts);

        try{
            dbFirebase = new DBFirebase();
            comidaService = new ComidaService();
        }catch (Exception e){
            Log.e("DB", e.toString());
        }

        comidaAdapter = new ComidaAdapter(this, arrayComida);
        listViewComida.setAdapter(comidaAdapter);
        //MUESTRA LOS DATOS CON GETDATA(OBTENER INFORMACION) DE LA BD
        dbFirebase.getData(comidaAdapter, arrayComida);

        //AGREGA EL MENU CREADO

        btnAgregarListProducts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent inte = new Intent(getApplicationContext(), Crear_Product.class);
                startActivity(inte);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Aqui se enlaza el menu que se creo
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent intent;
        switch (item.getItemId()){
            case R.id.itemMap:
                 intent = new Intent(getApplicationContext(), Map.class);
                startActivity(intent);
            case R.id.itemComida:
                return true;
            case R.id.itemCoctel:
                return true;
            case R.id.itemCerveza:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}