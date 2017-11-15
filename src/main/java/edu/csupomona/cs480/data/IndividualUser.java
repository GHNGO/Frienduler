package edu.csupomona.cs480.data;

import java.util.ArrayList;

public class IndividualUser extends CalendarUser{

	private String firstName;
	private String lastName;

	ArrayList<IndividualUser> friends;
	ArrayList<GroupUser> groupsJoined;
	
	public IndividualUser(String id, String firstName, String lastName) {
		super(id);
		this.firstName = firstName;
		this.lastName = lastName;
		generateNewLists();
	}
	
	public IndividualUser(String id) {
		this(id, "", "");
		generateNewLists();
	}
	
	private void generateNewLists() {
		friends = new ArrayList<IndividualUser>();
		groupsJoined = new ArrayList<GroupUser>();
	}
	
	public ArrayList<IndividualUser> getFriends() {
		return friends;
	}
	public void setFriends(ArrayList<IndividualUser> friends) {
		this.friends = friends;
	}
	public ArrayList<GroupUser> getGroupsJoined() {
		return groupsJoined;
	}
	public void setGroupsJoined(ArrayList<GroupUser> groupsJoined) {
		this.groupsJoined = groupsJoined;
	}
	
	public boolean addFriend(IndividualUser ind) {
		int index = findIndexOfUser(ind);
		if(index == -1) {
			friends.add(ind);
			return true;
		}
		return false;
	}
	
	public boolean removeFriend(IndividualUser ind) {
		int index = findIndexOfUser(ind);
		if(index == -1) {
			return false;
		}
		friends.remove(index);
		return true;
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
}
