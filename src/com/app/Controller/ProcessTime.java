package com.app.Controller;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ProcessTime {
	
	public ProcessTime() {
	};
	
	
	public void executeTime(){

		// Get the actual date
		Date cDate = new Date();
        String fDate = new SimpleDateFormat("MM-dd").format(cDate);
        String[] dateArray = fDate.split("-");
        
        
        
	}
}
