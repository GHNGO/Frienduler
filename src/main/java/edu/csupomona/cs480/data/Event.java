package edu.csupomona.cs480.data;

import edu.csupomona.cs480.database.MalformedEventException;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

public class Event implements Comparable {
	private String name;

	private String linkedUserId;

	/**
	 * Formatted as hh:mm
	 */
	private String startTime;
	private String endTime;

	/**
	 * Formatted as MM/DD/YY
	 */
	private String startDate;
	private String endDate;


	public Event(String linkedUserId, String name, Timestamp start, Timestamp end) throws MalformedEventException {
		String[] s = parseIntoDateAndTime(start);
		String[] e = parseIntoDateAndTime(end);

		this.linkedUserId = linkedUserId;
		this.name = name;

		this.startTime = s[1];
		this.endTime = e[1];
		this.startDate = s[0];
		this.endDate = e[0];

	}
	
	public Event(String linkedUserId, String name, int startMonth, int startDay, int startYear, int startHour, int startMinute, int endMonth, int endDay, int endYear, int endHour, int endMinute) {
		String stMonth = (startMonth < 10) ? ("0" + String.valueOf(startMonth)) : (String.valueOf(startMonth));
		String stDay = (startDay < 10) ? ("0" + String.valueOf(startDay)) : (String.valueOf(startDay));
		String stYear = (startYear < 10) ? ("000" + String.valueOf(startYear)) : (startYear < 100) ? "00" + String.valueOf(startYear) : (startYear < 1000) ?  "0" + String.valueOf(startYear) : String.valueOf(startYear);
		String stHr = (startHour < 10) ? ("0" + String.valueOf(startHour)) : (String.valueOf(startHour));
		String stMin = (startMinute < 10) ? ("0" + String.valueOf(startMinute)) : (String.valueOf(startMinute));
		String endMo = (endMonth < 10) ? ("0" + String.valueOf(endMonth)) : (String.valueOf(endMonth));
		String endD = (endDay < 10) ? ("0" + String.valueOf(endDay)) : (String.valueOf(endDay));
		String endY = (endYear < 10) ? ("000" + String.valueOf(endYear)) : (endYear < 100) ? "00" + String.valueOf(endYear) : (endYear < 1000) ?  "0" + String.valueOf(endYear) : String.valueOf(endYear);
		String endHr = (endHour < 10) ? ("0" + String.valueOf(endHour)) : (String.valueOf(endHour));
		String endMin = (endMinute < 10) ? ("0" + String.valueOf(endMinute)) : (String.valueOf(endMinute));

		this.linkedUserId = linkedUserId;
		this.name = name;
		this.startTime = "" + stHr + ":" + stMin;
		this.startDate = "" + stMonth + "/" + stDay + "/" + stYear;
		this.endTime = "" + endHr + ":" + endMin;
		this.endDate = "" + endMo + "/" + endD + "/" + endY;
		
	}

	public Event(String linkedUserId, String name, String startMonth, String startDay, String startYear, String startHour, String startMinute, String endMonth, String endDay, String endYear, String endHour, String endMinute ) {
		this.linkedUserId = linkedUserId;
		this.name = name;
		this.startTime = "" + startHour + ":" + startMinute;
		this.startDate = "" + startMonth + "/" + startDay + "/" + startYear;
		this.endTime = "" + endHour + ":" + endMinute;
		this.endDate = "" + endMonth + "/" + endDay + "/" + endYear;
	}

