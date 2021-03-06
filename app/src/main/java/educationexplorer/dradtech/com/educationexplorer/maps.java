package educationexplorer.dradtech.com.educationexplorer;

import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;

import com.google.android.gms.location.LocationListener;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

public class maps extends FragmentActivity implements OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        LocationListener {

    private GoogleMap mMap;
    String address, clg_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        //Handling Bundle received
        Bundle bundle = new Bundle();
        bundle = getIntent().getExtras();
        address = bundle.getString("address_key"); //address is received
        clg_name = bundle.getString("name_key");
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
        LatLng latLng ;
        TextView txt = (TextView) findViewById(R.id.txt); //old code
        txt.setText(clg_name);
        String location=address.toString(); //old code
        List<Address> addressList = null;
        MarkerOptions mo=new MarkerOptions();

        if (! location.equals("")){
            Geocoder geocoder=new Geocoder(this);
            try {
                addressList=geocoder.getFromLocationName(location,1);
            } catch (IOException e) {
                e.printStackTrace();
            }
            for(int i=0;i<addressList.size();i++){
                Address address=addressList.get(i);
                latLng=new LatLng(address.getLatitude(),address.getLongitude());
                mo.position(latLng);
                mMap.addMarker(mo);
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng,18));
                mo.snippet("Distance=");
                mMap.addMarker(mo);
//                mMap.animateCamera(CameraUpdateFactory.zoomIn());

            }
        }
//        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 6));
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mMap.setMyLocationEnabled(true);


    }

    public void onClick(View v){
        if(v.getId()==R.id.btn){


            String location=address.toString(); //old code
//            String location=address; //new code
            List<Address> addressList = null;
            MarkerOptions mo=new MarkerOptions();

            if (! location.equals("")){
                Geocoder geocoder=new Geocoder(this);
                try {
                    addressList=geocoder.getFromLocationName(location,2);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                for(int i=0;i<addressList.size();i++){
                    Address address=addressList.get(i);
                    LatLng latLng = new LatLng(address.getLatitude(), address.getLongitude());
                    mo.position(latLng);
                    mMap.addMarker(mo);
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng,15));

                    mMap.animateCamera(CameraUpdateFactory.zoomIn());
                    mo.snippet("Distance=");
                    mMap.addMarker(mo);

                }
            }


        }

        
    }




    @Override
    public void onLocationChanged(Location location) {

    }



    @Override
    public void onConnected(@Nullable Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}
