package edu.csupomona.cs480.controller;


import java.io.IOException;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;


import edu.csupomona.cs480.data.*;
import edu.csupomona.cs480.data.Number;
import edu.csupomona.cs480.database.DatabaseInterface;
import edu.csupomona.cs480.database.MalformedEventException;
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;
import org.apache.commons.io.output.NullOutputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import com.google.common.base.Splitter;

import edu.csupomona.cs480.App;
import edu.csupomona.cs480.data.provider.UserManager;
import edu.csupomona.cs480.database.DatabaseInterface;
import edu.csupomona.cs480.links.provider.LinkManager;
import edu.csupomona.cs480.data.provider.CalendarUserManager;
import edu.csupomona.cs480.data.provider.EventList;
import si.kobalj.stopwatch.CStopWatchFactory;
import si.kobalj.stopwatch.model.IStopWatch;

/**
 * This is the controller used by Spring framework.
 * <p>
 * The basic function of this controller is to map
 * each HTTP API Path to the correspondent method.
 *
 */

@RestController
public class WebController {

	/**
	 * When the class instance is annotated with
	 * {@link Autowired}, it will be looking for the actual
	 * instance from the defined beans.
	 * <p>
	 * In our project, all the beans are defined in
	 * the {@link App} class.
	 */
	@Autowired
	private UserManager userManager;

	@Autowired
	private LinkManager linkManager;

	@Autowired
	private ArrayList<Number> numbers;

	@Autowired
	private DescriptiveStatistics numberStats;

	@Autowired
	private DatabaseInterface databaseInterface;

	@Autowired
	private CalendarUserManager calendarUserManager;
	
	//Can we create a new user from the url? Set false if we implement registering.
	private static final boolean CREATE_USER_FROM_URL = true;

	/**
	 * This is a simple example of how the HTTP API works.
	 * It returns a String "OK" in the HTTP response.
	 * To try it, run the web application locally,
	 * in your web browser, type the link:
	 * 	http://localhost:8080/cs480/ping
	 */
	@RequestMapping(value = "/cs480/ping", method = RequestMethod.GET)
	String healthCheck() {
		// You can replace this with other string,
		// and run the application locally to check your changes
		// with the URL: http://localhost:8080/
		return "OK";
	}

	/**
	 * This is a simple example of how to use a data manager
	 * to retrieve the data and return it as an HTTP response.
	 * <p>
	 * Note, when it returns from the Spring, it will be
	 * automatically converted to JSON format.
	 * <p>
	 * Try it in your web browser:
	 * 	http://localhost:8080/cs480/user/user101
	 */
//	@RequestMapping(value = "/cs480/user/{userId}", method = RequestMethod.GET)
//	User getUser(@PathVariable("userId") String userId) {
//		User user = userManager.getUser(userId);
//		return user;
//	}

	@RequestMapping(value="/cs480/user", method=RequestMethod.GET)
	String tellUserToInputParameter() {
		return "Please input the user that you wish to get.  Do it like this: .../user/<user> where <user> is the user to get";
	}

	@RequestMapping(value = "/cs480/user/{userId}", method = RequestMethod.GET)
	User getUser(@PathVariable("userId") String userId) throws SQLException {
		return databaseInterface.getUser(userId);
	}

	/**
	 * This is an example of sending an HTTP POST request to
	 * update a user's information (or create the user if not
	 * exists before).
	 *
	 * You can test this with a HTTP client by sending
	 *  http://localhost:8080/cs480/user/user101
	 *  	name=John major=CS
	 *
	 * Note, the URL will not work directly in browser, because
	 * it is not a GET request. You need to use a tool such as
	 * curl.
	 *
	 * @param id User ID
	 * @param firstName First Name of the User
	 * @param lastName Last Name of the User
	 * @return
	 */
	@RequestMapping(value = "/cs480/user/{userId}", method = RequestMethod.POST)
	void addUser(@PathVariable("userId") String id,
					   @RequestParam("firstName") String firstName,
					   @RequestParam("lastName") String lastName) throws SQLException{
		databaseInterface.addUser(id, firstName, lastName);
	}

	/**
	 * This API deletes the user. It uses HTTP DELETE method.
	 *
	 * @param userId
	 */
	@RequestMapping(value = "/cs480/user/{userId}", method = RequestMethod.DELETE)
	void deleteUser(
			@PathVariable("userId") String userId) throws SQLException {
		databaseInterface.deleteUser(userId);
	}

	/**
	 * This API lists all the users in the current database.
	 *
	 * @return
	 */
	@RequestMapping(value = "/cs480/users/list", method = RequestMethod.GET)
	List<IndividualUser> listAllUsers() throws SQLException {
		return databaseInterface.listAllUsers();
	}

