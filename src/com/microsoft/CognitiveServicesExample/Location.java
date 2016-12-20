package com.microsoft.CognitiveServicesExample;

import android.content.Context;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

/**
 * Created by Snehil Verma on 10/22/2016.
 */
public class Location extends AppCompatActivity {
    GPSTracker gps;
    String location1;
    String location2;
    String location3;
    String location4;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.location);

        gps = new GPSTracker(this);
        if(gps.canGetLocation());
        Button b2=(Button)findViewById(R.id.button2);

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gps = new GPSTracker(Location.this);

                // check if GPS enabled
                if(gps.canGetLocation()){

                    double latitude = gps.getLatitude();
                    double longitude = gps.getLongitude();

                    // \n is for new line
                    Geocoder geocoder = new Geocoder(getApplicationContext(), Locale.getDefault());
                    try {
                        List<Address> listAddresses = geocoder.getFromLocation(latitude, longitude, 1);
                        if(null!=listAddresses&&listAddresses.size()>0){
                            location1 = listAddresses.get(0).getAddressLine(0);
                            location2=listAddresses.get(0).getAddressLine(1);
                            location3 = listAddresses.get(0).getAddressLine(2);
                            location4= listAddresses.get(0).getAddressLine(3);


                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }


                    Toast.makeText(getApplicationContext(),location1+" "+location2+" "+location3+" "+location4, Toast.LENGTH_LONG).show();

                    }

                else{
                    // can't get location
                    // GPS or Network is not enabled
                    // Ask user to enable GPS/network in settings
                    gps.showSettingsAlert();
                }





            }
        });
    }


}
