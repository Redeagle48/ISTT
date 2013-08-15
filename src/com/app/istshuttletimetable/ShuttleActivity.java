package com.app.istshuttletimetable;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;

import com.app.domain.Trip;

public class ShuttleActivity extends Activity {
	
	private TripItemAdapter aa;
	private ArrayList<Trip> todoItems;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		//Read XML from assets folder
		Context ctx = getApplicationContext();
		com.app.Controller.ProcessXML processXML = new com.app.Controller.ProcessXML();
		processXML.executeXMLSources(ctx);
		
		//Build Knowledge Base
		com.app.Controller.ProcessDomain processDomain = new com.app.Controller.ProcessDomain();
		processDomain.executeDomain(processXML.getNormal(), "normal");
		processDomain.executeDomain(processXML.getExams(), "exams");
		
		
		// Get the actual date
		Date cDate = new Date();
		String fDate = new SimpleDateFormat("MM-dd").format(cDate);
		String[] dateArray = fDate.split("-");


		setContentView(R.layout.activity_shuttle);
	
		FragmentManager fm = getFragmentManager();
		TripListFragment tripListFragment = (TripListFragment)fm.findFragmentById(R.id.TripListFragment);

		//Create the array list of to do items
		todoItems = com.app.domain.Values.trip_normal;

		//Create the array adapter to bind the array to the listview
		int resID = R.layout.triplist_item;
		aa = new TripItemAdapter(this,resID,
				todoItems);

		//Bind the array adapter to the list view
		tripListFragment.setListAdapter(aa);
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.shuttle, menu);
		return true;
	}

}
