package edu.csupomona.cs480.data;

import java.util.ArrayList;

public class CalendarUser extends User{
	private ArrayList<Event> eventArrayList = new ArrayList<Event>();
	
	public ArrayList<Event> getEventArrayList() {
        return eventArrayList;
    }
	
	public ArrayList<Event> setEventArrayList(ArrayList<Event> eventArrayList) {
		ArrayList<Event> oldEventArrayList = this.eventArrayList;
		this.eventArrayList = eventArrayList;
		return oldEventArrayList;
    }
	
	public Event removeEventByName(String eventName) {
		int index = findEventIndexByName(eventName);
		if(index != -1)
			return eventArrayList.get(index);
		return null;
	}
	
	private int findEventIndexByName(String eventName) {
		int index = -1;
		for(int i = 0; i < eventArrayList.size(); i++) {
			Event e = eventArrayList.get(i);
			if(e == null)
				return index;
			else if (e.getName().equals(eventName))
				return i;
		}
		return index;
	}
}
