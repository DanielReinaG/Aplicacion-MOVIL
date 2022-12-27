package com.example.applicationrestaurant.Views.Products;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.applicationrestaurant.R;
import com.example.applicationrestaurant.Servicios.ComidaService;

import de.hdodenhof.circleimageview.CircleImageView;

public class InfoComida extends AppCompatActivity {
    private ComidaService comidaService;
    private LinearLayout LinearInfo;
    private CircleImageView imgInfoProduct;
    private TextView textNameInfoProduct, textDescriptionInfoProduct, textPriceInfoProduct;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_comida);

        LinearInfo = (LinearLayout) findViewById(R.id.LinearInfo);
        imgInfoProduct = (CircleImageView) findViewById(R.id.imgInfoProduct);
        textNameInfoProduct = (TextView) findViewById(R.id.textNameInfoProduct);
        textDescriptionInfoProduct = (TextView) findViewById(R.id.textDescriptionInfoProduct);
        textPriceInfoProduct = (TextView) findViewById(R.id.textPriceInfoProduct);


        //ENVIA LA INFORMACION Y DEBE RECIBIRSE EN EL ADAPTER
        Intent intent = getIntent();
        textNameInfoProduct.setText(intent.getStringExtra("name"));
        textDescriptionInfoProduct.setText(intent.getStringExtra("description"));
        textPriceInfoProduct.setText(String.valueOf(intent.getIntExtra("price",0)));

        Glide.with(this)
                .load(intent.getStringExtra("image"))
                .override(500, 500)
                .into(imgInfoProduct);

        LinearInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ListComida.class);
                startActivity(intent);
            }
        });

    }
}