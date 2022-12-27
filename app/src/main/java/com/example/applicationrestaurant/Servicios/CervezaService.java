package com.example.applicationrestaurant.Servicios;

import android.database.Cursor;

import com.example.applicationrestaurant.Entities.Cervezas;

import java.util.ArrayList;

public class CervezaService {
    public ArrayList<Cervezas> cursorToArray(Cursor cursor){
        ArrayList<Cervezas> list = new ArrayList<>();
        if(cursor.getCount() !=0){
            while(cursor.moveToNext()){
                Cervezas cervezas = new Cervezas(
                        cursor.getString(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        Integer.parseInt(cursor.getString(3))
                );
                list.add(cervezas);
            }
        }
        return list;
    }
}
