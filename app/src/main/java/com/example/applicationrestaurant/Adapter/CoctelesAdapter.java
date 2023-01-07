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
import android.widget.TextView;

import androidx.cardview.widget.CardView;

import com.bumptech.glide.Glide;
import com.example.applicationrestaurant.DB.DBFirebase;
import com.example.applicationrestaurant.Entities.Cocteles;
import com.example.applicationrestaurant.R;
import com.example.applicationrestaurant.Views.ADMIN.VISTAS.Crear_ProductCocteles;
import com.example.applicationrestaurant.Views.ADMIN.Products.InfoCocteles;
import com.example.applicationrestaurant.Views.ADMIN.Products.ListCocteles;

import java.util.ArrayList;

public class CoctelesAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Cocteles>  arrayCocteles;

    public void setFilteredListCoc(ArrayList<Cocteles> filteredList){
        this.arrayCocteles = filteredList;
        notifyDataSetChanged();
    }

    public CoctelesAdapter(Context context, ArrayList<Cocteles> arrayCocteles) {
        this.context = context;
        this.arrayCocteles = arrayCocteles;
    }

    @Override
    public int getCount() {
        return arrayCocteles.size();
    }

    @Override
    public Object getItem(int i) {
        return arrayCocteles.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        //ES PARA VINCULAR EL LAYOUT CON ESTA VISTA CON (FROM(THIS.CONTEXT))
        LayoutInflater layoutInflater = LayoutInflater.from(this.context);
        view = layoutInflater.inflate(R.layout.products_template, null);

        Cocteles cocteles = arrayCocteles.get(i);

        //CAJAS DEL LAYOUT
        ImageView imgProductTemplate = (ImageView) view.findViewById(R.id.imgProductTemplate);
        TextView textNameProductTemplate = (TextView) view.findViewById(R.id.textNameProductTemplate);
        TextView textDescriptionProductTemplate = (TextView) view.findViewById(R.id.textDescriptionProductTemplate);
        TextView textPriceProductTemplate = (TextView) view.findViewById(R.id.textPriceProductTemplate);
        Button buttonEliminarProduct_template = (Button) view.findViewById(R.id.buttonEliminarProduct_template);
        Button buttonActualizarProduct_template = (Button) view.findViewById(R.id.buttonActualizarProduct_template);
        CardView cardView = (CardView) view.findViewById(R.id.cardView);

        //AQUI SE TRAE LA INFORMACION DESDE LA BASE DE DATOS A LAS CAJITAS
        textNameProductTemplate.setText(cocteles.getName());
        textDescriptionProductTemplate.setText(cocteles.getDescription());
        textPriceProductTemplate.setText(String.valueOf(cocteles.getPrice()));

        //CON GLIDE SE TRAE LA IMAGEN DESDE EL STORAGE AL LAYOUT, ESPECIALMENTE
        //A LA CAJITA DE LA IMAGEN
        Glide.with(context)
                .load(cocteles.getImage())
                .override(500,500)
                .into(imgProductTemplate);

        //ANIMACION DE LOS CARD VIEW
        cardView.startAnimation(AnimationUtils.loadAnimation(this.context,R.anim.anim_one));

        //EVENTO AL PRESIONAR EL BOTON ELIMINAR
        buttonEliminarProduct_template.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBFirebase dbFirebase = new DBFirebase();
                dbFirebase.deleteDataCocteles(cocteles.getId());
                Intent intent = new Intent(context.getApplicationContext(), ListCocteles.class);
                context.startActivity(intent);
            }
        });

        //EVENTO AL PRESIONAR EL BOTON ACTUALIZAR
        buttonActualizarProduct_template.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context.getApplicationContext(), Crear_ProductCocteles.class);
                //LOS PUTEXTRA PERMITEN ENVIAR LA INFORMACION DE LA CAJA QUE CONTIENE LA INFORMACION AL OTRO LAYOUT PARA SCTUALIZARLO
                intent.putExtra("editC", true);
                intent.putExtra("id", cocteles.getId());
                intent.putExtra("name", cocteles.getName());
                intent.putExtra("description", cocteles.getDescription());
                intent.putExtra("price", cocteles.getPrice());
                intent.putExtra("image", cocteles.getImage());
                context.startActivity(intent);

            }
        });

        return view;
    }
}
