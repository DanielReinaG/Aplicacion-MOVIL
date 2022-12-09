package com.example.applicationrestaurant;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Info extends AppCompatActivity {
    private ImageView imgInfoProduct;
    private TextView textNameInfoProduct, textDescriptionInfoProduct, textPriceInfoProduct;
    private Button btnInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        imgInfoProduct = (ImageView) findViewById(R.id.imgInfoProduct);
        textNameInfoProduct = (TextView) findViewById(R.id.textNameInfoProduct);
        textDescriptionInfoProduct = (TextView) findViewById(R.id.textDescriptionInfoProduct);
        textPriceInfoProduct = (TextView) findViewById(R.id.textPriceInfoProduct);
        btnInfo = (Button) findViewById(R.id.btnInfo);

        Intent intent = getIntent();
        textNameInfoProduct.setText(intent.getStringExtra("name"));
        textDescriptionInfoProduct.setText(intent.getStringExtra("description"));
        textPriceInfoProduct.setText(String.valueOf(intent.getIntExtra("price",0)));
        imgInfoProduct.setImageResource(intent.getIntExtra("image", 0));

        btnInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),ListProducts.class);
                startActivity(intent);
            }
        });

    }
}