	/*********** Web UI Test Utility **********/
	/**
	 * This method provide a simple web UI for you to test the different
	 * functionalities used in this web service.
	 */
	@RequestMapping(value = "/cs480/home", method = RequestMethod.GET)
	ModelAndView getUserHomepage() throws SQLException{
		ModelAndView modelAndView = new ModelAndView("home");
		modelAndView.addObject("users", listAllUsers());
		modelAndView.addObject("events", listEvents());
		return modelAndView;
	}

	/*********** Web UI Test Utility **********/
	/**
	 * This method provide a simple web UI for you to test the different
	 * functionalities used in this web service.
	 */

	@RequestMapping(value = "/cs480", method = RequestMethod.GET)
	ModelAndView getUserHomepaged() throws SQLException{
		ModelAndView modelAndView = new ModelAndView("home");
		modelAndView.addObject("users", listAllUsers());
		return modelAndView;
	}

	@RequestMapping(value = "/error", method = RequestMethod.GET)
	ModelAndView error() throws SQLException {
		ModelAndView modelAndView = new ModelAndView("error");
		return modelAndView;
	}

	@RequestMapping(value = "/Frienduler/Table", method = RequestMethod.GET)
	ModelAndView Table() throws SQLException {
		ModelAndView modelAndView = new ModelAndView("MainPG");
		return modelAndView;
	}
	@RequestMapping(value = "/Frienduler/Addfriend", method = RequestMethod.GET)
	ModelAndView Addfriend() throws SQLException {
		ModelAndView modelAndView = new ModelAndView("Addfriend");
		return modelAndView;
	}
	@RequestMapping(value = "/Frienduler/{userId}/compare", method = RequestMethod.GET)
	ModelAndView compare(@PathVariable("userId") String userId ) throws SQLException {
		ModelAndView modelAndView = new ModelAndView("compare");
		PersonOnlineObjectPresenter userInstance = calendarUserManager.generatePersonOnlineObjectPresenter( userId );
		if( userInstance != null ) {
			modelAndView.addObject( "friends", userInstance.getFriends() );
			modelAndView.addObject("userId", userId );
			return modelAndView;
		}
		else {
			return error();
		}
	}

	@RequestMapping( value = "/Frienduler/{userId}", method = RequestMethod.GET)
	ModelAndView userMainPage(@PathVariable("userId") String userId ) throws SQLException {
		ModelAndView modelAndView = new ModelAndView("userMainPage");
		PersonOnlineObjectPresenter userInstance = calendarUserManager.generatePersonOnlineObjectPresenter( userId );
		if( userInstance == null ) {
			System.out.println( "[WebController] Cannot find user." );
			if( CREATE_USER_FROM_URL ) {
				System.out.println("CREATEUSERFROMURL");
				calendarUserManager.addUser( userId );
				userInstance = calendarUserManager.generatePersonOnlineObjectPresenter( userId );
			}
			else {
				return error();
			}
		}
		modelAndView.addObject( "userId", userInstance.getUserId() );
		modelAndView.addObject( "events", userInstance.getSchedule() );
		modelAndView.addObject( "friends", userInstance.getFriends() );
		return modelAndView;
	}

	@RequestMapping( value = "/Frienduler/listUsers", method = RequestMethod.GET )
	ModelAndView listUsers() throws SQLException{
		ModelAndView modelAndView = new ModelAndView( "userList" );
		modelAndView.addObject( "users", calendarUserManager.getAllUsers() );
		return modelAndView;
	}

	@RequestMapping( value="/Frienduler/eventList", method= RequestMethod.GET)
	EventList listEvents() {
		return databaseInterface.getAllEvents();
	}

	@RequestMapping(value = "/Frienduler/{userId}/createEvent", method = RequestMethod.GET)
	ModelAndView createEventForUser(@PathVariable("userId") String userId ) throws SQLException {
		ModelAndView modelAndView = new ModelAndView("tester");
		modelAndView.addObject( "friends", calendarUserManager.getUser( userId ).getFriends() );
		modelAndView.addObject("userId", userId );
		return modelAndView;
	}
	
	@RequestMapping( value = "/Frienduler/{userId}/createEvent", method = RequestMethod.POST )
	void addEvent( @PathVariable("userId") String userId,
				   @RequestParam("eventName") String eventName,
				   @RequestParam("startDate") String startDate,
				   @RequestParam("startTime") String startTime,
				   @RequestParam("endDate") String endDate,
				   @RequestParam("endTime") String endTime) throws MalformedEventException {
		if (startDate.contains("-") && endDate.contains("-")) {
			String[] startDateSplit = startDate.split("-");
			String startMonth = startDateSplit[1];
			String startDay = startDateSplit[2];
			String startYear = startDateSplit[0];
			String[] endDateSplit = endDate.split("-");
			String endMonth = endDateSplit[1];
			String endDay = endDateSplit[2];
			String endYear = endDateSplit[0];
			String[] startTimeSplit = startTime.split(":");
			String[] endTimeSplit = endTime.split(":");
			System.out.println("Running add event");
			calendarUserManager.addEvent(userId, new Event(userId, eventName, startMonth, startDay, startYear, startTimeSplit[0], startTimeSplit[1], endMonth, endDay, endYear, endTimeSplit[0], endTimeSplit[1]));
		} else {
			calendarUserManager.addEvent(userId, new Event(userId, eventName, startTime, endTime, startDate, endDate));
		}

	}

