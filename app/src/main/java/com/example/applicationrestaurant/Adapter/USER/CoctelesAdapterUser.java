package com.example.applicationrestaurant.Adapter.USER;

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
import com.example.applicationrestaurant.Entities.Cocteles;
import com.example.applicationrestaurant.R;
import com.example.applicationrestaurant.Views.ADMIN.Products.InfoCocteles;

import java.util.ArrayList;

public class CoctelesAdapterUser extends BaseAdapter {
    private Context context;
    private ArrayList<Cocteles> arrayCoctelesUser;

    public CoctelesAdapterUser(Context context, ArrayList<Cocteles> arrayCoctelesUser) {
        this.context = context;
        this.arrayCoctelesUser = arrayCoctelesUser;
    }

    public void setFilteredListCoc(ArrayList<Cocteles> filteredList){
        this.arrayCoctelesUser = filteredList;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return arrayCoctelesUser.size();
    }

    @Override
    public Object getItem(int i) {
        return arrayCoctelesUser.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater layoutInflater = LayoutInflater.from(this.context);
        view = layoutInflater.inflate(R.layout.product_template_user, null);

        Cocteles cocteles = arrayCoctelesUser.get(i);
        ImageView imgProductTemplate = (ImageView) view.findViewById(R.id.imgProductTemplate);
        TextView textNameProductTemplate = (TextView) view.findViewById(R.id.textNameProductTemplate);
        TextView textDescriptionProductTemplate = (TextView) view.findViewById(R.id.textDescriptionProductTemplate);
        TextView textPriceProductTemplate = (TextView) view.findViewById(R.id.textPriceProductTemplate);
        CardView cardView = (CardView) view.findViewById(R.id.cardView);

        textNameProductTemplate.setText(cocteles.getName());
        textDescriptionProductTemplate.setText(cocteles.getDescription());
        textPriceProductTemplate.setText(String.valueOf(cocteles.getPrice()));

        Glide.with(context)
                .load(cocteles.getImage())
                .override(500,500)
                .into(imgProductTemplate);

        cardView.startAnimation(AnimationUtils.loadAnimation(this.context,R.anim.anim_one));

        imgProductTemplate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context.getApplicationContext(), InfoCocteles.class);
                intent.putExtra("name", cocteles.getName());
                intent.putExtra("description", cocteles.getDescription());
                intent.putExtra("price", cocteles.getPrice());
                intent.putExtra("image", cocteles.getImage());
                intent.putExtra("id", cocteles.getId());
                context.startActivity(intent);
            }
        });


        return view;
    }
}
