package com.app.Controller;

import java.util.ArrayList;

import android.util.Log;

public class ProcessDomain {

	public ProcessDomain() {
		
	}
	
	public void executeDomain(ArrayList<String[]> d, String season) {
		Log.i("ExecuteDomain","Para: " + season);
		for (String[] e : d) {
			Log.i("ExecuteDomain","Slot:");
			Log.i("ExecuteDomain",e[0]);
			Log.i("ExecuteDoamin",e[1]);
		}
	}
}
