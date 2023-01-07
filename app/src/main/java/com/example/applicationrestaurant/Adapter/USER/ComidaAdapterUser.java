package com.example.applicationrestaurant.Adapter.USER;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;

import com.bumptech.glide.Glide;
import com.example.applicationrestaurant.Entities.Comida;
import com.example.applicationrestaurant.R;
import com.example.applicationrestaurant.Views.ADMIN.Products.InfoComida;

import java.util.ArrayList;

public class ComidaAdapterUser extends BaseAdapter {
    private Context context;
    private ArrayList<com.example.applicationrestaurant.Entities.Comida> arrayComidaUser;

    public void setFilteredList(ArrayList<Comida> filteredList) {
        this.arrayComidaUser = filteredList;
        notifyDataSetChanged();
    }

    public ComidaAdapterUser(Context context, ArrayList<Comida> arrayComidaUser) {
        this.context = context;
        this.arrayComidaUser = arrayComidaUser;
    }

    @Override
    public int getCount() {
        return arrayComidaUser.size();
    }

    @Override
    public Object getItem(int i) {
        return arrayComidaUser.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater layoutInflater = LayoutInflater.from(this.context);
        view = layoutInflater.inflate(R.layout.product_template_user,null);

        Comida comida = arrayComidaUser.get(i);
        ImageView imgProductTemplate = (ImageView) view.findViewById(R.id.imgProductTemplate);
        TextView textNameProductTemplate = (TextView) view.findViewById(R.id.textNameProductTemplate);
        TextView textDescriptionProductTemplate = (TextView) view.findViewById(R.id.textDescriptionProductTemplate);
        TextView textPriceProductTemplate = (TextView) view.findViewById(R.id.textPriceProductTemplate);
        CardView cardView = (CardView) view.findViewById(R.id.cardView);

        textNameProductTemplate.setText(comida.getName());
        textDescriptionProductTemplate.setText(comida.getDescription());
        textPriceProductTemplate.setText(String.valueOf(comida.getPrice()));

        Glide.with(context)
                .load(comida.getImage())
                .override(500, 500)
                .into(imgProductTemplate);

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

        return view;
    }
}
