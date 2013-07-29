package com.app.istshuttletimetable;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;

public class ShuttleActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        com.app.Controller.ProcessTime p = new com.app.Controller.ProcessTime();
        p.executeTime();
        
        setContentView(R.layout.activity_shuttle);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.shuttle, menu);
        return true;
    }
    
}
