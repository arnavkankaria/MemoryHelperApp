package com.arnav.memoryhelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
//import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;


import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

import android.widget.Toast;

import java.io.IOException;
import java.util.List;

public class maplost extends FragmentActivity implements OnMapReadyCallback, GoogleApiClient.OnConnectionFailedListener,
        GoogleApiClient.ConnectionCallbacks {

    Button bContact;
    private GoogleMap mMap;
    //gac = google api client
    private GoogleApiClient gac;
    boolean bConnected = false;
    private LocationRequest locReq;
    private static final int minDistance = 10;
    private static final int locFrequency = 101;
    SQLiteDatabase mydb;
    double lat = 0.0;
    double lng = 0.0;
    private FusedLocationProviderClient mFusedLocationClient;
    LocationCallback m_LocationCallback;
    Button stop;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maplost);

        showPermissionDialog();

        mydb = openOrCreateDatabase("memoryhelper",MODE_PRIVATE,null);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


        //sendMessage("", "");

        /*bContact = findViewById(R.id.helpcontact_button);
        bContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent a = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
                startActivityForResult(a, 7);


            }
        });*/
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        bConnected = false;
    }
    @Override
    public void onConnected(@Nullable Bundle bundle) {
        bConnected = true;
    }
    @Override
    public void onConnectionSuspended(int i) {
        bConnected = false;
    }


    public void setMapMarker(Location location) {
        if (location != null) {
            lat = location.getLatitude();
            lng = location.getLongitude();
        }

        LatLng currLoc = new LatLng(lat , lng);
        mMap.addMarker(new MarkerOptions().position(currLoc).title("Marker at current location"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(currLoc));

        List<Address> addresses = null;

        try {
            Geocoder gc = new Geocoder(this);
            addresses = gc.getFromLocation(
                    lat, lng,
                    // In this sample, get just a single address.
                    1);
            if (!addresses.isEmpty()) {
                Address address = addresses.get(0);
                System.out.print("Address URL = " + address.getUrl());
                System.out.print("Address String = " + address.toString());

                //addrStr = "http://maps.google.com/?q=" +lat+","+lng;

                //addrStr = "Lat: "+ lat + "lng: " + lng + "\n" + address.toString();
                /*
                        address.getPremises()+","+ address.getThoroughfare()+","+address.getSubThoroughfare()+","+address.getFeatureName()
                        +","+address.getSubLocality()+","+address.getLocality()+","+
                        address.getSubAdminArea()+","+address.getExtras()+","+
                        address.getAdminArea()+","+address.getCountryName()+","+
                        ","+address.getPostalCode() + "," + address.toString();
                */
                //System.out.println(addrStr);

               // Toast.makeText (this, addrStr, Toast.LENGTH_LONG).show();
                sendMessage("", "I'm@: " + "http://maps.google.com/?q=" +lat+","+lng);


            }
        } catch (IOException ioException) {
            // Catch network or other I/O problems.
        } catch (IllegalArgumentException illegalArgumentException) {
            // Catch invalid latitude or longitude values.
            /*
            errorMessage = getString(R.string.invalid_lat_long_used);
            Log.e(TAG, errorMessage + ". " +
                    "Latitude = " + location.getLatitude() +
                    ", Longitude = " +
                    location.getLongitude(), illegalArgumentException);  */
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

        // Add a marker and move the camera
        /*gac = new GoogleApiClient.Builder(this)
                .addApi(LocationServices.API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();
        gac.connect();*/
        //-------------------------------
        try {

            mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

            mFusedLocationClient.getLastLocation().addOnSuccessListener(this, new OnSuccessListener<Location>() {
                        @Override
                        public void onSuccess(Location location) {
                            // Got last known location. In some rare situations this can be null.
                            if (location != null) {
                                // Logic to handle location object

                                setMapMarker(location);
                            }
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.d("MapDemoActivity", "Error trying to get last GPS location");
                            e.printStackTrace();
                        }
                    });
            locReq = LocationRequest.create();
            locReq.setInterval(60*1000);
            //locReq.setFastestInterval(1000);
            locReq.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
            //locReq.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);

            if (m_LocationCallback == null) {
                m_LocationCallback = new LocationCallback() {
                    @Override
                    public void onLocationResult(LocationResult locationResult) {
                        for (Location location : locationResult.getLocations()) {
                            // Update UI with location data
                            setMapMarker(location);
                        }
                    }
                };
            }

            mFusedLocationClient.requestLocationUpdates(
                    locReq,
                    /*
                    new LocationCallback() {
                        @Override
                        public void onLocationResult(LocationResult locationResult) {
                            for (Location location : locationResult.getLocations()) {
                                // Update UI with location data
                                setMapMarker(location);
                            }
                        }
                    }*/
                    m_LocationCallback,
                    Looper.myLooper()
            );
        } catch (SecurityException ex) {
            ex.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
       // setMapMarker(null);
        /*
        LatLng currLoc = new LatLng(lat , lng);
        mMap.addMarker(new MarkerOptions().position(currLoc).title("Marker at current location"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(currLoc));
        */

       // sendMessage("", "I'm@: " + "http://maps.google.com/?q=" +lat+","+lng);

    }

    public Location getCurrentLocation() {
        LocationManager mgr = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.ACCESS_FINE_LOCATION},
                    1);
        }
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) ==
                PackageManager.PERMISSION_GRANTED) {
            Location location = mgr.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            if (location == null) {
                location = mgr.getLastKnownLocation(mgr.NETWORK_PROVIDER);
            }
            return location;
        }
        return null;
    }

    public void sendMessage(String strMobileNo, String strMessage) {
        String num="", name = "", status = "";

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS},0);

        } else {

            try {
                Cursor c = mydb.rawQuery("select * from contacts where status = 'active'", null);
                if (c.moveToFirst()) {
                    while (!c.isAfterLast()) {
                        num = c.getString(c.getColumnIndex("number"));
                        name = c.getString(c.getColumnIndex("name"));
                        status = c.getString(c.getColumnIndex("status"));
                        try {
                            SmsManager smsManager = SmsManager.getDefault();
                            Toast.makeText(getApplicationContext(), name + " " + num, Toast.LENGTH_LONG).show();

                            smsManager.sendTextMessage(num, null, strMessage, null, null);
                            //    Toast.makeText(getApplicationContext(), "Your message sent", Toast.LENGTH_LONG).show();

                        } catch (Exception ex) {
                            Toast.makeText(getApplicationContext(), ex.getMessage().toString(), Toast.LENGTH_LONG).show();
                        }


                        c.moveToNext();
                    }
                }
            } catch (SQLiteException e){
                e.printStackTrace();
                Toast.makeText(maplost.this, "Add your emergency contacts", Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                e.printStackTrace();
            }
            /*String name = c.getString(0);
            String num = c.getString(1);
            String status = c.getString(2);*/

            /*
            Toast.makeText(getApplicationContext(), name + " " + num, Toast.LENGTH_LONG).show();

            try {
                SmsManager smsManager = SmsManager.getDefault();
                smsManager.sendTextMessage(num, null, strMessage, null, null);
            //    Toast.makeText(getApplicationContext(), "Your message sent", Toast.LENGTH_LONG).show();

            } catch (Exception ex) {
                Toast.makeText(getApplicationContext(), ex.getMessage().toString(), Toast.LENGTH_LONG).show();
            }
            */

            stop = findViewById(R.id.stop_button);

            stop.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mFusedLocationClient.removeLocationUpdates(m_LocationCallback);
                    maplost.this.finish();
                }
            });


        }

    }

    public void showPermissionDialog() {

        int secondsDelayed = 1;

        new Handler().postDelayed(new Runnable() {
            public void run() {
                boolean result = Utility.checkAndRequestPermissions(maplost.this);
            }
        }, secondsDelayed * 100);
    }

}
