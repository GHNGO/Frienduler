package edu.csupomona.cs480.util;

import java.sql.Date;
import java.text.DateFormat;

import edu.csupomona.cs480.data.Event;
import edu.csupomona.cs480.data.provider.EventList;

public class EventListHelper {
	public static EventList findFreeTimes(EventList events) {
		EventList freeTimes = new EventList();
		
		for(int i = 0; i < events.size()-1; i++) {
			Event e = events.get(i);
			Event next = events.get(i+1);
			
			String event1EndTime = e.getEndTime();
			String event2StartTime = next.getStartTime();
			
			if(e.getDate().equals(next.getDate()) && event1EndTime.equals(event2StartTime)) {
				//events follow each other so there is no free time slot
			}
			else {
				//there is a gap in between events
				Event freeTime = new Event("Free Time", e.getEndTime(), next.getStartTime(), e.getDate());
				freeTimes.add(freeTime);
			}
		}
		return freeTimes;
	}

	//check if time slot is occupied by any of the events in an EventList
	public static boolean timeOccupied(EventList events, String timeSlot) {
		for(int i = 0; i <  events.size(); i++) {
			Event e = events.get(i);
			boolean isOccupied = eventContainsTime(e, timeSlot);
			if(isOccupied) {
				return true;
			}
		}
		return false;
	}
	
	public static boolean eventContainsTime(Event event, String timeSlot) {
		String[] time = timeSlot.split(" - ");
		/*
		 * time[0] is day
		 * time[1] is start time
		 * time[2] = end time
		 */
		if(time[0].equals(event.getDate())) {
			long startTime = convertStringToTime(time[1]);
			long endTime = convertStringToTime(time[2]);
			
			long eventStart = convertStringToTime(event.getStartTime());
			long eventEnd = convertStringToTime(event.getEndTime());
			
			boolean 
			if(startTime <= eventStart && endTime > eventStart) {
				return true;
			}
		}
		return false;
	}
	
	public static long convertStringToTime(String time) {
		DateFormat dF = DateFormat.getDateInstance();
		try {
		Date date = (Date)(dF.parse(time));
		return date.getTime();
		}
		catch(Exception e) {
			System.out.println(e.toString());
			return -1;
		}
	}
}
