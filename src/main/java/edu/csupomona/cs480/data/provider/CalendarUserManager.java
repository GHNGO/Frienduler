package edu.csupomona.cs480.data.provider;

import edu.csupomona.cs480.App;
import edu.csupomona.cs480.data.*;
import edu.csupomona.cs480.database.DatabaseInterface;

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
   * @param user = user name to add
   * @return <code>true</code> if added new, <code>false</code> if already existed
   */
  public boolean addUser(String user) {
	//check if the user exists already (in hash set)
    if (!userNameList.contains(user)) {
     //user doesn't exist already
      userNameList.add(user);
      databaseInterface.addUser(user);
      return true;
    } 
    else {
      //user does exist already
      return false;
    }
  }

  public boolean removeUser(String user) {
	//check if the user exists already (in hash set)
	if (!userNameList.contains(user)) {
	  //user doesn't exist already
	  return false;
	} 
	else {
      //user exists so we delete them
	  userNameList.remove(user);
	  databaseInterface.deleteUser(user);
	  return true;
	}
  }

  /**
   * Adds a blank group
   * @param group = group name to add
   * @return <code>true</code> if added new, <code>false</code> if already existed
   */
  public boolean addGroup(String group) {
	//check if the group exists already (in hash set)
    if (!groupNameList.contains(group)) {
     //group doesn't exist already
      groupNameList.add(group);
      databaseInterface.addGroup(group);
      return true;
    } 
    else {
      //group does exist already
      return false;
    }
  }

  public boolean removeGroup(String group) {
	//check if the group exists already (in hash set)
    if (!groupNameList.contains(group)) {
      //group doesn't exist already
      return false;
    } 
    else {
      //group does exist already
      groupNameList.remove(group);
      databaseInterface.deleteGroup(group);
      return true;
    }
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
    return schedule;
  }

  public IndividualUser getUser(String user) {
	//check if the user exists already (in hash set)
	if (!userNameList.contains(user)) {
	  //user doesn't exist already
	  return null;
	} 
	else {
      //user exists so we can retrieve them
	  IndividualUser ind = databaseInterface.getUser(user);
	  return ind;
	}
  }

  public GroupUser getGroup(String group) {
	//check if the group exists already (in hash set)
	if (!groupNameList.contains(group)) {
	  //group doesn't exist already
	  return null;
	} 
	else {
      //group exists so we can retrieve them
	  GroupUser grp = databaseInterface.getGroup(group);
	  return grp;
	}
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
    GroupUser g = getGroup(group);
    IndividualUser i = getUser(user);
    if(g != null && i != null) {
    	g.addUser(i);
    	return true;
    }
    return false;
  }

  public boolean removeUserFromGroup(String user, String group) {
    GroupUser g = getGroup(group);
    //group exists
    if(g != null) {
    	IndividualUser u = getUser(user);
    	return g.removeUser(u);
    }
    return false;
  }
}
