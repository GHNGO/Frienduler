package edu.csupomona.cs480.data.provider;

import edu.csupomona.cs480.App;
import edu.csupomona.cs480.data.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Locale;

import edu.csupomona.cs480.database.DatabaseInterface;
import freemarker.template.Configuration;
import freemarker.template.Version;

public class CalendarUserManager {

  private static final CalendarUserManager instance = new CalendarUserManager();

//  private HashMap<String, IndividualUser> userList;
  private HashSet<String> userNameList;
//  private HashMap<String, GroupUser> groupList;
  private HashSet<String> groupNameList;

  private DatabaseInterface databaseInterface;

  private CalendarUserManager() {
	//This should be the ONLY instance of the cfg file. DO NOT create it anywhere else!
	Configuration cfg = new Configuration(Configuration.VERSION_2_3_27);
	//@todo Create lib folder to store templates
	cfg.setClassForTemplateLoading( CalendarUserManager.class, "templates" );
	cfg.setIncompatibleImprovements( new Version( 2, 3, 20 ) );
	cfg.setLocale(Locale.US);
	databaseInterface = App.sqlInterface();
  }

  public static CalendarUserManager getInstance() {
    return instance;
  }

  /**
   * Adds a blank user
   * @param user user name to add
   * @return <code>true</code> if added new, <code>false</code> if already existed
   */
  public boolean addUser(String user) {
    if (!userNameList.contains(user)) {
      userNameList.add(user);
      return true;
    } else {
      return false;
    }
//    if (!userList.containsKey(user)) {
//      userList.put(user, new IndividualUser(user));
//      return true;
//    } else {
//      return false;
//    }
  }

  public boolean removeUser(String user) {
//    if(!userList.containsKey(user)) {
      return false;
//    }
//    userList.remove(user);
//    return true;
  }

  public boolean addGroup(String group) {
    GroupUser groupUser = new GroupUser();
//    GroupUser g = groupList.putIfAbsent(group, groupUser);
//    if (g == null) {
//      return false;
//    }
    return true;
  }

  public boolean removeGroup(String group) {
//    if (!groupList.containsKey(group)) {
//      return false;
//    }
//    groupList.remove(group);
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
//    iUser = userList.get(user);
//    ArrayList<Event> userSchedule = iUser.getSchedule();
//    for (Event e : userSchedule) {
//      schedule += e.toString();
//      schedule += "\n";
//    }
    return schedule;
  }

  public IndividualUser getUser(String user) {
//    return userList.get(user);
    return null;
  }

  public GroupUser getGroup(String group) {
//    return groupList.get(group);
    return null;
  }

  public String getMembersForGroup(String group) {
    String groupMembers = "";
//    GroupUser groupValue = groupList.get(group);
//    ArrayList<IndividualUser> members = groupValue.getMembers();
//    for(IndividualUser u : members) {
//      groupMembers += u.getId();
//      groupMembers += ", ";
//    }
    return groupMembers;
  }

  public boolean addUserToGroup(String user, String group) {
//    GroupUser g = groupList.get(group);
//    IndividualUser i = userList.get(user);
//    return g.addUser(i);
    return false;
  }

  public boolean removeUserFromGroup(String user, String group) {
//    GroupUser g = groupList.get(group);
//    IndividualUser i = userList.get(user);
//    return g.removeUser(i);
    return false;
  }
}
