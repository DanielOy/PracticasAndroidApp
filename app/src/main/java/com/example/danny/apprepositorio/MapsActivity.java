package com.example.danny.apprepositorio;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    public static final LatLng punto1= new LatLng(20.693661,-88.197272);
    public static final LatLng punto2= new  LatLng(20.692175, -88.209884);
    public static final LatLng punto3 = new LatLng(20.686138, -88.209399);

    private Marker mPunto1;
    private Marker mPunto2;
    private Marker mPunto3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }



    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        List<Marker> markerList = new ArrayList<>();

        mPunto1 =mMap.addMarker(new MarkerOptions().position(punto1).title("Proyecto Disponible ESCRITORIO")
        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
        mPunto1.setTag(0);
        markerList.add(mPunto1);

        mPunto2 =mMap.addMarker(new MarkerOptions().position(punto2).title("Proyecto Disponible MOVIL")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE)));
        mPunto2.setTag(0);
        markerList.add(mPunto2);

        mPunto3 =mMap.addMarker(new MarkerOptions().position(punto3).title("Proyecto Disponible WEB")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
        mPunto3.setTag(0);
        markerList.add(mPunto3);



        for (Marker m: markerList){

            LatLng latLng = new LatLng(m.getPosition().latitude, m.getPosition().longitude);
            mMap.addMarker( new MarkerOptions().position(latLng));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng,10));
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 13));
        }




        // Add a marker in Sydney and move the camera
        //LatLng valladolid = new LatLng(20, -88);
        //mMap.addMarker(new MarkerOptions().position(valladolid).title("marcador en Valladolid")
        //.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));
        //mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(valladolid,15));
    }
}
