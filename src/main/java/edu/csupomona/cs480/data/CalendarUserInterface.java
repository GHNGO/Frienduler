package edu.csupomona.cs480.data;

import java.util.ArrayList;

public interface CalendarUserInterface {
	public ArrayList<Event> getSchedule();
	
	public boolean addEvent(Event e);

	public boolean removeEvent(Event e);
	
	public boolean eventScheduled(Event e);

	public String getId();
}
