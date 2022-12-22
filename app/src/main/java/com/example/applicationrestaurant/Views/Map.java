package com.example.applicationrestaurant.Views;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;

import com.example.applicationrestaurant.R;
import com.example.applicationrestaurant.Views.Products.ListComida;

import org.osmdroid.config.Configuration;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapController;
import org.osmdroid.views.MapView;

public class Map extends AppCompatActivity {
    private MapView map;
    private MapController mapController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        map = (MapView) findViewById(R.id.mapView);

        Configuration.getInstance().load(this, PreferenceManager.getDefaultSharedPreferences(this));
        mapController = (MapController) map.getController();

        GeoPoint colombia = new GeoPoint(4.570868, -74.297333);

        mapController.setCenter(colombia);
        mapController.setZoom(10);
        map.setMultiTouchControls(true);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Aqui se enlaza el menu que se creo
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent intent;
        switch (item.getItemId()){
            case R.id.itemMap:
                intent = new Intent(getApplicationContext(), Map.class);
                startActivity(intent);
            case R.id.itemComida:
                intent = new Intent(getApplicationContext(), ListComida.class);
                startActivity(intent);
            case R.id.itemCoctel:
                return true;
            case R.id.itemCerveza:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}