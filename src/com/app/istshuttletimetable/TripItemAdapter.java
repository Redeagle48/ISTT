package com.app.istshuttletimetable;

import java.util.List;

import android.content.ClipData.Item;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.app.domain.Trip;

public class TripItemAdapter extends ArrayAdapter<Trip>{

	int resource;

	public TripItemAdapter(Context context,
			int resource,
			List<Trip> items){
		super(context, resource, items);
		this.resource = resource;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LinearLayout tripView;
		
		Trip item = getItem(position);
		
		String depart = item.getStartPoint();
		String arrive = item.getEndPoint();
		
		String places = depart+" - "+arrive;
		
		String hour_init = item.getHourInit();
		
		if(convertView == null) {
			tripView = new LinearLayout(getContext());
			String inflater = Context.LAYOUT_INFLATER_SERVICE;
			LayoutInflater li;
			li = (LayoutInflater)getContext().getSystemService(inflater);
			li.inflate(resource, tripView, true);
		} else {
			tripView = (LinearLayout) convertView;
		}
		
		TextView dateView = (TextView)tripView.findViewById(R.id.rowDate);
		TextView taskView = (TextView)tripView.findViewById(R.id.row);
		
		dateView.setText(hour_init);
		taskView.setText(places);
		
		return tripView;
	}

}
