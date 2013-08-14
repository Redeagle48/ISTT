package com.app.domain;

import java.util.ArrayList;
import java.util.HashMap;


public class Values {
	public static boolean debugXML = false;
	public static boolean debugDomain = true;
	
	public static String XML_TRIPS = "Schedule.xml";
	public static String XML_SEASONS = "Seasons.xml";
	
	public static HashMap<Integer, ArrayList<Slot>> seasons = new HashMap<Integer, ArrayList<Slot>>();
	
	public static ArrayList<Trip> trip_exams = new ArrayList<Trip>();
	public static ArrayList<Trip> trip_normal = new ArrayList<Trip>();
}
