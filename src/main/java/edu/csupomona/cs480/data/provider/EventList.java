package edu.csupomona.cs480.data.provider;

import edu.csupomona.cs480.data.Event;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;

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

    public Event findEventByName(String eventName) {
        int index = findEventIndexByName(eventName);
        if(index != -1) {
            return this.get(index);
        }
        return null;
    }


    private int findEventIndexByName(String eventName) {
        int index = -1;
        for(int i = 0; i < this.size(); i++) {
            Event e = this.get(i);
            if(e == null)
                return index;
            else if (e.getName().equals(eventName))
                return i;
        }
        return index;
    }

    public Event removeEventByName(String eventName) {
        int index = findEventIndexByName(eventName);
        if(index != -1)
            return this.remove(index);
        return null;
    }
    
    public void sort() {
	     this.sort(new Comparator<Event>() {
             @Override
             public int compare(Event o1, Event o2) {
                 int o1StartYear = Integer.parseInt(o1.getStartField(2));
                 int o1StartMonth = Integer.parseInt(o1.getStartField(0));
                 int o1StartDay = Integer.parseInt(o1.getStartField(1));
                 int o1StartHour = Integer.parseInt(o1.getStartField(3));
                 int o1StartMinute = Integer.parseInt(o1.getStartField(4));

                 int o2StartYear = Integer.parseInt(o2.getStartField(2));
                 int o2StartMonth = Integer.parseInt(o2.getStartField(0));
                 int o2StartDay = Integer.parseInt(o2.getStartField(1));
                 int o2StartHour = Integer.parseInt(o2.getStartField(3));
                 int o2StartMinute = Integer.parseInt(o2.getStartField(4));

                 Calendar o1C = Calendar.getInstance();
                 o1C.set(o1StartYear, o1StartMonth, o1StartDay, o1StartHour, o1StartMinute);
                 Date o1Date = o1C.getTime();

                 Calendar o2C = Calendar.getInstance();
                 o2C.set(o2StartYear, o2StartMonth, o2StartDay, o2StartHour, o2StartMinute);
                 Date o2Date = o2C.getTime();

                 return o1Date.compareTo(o2Date);
             }
         });
    }


}
