package edu.csupomona.cs480.data;

import edu.csupomona.cs480.data.provider.CalendarUserManager;

import java.util.ArrayList;
import java.util.Arrays;

public class GroupUser extends CalendarUser {
	GroupMembersList members;

	public GroupUser() {
		super("");
		members = new GroupMembersList();
	}

	public GroupUser(String groupId) {
		super(groupId);
		members = new GroupMembersList();
	}

	public GroupUser(String groupId, String users) {
		this(-1, groupId, users);
	}

	public GroupUser(int id, String groupId, String users) {
		super(groupId);
		members = new GroupMembersList();
		if (!users.equals("{}")) {
			String[] s = users.split("[{},]");
			System.out.println(Arrays.toString(s));
			for (int i = 1; i < s.length; i++) {
				IndividualUser u = CalendarUserManager.getInstance().getUser(Integer.parseInt(s[i]));
				if (u != null) {
					members.add(u);
				}
			}
		}

	}

	public GroupUser(String groupId, IndividualUser... ind) {
		this(-1, groupId, ind);
	}

	public GroupUser(int id, String groupId, IndividualUser... ind) {
		super(groupId);
		members = new GroupMembersList();
		for (IndividualUser i: ind) {
			addUser(i);
		}
	}

	public GroupUser(String groupId, ArrayList<IndividualUser> userList) {
		this(-1, groupId, userList);
	}

	public GroupUser(int id, String groupId, ArrayList<IndividualUser> userList) {
		super(groupId);
		members = (GroupMembersList) userList;
		this.idNum = id;
	}
	
	public boolean addUser(IndividualUser ind) {
		if (this.getMembers().contains(ind)) {
			return false;
		} else {
			members.add(ind);
			return true;
		}
//		int index = findindexofuser(ind);
//		if(index == -1) {
//			members.add(ind);
//			return true;
//		}
//		return false;
	}
	
	public String removeUser(IndividualUser ind) {
		if (members.contains(ind)) {
			members.remove(ind);
			return "" + ind.getId() + " was successfully removed from group " + this.id;
		} else {
			return "" + ind.getId() + " was not a member of group " + this.getId();
		}
	}

	private int findIndexOfUser(IndividualUser ind) {
		return members.indexOf(ind);
	}

	public GroupMembersList getMembers() {
		return members;
	}

	@Override
	public String toString() {
		return "Group: ID=" + id + "; Members: " + members;
	}
}
