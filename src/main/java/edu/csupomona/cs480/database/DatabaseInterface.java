package edu.csupomona.cs480.database;

import edu.csupomona.cs480.data.*;
import edu.csupomona.cs480.data.provider.CalendarUserManager;
import edu.csupomona.cs480.data.provider.EventList;

import java.sql.*;
import java.util.ArrayList;


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
            s.close();

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

            s.close();
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

            s.execute();


            ResultSet results = s.getResultSet();
            if (!results.isBeforeFirst()) {
                results.close();
                s.close();
                return null;
            } else {
                results.next();
                IndividualUser u = new IndividualUser(results.getInt(1), results.getString(2), results.getString(3), results.getString(4), results.getString(5), getEvents(userId));
                results.close();
                s.close();
                return u;
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

            s.execute();
            ResultSet results = s.getResultSet();
            if (!results.isBeforeFirst()) {
                results.close();
                s.close();
                return null;
            } else {
                results.next();
                IndividualUser u = new IndividualUser(results.getInt(1), results.getString(2), results.getString(3), results.getString(4), results.getString(5));
                results.close();
                s.close();
                return u;
            }

        } catch (SQLException e) {
            sqlException(e);
            return null;
        }
    }

    public String getUserNameFromIdNum(int id) {
        try {
            PreparedStatement s = sql.prepareStatement("SELECT id, userName FROM Users WHERE id=?;");
            s.setInt(1,id);

            s.execute();

            ResultSet results = s.getResultSet();
            if (!results.isBeforeFirst()) {
                results.close();
                s.close();
                return "";
            } else {
                results.next();
                String res = results.getString(2);
                results.close();
                s.close();
                return res;
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
            s.setString(1, groupId);

            s.execute();

            ResultSet results = s.getResultSet();
            if (!results.isBeforeFirst()) {
                results.close();
                s.close();
                return null;
            } else {
                results.next();
                int r1 = results.getInt(1);
                String r2 = results.getString(2);
                String r3 = results.getString(3);
                results.close();
                s.close();
                return new GroupUser(r1, r2, r3);
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
            s.execute();
            ResultSet results = s.getResultSet();
            if (!results.isBeforeFirst()) {
                results.close();
                s.close();
                return -1;
            } else {
                results.next();
                int id = results.getInt(1);
                results.close();
                s.close();
                return id;
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
            s.close();
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

            s.close();
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

            s.close();
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

            s.close();
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
            PreparedStatement s = sql.prepareStatement("UPDATE Groups SET members=? WHERE groupName=?");

            s.setString(2, group.getId());

            System.out.println(group.getMembers().toString());
            s.setString(1, group.getMembers().toString());
            s.execute();

            s.close();
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
    public GroupList getGroupsMemberOf(IndividualUser user) {
        try{
            int idNum = user.getIdNum();
            PreparedStatement s = sql.prepareStatement("SELECT id, groupName FROM Groups WHERE members REGEXP ?");
            String before = "[{,]";
            String after = "[},]";
            String regex = before + idNum + after;
            s.setString(1,regex);
            s.execute();

            ResultSet results = s.getResultSet();
            GroupList groups = new GroupList();
            if (!results.isBeforeFirst()) {
                results.close();
                s.close();

                return groups;
            }

            while(!results.isLast()) {
                results.next();
                GroupUser u = new GroupUser(results.getInt(1), results.getString(2));
                groups.add(u);
            }

            s.close();
            results.close();

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
            ArrayList<IndividualUser> users = new ArrayList<>();
            if (!results.isBeforeFirst()) {
                results.close();
                s.close();
                return users;
            }

            while (!results.isLast()) {
                results.next();
                IndividualUser u = new IndividualUser(results.getInt(1), results.getString(2), results.getString(3), results.getString(4));

                users.add(u);

            }

            results.close();
            s.close();

            return users;
        } catch (SQLException e) {
            sqlException(e);
            return null;
        }
    }

    public ArrayList<GroupUser> listAllGroups() {
        try {
            PreparedStatement s = sql.prepareStatement("SELECT id, groupName, members FROM Groups ");
            s.execute();
            ResultSet results = s.getResultSet();
            ArrayList<GroupUser> groups = new ArrayList<>();
            if (!results.isBeforeFirst()) {
                results.close();
                s.close();
                return groups;
            }

            while (!results.isLast()) {
                results.next();
                GroupUser group = new GroupUser(results.getInt(1), results.getString(2), results.getString(3));
                groups.add(group);
            }

            results.close();
            s.close();

            return groups;
        } catch (SQLException e) {
            sqlException(e);
            return null;
        }
    }

    public ArrayList<String> listAllGroupNames() {
        try {
            PreparedStatement s = sql.prepareStatement("SELECT id, groupName FROM Groups ");
            s.execute();
            ResultSet results = s.getResultSet();
            ArrayList<String> groupNames = new ArrayList<>();
            if (!results.isBeforeFirst()) {
                results.close();
                s.close();
                return groupNames;
            }

            while (!results.isLast()) {
                results.next();
                groupNames.add(results.getString(2));
            }

            results.close();
            s.close();

            return groupNames;
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
        addEvent(e.getLinkedUserId(), e.getName(), e.getStartDate(), e.getEndDate(), e.getStartTime(), e.getEndTime());
    }

    /**
     * Adds an event to the database
     * @param linkedUserId Username that the event is for
     * @param startDate Start date for the event in the format "MM/DD/YY"
     * @param endDate End date for the event in the format "MM/DD/YY"
     * @param startTime Start time for the event in the format "hh:mm"
     * @param endTime End time for the event in the format "hh:mm"
     * @throws MalformedEventException if the dates or times are not in the correct formatting
     */
    public void addEvent(String linkedUserId, String eventName, String startDate, String endDate, String startTime, String endTime) throws MalformedEventException {

        int idUser = getIdOfUser(linkedUserId);

        if (!(startDate.matches("[0-1][0-9]/[0-3][0-9]/[0-9][0-9][0-9][0-9]") && endDate.matches("[0-1][0-9]/[0-3][0-9]/[0-9][0-9][0-9][0-9]") &&
                startTime.matches("^[0-1][0-9][:][0-9][0-9]$|^[0-9][:][0-9][0-9]$") && startTime.matches("^[0-1][0-9][:][0-9][0-9]$|^[0-9][:][0-9][0-9]$"))) {
            System.err.println("Bad Event formatting");
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

        try{
            PreparedStatement s = sql.prepareStatement("INSERT INTO Events (userId, eventName, startMonth, startDay, startYear, " +
                    "startHour, startMinute, endMonth, endDay, endYear, endHour, endMinute) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)");
            s.setInt(1, idUser);
            s.setString(2, eventName);
            s.setInt(3, startMonth);
            s.setInt(4,startDay);
            s.setInt(5, startYear);
            s.setInt(6, startHour);
            s.setInt(7, startMin);
            s.setInt(8, endMonth);
            s.setInt(9, endDay);
            s.setInt(10, endYear);
            s.setInt(11, endHour);
            s.setInt(12, endMin);
            s.execute();
            s.close();
        } catch (SQLException e) {
            sqlException(e);
        }
    }

    public void deleteEvent(String userId, String eventName) {
        try{
            PreparedStatement s = sql.prepareStatement("DELETE FROM Events WHERE userId=? AND eventName=?");
            s.setInt(1, getIdOfUser(userId));
            s.setString(2, eventName);
            s.execute();
            s.close();
        } catch (SQLException e) {
            sqlException(e);
        }
    }


    public EventList getAllEvents() {
        try {
            PreparedStatement s = sql.prepareStatement("SELECT userId, eventName, startMonth, startDay, startYear, startHour, startMinute" +
                    ", endMonth, endDay, endYear, endHour, endMinute FROM Events ");
            s.execute();
            ResultSet results = s.getResultSet();

            EventList events = new EventList();
            if (!results.isBeforeFirst()) {
                results.close();
                s.close();
                return events;
            }

            while (!results.isLast()) {
                results.next();
                Event e = new Event(getUserNameFromIdNum(results.getInt(1)), results.getString(2), results.getInt(3), results.getInt(4), results.getInt(5), results.getInt(6), results.getInt(7), results.getInt(8), results.getInt(9), results.getInt(10),
                        results.getInt(11), results.getInt(12));
                events.add(e);
            }

            results.close();
            s.close();

            return events;
        } catch (SQLException e) {
            sqlException(e);
            return null;
        }
    }

    /**
     * Gets all events for a certain user
     * @param userName The user to get the events for
     * @return A list of events for the user
     */
    public EventList getEvents(String userName) {
        try {
            PreparedStatement s = sql.prepareStatement("SELECT userId, eventName, startMonth, startDay, startYear, startHour, startMinute" +
                    ", endMonth, endDay, endYear, endHour, endMinute FROM Events WHERE userId=?");
            s.setInt(1, getIdOfUser(userName));
            s.execute();
            ResultSet results = s.getResultSet();

            EventList events = new EventList();
            if (!results.isBeforeFirst()) {
                return events;
            }

            while (!results.isLast()) {
                results.next();
                Event e = new Event(userName, results.getString(2),
                        results.getInt(3), results.getInt(4), results.getInt(5),
                        results.getInt(6), results.getInt(7), results.getInt(8),
                        results.getInt(9), results.getInt(10), results.getInt(11),
                        results.getInt(12));
                events.add(e);
            }
            results.close();
            s.close();
            events.sort();

            return events;
        } catch (SQLException e) {
            System.err.println("SQLE");
            sqlException(e);
            return null;
        }
    }

    public FriendsList getFriends(String userName) {
        try {
            PreparedStatement s = sql.prepareStatement("SELECT friends FROM Users WHERE userName=?");
            s.setString(1, userName);
            s.execute();
            FriendsList friendsList = new FriendsList();

            ResultSet results = s.getResultSet();

            if (!results.isBeforeFirst()) {
                results.close();
                s.close();
                return friendsList;
            }

            while (!results.isClosed() && !results.isLast()) {
                results.next();
                String st = results.getString(1);
                results.close();
                s.close();
                if (st.equals("{}")) {
                    return friendsList;
                }
                String[] str = st.split("[{},]");
                for (int i = 1; i < str.length; i++) {
                    IndividualUser u = CalendarUserManager.getInstance().getUser(Integer.parseInt(str[i]));
                    if (u != null) {
                        friendsList.add(u);
                    }
                }
            }
            results.close();
            s.close();
            return friendsList;
        } catch (SQLException e) {
            sqlException(e);
            return null;
        }
    }

    public String getFriendsAsString(String userName) {
        try {
            PreparedStatement s = sql.prepareStatement("SELECT friends FROM Users WHERE userName=?");
            s.setString(1, userName);
            s.execute();

            ResultSet results = s.getResultSet();



            if (!results.isBeforeFirst()) {
                results.close();
                s.close(); System.out.println("Closing SQL Connection");
                return "";
            }

            results.next();
            String friendsString = results.getString(1);
            results.close();
            s.close();

            return friendsString;
        } catch (SQLException e) {
            sqlException(e);
            return null;
        }
    }

    public ArrayList<IndividualUser> getListUsersNotFriends(String userName) {
        IndividualUser user = CalendarUserManager.getInstance().getUser(userName);
        FriendsList friends = user.getFriends();
        ArrayList<IndividualUser> userList = CalendarUserManager.getInstance().getAllUsers();
        ArrayList<IndividualUser> usersNotFriends = new ArrayList<>();
        for (IndividualUser u : userList) {
            if (!userName.equals(u.getId()) && !friends.contains(u)) {
                usersNotFriends.add(u);
            }
        }
        return usersNotFriends;
    }

    public Event getEvent(String userId, String eventName) {
        try {
            PreparedStatement s = sql.prepareStatement("SELECT id, userId, eventName, startMonth, startDay, startYear, startHour, startMinute," +
                    "endMonth, endDay, endYear, endHour, endMinute FROM Events WHERE eventName = ? AND userId = ?");
            s.setString(1, eventName);
            s.setInt(2, getIdOfUser(userId));
            s.execute();

            ResultSet results = s.getResultSet();

            if (!results.isBeforeFirst()) {
                results.close();
                s.close();
                return null;
            } else {
                results.next();
                int[] resInts = new int[10];
                String r1 = results.getString(2);
                String r2 = results.getString(3);
                for (int i = 0; i < resInts.length; i++) {
                    resInts[i] = results.getInt(i+4);
                }

                results.close();
                s.close();

                return new Event(r1,r2,resInts[0], resInts[1], resInts[2], resInts[3], resInts[4], resInts[5], resInts[6],
                        resInts[7], resInts[8], resInts[9]);
            }



        } catch (SQLException e) {
            sqlException(e);
            return null;
        }
    }

    public boolean closeConnection() {
        System.out.println("Closing SQL Connections");
        try {
            if (this.sql.isClosed()) {
                return false;
            } else {
                this.sql.close();
                return true;
            }

        } catch (SQLException e) {
            sqlException(e);
            return false;
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