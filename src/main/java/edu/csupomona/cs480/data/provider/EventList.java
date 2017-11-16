package edu.csupomona.cs480.data.provider;

import java.util.ArrayList;

import edu.csupomona.cs480.data.Event;

/**
 * List of Events to allow for easier string readout
 */
public class EventList extends ArrayList<Event>{
	 @Override
    public String toString() {
        String m = "{";
        if (this.size() <= 2) {
            for (int i = 0; i < this.size() - 1; i++) {
                m = m + "" + this.get(i).toString();
                m = m + ",";
            }
            m = m + "" + this.get(this.size() - 1).toString();
        } else if (this.size() == 1) {
            m = m + "" + this.get(0).toString();
        }
        m = m + "}";
        return m;
    }
}
