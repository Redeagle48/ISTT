package com.app.domain;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Trip {
		
		Date hour_init, hour_end;
				
		ArrayList<String> stops; //stops between the start and end point
		
		public Trip(String depart, String arrive, String hour_init, String hour_end) {
			stops = new ArrayList<String>();
			
			stops.add(depart);
			stops.add(arrive);
			
			SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
			try {
				this.hour_init = sdf.parse(hour_init);
				this.hour_end = sdf.parse(hour_end);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		public String getHourInit() {
			return new SimpleDateFormat("HH:mm").format(this.hour_init);
		}
		
		public String getHourEnd() {
			return new SimpleDateFormat("HH:mm").format(this.hour_end);
		}
				
		public String getStartPoint(){
			return stops.get(0);
		}
		
		public String getEndPoint(){
			return stops.get(stops.size()-1);
		}
		
		public ArrayList<String> getStops() {
			return stops;
		}
		
		public ArrayList<String> getMiddleStops(){
			ArrayList<String> middleStops = new ArrayList<String>();
			for (int i = 0; i < stops.size(); i++) {
				if(i != 0 || i != stops.size()-1) {
					middleStops.add(stops.get(i));
				}
			}
			
			return middleStops;
		}
}
