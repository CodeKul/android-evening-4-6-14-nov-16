package com.codekul.googlemaps;

import android.graphics.Color;
import android.support.multidex.MultiDex;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MultiDex.install(this);

        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);

        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                mMap.addMarker(new MarkerOptions()
                        .position(latLng).title("we dont have"));
            }
        });

        puneMarker();

        polyLine();

        circle();

        zoomMap();
    }

    private void zoomMap() {
        CameraUpdate cameraPosition = CameraUpdateFactory.newLatLngZoom(new LatLng(18.72,72.56), 15);
        mMap.moveCamera(cameraPosition);
        mMap.animateCamera(cameraPosition);
    }

    private void puneMarker() {
        LatLng pune = new LatLng(18.72, 72.56);
        mMap.addMarker(new MarkerOptions().position(pune).title("We are in Pune"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(pune));
    }

    public void polyLine(){
        mMap.addPolyline(new PolylineOptions()
                .width(1)
                .color(Color.RED)
                .add(new LatLng(18.72,72.56),new LatLng(17.82,79.50)));
    }

    public void circle() {
        mMap.addCircle(new CircleOptions()
                .center(new LatLng(18.72,72.56))
                .fillColor(Color.GREEN)
                .radius(100)
                .strokeColor(Color.RED)
                .strokeWidth(2));
    }
}
