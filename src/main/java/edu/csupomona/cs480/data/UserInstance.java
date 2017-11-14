package edu.csupomona.cs480.data;

import edu.csupomona.cs480.data.provider.CalendarUserManager;

public class UserInstance {

  private IndividualUser currentUser;
  private CalendarUserManager cum;

  public UserInstance(String user) {
    cum = CalendarUserManager.getInstance();
    currentUser = cum.getUser(user);
  }

  public boolean addEvent(String event) {
    //TODO:
    return true;
  }

  public boolean removeEvent(String event) {
    //TODO:
    return true;
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

  public String getScheduleForUser(String user) {
    //TODO:
    String schedule = "";
    return schedule;
  }

  // Return List of CalendarUsers instead?
  public String getFriends(String s) {
    //TODO:
    String friends = "";
    return friends;
  }
}
