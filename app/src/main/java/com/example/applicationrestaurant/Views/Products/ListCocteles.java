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

import com.example.applicationrestaurant.Adapter.CoctelesAdapter;
import com.example.applicationrestaurant.DB.DBFirebase;
import com.example.applicationrestaurant.Entities.Cocteles;
import com.example.applicationrestaurant.R;
import com.example.applicationrestaurant.Servicios.CoctelesService;
import com.example.applicationrestaurant.Servicios.ComidaService;
import com.example.applicationrestaurant.Views.Crear_Product;
import com.example.applicationrestaurant.Views.Crear_ProductCocteles;
import com.example.applicationrestaurant.Views.InicioMenu;
import com.example.applicationrestaurant.Views.Map;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class ListCocteles extends AppCompatActivity {
    private DBFirebase dbFirebase;
    private CoctelesAdapter coctelesAdapter;
    private ListView listViewCocteles;
    private CoctelesService coctelesService;
    private ArrayList<Cocteles> arrayCocteles;
    private FloatingActionButton btnAgregarListCocteles;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_cocteles);
        arrayCocteles = new ArrayList<>();
        listViewCocteles = (ListView) findViewById(R.id.listViewCocteles);
        btnAgregarListCocteles = (FloatingActionButton) findViewById(R.id.btnAgregarListCocteles);

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

            case R.id.Menu:
                intent = new Intent(getApplicationContext(), InicioMenu.class);
                startActivity(intent);
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}