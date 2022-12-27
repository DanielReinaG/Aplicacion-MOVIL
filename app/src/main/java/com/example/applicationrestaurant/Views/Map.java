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
import org.osmdroid.views.overlay.Marker;

public class Map extends AppCompatActivity {
    private MapView map;
    private MapController mapController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        Configuration.getInstance().load(this, PreferenceManager.getDefaultSharedPreferences(this));

        GeoPoint villavicencio = new GeoPoint(4.13961, -73.63572);
        map = (MapView) findViewById(R.id.mapView);
        map.setBuiltInZoomControls(true);
        mapController = (MapController) map.getController();
        mapController.setCenter(villavicencio);
        mapController.setZoom(17);
        map.setMultiTouchControls(true);

        Marker marker = new Marker(map);
        marker.setPosition(villavicencio);
        map.getOverlays().add(marker);
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

            case R.id.Menu:
                intent = new Intent(getApplicationContext(), InicioMenu.class);
                startActivity(intent);
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}