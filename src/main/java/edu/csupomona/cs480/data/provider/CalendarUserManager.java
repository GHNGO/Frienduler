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
    userList.putIfAbsent(user, individualUser);
    return true;
  }

  public boolean removeUser(String user) {
    userList.remove(user);
    return true;
  }

  public boolean addGroup(String group) {
    GroupUser groupUser = new GroupUser();
    groupList.putIfAbsent(group, groupUser);
    return true;
  }

  public boolean removeGroup(String group) {
    groupList.remove(group);
    return true;
  }

  public ArrayList<Event> compareSchedule(ArrayList<Event> events) {
    //TODO: implement
    return events;
  }

  public boolean checkTimeAvailableForUser(String user) {
    //TODO: implement
    return true;
  }

  public String getScheduleForUser(String user) {
    //TODO: implement
    String schedule = "";
    return schedule;
  }

  public IndividualUser getUser(String user) {
    IndividualUser individualUser = userList.get(user);
    return individualUser;
  }

  public GroupUser getGroup(String group) {
    GroupUser groupUser = groupList.get(group);
    return groupUser;
  }

  public String getMembersForGroup(String group) {
    String groupMembers = "";
    GroupUser groupValue = null;
    for(String key : groupList.keySet()) {
      if (key == group) {
        groupValue = groupList.get(key);
        break;
      }
    }
    ArrayList<IndividualUser> members = groupValue.getMembers();
    for(IndividualUser u : members) {
      groupMembers += u.getId();
    }
    return groupMembers;
  }

  public boolean addUserToGroup(String user) {
    //TODO: implement. get group user, use addUser method
    return true;
  }

  public boolean removeUserFromGroup(String user) {
    //TODO: implement. get group user, use removeUser method.
    return true;
  }
}
