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

import com.example.applicationrestaurant.Adapter.CervezaAdapter;
import com.example.applicationrestaurant.DB.DBFirebase;
import com.example.applicationrestaurant.Entities.Cervezas;
import com.example.applicationrestaurant.R;
import com.example.applicationrestaurant.Servicios.CervezaService;
import com.example.applicationrestaurant.Servicios.ComidaService;
import com.example.applicationrestaurant.Views.CrearProductCerveza;
import com.example.applicationrestaurant.Views.InicioMenu;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class ListCervezas extends AppCompatActivity {
    private DBFirebase dbFirebase;
    private CervezaService cervezaService;
    private ListView listViewCervezas;
    private CervezaAdapter cervezaAdapter;
    private ArrayList<Cervezas> arrayCerveza;
    private FloatingActionButton btnAgregarListCervezas;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_cervezas);
        arrayCerveza = new ArrayList<>();
        listViewCervezas = (ListView) findViewById(R.id.listViewCervezas);
        btnAgregarListCervezas = (FloatingActionButton) findViewById(R.id.btnAgregarListCervezas);

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