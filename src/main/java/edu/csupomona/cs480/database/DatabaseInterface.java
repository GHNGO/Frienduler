package edu.csupomona.cs480.database;

import edu.csupomona.cs480.data.GroupUser;
import edu.csupomona.cs480.data.IndividualUser;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import edu.csupomona.cs480.data.GroupUser;
import edu.csupomona.cs480.data.IndividualUser;

/**
 * This class serves as a singular class for all accesses to the SQL database.  There should only be one instance of this
 * in use at any given time
 */
public class DatabaseInterface {

    /**
     * Connection to the database
     */
    private Connection sql;

    /**
     * Creates a new database interface instance with a connection to an SQL database
     * @param url URL of database to connect to
     * @throws SQLException If there is an error connecting to the database
     */
    public DatabaseInterface(String url) throws SQLException {
        sql = DriverManager.getConnection(url);
    }


    /**
     * Adds a new user to the database, assuming that the user does not already exist.
     * If the user already exists, use <code>updateUser()</code>
     * @param u User object to add to database
     */
    public void addUser(IndividualUser u) {
        try {
            PreparedStatement s = sql.prepareStatement("INSERT INTO Users (userName, firstName, lastName) VALUES (?,?,?);");
            s.setString(1, u.getId());
            s.setString(2, u.getFirstName());
            s.setString(3, u.getLastName());
            s.execute();

        } catch (SQLException e) {
            sqlException(e);
        }
    }

    /**
     * Adds a new blank user to the database, assuming that the user does not already exist.
     * If the user already exists, use <code>updateUser()</code>
     * @param userId user ID to add to the database
     */
    public void addUser(String userId) {
        addUser(new IndividualUser(userId));
    }

    /**
     * Adds a new user to the database, assuming that the user does not already exist
     * @param userId User ID to add to the database
     * @param firstName First name of added user
     * @param lastName Last name of added user
     */
    public void addUser(String userId, String firstName, String lastName) {
        addUser(new IndividualUser(userId, firstName, lastName));
    }

    /**
     * Adds a new group to the database, assuming that the group does not already exist.
     * If the group already exists, use <code>updateGroup()</code>
     * @param group GroupUser object to add to database
     */
    public void addGroup(GroupUser group) {
        try {
            PreparedStatement s = sql.prepareStatement("INSERT INTO Groups (groupName, members) VALUES (?,?);");
            s.setString(1, group.getId());

            s.setString(2, group.getMembers().toString());
            s.execute();
        } catch (SQLException e) {
            sqlException(e);
        }
    }

    /**
     * Adds a new blank group to the database, assuming that the group does not already exist.
     * @param groupId Group ID for the new group to add
     */
    public void addGroup(String groupId) {
        addGroup(new GroupUser(groupId));
    }

    /**
     * Retrieves a user from the database assuming it already exists.
     * @param userId User ID for the user to be retrieved
     * @return An <code>IndividualUser</code> object for the retrieved user
     */
    public IndividualUser getUser(String userId) {
        try {
            PreparedStatement s = sql.prepareStatement("SELECT id, userName, firstName, lastName FROM Users WHERE userName=?;");
            s.setString(1,userId);

            if (s.execute()) {
                ResultSet results = s.getResultSet();
                if (!results.isBeforeFirst()) {
                    return null;
                } else {
                    results.next();
                    IndividualUser u;
                    u = new IndividualUser(results.getInt(1), results.getString(2), results.getString(3), results.getString(4));
                    return u;
                }
            } else {
                return null;
            }

        } catch (SQLException e) {
            sqlException(e);
            return null;
        }
    }

    /**
     * Retrieves a group from the database assuming it already exists.
     * @param groupId ID for the group to be retrieved
     * @return An <code>GroupUser</code> object for the retrieved group
     */
    public GroupUser getGroup(String groupId) {
        try {
            PreparedStatement s = sql.prepareStatement("SELECT id, groupName, members FROM Groups WHERE groupName=?;");
            s.setString(1,groupId);

            if (s.execute()) {
                ResultSet results = s.getResultSet();
                if (!results.isBeforeFirst()) {
                    return null;
                } else {
                    results.next();
                    return new GroupUser(results.getInt(1), results.getString(2), results.getString(3));
                }
            } else {
                return null;
            }
        } catch (SQLException e) {
            sqlException(e);
            return null;
        }
    }

    /**
     * Deletes a user from the database
     * @param userId Username for the user to delete
     */
    public void deleteUser(String userId) {
        deleteUser(getUser(userId));
    }

    /**
     * Deletes a user from the database
     * @param user <code>IndividualUser</code> object for the user to delete
     */
    public void deleteUser(IndividualUser user) {
        try{
            PreparedStatement s = sql.prepareStatement("DELETE FROM Users WHERE userName=? AND id=? AND firstName=? AND lastName=?;");
            s.setString(1, user.getId());
            s.setInt(2,user.getIdNum());
            s.setString(3, user.getFirstName());
            s.setString(4, user.getLastName());
            s.execute();
        } catch (SQLException e) {
            sqlException(e);
        }
    }

