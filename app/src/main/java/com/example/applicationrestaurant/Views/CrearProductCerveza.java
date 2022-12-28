package com.example.applicationrestaurant.Views;

import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.applicationrestaurant.DB.DBFirebase;
import com.example.applicationrestaurant.Entities.Cervezas;
import com.example.applicationrestaurant.R;
import com.example.applicationrestaurant.Servicios.CervezaService;
import com.example.applicationrestaurant.Views.Products.ListCervezas;

public class CrearProductCerveza extends AppCompatActivity {
    private EditText editNameFormCreateCerveza,editContentAlcoholFormCreateCerveza,editPriceFormCreateCerveza;
    private TextView textNameFormCreateCerveza,textContentAlcoholFormCreateCerveza,textPriceFormCreateCerveza,textFormCreateCerveza,textFormCancelCerveza;
    private DBFirebase dbFirebase;
    private ActivityResultLauncher<String> content;
    private CervezaService cervezaService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_product_cerveza);

        textNameFormCreateCerveza = (TextView) findViewById(R.id.textNameFormCreateCerveza);
        textContentAlcoholFormCreateCerveza = (TextView) findViewById(R.id.textContentAlcoholFormCreateCerveza);
        textPriceFormCreateCerveza = (TextView) findViewById(R.id.textPriceFormCreateCerveza);
        textFormCreateCerveza = (TextView) findViewById(R.id.textFormCreateCerveza);
        textFormCancelCerveza = (TextView) findViewById(R.id.textFormCancelCerveza);

        editNameFormCreateCerveza = (EditText) findViewById(R.id.editNameFormCreateCerveza);
        editContentAlcoholFormCreateCerveza = (EditText) findViewById(R.id.editContentAlcoholFormCreateCerveza);
        editPriceFormCreateCerveza = (EditText) findViewById(R.id.editPriceFormCreateCerveza);

        try{
            dbFirebase = new DBFirebase();
            cervezaService = new CervezaService();
        }catch (Exception e) {
            Log.e("DB", e.toString());
        }


        Intent intentIN = getIntent();
        if(intentIN.getBooleanExtra("edit", false)){
            editNameFormCreateCerveza.setText(intentIN.getStringExtra("name"));
            editContentAlcoholFormCreateCerveza.setText(intentIN.getStringExtra("contentAlcohol"));
            editPriceFormCreateCerveza.setText(String.valueOf(intentIN.getIntExtra("price", 0)));
        }

        textFormCreateCerveza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cervezas cervezas = new Cervezas(
                        editNameFormCreateCerveza.getText().toString(),
                        editContentAlcoholFormCreateCerveza.getText().toString(),
                        Integer.parseInt(editPriceFormCreateCerveza.getText().toString())
                );
                if(intentIN.getBooleanExtra("edit", false)){
                    String id = intentIN.getStringExtra("id");
                    cervezas.setId(id);
                    dbFirebase.updateDataCerveza(cervezas);
                }else{
                    dbFirebase.insertDataCervezas(cervezas);
                }
                Intent intent = new Intent(getApplicationContext(), ListCervezas.class);
                startActivity(intent);
            }
        });

        textFormCancelCerveza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ListCervezas.class);
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
    public void clean(){
        editNameFormCreateCerveza.setText("");
        editContentAlcoholFormCreateCerveza.setText("");
        editPriceFormCreateCerveza.setText("");
    }

}