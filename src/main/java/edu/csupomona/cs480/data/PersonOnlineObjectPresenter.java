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
  public String checkTimeAvailableForUser(String user) {
    //TODO:
    String avail = "";
    boolean timeAvailable = cum.checkTimeAvailableForUser(user);
    avail += timeAvailable;
    return avail;
  }
  
  //calls CalendarUserManager's getScheduleForUser(on the user parameter)
  public String getScheduleForUser(String user) {
    //TODO:
    String schedule = cum.getScheduleForUser(user);
    return schedule;
  }

  public ArrayList<IndividualUser> getFriends() {
    ArrayList<IndividualUser> friends = currentUser.getFriends();
    return friends;
  }
}
