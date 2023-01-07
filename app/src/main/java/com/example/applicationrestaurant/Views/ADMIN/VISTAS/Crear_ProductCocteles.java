package com.example.applicationrestaurant.Views.ADMIN.VISTAS;

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

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.applicationrestaurant.DB.DBFirebase;
import com.example.applicationrestaurant.Entities.Cocteles;
import com.example.applicationrestaurant.R;
import com.example.applicationrestaurant.Servicios.CoctelesService;
import com.example.applicationrestaurant.Views.ADMIN.Products.ListCocteles;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class Crear_ProductCocteles extends AppCompatActivity {
    private EditText editNameFormCreate, editDescriptFormCreate,editPriceFormCreate;
    private TextView textNameFormCreate,textDescriptFormCreate,textPriceFormCreate,textFormCreate,textFormCancel;
    private ImageView imgFormCreate;
    private DBFirebase dbFirebase;
    private ActivityResultLauncher<String> content;
    private CoctelesService coctelesService;
    private String urlImage;
    private StorageReference storageReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_product_cocteles);

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


        try {
            dbFirebase = new DBFirebase();
            coctelesService = new CoctelesService();
            storageReference = FirebaseStorage.getInstance().getReference();
            content = registerForActivityResult(
                    new ActivityResultContracts.GetContent(),
                    new ActivityResultCallback<Uri>() {
                        //GUARDA LAS IMAGENES EN EL FORMULARIO EN LA BD
                        @Override
                        public void onActivityResult(Uri result) {
                            Uri uri = result;
                            StorageReference filePath = storageReference.child("imagesCocteles").child(uri.getLastPathSegment());
                            filePath.putFile(uri)
                                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                        @Override
                                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                            Toast.makeText(getApplicationContext(), "Imagen cargada", Toast.LENGTH_SHORT).show();
                                            filePath.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                                @Override
                                                public void onSuccess(Uri uri) {
                                                    Uri dowloadUrl = uri;
                                                    urlImage = dowloadUrl.toString();
                                                    Glide.with(Crear_ProductCocteles.this).load(dowloadUrl)
                                                            .override(500, 500)
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
        if(intentIN.getBooleanExtra("editC", false)){
            editNameFormCreate.setText(intentIN.getStringExtra("name"));
            editDescriptFormCreate.setText(intentIN.getStringExtra("description"));
            editPriceFormCreate.setText(String.valueOf(intentIN.getIntExtra("price", 0)));

            Glide.with(this)
                    .load(intentIN.getStringExtra("image"))
                    .override(500, 500)
                    .into(imgFormCreate);
        }

        textFormCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cocteles cocteles = new Cocteles(
                        //OBTIENE LO ESCRITO EN LOS EDITS Y LOS CONVIERTE A STRING
                        editNameFormCreate.getText().toString(),
                        editDescriptFormCreate.getText().toString(),
                        Integer.parseInt(editPriceFormCreate.getText().toString()),
                        urlImage
                );
                //SI VIENE POR EDIT ACTUALIZA EL PRODUCTO
                if(intentIN.getBooleanExtra("editC", false)) {
                    String id = intentIN.getStringExtra("id");
                    String image = intentIN.getStringExtra("image");
                    cocteles.setImage(image);
                    cocteles.setId(id);
                    dbFirebase.updateDataCocteles(cocteles);
                    //SINO, CREA EL PRODUCTO EN LA BD

                }else{
                    dbFirebase.insertDataCocteles(cocteles);
                }

                //CUANDO CREA EL PRODUCTO LO DEVUELVE A LA PAGINA INTERIR
                Intent intent = new Intent(getApplicationContext(), ListCocteles.class);
                startActivity(intent);
            }
        });

        textFormCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ListCocteles.class);
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
