package com.artica.telesalud.tph.android.activity;

import android.app.Activity;
import android.content.Context;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.artica.telesalud.tph.android.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MapsActivity  extends FragmentActivity implements GoogleMap.OnMapLongClickListener,GoogleMap.OnMapClickListener,GoogleMap.OnMarkerDragListener, LocationListener {

    // Google Map
    private GoogleMap googleMap;
    private LocationManager locationManager;
    private String provider;
    private Geocoder geocoder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        geocoder = new Geocoder(this, Locale.US);
        // Get the location manager
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Criteria criteria = new Criteria();
        provider = locationManager.getBestProvider(criteria, false);
        Location location = locationManager.getLastKnownLocation(provider);
        if(location != null) {
            try {
                // Loading map
                initilizeMap(location);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * function to load map. If map is not created it will create it for you
     * */
    private void initilizeMap(Location location) {
        if (googleMap == null) {
            googleMap = ((MapFragment) getFragmentManager().findFragmentById(
                    R.id.map)).getMap();

            // check if map is created successfully or not
            if (googleMap == null) {
                Toast.makeText(getApplicationContext(),
                        "Sorry! unable to create maps", Toast.LENGTH_SHORT)
                        .show();
            }
        }
        googleMap.setOnMarkerDragListener(this);
        googleMap.setOnMapLongClickListener(this);
        googleMap.setOnMapClickListener(this);
        googleMap.clear();
        CameraPosition INIT =
                new CameraPosition.Builder()
                        .target(new LatLng(location.getLatitude(), location.getLongitude()))
                        .zoom(17.5F)
                        .bearing(300F) // orientation
                        .tilt( 50F) // viewing angle
                        .build();

        // use map to move camera into position
        googleMap.moveCamera( CameraUpdateFactory.newCameraPosition(INIT) );

        //create initial marker
        googleMap.addMarker( new MarkerOptions()
                .position( new LatLng(location.getLatitude(), location.getLongitude()) )
                .title("Ubicación del evento")
                .snippet("").draggable(true)).showInfoWindow();
    }

    @Override
    protected void onResume() {
        super.onResume();
       // initilizeMap();
    }
    @Override
    public void onMarkerDrag(Marker arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onMarkerDragEnd(Marker arg0) {
        // TODO Auto-generated method stub
        LatLng dragPosition = arg0.getPosition();
        double dragLat = dragPosition.latitude;
        double dragLong = dragPosition.longitude;
        Log.i("info", "on drag end :" + dragLat + " dragLong :" + dragLong);
        Toast.makeText(getApplicationContext(), "Marker Dragged..!", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onMarkerDragStart(Marker arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onMapClick(LatLng arg0) {
        // TODO Auto-generated method stub
        googleMap.animateCamera(CameraUpdateFactory.newLatLng(arg0));
    }


    @Override
    public void onMapLongClick(LatLng arg0) {
        // TODO Auto-generated method stub

        //create new marker when user long clicks
       // googleMap.addMarker(new MarkerOptions()
               // .position(arg0)
                //.draggable(true));
    }
    public String getAddress(Location location) throws IOException {
        List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
        if(addresses.size() > 0)
        {
            Address address = addresses.get(0);
            StringBuilder sb = new StringBuilder();
            sb.append("Country: "+address.getCountryName()+"\nAdministrative Area: "+address.getAdminArea());
            sb.append("\nSub Admin Area: "+address.getSubAdminArea()+"\nLocality: "+address.getLocality());

            for(int i = 0; i < address.getMaxAddressLineIndex(); i++)
            {
                sb.append( "\nAddressLine "+i+": "+address.getAddressLine(i));
            }
            Bundle extras = address.getExtras();
            if(extras != null) {

                for (String key : extras.keySet()) {
                    String extra = "\nExtra: " + key + " = " + extras.get(key);
                }
            }
            return sb.toString();
        }
        return null;
    }
    @Override
    public void onLocationChanged(Location location) {
        Double lat = (location.getLatitude());
        Double lng = (location.getLongitude());
        try{
            List<Address> addresses = geocoder.getFromLocation(lat, lng, 1);
            if(addresses.size() > 0)
            {
                Address address = addresses.get(0);
                String s = "";
                s = s+ "Country: "+address.getCountryName()+"\nAdministrative Area: "+address.getAdminArea();
                s = s+"\nSub Admin Area: "+address.getSubAdminArea()+"\nLocality: "+address.getLocality();

                for(int i = 0; i < address.getMaxAddressLineIndex(); i++)
                {
                    s += "\nAddressLine "+i+": "+address.getAddressLine(i);
                }
                Bundle extras = address.getExtras();
                if(extras != null) {

                    for (String key : extras.keySet()) {
                        String extra = "\nExtra: " + key + " = " + extras.get(key);
                    }
                }

            }

        }
        catch(Exception ex )
        {

        }


    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onProviderEnabled(String provider) {
        Toast.makeText(this, "Enabled new provider " + provider,
                Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onProviderDisabled(String provider) {
        Toast.makeText(this, "Disabled provider " + provider,
                Toast.LENGTH_SHORT).show();
    }
}