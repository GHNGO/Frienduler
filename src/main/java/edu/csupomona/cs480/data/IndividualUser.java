package edu.csupomona.cs480.data;

import edu.csupomona.cs480.App;
import edu.csupomona.cs480.data.provider.CalendarUserManager;
import edu.csupomona.cs480.data.provider.EventList;

import java.util.ArrayList;

public class IndividualUser extends CalendarUser{

	private String firstName;
	private String lastName;

	ArrayList<String> list;

	FriendsList friends;
	ArrayList<GroupUser> groupsJoined;

	public IndividualUser(int idNum, String id, String firstName, String lastName, String friends) {
		super(id);
		this.firstName = firstName;
		this.lastName = lastName;
		this.idNum = idNum;

		list = new ArrayList<>();
		FriendsList friendsList = new FriendsList();

		if (friends.equals("{}")) {
			this.friends = friendsList;
		} else {
			String[] s = friends.split("[{},]");
			for (int i = 1; i < s.length; i++) {
				list.add(App.sqlInterface().getUserNameFromIdNum(Integer.parseInt(s[i])));
//				if (u != null) {
//					friendsList.add(u);
//				}
			}
		}

		this.friends = friendsList;
		this.groupsJoined = new ArrayList<>();
	}

	public IndividualUser(int idNum, String id, String firstName, String lastName, FriendsList friends) {
		super(id);
		this.firstName = firstName;
		this.lastName = lastName;
		this.idNum = idNum;
		this.friends = friends;
		this.groupsJoined = new ArrayList<>();
	}

	public IndividualUser(int idNum, String id, String firstName, String lastName, String friends, EventList schedule) {
		super(id);
		this.firstName = firstName;
		this.lastName = lastName;
		this.idNum = idNum;

		FriendsList friendsList = new FriendsList();

		if (friends.equals("{}")) {
			this.friends = friendsList;
		} else {
			String[] s = friends.split("[{},]");
			for (int i = 1; i < s.length; i++) {
				IndividualUser u = CalendarUserManager.getInstance().getUser(Integer.parseInt(s[i]));
				if (u != null) {
					friendsList.add(u);
				}
			}
		}

		this.friends = friendsList;
		this.setSchedule(schedule);
		this.groupsJoined = new ArrayList<>();
	}
	
	public IndividualUser(int idNum, String id, String firstName, String lastName) {
		super(id);
		this.firstName = firstName;
		this.lastName = lastName;
		this.idNum = idNum;
		generateNewLists();
	}

	public IndividualUser(String id, String firstName, String lastName) {
		this(-1, id, firstName, lastName);
	}

	public IndividualUser(int idNum, String id) {
		this(idNum, id, "", "");
		generateNewLists();
	}
	
	public IndividualUser(String id) {
		this(id, "", "");
	}
	
	private void generateNewLists() {
		friends = new FriendsList();
		groupsJoined = new ArrayList<GroupUser>();
	}
	
	public FriendsList getFriends() {
		if (list != null) {
			for (String s : list) {
				friends.add(CalendarUserManager.getInstance().getUser(s));
			}
		}

		return friends;
	}
	public void setFriends(FriendsList friends) {
		this.friends = friends;
	}
	public ArrayList<GroupUser> getGroupsJoined() {
		return groupsJoined;
	}
	public void setGroupsJoined(ArrayList<GroupUser> groupsJoined) {
		this.groupsJoined = groupsJoined;
	}
	
	public boolean addFriend(IndividualUser ind) {
		return !friends.contains(ind) && friends.add(ind);
	}
	
	public boolean removeFriend(IndividualUser ind) {
		return friends.contains(ind) && friends.remove(ind);
	}

	private int findIndexOfUser(IndividualUser ind) {
		return friends.indexOf(ind);
	}

	@Override
	public String toString() {
		return "User: ID: " + id + "; Full Name: " + firstName + " " + lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Override
	public boolean equals(Object obj) {
		IndividualUser userComp = (IndividualUser) obj;

		return userComp.id == this.id;
	}
}
