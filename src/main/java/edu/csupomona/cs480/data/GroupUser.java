package edu.csupomona.cs480.data;

import java.util.ArrayList;

public class GroupUser extends CalendarUser {
	ArrayList<IndividualUser> members;
	
	public GroupUser() {
		super("");
		members = new ArrayList<IndividualUser>();
	}
	
	
	public GroupUser(String id, IndividualUser ind) {
		super(id);
		members = new ArrayList<IndividualUser>();
		addUser(ind);
	}
	
	public boolean addUser(IndividualUser ind) {
		int index = findIndexOfUser(ind);
		if(index == -1) {
			members.add(ind);
			return true;
		}
		return false;
	}
	
	public boolean removeUser(IndividualUser ind) {
		int index = findIndexOfUser(ind);
		if(index == -1) {
			return false;
		}
		members.remove(index);
		return true;
	}

	private int findIndexOfUser(IndividualUser ind) {
		return members.indexOf(ind);
	}

	public ArrayList<IndividualUser> getMembers() {
		return members;
	}
}
