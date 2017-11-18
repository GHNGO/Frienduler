package edu.csupomona.cs480.data;

import edu.csupomona.cs480.data.provider.EventList;

public abstract class CalendarUser extends User implements CalendarUserInterface{
	private EventList  schedule;
	
	public CalendarUser(String id) {
		super(id);
		schedule = new EventList();
	}
	
	@Override
	public boolean eventScheduled(Event e) {
		int index = schedule.indexOf(e);
		if(index == -1)
			return false;
		return true;
	}

	@Override
	public boolean addEvent(Event e) {
		if(eventScheduled(e))
			return false;
		int indexBelong = findIndexWhereEventBelongs(e);
		schedule.add(indexBelong, e);
		return true;
	}

	private int findIndexWhereEventBelongs(Event e) {
		for(int i = 0; i < schedule.size(); i++) {
			Event current = schedule.get(i);
			int result = e.compareTo(current);
			if(result == 0) {
				//Event and current are at the same times
				//Can't add event due to scheduling conflict
				return -1;
			}
			else if(result < 0) {
				//Event is before result
				return i;
			}
			else {
				//Event is after result
			}
		}
		//Event is after all the events in schedule
		return schedule.size();
	}

	@Override
	public boolean removeEvent(Event e) {
		return schedule.remove(e);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public EventList getSchedule() {
		System.out.println( "[CalendarUser] Num of Events: " + schedule.size() );
        return schedule;
    }
	
	public EventList setSchedule(EventList schedule) {
		EventList oldschedule = this.schedule;
		this.schedule = schedule;
		return oldschedule;
    }
	
	public Event removeEventByName(String eventName) {
		int index = findEventIndexByName(eventName);
		if(index != -1)
			return schedule.remove(index);
		return null;
	}
	
	public Event findEventByName(String eventName) {
		int index = findEventIndexByName(eventName);
		if(index != -1) {
			return schedule.get(index);
		}
		return null;
	}
	
	
	private int findEventIndexByName(String eventName) {
		int index = -1;
		for(int i = 0; i < schedule.size(); i++) {
			Event e = schedule.get(i);
			if(e == null)
				return index;
			else if (e.getName().equals(eventName))
				return i;
		}
		return index;
	}
}