	public Event(String linkedUserId, String name, String startTime, String endTime, String startDate, String endDate) throws MalformedEventException {
		this.linkedUserId = linkedUserId;
		this.name = name;
		if (startDate.matches("[0-1][0-9]/[0-3][0-9]/[0-9][0-9][0-9][0-9]")) {
			this.startDate = startDate;
		} else {
			throw new MalformedEventException();
		}
		if (endDate.matches("[0-1][0-9]/[0-3][0-9]/[0-9][0-9][0-9][0-9]")) {
			this.endDate = endDate;
		} else {
			throw new MalformedEventException();
		}
		if (startTime.matches("^[0-1][0-9][:][0-9][0-9]$|^[0-9][:][0-9][0-9]$")) {
			this.startTime = startTime;
		} else {
			throw new MalformedEventException();
		}
		if (endTime.matches("^[0-1][0-9][:][0-9][0-9]$|^[0-9][:][0-9][0-9]$")) {
			this.endTime = endTime;
		} else {
			throw new MalformedEventException();
		}
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String date) {
		this.startDate = date;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String date) {
		this.endDate = date;
	}
	public String getLinkedUserId() {
		return linkedUserId;
	}
	public void setLinkedUserId(String linkedUserId) {
		this.linkedUserId = linkedUserId;
	}

	/**
	 * Gets the field num for the event start time
	 * @param fieldNum Field num with the convention {month, day, year, hour, minute}
	 * @return The value for the field num as a string
	 */
	public String getStartField(int fieldNum) {
		String[] startSplit = startDate.split("/");
		String[] startTimeSplit = startTime.split(":");
		if (fieldNum < 0) {
			System.err.println("Field Num Invalid");
			return "";
		} else if (fieldNum < 3) {
			return startSplit[fieldNum];
		} else if (fieldNum < 5) {
			return startTimeSplit[fieldNum-3];
		} else {
			System.err.println("Field Num Invalid");
			return "";
		}
	}

	/**
	 * Gets the field num for the event end time
	 * @param fieldNum Field num with the convention {month, day, year, hour, minute}
	 * @return The value for the field num as a string
	 */
	public String getEndField(int fieldNum) {
		String[] endSplit = endDate.split("/");
		String[] endTimeSplit = endTime.split(":");
		if (fieldNum < 0) {
			System.err.println("Field Num Invalid");
			return "";
		} else if (fieldNum < 3) {
			return endSplit[fieldNum];
		} else if (fieldNum < 5) {
			return endTimeSplit[fieldNum-3];
		} else {
			System.err.println("Field Num Invalid");
			return "";
		}
	}

	@Override
	public String toString() {
		String event = "";
		event = name + ": " + ", " + startTime + " - " + endTime;
		return event;
	}

	/**
	 * Compares start times
	 * @param arg0 Event to compare to
	 * @return -1 if this event starts before the compared one, 0 if this event starts at the same time, 1 if this event
	 * starts after the compared one
	 */
	@Override
	public int compareTo(Object arg0) {
		Event compared = (Event) arg0;
		int thisStartYear = Integer.parseInt(this.getStartField(2));
		int thisStartMonth = Integer.parseInt(this.getStartField(0));
		int thisStartDay = Integer.parseInt(this.getStartField(1));
		int thisStartHour = Integer.parseInt(this.getStartField(3));
		int thisStartMinute = Integer.parseInt(this.getStartField(4));

		int comparedStartYear = Integer.parseInt(compared.getStartField(2));
		int comparedStartMonth = Integer.parseInt(compared.getStartField(0));
		int comparedStartDay = Integer.parseInt(compared.getStartField(1));
		int comparedStartHour = Integer.parseInt(compared.getStartField(3));
		int comparedStartMinute = Integer.parseInt(compared.getStartField(4));

		Calendar thisC = Calendar.getInstance();
		thisC.set(thisStartYear, thisStartMonth, thisStartDay, thisStartHour, thisStartMinute);
		Date thisDate = thisC.getTime();

		Calendar compC = Calendar.getInstance();
		compC.set(comparedStartYear, comparedStartMonth, comparedStartDay, comparedStartHour, comparedStartMinute);
		Date comparedDate = compC.getTime();

		return thisDate.compareTo(comparedDate);
	}

	public String[] parseIntoDateAndTime(Timestamp s) {
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