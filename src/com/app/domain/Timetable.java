package com.app.domain;

import java.util.Hashtable;

public abstract class Timetable {
	int initTime;
	int endTime;
	
	protected Hashtable<Integer,String> schedule;
}
