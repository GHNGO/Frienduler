package edu.csupomona.cs480.util;

import edu.csupomona.cs480.data.Event;
import edu.csupomona.cs480.data.provider.EventList;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

public class EventListHelper {

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
		
		times[0] = Integer.parseInt(monthDayYear[0]);
		times[1] = Integer.parseInt(monthDayYear[1]);
		times[2] = Integer.parseInt(monthDayYear[2]);
		
		times[3] = Integer.parseInt(hourMinutes[0]);
		times[4] = Integer.parseInt(hourMinutes[1]);
		
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
		int numberOfDays = 30;
		if(days >= numberOfDays) {
			months += days/numberOfDays;
			days = (days%numberOfDays)+1;
		}
		if(months > 12) {
			years += months/12;
			months = (months%12) + 1;
		}
		

		int[] val = {months, days, years, hours, minutes};
		return val;
	}

	private static Calendar convertToCalendar(int[] mdyhm) {
		Calendar cal = Calendar.getInstance();
		cal.set(mdyhm[2], mdyhm[0], mdyhm[1], mdyhm[3], mdyhm[4]);
		return cal;
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

	public static boolean timesAreContinuous(String endTime, String startTime) {
		// {hh, mm}
		int[] t1 = convertToHoursAndMinutes(endTime);
		int[] t2 = convertToHoursAndMinutes(startTime);
		
		if(t1[0] == t2[0]) {
			//hours are the same, need to compare minutes
			if(t1[1] == t2[1]) {
				//minutes are the same
				// end time and start time are continuous
				return true;
			}
			else {
				return false;
			}
		}
		else {
			return false;
		}
	}
	
	public static int[] convertToHoursAndMinutes(String time) {
		int[] times = new int[2];
		String[] arr = time.split(":");
		times[0] = Integer.parseInt(arr[0]);
		times[1] = Integer.parseInt(arr[1]);
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
		String startStr = s.toString();
		return parseIntoDateAndTime(startStr);
	}

	public static String[] parseIntoDateAndTime(String startStr) {
		String[] ret = new String[2];
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
	
	public static EventList mergeContinuousEvents(EventList freeTimeSlots) {
		freeTimeSlots.sort();
		if(freeTimeSlots.size() > 0) {
			Event prev = freeTimeSlots.get(0);
			int count = 0;
			for(int i = 1; i < freeTimeSlots.size(); i ++) {
				Event e = freeTimeSlots.get(i);
				
				if(isContinuous(prev, e)) {
					freeTimeSlots.remove(prev);
					freeTimeSlots.remove(e);
					Event merged = mergeEvents(prev, e);
					freeTimeSlots.add(i-1, merged);
					i--;
					prev = merged;
				}
				else {
					prev = e;
				}
			}
		}
		return freeTimeSlots;
	}

	private static Event mergeEvents(Event prev, Event e) {
		Event ret = new Event(prev.getName(), prev.getStartTime(), e.getEndTime(), prev.getStartDate(), e.getEndDate());
		return ret;
	}

	public static boolean isContinuous(Event prev, Event e) {
		/*
		if(prev.getEndDate().equals(e.getStartDate())){
			return timesAreContinuous(prev.getEndTime(), e.getStartTime());
		}
		else if(daysAreContiuous(prev.getEndDate(),e.getStartDate())) {
			return timesAreContinuous(prev.getEndTime(), e.getStartTime());
		}
		
		else {
			return false;
		}
		*/
		return timesAreContinuous(prev.getEndTime(), e.getStartTime());
	}

	private static boolean daysAreContiuous(String endDate, String startDate) {
		/*
		int[] 
		if(endDate.indexOf("12/31")) {
			
		}
		*/
		return false;
	}
}