package edu.csupomona.cs480.data;

import java.util.ArrayList;


public class FriendsList extends ArrayList<IndividualUser> {

    @Override
    public String toString() {
        String m = "{";
        if (this.size() >= 2) {
            for (int i = 0; i < this.size() - 1; i++) {
                m = m + "" + this.get(i).getIdNum();
                m = m + ",";
            }
            m = m + "" + this.get(this.size() - 1).getIdNum();
        } else if (this.size() == 1) {
            m = m + "" + this.get(0).getIdNum();
        }
        m = m + "}";
        return m;
    }

    @Override
    public boolean contains(Object o) {
        IndividualUser compared = (IndividualUser) o;

        for (IndividualUser u: this) {
            if (u.getId().equals(compared.getId())) {
                return true;
            }
        }
        return false;
    }

    public void sort() {
        super.sort((o1, o2) -> {
            if (o1.idNum < o2.idNum) return -1;
            else if (o1.idNum > o2.idNum) return 1;
            else return 0;
        });
    }

    @Override
    public boolean add(IndividualUser user) {
        System.out.println("Adding " + user.id + " to friends list");
        return super.add(user);
    }
}
