package com.app.istshuttletimetable;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;

public class ShuttleActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        //Read XML from asset folder
        Context ctx = getApplicationContext();
        com.app.Controller.ProcessXML p = new com.app.Controller.ProcessXML();
        p.executeTrips(ctx);
        
        
        setContentView(R.layout.activity_shuttle);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.shuttle, menu);
        return true;
    }
    
}
