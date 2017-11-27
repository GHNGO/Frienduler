package edu.csupomona.cs480.util;

import java.sql.Date;
import java.text.DateFormat;
import java.sql.Timestamp;

import edu.csupomona.cs480.data.Event;
import edu.csupomona.cs480.data.provider.EventList;

public class EventListHelper {
	/**
	 * @param events
	 * @return EventList of free times up to a week
	 */
	/*
	public static EventList findFreeTimes(EventList events) {
		EventList freeTimes = findFreeTimes(events, 0, 0, 1, 0, 0, 0);
		return freeTimes;
	}
	
	/**
	 * 
	 * @param events
	 * @param weeks
	 * @param days
	 * @param hours
	 * @param minutes
	 * @return EventList of Events that represent free time up to the amount of time specified
	 * by the parameters, weeks, days, hours, minutes from current time
	 */
	/*
	public static EventList findFreeTimes(EventList events, int years, int months, int weeks, int days, int hours, int minutes) {
		// TODO Auto-generated method stub
		EventList freeTimes = new EventList();
		String[] startTime = getCurrentTime();
		String[] stopTime = addTime(startTime, years, months, weeks, days, hours, minutes);
		
		for(int i = 0; i < events.size(); i++) {
			Event e = events.get(i);
			
			String event1EndTime = e.getEndTime();
			String event2StartTime = e.getStartTime();
			String startDay = e.getStartDate();
			String endDay = e.getEndDate();
			
		}
		return freeTimes;
	}
	*/

	/**
	 * @param time is in the format
		 * time[0] is month/day/year
		 * time[1] is hour:min
	 * @return int[] of {month, day, year, hour, min}
	 */
	public static int[] convertTimeStringArrayToIntArray(String[] time) {
		String[] monthDayYear = time[0].split("/");
		String[] hourMinutes = time[1].split(":");
		int[] times = new int[5];
		
		times[0] = Integer.getInteger(monthDayYear[0]);
		times[1] = Integer.getInteger(monthDayYear[1]);
		times[2] = Integer.getInteger(monthDayYear[2]);
		
		times[3] = Integer.getInteger(hourMinutes[0]);
		times[4] = Integer.getInteger(hourMinutes[1]);
		
		return times;
	}
	
	public static String[] addTime(String[] startTime, int years, int months, int weeks, int days, int hours,
			int minutes) {
		int[] times = convertTimeStringArrayToIntArray(startTime);
		times = addTime(times, years, months, weeks, days, hours, minutes);
		String mmDDYY = months + "/" + days + "/" + years;
		String hhMM = hours + ":" + minutes; 
		
		String[] calculated = {mmDDYY, hhMM};
		return calculated;
	}
	
	
	public static String[] formatIntArrayToStringArray(int[] time) {
		String mmDDYY = time[0] + "/" + time[1] + "/" + time[2];
		String hhMM = time[3] + ":" + time[4]; 
		
		String[] calculated = {mmDDYY, hhMM};
		return calculated;
	}
	/**
	 * 
	 * @param times
	 * @param years
	 * @param months
	 * @param weeks
	 * @param days
	 * @param hours
	 * @param minutes
	 * @return int[] of {month, day, year, hour, min}
	 */
	public static int[] addTime(int[] times, int years, int months, int weeks, int days, int hours, int minutes) {
		months += times[0];
		days += times[1];
		years += times[2];
		
		hours += times[3];
		minutes += times[4];
		
		if(minutes >= 60) {
			hours += minutes/60;
			minutes = minutes%60;
		}
		if(hours >= 24) {
			days += hours/24;
			hours = hours%24;
		}
		if(days >= 7) {
			weeks += days/7;
			days = days%7;
		}
		if(weeks >= 4) {
			months += weeks/4;
			weeks = weeks%4;
		}
		if(months >= 12) {
			years += months/12;
			months = months%12;
		}
		
		int[] val = {months, days, years, hours, minutes};
		return val;
	}

	//check if time slot is occupied by any of the events in an EventList
	public static boolean timeOccupied(EventList events, String timeSlot) {
		Event e2 = convertStringToEvent(timeSlot);
		for(int i = 0; i <  events.size(); i++) {
			Event e = events.get(i);
			boolean hasConflict = hasTimeConflict(e, e2);
			if(hasConflict) {
				return true;
			}
		}
		return false;
	}
	
	private static Event convertStringToEvent(String timeSlot) {
		// TODO Auto-generated method stub
		return null;
	}

	//check if events have a time conflict
	public static boolean hasTimeConflict(Event event, Event event2) {
		int comparison = event.compareTo(event2);
		if(comparison == -1) { 
			//event starts before event2
			if(hasOverlap(event.getEndTime(),event2.getStartTime())) {
				return true;
			}
			else {
				return false;
			}
		}
		else if(comparison == 0){
			//event and event2 start at the same time
			if(hasOverlap(event.getStartTime(), event2.getStartTime())) {
				return true;
			}
			else {
				return false;
			}
		}
		else {
			//event 2 starts before event
			if(hasOverlap(event2.getEndTime(), event.getStartTime())) {
				return true;
			}
			else {
				return false;
			}
		}
	}
	
	public static boolean hasOverlap(String endTime, String startTime) {
		// {hh, mm}
		int[] t1 = convertToHoursAndMinutes(endTime);
		int[] t2 = convertToHoursAndMinutes(startTime);
		
		if(t1[0] > t2[0]) {
			//hours of endTime is greater than hours of start time
			return true;
		}
		else if(t1[0] == t2[0]) {
			//hours are the same, need to compare minutes
			if(t1[1] > t2[1]) {
				//minutes of endTime is greater than minutes of start time
				return true;
			}
			else if(t1[1] == t2[1]) {
				//minutes are the same
				// end time and start time are continuous
				return false;
			}
			else {
				//minutes of start time are after minutes of end time
				return true;
			}
		}
		else {
			//hours of start time are after hours of end time
			return true;
		}
	}

	public static int[] convertToHoursAndMinutes(String time) {
		int[] times = new int[2];
		String[] arr = time.split(":");
		times[0] = Integer.getInteger(arr[0]);
		times[1] = Integer.getInteger(arr[1]);
		return times;
	}

	public static String[] getCurrentTime() {
		Timestamp currentTime = new Timestamp(System.currentTimeMillis());
		String[] time = parseIntoDateAndTime(currentTime);
		return time;
	}
	
	/**
	 * Parses Time into a String array
	 * array[0] is month/day/year
	 * array[1] is hour:min
	 */
	public static String[] parseIntoDateAndTime(Timestamp s) {
		String[] ret = new String[2];

		String startStr = s.toString();

		String[] splitDT = startStr.split(" ");
		String date = splitDT[0];
		String time = splitDT[1];

		String[] parseDate = date.split("-");
		String year = parseDate[0];
		String month = parseDate[1];
		String day = parseDate[2];

		String[] parseTime = time.split(":");
		String hour = parseTime[0];
		String min = parseTime[1];

		String formDate = month + "/" + day + "/" + year;
		String formTime = hour + ":" + min;
		ret[0] = formDate;
		ret[1] = formTime;
		return ret;
	}
}