    /**
     * Deletes a group from the database
     * @param groupId Group ID for the group to delete
     */
    public void deleteGroup(String groupId) {
        deleteGroup(getGroup(groupId));
    }

    /**
     * Deletes a group from the database
     * @param group <code>GroupUser</code> object for the group to delete
     */
    public void deleteGroup(GroupUser group) {
        try{
            PreparedStatement s = sql.prepareStatement("DELETE FROM Groups WHERE groupName=? AND id=?;");
            s.setString(1, group.getId());
            s.setInt(2, group.getIdNum());
            s.execute();
        } catch (SQLException e) {
            sqlException(e);
        }
    }

    /**
     * Updates a user's first and/or last name
     * @param u User object for the updated user
     */
    public void updateUser(IndividualUser u) {
        updateUser(u.getId(), u.getFirstName(), u.getLastName());
    }

    /**
     * Updates a user's first and/or last name
     * @param userName User Name to lookup in database. NOTE: MUST BE ALREADY IN DATABASE. THIS VALUE IS NOT CHANGED.
     * @param firstName New First Name
     * @param lastName New Last Name
     */
    public void updateUser(String userName, String firstName, String lastName) {
        try{
            PreparedStatement s = sql.prepareStatement("UPDATE Users SET firstName=?, lastName=? WHERE userName=?");
            s.setString(1, firstName);
            s.setString(2, lastName);
            s.setString(3, userName);
            s.execute();
        } catch (SQLException e) {
            sqlException(e);
        }
    }

    /**
     * Updates a group's members
     * @param group GroupUser object for the updated group
     */
    public void updateGroup(GroupUser group) {
        try{
            PreparedStatement s = sql.prepareStatement("UPDATE Groups SET members=? WHERE groupName=? AND id=?");

            s.setString(2, group.getId());
            s.setInt(3, group.getIdNum());

            s.setString(1, group.getMembers().toString());
            s.execute();
        } catch (SQLException e) {
            sqlException(e);
        }
    }


    /**
     * Runs if an <code>SQLException</code> occurs
     * @param e The exception that occurred
     */
    private void sqlException(SQLException e) {
        System.err.println("SQLException: " + e.getMessage());
        System.err.println("SQLState: " + e.getSQLState());
        System.err.println("VendorError: " + e.getErrorCode());
        System.exit(-1);
    }

    /**
     * Gets the groups that the user with this user ID is a member of
     * @param i Username for user to look up information for
     * @return <code>ArrayList</code> of <code>GroupUser</code> objects that this user is a member of.
     */
    public ArrayList<GroupUser> getGroupsMemberOf(String i) {
        return getGroupsMemberOf(getUser(i));
    }

    /**
     * Gets the groups that the user is a member of
     * @param user User to look up information for
     * @return <code>ArrayList</code> of <code>GroupUser</code> objects that this user is a member of.
     */
    public ArrayList<GroupUser> getGroupsMemberOf(IndividualUser user) {
        try{
            int idNum = user.getIdNum();
            PreparedStatement s = sql.prepareStatement("SELECT id, groupName FROM Groups WHERE members REGEXP ?");
            String before = "[{,]";
            String after = "[},]";
            String regex = before + idNum + after;
            s.setString(1,regex);
            s.execute();

            ResultSet results = s.getResultSet();
            ArrayList<GroupUser> groups = new ArrayList<>();
            if (!results.isBeforeFirst()) {
                return groups;
            }

            while(!results.isLast()) {
                results.next();
                GroupUser u = new GroupUser(results.getInt(1), results.getString(2));
                groups.add(u);
            }
            return groups;
        } catch (SQLException e) {
            sqlException(e);
            return null;
        }
    }

    /**
     * Lists all users in the database
     * @return A list of <code>IndividualUser</code> objects representing all of the users in the database
     */
    public ArrayList<IndividualUser> listAllUsers() {
        try {
            PreparedStatement s = sql.prepareStatement("SELECT id, userName, firstName, lastName FROM Users ");
            s.execute();
            ResultSet results = s.getResultSet();
            ResultSetMetaData resultsMeta = s.getMetaData();
            int colCount = resultsMeta.getColumnCount();
            ArrayList<IndividualUser> users = new ArrayList<>();
            if (!results.isBeforeFirst()) {
                return users;
            }

            while (!results.isLast()) {
                results.next();
                IndividualUser u = new IndividualUser(results.getInt(1), results.getString(2), results.getString(3), results.getString(4));
//                for (int i = 1; i <= colCount; i++) {
//                    System.out.print(results.getString(i) + " ");
//                }
//                System.out.println();
                users.add(u);
//                System.out.println(u);
            }
            return users;
        } catch (SQLException e) {
            sqlException(e);
            return null;
        }
    }
}
