package edu.csupomona.cs480.database;

/**
 * Created by Connor on 11/19/2017.
 */
public class MalformedEventException extends Exception {

    public MalformedEventException() {
        super("The date or time in the event is not formatted correctly");
    }

}
