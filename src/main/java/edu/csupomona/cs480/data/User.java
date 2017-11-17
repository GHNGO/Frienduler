package edu.csupomona.cs480.data;

import java.util.Date;


/**
 * The basic user object.
 */
public abstract class User {

	/** The user name */
	protected String id;

	/** The unique user Id */
    protected int idNum;

    /**

    /** The timestamp when the user is being created */
    protected String creationTime = new Date(System.currentTimeMillis()).toString();

    public User(String id) {
    	this.id = id;
	}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

	public String getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(String creationTime) {
		this.creationTime = creationTime;
	}

	public String toString() {
    	return "Username: " + id;
	}

	public int getIdNum() {
		return idNum;
	}

	public void setIdNum(int idNum) {
		this.idNum = idNum;
	}
}
