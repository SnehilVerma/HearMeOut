package com.microsoft.CognitiveServicesExample;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;


public class ChildDetails extends AppCompatActivity {
    final String MY_PREFS_NAME="CFD";
    SharedPreferences prefs;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailschild);


        TextView tv1=(TextView)findViewById(R.id.cname);
        TextView tv2=(TextView)findViewById(R.id.age);
        TextView tv3=(TextView)findViewById(R.id.bgroup);
        TextView tv4=(TextView)findViewById(R.id.cchild);
        TextView tv5=(TextView)findViewById(R.id.p1);
        TextView tv6=(TextView)findViewById(R.id.m1);
        TextView tv7=(TextView)findViewById(R.id.p2);
        TextView tv8=(TextView)findViewById(R.id.m2);

        //prefs = getSharedPreferences(MY_PREFS_NAME, Context.MODE_PRIVATE);
        final SharedPreferences prefs= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
           String name = prefs.getString("cname", "Nimish");//"No name defined" is the default value.
            String age = prefs.getString("age", "not specified");
            String blood=prefs.getString("bgroup", "not specified");
            String addr=prefs.getString("addr","not specified");
            String mname=prefs.getString("mname","not specified");
            String mcon=prefs.getString("mcon","not specified");
            String fname=prefs.getString("fname","not specified");
            String fcon=prefs.getString("fcon","not specified");


            tv1.setText(name);
            tv2.setText(age);
            tv3.setText(blood);
            tv4.setText(addr);
            tv5.setText(mname);
            tv6.setText(mcon);
            tv7.setText(fname);
            tv8.setText(fcon);



        }

    /*
        Bundle b=new Bundle();
        b=getIntent().getExtras();
        String s1=b.getString("name");
        String s2=b.getString("age");
        String s3=b.getString("blood");
        String s4=b.getString("addr");
        String s5=b.getString("mname");
        String s6=b.getString("mcon");
        String s7=b.getString("fname");
        String s8=b.getString("fcon");
        */



    }

