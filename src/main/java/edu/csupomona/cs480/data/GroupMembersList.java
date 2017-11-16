package edu.csupomona.cs480.data;

import java.util.ArrayList;

/**
 * List of Group Members to allow for easier string readout
 */
public class GroupMembersList extends ArrayList<IndividualUser> {
    @Override
    public String toString() {
        String m = "{";
        if (this.size() <= 2) {
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
}
