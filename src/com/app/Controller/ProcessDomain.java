package com.app.Controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import android.util.Log;

import com.app.domain.Slot;

public class ProcessDomain {

	public ProcessDomain() {

	}

	public void executeDomain(ArrayList<String[]> d, String season) {

		if(com.app.domain.Values.debugDomain){

			Log.i("ExecuteDomain","===========BEGIN=============");
			Log.i("ExecuteDomain","Para: " + season);
			for (String[] e : d) {
				Log.i("ExecuteDomain","==>Slot");
				Log.i("ExecuteDomain","date init: " + e[0]);
				Log.i("ExecuteDomain","date end: " + e[1]);
			}
			Log.i("ExecuteDomain","===========END===============");
		}

		HashMap<Integer,Slot> seasons = com.app.domain.Values.seasons;
		for(int iterator = 0; iterator < d.size(); iterator++ ) {
			String date_initString = d.get(iterator)[0];
			String date_endString = d.get(iterator)[1];
			DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			Date startDate;
			int month = 0;
			try {
				startDate = df.parse(date_endString);
				Calendar cal = Calendar.getInstance();
				cal.setTime(startDate);

				month = cal.get(Calendar.MONTH);
				month++;

			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			Slot slot = new Slot(season,date_initString,date_endString);
			//TODO
			seasons.put(month, slot);
		}

		if(com.app.domain.Values.debugDomain){
			for ( Integer slot: seasons.keySet()) {
				Slot slotSlot = seasons.get(slot);
				Log.i("ExecuteDomain","Key " + slot);
				Log.i("ExecuteDomain","KeySet " + slotSlot);
			}
		}
	}
}
