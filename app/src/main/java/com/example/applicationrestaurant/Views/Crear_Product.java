package com.example.applicationrestaurant.Views;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.applicationrestaurant.DB.DBFirebase;
import com.example.applicationrestaurant.Entities.Comida;
import com.example.applicationrestaurant.R;
import com.example.applicationrestaurant.Servicios.ComidaService;
import com.example.applicationrestaurant.Views.Products.ListComida;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import org.osmdroid.views.MapView;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class Crear_Product extends AppCompatActivity {
    //BUTTON YA ESTA
    private EditText editNameFormCreate, editDescriptFormCreate,editPriceFormCreate;
    private TextView textNameFormCreate,textDescriptFormCreate,textPriceFormCreate,textFormCreate,textFormCancel;
    private ImageView imgFormCreate;
    private DBFirebase dbFirebase;
    private ActivityResultLauncher<String> content;
    private ComidaService comidaService;
    private MapView mapView;
    private String urlImage;
    private StorageReference storageReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_product);

        //LLAMAR BOTONES
        urlImage = "";
        textFormCreate = (TextView) findViewById(R.id.textFormCreate);
        textFormCancel = (TextView) findViewById(R.id.textFormCancel);
        textNameFormCreate = (TextView) findViewById(R.id.textNameFormCreate);
        textDescriptFormCreate = (TextView) findViewById(R.id.textDescriptFormCreate);
        textPriceFormCreate = (TextView) findViewById(R.id.textPriceFormCreate);
        editNameFormCreate = (EditText) findViewById(R.id.editNameFormCreate);
        editDescriptFormCreate = (EditText) findViewById(R.id.editDescriptFormCreate);
        editPriceFormCreate = (EditText) findViewById(R.id.editPriceFormCreate);
        imgFormCreate = (ImageView) findViewById(R.id.imgFormCreate);

        //EVENTO DE SELECCIONAR IMAGEN DE LA CARPETA DE ARCHIVOS
        try {
            dbFirebase = new DBFirebase();
            comidaService = new ComidaService();
            storageReference = FirebaseStorage.getInstance().getReference();
            content = registerForActivityResult(
                    new ActivityResultContracts.GetContent(),
                    new ActivityResultCallback<Uri>() {
                        //GUARDA LAS IMAGENES EN EL FORMULARIO EN LA BD
                        @Override
                        public void onActivityResult(Uri result) {
                            Uri uri = result;
                            StorageReference filePath = storageReference.child("images").child(uri.getLastPathSegment());
                            filePath.putFile(uri)
                                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                        @Override
                                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                            Toast.makeText(getApplicationContext(),"Imagen cargada", Toast.LENGTH_SHORT).show();
                                            filePath.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                                @Override
                                                public void onSuccess(Uri uri) {
                                                    Uri dowloadUrl = uri;
                                                    urlImage = dowloadUrl.toString();
                                                    Glide.with(Crear_Product.this).load(dowloadUrl)
                                                            .override(500,500)
                                                            .into(imgFormCreate);
                                                }
                                            });
                                        }
                                    });
                        }
                    });
        } catch (Exception e) {
            Log.e("DB", e.toString());
        }

        Intent intentIN = getIntent();
        if(intentIN.getBooleanExtra("edit", false)){
            editNameFormCreate.setText(intentIN.getStringExtra("name"));
            editDescriptFormCreate.setText(intentIN.getStringExtra("description"));
            editPriceFormCreate.setText(String.valueOf(intentIN.getIntExtra("price", 0)));

            Glide.with(this)
                    .load(intentIN.getStringExtra("image"))
                    .override(500, 500)
                    .into(imgFormCreate);
        }

        //btnForm AL DAR AL BOTONTEXT CREA UN NUEVO PRODUCTO EN LA TABLA FOOD
        textFormCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Comida comida = new Comida(
                        editNameFormCreate.getText().toString(),
                        editDescriptFormCreate.getText().toString(),
                        Integer.parseInt(editPriceFormCreate.getText().toString()),
                        urlImage
                );
                if(intentIN.getBooleanExtra("edit", false)) {
                    String id = intentIN.getStringExtra("id");
                    //comida.setId(intentIN.getStringExtra("id"));
                    comida.setId(id);
                    dbFirebase.updateData(comida);
                }else{
                    dbFirebase.insertData(comida);
                }

                //AQUI SE REFIERE AL METODO INSERTDATA QUE ES CREAR EL PRODUCTO EN LA BD
                //dbFirebase.insertData(comida);
                //CUANDO CREA EL PRODUCTO LO DEVUELVE A LA PAGINA INTERIR
                Intent intent = new Intent(getApplicationContext(), ListComida.class);
                startActivity(intent);
            }
        });

        textFormCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ListComida.class);
                startActivity(intent);
            }
        });

        imgFormCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                content.launch("image/*");
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
        public void clean(){
            editNameFormCreate.setText("");
            editDescriptFormCreate.setText("");
            editPriceFormCreate.setText("");
            imgFormCreate.setImageResource(R.drawable.ic_launcher_background);
        }

    }