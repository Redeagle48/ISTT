package com.app.domain;


public class Slot {

	String season;
	String begin;
	String end;
	
	public Slot(String season,String begin,String end){
		this.season = season;
		this.begin = begin;
		this.end = end;
	}
	
	public String getSeason(){
		return season;
	}
	
	public String getSlotBeginDate() {
		return begin;
	}
	
	public String getSlotEndDate() {
		return end;
	}

}
