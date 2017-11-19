package edu.csupomona.cs480.data;

import java.util.ArrayList;

import edu.csupomona.cs480.data.provider.CalendarUserManager;
import edu.csupomona.cs480.data.provider.EventList;

public class PersonOnlineObjectPresenter {

  private IndividualUser currentUser;
  private CalendarUserManager cum;

  public PersonOnlineObjectPresenter(String user) {
    cum = CalendarUserManager.getInstance();
    currentUser = cum.getUser(user);
  }

  public boolean addEvent(String event) {
	Event e = currentUser.findEventByName(event);
	if(e != null) {
		return false;
	}
	currentUser.addEvent(e);
    return true;
  }

  public boolean removeEvent(String event) {
	Event e = currentUser.removeEventByName(event);
	if(e != null) {
		return true;
	}
    return false;
  }

  //compares schedules with s
  public String compareSchedules(String s) {
	ArrayList<CalendarUser> calUsers = new ArrayList<CalendarUser>();
	CalendarUser calUser = cum.getGroup(s);
	if(calUser == null) {
		calUser = cum.getUser(s);
		if(calUser != null) {
			calUsers.add(calUser);
		}
	}
	else {
		calUsers.add(calUser);
	}
	calUsers.add(currentUser);
	EventList e = cum.compareSchedule(calUsers);
	
    return e.toString();
  }

  //calls CalendarUserManager's checkTimeAvailableForUser(on the user parameter)
  public String checkTimeAvailableForUser(String user, String timeSlot) {
    //TODO:
    boolean timeAvailable = cum.checkTimeAvailableForUser(user, timeSlot);
    return ""+timeAvailable;
  }
  
  //calls CalendarUserManager's getScheduleForUser(on the user parameter)
  public String getStringScheduleForUser(String user) {
    //TODO:
    String schedule = cum.getStringScheduleForUser(user);
    return schedule;
  }
  
  public EventList getScheduleForUser(String user) {
	  EventList schedule = cum.getScheduleForUser(user);
	  return schedule;
  }

  public ArrayList<IndividualUser> getFriends() {
    ArrayList<IndividualUser> friends = currentUser.getFriends();
    return friends;
  }
  
  public EventList getSchedule() {
	  return currentUser.getSchedule();
  }
  
  public String getUserId() {
	  return currentUser.getId();
  }
}
