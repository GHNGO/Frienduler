package edu.csupomona.cs480.data.provider;

import edu.csupomona.cs480.App;
import edu.csupomona.cs480.data.*;
import edu.csupomona.cs480.database.DatabaseInterface;
import edu.csupomona.cs480.util.EventListHelper;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Locale;

import edu.csupomona.cs480.database.DatabaseInterface;
import edu.csupomona.cs480.database.MalformedEventException;
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
	//@todo Create lib folder to store templates
	databaseInterface = App.sqlInterface();
	userNameList = new HashSet<String>();
	groupNameList = new HashSet<String>();
	ArrayList<IndividualUser> invUsers = databaseInterface.listAllUsers();
	for( IndividualUser user: invUsers ) {
		userNameList.add( user.getId() );
	}
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
   * @param group group name to add
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
    } else {
      //group does exist already
      groupNameList.remove(group);
      databaseInterface.deleteGroup(group);
      return true;
    }
  }

  /**
   * calculate the times based off increments of 30 minutes
   * @param users
   * @return EventList of times free for all users
   */
  
  public EventList compareSchedule(ArrayList<CalendarUser> users) {
	int hours = 0;
	int minutes = 30;
	int daysToCheck = 7;
	EventList freeTimeSlots = findFreeTimes(users, daysToCheck, hours, minutes);
    return freeTimeSlots;
  }

  public EventList findFreeTimes(ArrayList<CalendarUser> users, int daysToCheck, int intervalHours, int intervalMinutes) {
	EventList freeTimeSlots = new EventList();
	intervalMinutes = intervalMinutes + intervalHours * 60;
	String[] startTime = EventListHelper.getCurrentTime();
	//int[] of {month, day, year, hour, min}
	int[] timeSlotBegin = EventListHelper.convertTimeStringArrayToIntArray(startTime);
	int[] timeSlotEnd = EventListHelper.addTime(timeSlotBegin, 0, 0, 0, 0, intervalHours, intervalMinutes);
	
	int numMinutes = 24 * 2 * 30 * daysToCheck;
	for(int j = 0; j < numMinutes; j+=30) {
		//calculate time slot
		timeSlotBegin = EventListHelper.addTime(timeSlotBegin, 0, 0, 0, 0, 0, j);
		
		// Generate Event Representing Free Time
		// String[] formatted as MM/DD/YY, HH:MM
		String[] beginTime = EventListHelper.formatIntArrayToStringArray(timeSlotBegin);
		String[] endTime = EventListHelper.formatIntArrayToStringArray(timeSlotEnd);
		//Event e = new Event("Free Time", start, end, date);
		Event e = new Event("Free Time", beginTime[1], endTime[1], beginTime[0]);
		
		boolean allUsersFree = true;
		for(int k = 0; k < users.size(); k++) {
			boolean currentUserFree = users.get(k).isUserFreeAtTime(e);
			if(!currentUserFree) {
				allUsersFree = false;
				k = users.size();
			}
		}
		
		if(allUsersFree) {
			freeTimeSlots.add(e);
		}
	}

	return freeTimeSlots;
}

public boolean checkTimeAvailableForUser(String user, String timeSlot) {
	IndividualUser us = getUser(user);
	if(us != null) {
		return EventListHelper.timeOccupied(us.getSchedule(), timeSlot);
	}
	else {
		return false;
	}
  }

  public String getStringScheduleForUser(String user) {
    String schedule = "";
    IndividualUser iUser = getUser(user);
    //user exists
    if(iUser != null) {
    	EventList events = iUser.getSchedule();
    	schedule = events.toString();
    }
    return schedule;
  }
  
  public EventList getScheduleForUser( String user ) {
	  IndividualUser iUser = getUser( user );
	  if(iUser != null ) {
	      return databaseInterface.getEvents(user);
	      //		  return iUser.getSchedule();
	  }
	  return null;
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
    GroupUser groupValue = getGroup(group);
    //if group exists
    if(groupValue != null) {
      GroupMembersList members= groupValue.getMembers();
      groupMembers = members.toString();
    }
    return groupMembers;
  }
  
  public ArrayList<IndividualUser> getAllUsers(){
	  return databaseInterface.listAllUsers();
  }

  public boolean addUserToGroup(String user, String group) {
    GroupUser g = getGroup(group);
    IndividualUser i = getUser(user);
    if(g != null && i != null) {
    	g.addUser(i);
    	databaseInterface.updateGroup(g);
    	return true;
    }
    return false;
  }

  public boolean removeUserFromGroup(String user, String group) {
    GroupUser g = getGroup(group);
    //group exists
    if(g != null) {
    	IndividualUser u = getUser(user);
    	// user exists and user is member of group
    	if (u != null && databaseInterface.getGroupsMemberOf(u).contains(g)) {
    	    boolean b = g.removeUser(u);
    	    databaseInterface.updateGroup(g);
            return b;
        } else {
    	    return false;
        }
    }
    return false;
  }
  
  
 public PersonOnlineObjectPresenter generatePersonOnlineObjectPresenter(String id) {
	 IndividualUser i = getUser(id);
	 //Do not create a PersonOnlineObjectPresenter 
	 //if the user id does not correspond to a user
	 if(i == null) {
		 return null;
	 }
	 PersonOnlineObjectPresenter userInstance= new PersonOnlineObjectPresenter(id);
	 
	 return userInstance;
 }

 public boolean addEvent(String userId, Event e) throws MalformedEventException {
      IndividualUser user = getUser(userId);
      if (user == null) {
          return false;
      } else {
          boolean b = user.addEvent(e);
          System.out.println(b);
          if (b) {
              databaseInterface.addEvent(e);
              return true;
          } else {
              return false;
          }
      }
 }

 public boolean removeEvent(String userId, String eventName) {
      IndividualUser user = getUser(userId);
      if (user == null) {
          return false;
      } else {
          Event e = databaseInterface.getEvent(userId, eventName);
          if (e != null) {
              databaseInterface.deleteEvent(userId, eventName);
              return user.removeEvent(e);
          } else {
              return false;
          }
      }
 }
}
