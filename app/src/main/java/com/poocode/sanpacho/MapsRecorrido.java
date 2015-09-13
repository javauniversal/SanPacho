package com.poocode.sanpacho;

import android.content.IntentSender;
import android.graphics.Color;
import android.location.Location;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.maps.android.kml.KmlLayer;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

public class MapsRecorrido extends FragmentActivity implements OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener, LocationListener {

    private GoogleMap mMap; // Might be null if Google Play services APK is not available.
    private GoogleApiClient mGoogleApiClient;
    private LocationRequest mLocationRequest;
    private final static int CONNECTION_FAILURE_RESOLUTION_REQUEST = 9000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.maps_recorrido);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();

        // Create the LocationRequest object
        mLocationRequest = LocationRequest.create()
                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                .setInterval(10 * 1000)        // 10 seconds, in milliseconds
                .setFastestInterval(1000); // 1 second, in milliseconds

        mapFragment.getMapAsync(this);

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        mMap.setMyLocationEnabled(true);
        // Add a marker in Sydney and move the camera
        //LatLng sydney = new LatLng(-34, 151);
        //mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        //mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

        KmlLayer layer = null;
        try {
            layer = new KmlLayer(mMap, R.raw.mapkml, this);
            layer.addLayerToMap();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        setUpMap();
        //setPoly();
    }

    private void setPoly() {
        PolygonOptions regionLayer = new PolygonOptions();
        regionLayer.strokeWidth(5).strokeColor(Color.argb(20, 50, 0, 255)).fillColor(Color.argb(20, 50, 0, 255));


        //regionLayer.add(new LatLng(, ));

        mMap.addPolygon(regionLayer);
    }

    @Override
    public void onLocationChanged(Location location) {
        LatLng myCoordinates = new LatLng(location.getLatitude(),location.getLongitude());
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(myCoordinates)      // Sets the center of the map to LatLng (refer to previous snippet)
                .zoom(12)                   // Sets the zoom
                // .bearing(50)              // Sets the orientation of the camera to east
                //.tilt(45)                 // Sets the tilt of the camera to 30 degrees
                .build();                   // Creates a CameraPosition from the builder
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
    }


    private void setUpMap() {
        mMap.addMarker(new MarkerOptions().position(new LatLng(5.696498, -76.6574156)).title("kennedy"));
        mMap.addMarker(new MarkerOptions().position(new LatLng(5.694523, -76.6584134)).title("Cristo rey"));
        mMap.addMarker(new MarkerOptions().position(new LatLng(5.6918753, -76.6602588)).title("Roma"));
        mMap.addMarker(new MarkerOptions().position(new LatLng(5.6869858, -76.6602266)).title("Yesquita"));
        mMap.addMarker(new MarkerOptions().position(new LatLng(5.6877651, -76.6597223)).title("Yesca grande"));
        mMap.addMarker(new MarkerOptions().position(new LatLng(5.6899217, -76.658628)).title("Pandeyuca"));
        mMap.addMarker(new MarkerOptions().position(new LatLng(5.6899537, -76.6567504)).title("Alameda Reyes"));
        mMap.addMarker(new MarkerOptions().position(new LatLng(5.6899644, -76.6545081)).title("Las margaritas"));
        mMap.addMarker(new MarkerOptions().position(new LatLng(5.6903274, -76.6526413)).title("La esmeralda"));
        mMap.addMarker(new MarkerOptions().position(new LatLng(5.6928255, -76.65602090000002)).title("El silencio"));
        mMap.addMarker(new MarkerOptions().position(new LatLng(5.6921103, -76.6581559)).title("Cesar conto"));
        mMap.addMarker(new MarkerOptions().position(new LatLng(5.695273, -76.6560986)).title("Tomas perez"));
        mMap.addMarker(new MarkerOptions().position(new LatLng(5.694846000000001, -76.6561255)).title("Llegada desfile tomas perez"));
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        if (connectionResult.hasResolution()) {
            try {
                // Start an Activity that tries to resolve the error
                connectionResult.startResolutionForResult(this, CONNECTION_FAILURE_RESOLUTION_REQUEST);

            } catch (IntentSender.SendIntentException e) {
                // Log the error
                e.printStackTrace();
            }
        } else {
            Toast.makeText(this, "Location services connection failed with code ", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        mGoogleApiClient.connect();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mGoogleApiClient.isConnected()) {
            LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, this);
            mGoogleApiClient.disconnect();
        }
    }

    @Override
    public void onConnected(Bundle bundle) {
        Location location = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);

        if (location == null) {
            LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);
        }
        else {
            LatLng myCoordinates = new LatLng(5.69211,-76.65815);
            //onLocationChanged(location);
            positionMaps(myCoordinates);
        }
    }

    private void positionMaps(LatLng position){
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(position)      // Sets the center of the map to LatLng (refer to previous snippet)
                .zoom(15)                   // Sets the zoom
                // .bearing(50)              // Sets the orientation of the camera to east
                // .tilt(45)                 // Sets the tilt of the camera to 30 degrees
                .build();                   // Creates a CameraPosition from the builder
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

}
