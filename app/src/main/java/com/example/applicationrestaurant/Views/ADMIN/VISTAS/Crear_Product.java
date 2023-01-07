package com.example.applicationrestaurant.Views.ADMIN.VISTAS;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.applicationrestaurant.DB.DBFirebase;
import com.example.applicationrestaurant.Entities.Comida;
import com.example.applicationrestaurant.R;
import com.example.applicationrestaurant.Servicios.ComidaService;
import com.example.applicationrestaurant.Views.ADMIN.Products.ListComida;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import org.osmdroid.views.MapView;

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
        //AQUI GUARDA LA IMAGEN CARGADA AL STORAGE DE FIREBASE
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
            }catch (Exception e) {
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

        //AL OPRIMIR EL BOTON TEXTFORMCREATE CREA UN NUEVO PRODUCTO EN LA TABLA FOOD
        //AQUI CREA EL PRODUCTO
        textFormCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Comida comida = new Comida(
                        //OBTIENE LO ESCRITO EN LOS EDITS Y LOS CONVIERTE A STRING
                        editNameFormCreate.getText().toString(),
                        editDescriptFormCreate.getText().toString(),
                        Integer.parseInt(editPriceFormCreate.getText().toString()),
                        urlImage
                );
                    //SI VIENE POR EDIT ACTUALIZA EL PRODUCTO
                    if(intentIN.getBooleanExtra("edit", false)) {
                        String image = intentIN.getStringExtra("image");
                        String id = intentIN.getStringExtra("id");
                        comida.setId(id);
                        comida.setImage(image);
                        dbFirebase.updateDataComida(comida);
                        //SINO, CREA EL PRODUCTO EN LA BD
                    }else{
                        dbFirebase.insertDataComida(comida);
                    }
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

        public void clean(){
            editNameFormCreate.setText("");
            editDescriptFormCreate.setText("");
            editPriceFormCreate.setText("");
            imgFormCreate.setImageResource(R.drawable.ic_launcher_background);
        }

    }