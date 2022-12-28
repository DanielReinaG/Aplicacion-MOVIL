package com.example.applicationrestaurant.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;

import com.bumptech.glide.Glide;
import com.example.applicationrestaurant.DB.DBFirebase;
import com.example.applicationrestaurant.Entities.Comida;
import com.example.applicationrestaurant.Views.Crear_Product;
import com.example.applicationrestaurant.Views.Products.InfoComida;
import com.example.applicationrestaurant.R;
import com.example.applicationrestaurant.Views.Products.ListComida;

import java.util.ArrayList;

public class ComidaAdapter extends BaseAdapter{
    private Context context;
    private ArrayList<Comida> arrayComida;

    public void setFilteredList(ArrayList<Comida> filteredList){
        this.arrayComida = filteredList;
        notifyDataSetChanged();
    }

    public ComidaAdapter(Context context, ArrayList<Comida> arrayComida) {
        this.context = context;
        this.arrayComida = arrayComida;
    }

    @Override
    public int getCount() {
        return arrayComida.size();
    }

    @Override
    public Object getItem(int i) {
        return arrayComida.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater layoutInflater = LayoutInflater.from(this.context);
        view = layoutInflater.inflate(R.layout.products_template,null);

        Comida comida = arrayComida.get(i);
        ImageView imgProductTemplate = (ImageView) view.findViewById(R.id.imgProductTemplate);
        TextView textNameProductTemplate = (TextView) view.findViewById(R.id.textNameProductTemplate);
        TextView textDescriptionProductTemplate = (TextView) view.findViewById(R.id.textDescriptionProductTemplate);
        TextView textPriceProductTemplate = (TextView) view.findViewById(R.id.textPriceProductTemplate);
        Button buttonEliminarProduct_template = (Button)view.findViewById(R.id.buttonEliminarProduct_template);
        Button buttonActualizarProduct_template = (Button)view.findViewById(R.id.buttonActualizarProduct_template);
        CardView cardView = (CardView) view.findViewById(R.id.cardView);


        textNameProductTemplate.setText(comida.getName());
        textDescriptionProductTemplate.setText(comida.getDescription());
        textPriceProductTemplate.setText(String.valueOf(comida.getPrice()));

        Glide.with(context)
                .load(comida.getImage())
                .override(500, 500)
                .into(imgProductTemplate);

        //ANIMACION DE LOS CARD VIEW
        cardView.startAnimation(AnimationUtils.loadAnimation(this.context,R.anim.anim_one));

        imgProductTemplate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context.getApplicationContext(), InfoComida.class);
                intent.putExtra("name", comida.getName());
                intent.putExtra("description", comida.getDescription());
                intent.putExtra("price", comida.getPrice());
                intent.putExtra("image", comida.getImage());
                intent.putExtra("id",comida.getId());
                context.startActivity(intent);
            }
        });

        buttonEliminarProduct_template.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBFirebase dbFirebase = new DBFirebase();
                dbFirebase.deleteDataComida(comida.getId());
                Intent intent = new Intent(context.getApplicationContext(), ListComida.class);
                context.startActivity(intent);

            }
        });

        //ACTUALIZAR PRODUCTO SELECCIONADO EN EL PRODUCT_TEMPLATE
        buttonActualizarProduct_template.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context.getApplicationContext(), Crear_Product.class);
                intent.putExtra("edit", true);
                intent.putExtra("id",comida.getId());
                intent.putExtra("name", comida.getName());
                intent.putExtra("description", comida.getDescription());
                intent.putExtra("price", comida.getPrice());
                intent.putExtra("image", comida.getImage());

                context.startActivity(intent);
            }
        });
        return view;
    }
}
