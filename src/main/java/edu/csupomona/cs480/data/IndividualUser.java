package edu.csupomona.cs480.data;

import java.util.ArrayList;

public class IndividualUser extends CalendarUser{
	ArrayList<IndividualUser> friends;
	ArrayList<GroupUser> groupsJoined;
	
	public IndividualUser(String id) {
		super(id);
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

}
