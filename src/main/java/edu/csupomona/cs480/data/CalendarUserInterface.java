package edu.csupomona.cs480.data;

import java.util.ArrayList;

public interface CalendarUserInterface {
	ArrayList<Event> getSchedule();
	
	boolean addEvent(Event e);

	boolean removeEvent(Event e);
	
	boolean eventInSchedule(Event e);
	
	String getId();
}
