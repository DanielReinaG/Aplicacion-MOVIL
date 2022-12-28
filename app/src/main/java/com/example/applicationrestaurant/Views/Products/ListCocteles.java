package com.example.applicationrestaurant.Views.Products;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.SearchView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.example.applicationrestaurant.Adapter.CoctelesAdapter;
import com.example.applicationrestaurant.DB.DBFirebase;
import com.example.applicationrestaurant.Entities.Cocteles;
import com.example.applicationrestaurant.R;
import com.example.applicationrestaurant.Servicios.CoctelesService;
import com.example.applicationrestaurant.Views.Crear_ProductCocteles;
import com.example.applicationrestaurant.Views.InicioMenu;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class ListCocteles extends AppCompatActivity {

    private ListView listViewCocteles;
    private ArrayList<Cocteles> arrayCocteles;
    private CoctelesAdapter coctelesAdapter;
    private SearchView searchViewBuscar;

    private CoctelesService coctelesService;
    private DBFirebase dbFirebase;
    private FloatingActionButton btnAgregarListCocteles;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_cocteles);
        arrayCocteles = new ArrayList<>();
        listViewCocteles = (ListView) findViewById(R.id.listViewCocteles);
        btnAgregarListCocteles = (FloatingActionButton) findViewById(R.id.btnAgregarListCocteles);
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