	@RequestMapping( value="/Frienduler/{userId}/{eventName}", method=RequestMethod.DELETE)
	void deleteEvent(@PathVariable("userId") String userId,
					 @PathVariable("eventName") String eventName) {
		System.out.println(calendarUserManager.removeEvent(userId, eventName));
	}


	/************ A3 Added Methods ************/
	/**
	 * By following the request mapping methods in WebController.java,
	 * you need to add a new method in that class to create a new HTTP API.
	 */
	@RequestMapping(value = "/cs480/genn", method = RequestMethod.GET)
	String returnDateAndTime() {
		Date dNow = new Date( );
	    SimpleDateFormat ft = new SimpleDateFormat ("EEE, MMM d, yyyy 'at' hh:mm:ss a zzz");

		return ("You ran this page on: " + ft.format(dNow));
	}

	@RequestMapping(value = "/cs480/jarod", method = RequestMethod.GET)
	String helloWorld() {

		return ("Hello world");
	}

	//Lloyd Zhang's A3
	@RequestMapping( value = "/cs480/lloyd", method = RequestMethod.GET )
	String returnGoodByeWorld()
	{
		return "Goodbye World!";
	}

	//Jack Zhang A3
	@RequestMapping(value = "/cs480/shakeNbake", method = RequestMethod.GET)
	String Dungo() {
		return("Bob it!");
	}

	//Connor Baskin A3
	@RequestMapping(value = "/cs480/snake", method = RequestMethod.GET)
	@ResponseBody ModelAndView snakeGame() {
		ModelAndView snakeGame = new ModelAndView("snake");
		return snakeGame;
	}


/************ A4 Added Methods ************/
	/**
	 * New A4 library methods
	 */
	//Connor Baskin A4
	@RequestMapping(value="/cs480/addNumber", method=RequestMethod.POST)
	String addNumber(
		@RequestParam("number") String number) {
		if (number == null || number.equals("")) {
			return "You must have the number to add as a parameter of the request";
		} else {
			try {
				double num = Double.valueOf(number);
				numbers.add(new Number(num));
				numberStats.addValue(num);
				return "Added " + num;
			} catch (NumberFormatException e) {
				return "Your number is formatted incorrectly, please try again";
			}
		}
	}

	//Connor Baskin A4
	@RequestMapping(value="/cs480/makeNumbers", method=RequestMethod.GET)
	ModelAndView getPageAddNumbers() {
		ModelAndView modelAndView = new ModelAndView("numberAdd");
		modelAndView.addObject("numbers", numbers);
		return modelAndView;
	}

	//Connor Baskin A4
	@RequestMapping(value="/cs480/getAverage", method=RequestMethod.GET)
	String getAverage() {
		if (numbers.isEmpty()) {
			return "There are no numbers in the array, The average is 0";
		} else {
			return "The values in the array are " + Arrays.toString(numbers.toArray()) + "; The average is: " + numberStats.getMean();
		}
	}

	// genevieve A4
	@RequestMapping(value = "/cs480/split", method = RequestMethod.GET)
	List<String> splitter() {
		String input = "This splits a sentence at spaces. Hip hip!";

		List<String> words = Splitter.on(" ").splitToList(input);
		return words;
	}


	//jarods A4
	//http://localhost:8080/cs480/linkGrabber
	@RequestMapping(value = "/cs480/linkGrabber", method = RequestMethod.GET)
	String linkGrabber() {
		try
		{
			linkManager.connect("http://www.yahoo.com");
			return linkManager.printImageDetails();
		}
		catch(Exception e) {
			System.out.println(e.toString());
		}
		return "";
	}

	//Lloyd Zhang A4
	@RequestMapping( value = "/cs480/io", method = RequestMethod.POST )
	String dump( @RequestParam("text") String text ) {
		NullOutputStream nos = new NullOutputStream();
		byte[] bytes = text.getBytes();
		try {
			nos.write( bytes );
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "Your text has been sucessfully dumped! Have a good day!";
	}
	//Jack Zhang A4
		@RequestMapping( value = "/cs480/test", method = RequestMethod.GET )
		String watchtests() throws InterruptedException {
			IStopWatch stopWatch = CStopWatchFactory.getStopWatchBuilder().build();
			stopWatch.startGlobal();
			Thread.sleep(999);
			stopWatch.stopGlobal();
			return stopWatch.toString();
		}

}
