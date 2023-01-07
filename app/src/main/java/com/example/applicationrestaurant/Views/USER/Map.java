package com.example.applicationrestaurant.Views.USER;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.MenuItem;

import com.example.applicationrestaurant.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.osmdroid.config.Configuration;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapController;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Marker;

public class Map extends AppCompatActivity {
    private MapView map;
    private MapController mapController;
    private BottomNavigationView bottom_navigation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        Configuration.getInstance().load(this, PreferenceManager.getDefaultSharedPreferences(this));

        GeoPoint villavicencio = new GeoPoint(4.13961, -73.63572);
        map = (MapView) findViewById(R.id.mapView);
        bottom_navigation = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        bottom_navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        map.setBuiltInZoomControls(true);
        mapController = (MapController) map.getController();
        mapController.setCenter(villavicencio);
        mapController.setZoom(17);
        map.setMultiTouchControls(true);

        Marker marker = new Marker(map);
        marker.setPosition(villavicencio);
        map.getOverlays().add(marker);
    }

    private final BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    switch (item.getItemId()){
                        case R.id.nav_menu:
                            Intent intent = new Intent(getApplicationContext(), Comida.class);
                            startActivity(intent);
                            return true;

                        case R.id.nav_coctel:
                            Intent intent2 = new Intent(getApplicationContext(), Cocteles.class);
                            startActivity(intent2);
                            return true;

                        case R.id.nav_cerveza:
                            Intent intent3 = new Intent(getApplicationContext(), Cervezas.class);
                            startActivity(intent3);
                            return true;

                        case R.id.nav_map:
                            Intent intent4 = new Intent(getApplicationContext(), Map.class);
                            startActivity(intent4);
                            return true;

                        case R.id.nav_login:
                            Intent intent5 = new Intent(getApplicationContext(), Login.class);
                            startActivity(intent5);
                            return true;

                    }
                    return false;
                }
            };
}