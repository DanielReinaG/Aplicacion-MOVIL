package com.example.applicationrestaurant.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.applicationrestaurant.DB.DBFirebase;
import com.example.applicationrestaurant.Entities.Cervezas;
import com.example.applicationrestaurant.R;
import com.example.applicationrestaurant.Views.ADMIN.VISTAS.CrearProductCerveza;
import com.example.applicationrestaurant.Views.ADMIN.Products.ListCervezas;

import java.util.ArrayList;

public class CervezaAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Cervezas> arrayCervezas;


    public void setFilteredList(ArrayList<Cervezas> filteredList) {
        this.arrayCervezas = filteredList;
        notifyDataSetChanged();
    }

    public CervezaAdapter(Context context, ArrayList<Cervezas> arrayCervezas) {
        this.context = context;
        this.arrayCervezas = arrayCervezas;
    }

    @Override
    public int getCount() {
        return arrayCervezas.size();
    }

    @Override
    public Object getItem(int i) {
        return arrayCervezas.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater layoutInflater = LayoutInflater.from(this.context);
        view = layoutInflater.inflate(R.layout.products_cervezas_template,null);

        Cervezas cervezas = arrayCervezas.get(i);
        TextView textViewNameCerveza = (TextView) view.findViewById(R.id.textViewNameCerveza);
        TextView textViewContentAlcohol = (TextView) view.findViewById(R.id.textViewContentAlcohol);
        TextView textViewPrice = (TextView) view.findViewById(R.id.textViewPrice);
        TextView buttonEliminarCerveza_template = (TextView) view.findViewById(R.id.buttonEliminarCerveza_template);
        TextView buttonActualizarCerveza_template = (TextView) view.findViewById(R.id.buttonActualizarCerveza_template);

        textViewNameCerveza.setText(cervezas.getName());
        textViewContentAlcohol.setText(cervezas.getContentAlcohol());
        textViewPrice.setText(String.valueOf(cervezas.getPrice()));


        buttonEliminarCerveza_template.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBFirebase dbFirebase = new DBFirebase();
                dbFirebase.deleteDataCerveza(cervezas.getId());
                Intent intent = new Intent(context.getApplicationContext(), ListCervezas.class);
                context.startActivity(intent);
            }
        });

        buttonActualizarCerveza_template.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context.getApplicationContext(), CrearProductCerveza.class);
                intent.putExtra("edit", true);
                intent.putExtra("id",cervezas.getId());
                intent.putExtra("name", cervezas.getName());
                intent.putExtra("contentAlcohol", cervezas.getContentAlcohol());
                intent.putExtra("price", cervezas.getPrice());

                context.startActivity(intent);
            }
        });
        return view;
    }

}
