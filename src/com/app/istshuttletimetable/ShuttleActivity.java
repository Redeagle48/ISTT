package com.app.istshuttletimetable;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;

public class ShuttleActivity extends Activity {

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
		//TODO
		processDomain.organize();

		// Get the actual date
		Date cDate = new Date();
		String fDate = new SimpleDateFormat("MM-dd").format(cDate);
		String[] dateArray = fDate.split("-");


		setContentView(R.layout.activity_shuttle);
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.shuttle, menu);
		return true;
	}

}
