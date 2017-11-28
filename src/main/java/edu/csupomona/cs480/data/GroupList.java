package edu.csupomona.cs480.data;

import java.util.ArrayList;


public class GroupList extends ArrayList<GroupUser> {

    @Override
    public boolean contains(Object o) {
        if (!(o instanceof GroupUser)) {
            return false;
        } else {
            GroupUser user = (GroupUser) o;
            for (GroupUser u : this){
                if (u.getId().equals(user.getId())) {
                    return true;
                }
            }
            return false;
        }
    }
}
