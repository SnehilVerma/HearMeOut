package com.microsoft.CognitiveServicesExample;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

/**
 * Created by Snehil Verma on 10/22/2016.
 */
public class LauncherActivity extends AppCompatActivity {

    ImageButton b1;
    ImageButton b2;
    ImageButton b3;
    Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open);
        b1=(ImageButton)findViewById(R.id.imageButton);
        b2=(ImageButton)findViewById(R.id.imageButton2);
        b3=(ImageButton)findViewById(R.id.imageButton3);


        toolbar=(Toolbar)findViewById(R.id.toolbar);
        toolbar.setTitle("Hear Me Out");
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.menu_parent) {
            Intent i=new Intent(LauncherActivity.this,ChildInfo.class);
            startActivity(i);
            //parent
        }
        else if(id==R.id.menu_child){
            Intent i=new Intent(LauncherActivity.this,ChildDetails.class);
            startActivity(i);
            //child
        }

        else if(id==R.id.menu_help){
            Intent i=new Intent(LauncherActivity.this,Help.class);
            startActivity(i);
            //child
        }
        return true;
    }

    public void Speak(View view){
        Intent i=new Intent(LauncherActivity.this,MainActivity.class);
        startActivity(i);
    }

    public void Draw(View view){
        Intent i=new Intent(LauncherActivity.this,GestureRec.class);
        startActivity(i);
    }

    public void Choose(View view){
        Intent i=new Intent(LauncherActivity.this,ChooseImage.class);
        startActivity(i);
    }
}
