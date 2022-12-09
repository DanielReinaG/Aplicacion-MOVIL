package com.example.applicationrestaurant;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.example.applicationrestaurant.Adapter.ProductAdapter;
import com.example.applicationrestaurant.Entities.Product;

import java.util.ArrayList;

public class ListProducts extends AppCompatActivity {
    private ListView listViewProducts;
    private ProductAdapter productAdapter;
    private ArrayList<Product> arrayProducts;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_products);
        arrayProducts = new ArrayList<>();
        Product product1 = new Product("Hamburguesa Llanera", "200gr de carne",15000,R.drawable.hamb2);
        Product product2 = new Product("Hamburguesa Llanera", "200gr de carne",15000,R.drawable.hamb2);
        Product product3 = new Product("Hamburguesa Llanera", "200gr de carne",15000,R.drawable.hamb2);
        Product product4 = new Product("Hamburguesa Llanera", "200gr de carne",15000,R.drawable.hamb2);
        Product product5 = new Product("Hamburguesa Llanera", "200gr de carne",15000,R.drawable.hamb2);
        Product product6 = new Product("Hamburguesa Llanera", "200gr de carne",15000,R.drawable.hamb2);

        arrayProducts.add(product1);
        arrayProducts.add(product2);
        arrayProducts.add(product3);
        arrayProducts.add(product4);
        arrayProducts.add(product5);
        arrayProducts.add(product6);


        listViewProducts = (ListView) findViewById(R.id.listViewProducts);
        productAdapter = new ProductAdapter(this, arrayProducts);
        listViewProducts.setAdapter(productAdapter);
    }
}