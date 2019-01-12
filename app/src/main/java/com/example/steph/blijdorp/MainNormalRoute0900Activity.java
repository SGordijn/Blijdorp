package com.example.steph.blijdorp;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import android.location.LocationListener;
import android.support.v4.content.ContextCompat;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;


public class MainNormalRoute0900Activity extends FragmentActivity implements OnMapReadyCallback,
GoogleApiClient.ConnectionCallbacks,
GoogleApiClient.OnConnectionFailedListener,
LocationListener {

    private GoogleMap mMap;
    GoogleApiClient mGoogleApiClient;
    Location mLastLocation;
    Marker mCurrLocationMarker;
    LocationRequest mLocationRequest;
    private GoogleApiClient mClient;

    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_normal_route0900);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        mClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();

        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            checkLocationPermission();
        }
    }

    public boolean checkLocationPermission() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION);
            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION);
            }
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_LOCATION: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (ContextCompat.checkSelfPermission(this,
                            Manifest.permission.ACCESS_FINE_LOCATION)
                            == PackageManager.PERMISSION_GRANTED) {
                        if (mGoogleApiClient == null) {
                            buildGoogleApiClient();
                        }
                        mMap.setMyLocationEnabled(true);
                    }
                } else {
                    Toast.makeText(this, "Geen Toestemming", Toast.LENGTH_LONG).show();
                }
                return;
            }
        }
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
        mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)
                    == PackageManager.PERMISSION_GRANTED) {
                buildGoogleApiClient();
                mMap.setMyLocationEnabled(true);
            }
        }
        else {
            buildGoogleApiClient();
            mMap.setMyLocationEnabled(true);
        }

//        Polyline line = mMap.addPolyline(new PolylineOptions()
//                .add(new LatLng(51.5, -0.1), new LatLng(40.7, -74.0))
//                .width(5)
//                .color(Color.RED));

        LatLng routeStart = new LatLng(51.928040, 4.444514);
        mMap.addMarker(new MarkerOptions().position(routeStart).title("Start Route. Klik op pinnetjes voor informatie.")).showInfoWindow();
        mMap.moveCamera(CameraUpdateFactory.newLatLng(routeStart));
        float zoomLevel = 18.0f;
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(routeStart, zoomLevel));

        Polyline line1 = mMap.addPolyline(new PolylineOptions()
                .add(new LatLng(51.928040, 4.444514), new LatLng(51.928570, 4.444558))
                .width(5)
                .color(Color.RED));

        LatLng routeMain1 = new LatLng(51.928570, 4.444558);
        mMap.addMarker(new MarkerOptions().position(routeMain1).title("Ga Oceanium in."));

        Polyline line2 = mMap.addPolyline(new PolylineOptions()
                .add(new LatLng(51.928570, 4.444558), new LatLng(51.928051, 4.445776))
                .width(5)
                .color(Color.RED));

        LatLng routeMain2 = new LatLng(51.928051, 4.445776);
        mMap.addMarker(new MarkerOptions().position(routeMain2).title("Ga door het Oceanium en eindig hier."));

        Polyline line3 = mMap.addPolyline(new PolylineOptions()
                .add(new LatLng(51.928051, 4.445776), new LatLng(51.928571, 4.447311))
                .width(5)
                .color(Color.RED));

        Polyline line4 = mMap.addPolyline(new PolylineOptions()
                .add(new LatLng(51.928571, 4.447311), new LatLng(51.928518, 4.449508))
                .width(5)
                .color(Color.RED));

        Polyline line5 = mMap.addPolyline(new PolylineOptions()
                .add(new LatLng(51.928518, 4.449508), new LatLng(51.928688, 4.449911))
                .width(5)
                .color(Color.RED));

        Polyline line6 = mMap.addPolyline(new PolylineOptions()
                .add(new LatLng(51.928688, 4.449911), new LatLng(51.928451, 4.450220))
                .width(5)
                .color(Color.RED));

        LatLng routeMain3 = new LatLng(51.928451, 4.450220);
        mMap.addMarker(new MarkerOptions().position(routeMain3).title("Ga naar het poort van Azië en ga eten (optioneel)."));

        Polyline line7 = mMap.addPolyline(new PolylineOptions()
                .add(new LatLng(51.928451, 4.450220), new LatLng(51.928405, 4.451422))
                .width(5)
                .color(Color.RED));

        LatLng routeMain4 = new LatLng(51.928405, 4.451422);
        mMap.addMarker(new MarkerOptions().position(routeMain4).title("Ga het Taman Indah in."));

        // Add a marker in Sydney and move the camera
//        LatLng sydney = new LatLng(-34, 151);
//        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }

    protected synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
        mGoogleApiClient.connect();
    }

    @Override
    public void onLocationChanged(Location location) {

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

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        if (ContextCompat.checkSelfPermission(this,
                android.Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            mLastLocation = LocationServices.FusedLocationApi.getLastLocation(
                    mGoogleApiClient);
        }

        LatLng ownLocation = new LatLng(mLastLocation.getLatitude(), mLastLocation
                .getLongitude());
//        mMap.addMarker(new MarkerOptions().position(ownLocation).title("Je bent hier."));
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(ownLocation));
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}
