package com.microsoft.CognitiveServicesExample;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by Snehil Verma on 10/23/2016.
 */
public class ChildInfo extends AppCompatActivity {


    EditText cname;
    EditText age;
    EditText bgroup;
    EditText cchild;
    Button proceed;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        final String MY_PREFS_NAME="CFD";



        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_childinfo);


        final EditText cname=(EditText)findViewById(R.id.cname);
       final  EditText age=(EditText)findViewById(R.id.age);
        final EditText bgroup=(EditText)findViewById(R.id.bgroup);
        final EditText cchild=(EditText)findViewById(R.id.cchild);
        Button proceed=(Button)findViewById(R.id.proceed);
        final EditText mname=(EditText)findViewById(R.id.mname);
        final  EditText mcon=(EditText)findViewById(R.id.mcon);
        final EditText fname=(EditText)findViewById(R.id.fname);
        final EditText fcon=(EditText)findViewById(R.id.fcon);

        proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str1 = cname.getText().toString();
                String str2 = age.getText().toString();
                String str3 = bgroup.getText().toString();
                String str4 = cchild.getText().toString();
                String str5 = mname.getText().toString();
                String str6 = mcon.getText().toString();
                String str7 = fname.getText().toString();
                String str8 = fcon.getText().toString();

                SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                SharedPreferences.Editor editor = prefs.edit();

                editor.putString("cname", str1);
                editor.putString("age", str2);
                editor.putString("bgroup", str3);
                editor.putString("addr", str4);
                editor.putString("mname", str5);
                editor.putString("mcon", str6);
                editor.putString("fname", str7);
                editor.putString("fcon", str8);

                editor.commit();



                Intent i=new Intent(ChildInfo.this,ChildDetails.class);


                i.putExtra("name",str1);
                i.putExtra("age",str2);
                i.putExtra("blood",str3);
                i.putExtra("addr",str4);
                i.putExtra("mname",str5);
                i.putExtra("mcon",str6);
                i.putExtra("fname",str7);
                i.putExtra("fcon",str8);
                startActivity(i);

            }
        });

    }
}
