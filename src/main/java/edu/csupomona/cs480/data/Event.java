package edu.csupomona.cs480.data;

public class Event implements Comparable {
	private String name;
	private String startTime;
	private String endTime;
	private String startDate;
	private String endDate;
	
	public Event() {
		
	}
	
	public Event(String name, String startTime, String endTime, String date) {
		this.setName(name);
		this.setStartTime(startTime);
		this.setEndTime(endTime);
		this.startDate = date;
		this.endDate = date;
	}
	
	public Event(String name, String startTime, String endTime, String startDate, String endDate) {
		this.name = name;
		this.startTime = startTime;
		this.endTime = endTime;
		this.startDate = startDate;
		this.endDate = endDate;
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
}