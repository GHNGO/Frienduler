package edu.csupomona.cs480.database;

import edu.csupomona.cs480.data.Event;
import edu.csupomona.cs480.data.FriendsList;
import edu.csupomona.cs480.data.GroupUser;
import edu.csupomona.cs480.data.IndividualUser;
import edu.csupomona.cs480.data.provider.EventList;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.*;


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
            PreparedStatement s = sql.prepareStatement("INSERT INTO Users (userName, firstName, lastName, friends) VALUES (?,?,?,?);");
            s.setString(1, u.getId());
            s.setString(2, u.getFirstName());
            s.setString(3, u.getLastName());
            s.setString(4, u.getFriends().toString());
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
            PreparedStatement s = sql.prepareStatement("SELECT id, userName, firstName, lastName, friends FROM Users WHERE userName=?;");
            s.setString(1,userId);

            if (s.execute()) {
                ResultSet results = s.getResultSet();
                if (!results.isBeforeFirst()) {
                    return null;
                } else {
                    results.next();
                    IndividualUser u;
                    u = new IndividualUser(results.getInt(1), results.getString(2), results.getString(3), results.getString(4), results.getString(5));
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

    public IndividualUser getUser(int id) {
        try {
            PreparedStatement s = sql.prepareStatement("SELECT id, userName, firstName, lastName, friends FROM Users WHERE id=?;");
            s.setInt(1,id);

            if (s.execute()) {
                ResultSet results = s.getResultSet();
                if (!results.isBeforeFirst()) {
                    return null;
                } else {
                    results.next();
                    IndividualUser u;
                    u = new IndividualUser(results.getInt(1), results.getString(2), results.getString(3), results.getString(4), results.getString(5));
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

    public int getIdOfUser(String userId) {
        try{
            PreparedStatement s = sql.prepareStatement("SELECT id FROM Users WHERE userName=?");
            s.setString(1, userId);
            if (s.execute()) {
                ResultSet results = s.getResultSet();
                if (!results.isBeforeFirst()) {
                    return -1;
                } else {
                    results.next();
                    return results.getInt(1);
                }
            } else {
                return -1;
            }
        } catch (SQLException e) {
            sqlException(e);
            return -1;
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
     * Updates a user's first name, last name, and/or list of friends
     * @param u User object for the updated user
     */
    public void updateUser(IndividualUser u) {
        updateUser(u.getId(), u.getFirstName(), u.getLastName(), u.getFriends());
    }

    /**
     * Updates a user's first name, last name, and list of friends
     * @param userName User Name to lookup in database. NOTE: MUST BE ALREADY IN DATABASE. THIS VALUE IS NOT CHANGED.
     * @param firstName New First Name
     * @param lastName New Last Name
     * @param friends New Friends List
     */
    public void updateUser(String userName, String firstName, String lastName, FriendsList friends) {
        try{
            PreparedStatement s = sql.prepareStatement("UPDATE Users SET firstName=?, lastName=?, friends=? WHERE userName=?");
            s.setString(1, firstName);
            s.setString(2, lastName);
            s.setString(3, friends.toString());
            s.setString(4, userName);
            s.execute();
        } catch (SQLException e) {
            sqlException(e);
        }
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

    /**
     * Adds an event to the database
     * @param e Event object to add to the database
     * @throws MalformedEventException If the dates or times in the event are not in the correct formatting
     */
    public void addEvent(Event e) throws MalformedEventException {
        addEvent(e.getLinkedUserId(), e.getStartDate(), e.getEndDate(), e.getStartTime(), e.getEndTime());
    }

    /**
     * Adds an event to the database
     * @param linkedUserId Username that the event is for
     * @param startDate Start date for the event in the format "MM:DD:YY"
     * @param endDate End date for the event in the format "MM:DD:YY"
     * @param startTime Start time for the event in the format "hh:mm"
     * @param endTime End time for the event in the format "hh:mm"
     * @throws MalformedEventException if the dates or times are not in the correct formatting
     */
    public void addEvent(String linkedUserId, String startDate, String endDate, String startTime, String endTime) throws MalformedEventException {

        int idUser = getIdOfUser(linkedUserId);

        if (!(startDate.matches("[0-1][0-9]/[0-3][0-9]/[0-9][0-9][0-9][0-9]") && endDate.matches("[0-1][0-9]/[0-3][0-9]/[0-9][0-9][0-9][0-9]") &&
                startTime.matches("^[0-1][0-9][:][0-9][0-9]$|^[0-9][:][0-9][0-9]$") && startTime.matches("^[0-1][0-9][:][0-9][0-9]$|^[0-9][:][0-9][0-9]$"))) {
            throw new MalformedEventException();
        }

        String[] parseStartDate = startDate.split("/");
        int startMonth = Integer.parseInt(parseStartDate[0]);
        int startDay = Integer.parseInt(parseStartDate[1]);
        int startYear = Integer.parseInt(parseStartDate[2]);

        String[] parseStartTime = startTime.split(":");
        int startHour = Integer.parseInt(parseStartTime[0]);
        int startMin = Integer.parseInt(parseStartTime[1]);

        String[] parseEndDate = endDate.split("/");
        int endMonth = Integer.parseInt(parseEndDate[0]);
        int endDay = Integer.parseInt(parseEndDate[1]);
        int endYear = Integer.parseInt(parseEndDate[2]);

        String[] parseEndTime = endTime.split(":");
        int endHour = Integer.parseInt(parseEndTime[0]);
        int endMin = Integer.parseInt(parseEndTime[1]);

        Timestamp start = Timestamp.valueOf(LocalDateTime.of(startYear, startMonth, startDay, startHour, startMin));
        Timestamp end = Timestamp.valueOf(LocalDateTime.of(endYear, endMonth, endDay, endHour, endMin));

        try{
            PreparedStatement s = sql.prepareStatement("INSERT INTO Events (userId, startTime, endTime) VALUES (?,?,?)");
            s.setInt(1, idUser);
            s.setTimestamp(2, start);
            s.setTimestamp(3, end);
        } catch (SQLException e) {
            sqlException(e);
        }
    }

    /**
     * Gets all events for a certain user
     * @param userName The user to get the events for
     * @return A list of events for the user
     */
    public EventList getEvents(String userName) {
        try {
            PreparedStatement s = sql.prepareStatement("SELECT userId, eventName, startTime, endTime FROM Events WHERE userId=?");
            s.setString(1, userName);

            ResultSet results = s.getResultSet();
            ResultSetMetaData resultsMeta = s.getMetaData();
            int colCount = resultsMeta.getColumnCount();
            EventList events = new EventList();
            if (!results.isBeforeFirst()) {
                return events;
            }

            while (!results.isLast()) {
                results.next();
                Event e = new Event(getUser(results.getString(1)).getId(), results.getString(2), results.getTimestamp(3), results.getTimestamp(4));
                events.add(e);
            }

            return events;
        } catch (SQLException e) {
            sqlException(e);
            return null;
        } catch (MalformedEventException e) {
            e.printStackTrace();
            return null;
        }
    }

    public FriendsList getFriends(String userName) {
        try {
            PreparedStatement s = sql.prepareStatement("SELECT friends FROM Users WHERE userId=?");
            s.setString(1, userName);

            FriendsList friendsList = new FriendsList();

            ResultSet results = s.getResultSet();
            ResultSetMetaData resultsMeta = s.getMetaData();
            int colCount = resultsMeta.getColumnCount();
            if (!results.isBeforeFirst()) {
                return friendsList;
            }

            while (!results.isLast()) {
                results.next();
                String st = results.getString(1);
                if (st.equals("{}")) {
                    return friendsList;
                }
                String[] str = st.split("[{},]");
                for (int i = 1; i < str.length; i++) {
                    IndividualUser u = getUser(Integer.parseInt(str[i]));
                    if (u != null) {
                        friendsList.add(u);
                    }
                }
            }

            return friendsList;
        } catch (SQLException e) {
            sqlException(e);
            return null;
        }
    }

    public String getFriendsAsString(String userName) {
        try {
            PreparedStatement s = sql.prepareStatement("SELECT friends FROM Users WHERE userId=?");
            s.setString(1, userName);

            ResultSet results = s.getResultSet();
            ResultSetMetaData resultsMeta = s.getMetaData();
            int colCount = resultsMeta.getColumnCount();
            if (!results.isBeforeFirst()) {
                return "";
            }

            results.next();
            return results.getString(1);
        } catch (SQLException e) {
            sqlException(e);
            return null;
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
        e.printStackTrace();
//        System.exit(-1);
    }
}
