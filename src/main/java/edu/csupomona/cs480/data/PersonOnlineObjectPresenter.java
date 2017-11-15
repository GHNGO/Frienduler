package edu.csupomona.cs480.data;

import java.util.ArrayList;

import edu.csupomona.cs480.data.provider.CalendarUserManager;

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

  public String compareSchedules(String s) {
    //TODO:
    return s;
  }

  public String checkTimeAvailableForUser(String user) {
    //TODO:
    String avail = "";
    return avail;
  }

  //user is 
  public String getScheduleForUser(String user) {
    //TODO:
    String schedule = "";
    return schedule;
  }

  // Return List of CalendarUsers instead?
  public ArrayList<IndividualUser> getFriends() {
    ArrayList<IndividualUser> friends = currentUser.getFriends();
    return friends;
  }
}
