package edu.csupomona.cs480.data.provider;

import edu.csupomona.cs480.data.CalendarUser;
import edu.csupomona.cs480.data.Event;
import edu.csupomona.cs480.data.GroupUser;
import edu.csupomona.cs480.data.IndividualUser;

import java.util.ArrayList;
import java.util.HashMap;

public class CalendarUserManager {

  private static final CalendarUserManager instance = new CalendarUserManager();

  private HashMap<String, IndividualUser> userList;
  private HashMap<String, GroupUser> groupList;

  private CalendarUserManager() {

  }

  public static CalendarUserManager getInstance() {
    return instance;
  }

  public boolean addUser(String user) {
    IndividualUser individualUser = new IndividualUser();
    IndividualUser i = userList.putIfAbsent(user, individualUser);
    if (i == null) {
      return false;
    }
    return true;
  }

  public boolean removeUser(String user) {
    if(!userList.containsKey(user)) {
      return false;
    }
    userList.remove(user);
    return true;
  }

  public boolean addGroup(String group) {
    GroupUser groupUser = new GroupUser();
    GroupUser g = groupList.putIfAbsent(group, groupUser);
    if (g == null) {
      return false;
    }
    return true;
  }

  public boolean removeGroup(String group) {
    if (!groupList.containsKey(group)) {
      return false;
    }
    groupList.remove(group);
    return true;
  }

  public ArrayList<Event> compareSchedule(ArrayList<CalendarUser> users) {
    //TODO: implement
    ArrayList<Event> freeTimeSlots = new ArrayList<>();
    return freeTimeSlots;
  }

  public boolean checkTimeAvailableForUser(String user) {
    //TODO: implement
    return true;
  }

  public String getScheduleForUser(String user) {
    String schedule = "";
    IndividualUser iUser;
    iUser = userList.get(user);
    ArrayList<Event> userSchedule = iUser.getSchedule();
    for (Event e : userSchedule) {
      schedule += e.toString();
      schedule += "\n";
    }
    return schedule;
  }

  public IndividualUser getUser(String user) {
    return userList.get(user);
  }

  public GroupUser getGroup(String group) {
    return groupList.get(group);
  }

  public String getMembersForGroup(String group) {
    String groupMembers = "";
    GroupUser groupValue = groupList.get(group);
    ArrayList<IndividualUser> members = groupValue.getMembers();
    for(IndividualUser u : members) {
      groupMembers += u.getId();
      groupMembers += ", ";
    }
    return groupMembers;
  }

  public boolean addUserToGroup(String user, String group) {
    GroupUser g = groupList.get(group);
    IndividualUser i = userList.get(user);
    return g.addUser(i);
  }

  public boolean removeUserFromGroup(String user, String group) {
    GroupUser g = groupList.get(group);
    IndividualUser i = userList.get(user);
    return g.removeUser(i);
  }
}
