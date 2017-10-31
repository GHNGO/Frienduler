package edu.csupomona.cs480.data;

public class Event {
	private String name;
	private String startTime;
	private String endTime;
	private String date;
	
	public Event() {
		
	}
	public Event(String name, String startTime, String endTime, String date) {
		this.setName(name);
		this.setStartTime(startTime);
		this.setEndTime(endTime);
		this.setDate(date);
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
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}

	@Override
	public String toString() {
		String event = "";
		event = name + ": " + date + ", " + startTime + " - " + endTime;
		return event;
	}
}