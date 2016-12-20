package com.microsoft.CognitiveServicesExample;

import android.gesture.Gesture;
import android.gesture.GestureLibraries;
import android.gesture.GestureLibrary;
import android.gesture.GestureOverlayView;
import android.gesture.Prediction;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * Created by Snehil Verma on 10/22/2016.
 */
public class GestureRec extends AppCompatActivity implements GestureOverlayView.OnGesturePerformedListener {

    private GestureLibrary gestureLib;
    Toolbar toolbar;

    Map<String, String> emergency = new HashMap<String, String>();
    public class Pre_data {
        String name;
        double pre_score;

        Pre_data() {
            pre_score = 0;
            name = "null";
        }
    }

    Pre_data pre_data[] = new Pre_data[10];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        GestureOverlayView gestureOverlayView = new GestureOverlayView(this);
        View inflate = getLayoutInflater().inflate(R.layout.gesture_main, null);

        gestureOverlayView.addView(inflate);
        gestureOverlayView.addOnGesturePerformedListener(this);

        gestureLib = GestureLibraries.fromRawResource(this, R.raw.gesture);
        if (!gestureLib.load()) {
            finish();
        }
        setContentView(gestureOverlayView);

        toolbar=(Toolbar)gestureOverlayView.findViewById(R.id.toolbar);
        toolbar.setTitle("Hear Me Out");
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);

        for (int i = 0; i < 10; i++) {
            pre_data[i] = new Pre_data();
        }


        emergency.put("food", "1");
        emergency.put("hungry", "1");
        emergency.put("hunger", "1");
        emergency.put("cake", "1");
        emergency.put("cand.", "1");
        emergency.put("eat", "1");
        emergency.put("bread","1");


        emergency.put("loo", "2");
        emergency.put("toilet", "2");
        emergency.put("pee", "2");
        emergency.put("potty", "2");
        emergency.put("washroom", "2");

        emergency.put("lonely", "3");
        emergency.put("alone", "3");
        emergency.put("mom", "3");
        emergency.put("dad", "3");
        emergency.put("friend", "3");

        emergency.put("help", "4");
        emergency.put("danger", "4");
        emergency.put("scared", "4");
        emergency.put("save", "4");
        emergency.put("rescue", "4");
        emergency.put("scary", "4");





    }

    @Override
    public void onGesturePerformed(GestureOverlayView overlay, Gesture gesture) {
        ArrayList<Prediction> predictions = gestureLib.recognize(gesture);
        int j = 0;
        for (Prediction prediction : predictions) {
            pre_data[j].pre_score = prediction.score;
            pre_data[j].name = prediction.name;
            j++;


        }

        int pos = 0;
        for (int k = 0; k < 10; k++) {
            if (pre_data[k].pre_score > pre_data[pos].pre_score) {
                pos = k;
            }

        }


        pre_data[pos].name=pre_data[pos].name.toLowerCase();
        String status = (Boolean.toString(inDictionary(emergency, pre_data[pos].name.toLowerCase())));
        if (status != null) {
            String code = emergency.get(pre_data[pos].name.toLowerCase());
            performTask(code);
            Toast.makeText(GestureRec.this, pre_data[pos].name +" : "+ code, Toast.LENGTH_SHORT).show();


        }
            else{
                Toast.makeText(GestureRec.this,"No match",Toast.LENGTH_SHORT).show();}


    }


    public boolean inDictionary(Map<String, String> dict, String str) {


        if(dict.get(str) == null)
            return false;
        return true;

    }

    public void performTask(String code){

        if(code=="1"){
            sendFoodMessage();


        }
        else if(code=="2"){
            sendLooMessage();
        }
        else if(code=="3"){
            sendAloneMessage();}

        else if(code=="4"){
            GPSTracker gps;
            String location1="";
            String location2="";
            String location3="";
            String location4="";

            gps = new GPSTracker(GestureRec.this);

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
                String fin_loc=location1+" "+location2+" "+location3+" "+location4;
                sendAlertMessage(fin_loc);

            }

            else{
                // can't get location
                // GPS or Network is not enabled
                // Ask user to enable GPS/network in settings
                gps.showSettingsAlert();
            }





        }
    }


    public void sendFoodMessage() {
        Log.i("Send SMS", "");
        String phoneNo = "9591353047";
        String message = "Your child probably needs food. Pleased check on him.";

        try {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phoneNo, null, message, null, null);
            Toast.makeText(getApplicationContext(), "SMS sent.", Toast.LENGTH_LONG).show();
        }

        catch (Exception e) {
            Toast.makeText(getApplicationContext(), "SMS faild, please try again.", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }

    public void sendLooMessage() {
        Log.i("Send SMS", "");
        String phoneNo = "9663600190";
        String message = "Your child probably needs to go to the washroom. Please check on him";

        try {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phoneNo, null, message, null, null);
            Toast.makeText(getApplicationContext(), "SMS sent.", Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "SMS faild, please try again.", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }


    }

    public void sendAlertMessage(String location){
        Log.i("Send SMS", "");
        String phoneNo = "9591358441";

        String message = "Child in need of help ,LOCATION :"+location;

        try {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phoneNo, null, message, null, null);
            Toast.makeText(getApplicationContext(), "SMS sent.", Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "SMS faild, please try again.", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }


    }

    public void sendAloneMessage(){
        Log.i("Send SMS", "");
        String phoneNo = "9663600190";
        String message = "Hi! I want your attention can you talk to me.";

        try {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phoneNo, null, message, null, null);
            Toast.makeText(getApplicationContext(), "SMS sent.", Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "SMS failed, please try again.", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }


    }


}
