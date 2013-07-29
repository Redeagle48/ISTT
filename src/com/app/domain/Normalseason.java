package com.app.domain;

import java.util.ArrayList;

public class Normalseason extends Timetable{
	
	public ArrayList<String> itinerary = new ArrayList<String>();
	
	Normalseason(){
		String exampleDate = "9-17";
		super.schedule.put(Integer.parseInt(exampleDate.split("-")[1]),exampleDate);
		
		
	}
	
	
}
