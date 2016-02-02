package com.example.hectorcastillo.beacon.activists;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.hectorcastillo.beacon.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * Created by hector castillo on 1/2/16.
 */
public class MapActivity extends AppCompatActivity implements OnMapReadyCallback {

    static final LatLng GALERIA_360 = new LatLng(18.4849453,-69.9387551);
    static final LatLng AGORA_MALL = new LatLng(18.483469,-69.9411696);
    static final LatLng DIAMOND_MALL = new LatLng(18.4865226,-69.9433985);
    static final LatLng SMABIL = new LatLng(18.4829461,-69.9139248);


    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps_layout);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            // Providing back navigation.
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney, Australia, and move the camera.
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(GALERIA_360).title("Galeria 360"));
        mMap.addMarker(new MarkerOptions().position(AGORA_MALL).title("Agora Mall"));
        mMap.addMarker(new MarkerOptions().position(DIAMOND_MALL).title("Diamond mall"));
        mMap.addMarker(new MarkerOptions().position(SMABIL).title("Sambil"));

        // Move the camera instantly to hamburg with a zoom of 15.
       // mMap.moveCamera(CameraUpdateFactory.newLatLng(GALERIA_360));

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(GALERIA_360, 15));

        // Zoom in, animating the camera.
        mMap.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);
    }
}