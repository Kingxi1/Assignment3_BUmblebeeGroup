package com.example.assignment3;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;  // Google Maps
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        SupportMapFragment mapFragment =
                (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);

        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        // The map has been loaded successfully. Here, we obtain the actual GoogLeMap object.
        mMap = googleMap;

        // Click on the zoom button at the lower right corner
        mMap.getUiSettings().setZoomControlsEnabled(true);

        // First, set a default location (Auckland), and then change it to the current location later.
        LatLng auckland = new LatLng(-36.8485, 174.7633);

        // // Move the camera here and set the zoom level to 12
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(auckland, 12f));

        // 1/ Place a Marker and see the effect
        mMap.addMarker(new MarkerOptions()
                .position(auckland)
                .title("Auckland"));
    }
}