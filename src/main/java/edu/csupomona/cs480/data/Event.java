package edu.csupomona.cs480.data;

import edu.csupomona.cs480.database.MalformedEventException;

import java.sql.Time;
import java.sql.Timestamp;
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

	@Override
	public String toString() {
		String event = "";
		event = name + ": " + ", " + startTime + " - " + endTime;
		return event;
	}
	@Override
	public int compareTo(Object arg0) {
		// TODO Auto-generated method stub
		return 0;
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