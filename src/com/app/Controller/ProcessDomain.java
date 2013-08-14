package com.app.Controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Set;

import android.util.Log;

import com.app.domain.Slot;

public class ProcessDomain {

	public ProcessDomain() {

	}

	public void executeDomain(ArrayList<String[]> d, String season) {

		if(com.app.domain.Values.debugDomain){

			Log.i("ExecuteDomain","===========ARGS EXECUTEDOMAIN=============");
			Log.i("ExecuteDomain","Para: " + season);
			for (String[] e : d) {
				Log.i("ExecuteDomain","==>Slot");
				Log.i("ExecuteDomain","date init: " + e[0]);
				Log.i("ExecuteDomain","date end: " + e[1]);
			}
			Log.i("ExecuteDomain","===========END===============");
		}

		HashMap<Integer,ArrayList<Slot>> seasons = com.app.domain.Values.seasons;
		for(int iterator = 0; iterator < d.size(); iterator++ ) {
			String date_initString = d.get(iterator)[0];
			String date_endString = d.get(iterator)[1];
			DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			Date startDate;
			int month = 0;
			try {
				startDate = df.parse(date_initString);
				Calendar cal = Calendar.getInstance();
				cal.setTime(startDate);

				month = cal.get(Calendar.MONTH);
				++month;

			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			ArrayList<Slot> slot_array;
			if(!seasons.containsKey(month)) {
				slot_array = new ArrayList<Slot>();
			} else {
				slot_array = seasons.get(month);
			}

			Slot slot = new Slot(season,date_initString,date_endString);
			try {
				slot_array = AddSlot(slot_array, slot);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//slot_array.add(slot);

			seasons.put(month, slot_array);
		}

		if(com.app.domain.Values.debugDomain){
			Log.i("ExecuteDomain","===========HASHMAP============");
			Log.i("ExecuteDomain","===========ORGANIZED============");
			Log.i("ExecuteDomain", "Tamanho: " + seasons.size());
			Set<Integer> set = seasons.keySet();
			for (Integer i : set) {
				ArrayList<Slot> seasons_slot = seasons.get(i);
				Log.i("ExecuteDomain","Para mês: " + i);
				for (Slot slot : seasons_slot) {
					Log.i("ExecuteDomain",slot.getSeason() + " " + slot.getSlotBeginDate());
				}
			}
		}
	}

	//TODO
	public ArrayList<Slot> AddSlot(ArrayList<Slot> slot_array, Slot slot) throws ParseException {

		Log.i("Organize","=========COMECA=============");
		Log.i("Organize","tamanho do array "+slot_array.size());

		ArrayList<Slot> slot_temp = new ArrayList<Slot>();
		if(slot_array.size() == 0) {
			slot_temp.add(slot);
			Log.i("Organize","Acao para o array vazio");
		}
		else {
			Log.i("Organize","Acao para o array com elementos");

			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");
			Date slot_date = sdf.parse(slot.getSlotBeginDate());
			boolean done = false;
			for(int i = 0; i < slot_array.size(); i++) {
				Slot slotItem = slot_array.get(i);
				Date slotItem_date = sdf.parse(slotItem.getSlotBeginDate());
				if(slot_date.before(slotItem_date) && !done) {
					slot_temp.add(slot);
					slot_temp.add(slotItem);
					done = true;
				} else {
					slot_temp.add(slotItem);
				}

				if(i == slot_array.size()-1 && !done) {
					slot_temp.add(slot);
					done = true;
				}
			}
		}
		return slot_temp;
	}
}
