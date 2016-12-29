package com.codekul.locationapp;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final int PERMISSION_LOCATION = 1234;
    private Geocoder geocoder;

    private LocationManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        manager = (LocationManager) getSystemService(LOCATION_SERVICE);

        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            AlertDialog.Builder builder = new AlertDialog.Builder(this)
                    .setMessage("We need access your location ")
                    .setPositiveButton("Okay", (dialog, which) -> {
                        ActivityCompat.requestPermissions(this,
                                new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                                PERMISSION_LOCATION);
                    })
                    .setNegativeButton("No", (dialog,which)-> {
                        dialog.dismiss();
                    });

            builder.show();
        }
        else listenForLocations();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode == PERMISSION_LOCATION){
            if(permissions.length > 0){
                if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    listenForLocations();
                }
            }
        }
    }

    private void listenForLocations() {

        manager.requestLocationUpdates(LocationManager.PASSIVE_PROVIDER, 100, 0.5f, new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                setInfo("Lat - "+location.getLatitude()+" Lng - "+location.getLongitude());
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

            }
        });
    }
    private void allProviders() {
        List<String> providers = manager.getAllProviders();
        StringBuilder builder = new StringBuilder();
        for (String provider : providers) {
            builder.append("\n Provider - ").append(provider);
        }
        setInfo(builder.toString());
    }

    private void criteria() {
        Criteria criteria = new Criteria();
        criteria.setPowerRequirement(Criteria.POWER_HIGH);
        criteria.setCostAllowed(true);
        criteria.setSpeedRequired(true);
        criteria.setAltitudeRequired(true);

        setInfo("Best Provider is - "+manager.getBestProvider(criteria, false));
    }

    private void geocoder() {
        geocoder = new Geocoder(this);

        findViewById(R.id.btnOkay)
                .setOnClickListener(v -> forwardGeocoding(((EditText)findViewById(R.id.edtAnything))
                        .getText()
                        .toString()));

        findViewById(R.id.btnGetLoc)
                .setOnClickListener( v -> reverseGeocoding(getLat(), getLng()));
    }

    private void forwardGeocoding(String add){
        try {
            List<Address> addresses = geocoder.getFromLocationName(add,5);
            StringBuilder builder = new StringBuilder();
            for (Address address : addresses) {
                builder.append("\n country ").append(address.getCountryName())
                        .append("\n cc ").append(address.getCountryCode())
                        .append("\n aa").append(address.getAdminArea())
                        .append("\n Lat ").append(address.getLatitude())
                        .append("\n Lng ").append(address.getLongitude())
                        .append("\n ------------");
            }

            ((TextView)findViewById(R.id.textAnything)).setText(builder.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void reverseGeocoding(double lat, double lng){
        try {
            List<Address> addresses = geocoder.getFromLocation(lat,lng,5);
            StringBuilder builder = new StringBuilder();
            for (Address address : addresses) {
                builder.append("\n country ").append(address.getCountryName())
                        .append("\n cc ").append(address.getCountryCode())
                        .append("\n aa").append(address.getAdminArea())
                        .append("\n Address line 1 - ").append(address.getAddressLine(0))
                        .append("\n ------------");
            }

            setInfo(builder.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private double getLat() {
        return Double.parseDouble(((EditText)findViewById(R.id.edtLat)).getText().toString());
    }

    private double getLng() {
        return Double.parseDouble(((EditText)findViewById(R.id.edtLng)).getText().toString());
    }

    private void setInfo(String info) {
        ((TextView)findViewById(R.id.textAnything)).setText(info);
    }
}
