package edu.csupomona.cs480.data;

import java.util.ArrayList;

public interface CalendarUserInterface {
	ArrayList<Event> getSchedule();
	
	boolean addEvent(Event e);

	boolean removeEvent(Event e);
	
	boolean eventScheduled(Event e);

	Event findEventByName(String eventName);
	
	Event removeEventByName(String eventName);
	
	String getId();
}
