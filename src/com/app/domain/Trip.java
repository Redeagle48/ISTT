package com.app.domain;

import java.util.ArrayList;

public class Trip {
	
		ArrayList<String> stops; //stops between the start and end point
		
		Trip() {
			stops = new ArrayList<String>();
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
