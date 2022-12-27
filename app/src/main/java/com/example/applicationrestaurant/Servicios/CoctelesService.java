package com.example.applicationrestaurant.Servicios;

import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.widget.ImageView;

import com.example.applicationrestaurant.Entities.Cocteles;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class CoctelesService {
    public ArrayList<Cocteles> cursorToArray(Cursor cursor){
        ArrayList<Cocteles> list = new ArrayList<>();
        if(cursor.getCount() !=0){
            while (cursor.moveToNext()){
                Cocteles cocteles = new Cocteles(
                        cursor.getString(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        Integer.parseInt(cursor.getString(3)),
                        cursor.getString(4)
                );
                list.add(cocteles);
            }
        }
        return list;
    }
    public byte[] imageViewToByte(ImageView imageView){
        Bitmap bitmap = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100,stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;
    }

}
