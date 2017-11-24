package edu.csupomona.cs480.data;

import edu.csupomona.cs480.data.provider.EventList;

public abstract class CalendarUser extends User implements CalendarUserInterface{
	private EventList schedule;
	
	public CalendarUser(String id) {
		super(id);
		schedule = new EventList();
	}
	
	@Override
	public boolean eventInSchedule(Event e) {
		return schedule.contains(e);
	}

	@Override
	public boolean addEvent(Event e) {
		if(eventInSchedule(e))
			return false;
		schedule.add(e);
		schedule.sort();
		return true;
	}

//	private int findIndexWhereEventBelongs(Event e) {
//		for(int i = 0; i < schedule.size(); i++) {
//			Event current = schedule.get(i);
//			int result = e.compareTo(current);
//			if(result == 0) {
//				//Event and current are at the same times
//				//Can't add event due to scheduling conflict
//				return -1;
//			}
//			else if(result < 0) {
//				//Event is before result
//				return i;
//			}
//			else {
//				//Event is after result
//			}
//		}
//		//Event is after all the events in schedule
//		return schedule.size();
//	}

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
	

	

}
