package com.example.applicationrestaurant.Adapter.USER;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.applicationrestaurant.Entities.Cervezas;
import com.example.applicationrestaurant.R;

import java.util.ArrayList;

public class CervezaAdapterUser extends BaseAdapter {
    private Context context;
    private ArrayList<Cervezas> arrayCervezasUser;

    public CervezaAdapterUser(Context context, ArrayList<Cervezas> arrayCervezasUser) {
        this.context = context;
        this.arrayCervezasUser = arrayCervezasUser;
    }

    public void setFilteredList(ArrayList<Cervezas> filteredList) {
        this.arrayCervezasUser = filteredList;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return arrayCervezasUser.size();
    }

    @Override
    public Object getItem(int i) {
        return arrayCervezasUser.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater layoutInflater = LayoutInflater.from(this.context);
        view = layoutInflater.inflate(R.layout.products_cervezas_template_user,null);

        Cervezas cervezas = arrayCervezasUser.get(i);
        TextView textViewNameCerveza = (TextView) view.findViewById(R.id.textViewNameCerveza);
        TextView textViewContentAlcohol = (TextView) view.findViewById(R.id.textViewContentAlcohol);
        TextView textViewPrice = (TextView) view.findViewById(R.id.textViewPrice);

        textViewNameCerveza.setText(cervezas.getName());
        textViewContentAlcohol.setText(cervezas.getContentAlcohol());
        textViewPrice.setText(String.valueOf(cervezas.getPrice()));


        return view;
    }
}
