package edu.csupomona.cs480.controller;


import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;


import edu.csupomona.cs480.data.Number;
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;
import org.apache.commons.io.*;
import org.apache.commons.io.output.NullOutputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import com.google.common.base.Splitter;

import ch.qos.logback.core.util.TimeUtil;
import edu.csupomona.cs480.App;
import edu.csupomona.cs480.data.User;
import edu.csupomona.cs480.data.provider.UserManager;
import edu.csupomona.cs480.links.provider.LinkManager;
import si.kobalj.stopwatch.CStopWatchFactory;
import si.kobalj.stopwatch.model.IStopWatch;

import org.apache.commons.math3.random.RandomVectorGenerator;
import org.apache.commons.math3.random.SobolSequenceGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import edu.csupomona.cs480.links.provider.*;
import edu.csupomona.cs480.links.*;

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
	@RequestMapping(value = "/cs480/user/{userId}", method = RequestMethod.GET)
	User getUser(@PathVariable("userId") String userId) {
		User user = userManager.getUser(userId);
		return user;
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
	 * @param id
	 * @param name
	 * @param major
	 * @return
	 */
	@RequestMapping(value = "/cs480/user/{userId}", method = RequestMethod.POST)
	User updateUser(
			@PathVariable("userId") String id,
			@RequestParam("name") String name,
			@RequestParam(value = "major", required = false) String major) {
		User user = new User();
		user.setId(id);
		user.setMajor(major);
		user.setName(name);
		userManager.updateUser(user);
		return user;
	}

	/**
	 * This API deletes the user. It uses HTTP DELETE method.
	 *
	 * @param userId
	 */
	@RequestMapping(value = "/cs480/user/{userId}", method = RequestMethod.DELETE)
	void deleteUser(
			@PathVariable("userId") String userId) {
		userManager.deleteUser(userId);
	}

	/**
	 * This API lists all the users in the current database.
	 *
	 * @return
	 */
	@RequestMapping(value = "/cs480/users/list", method = RequestMethod.GET)
	List<User> listAllUsers() {
		return userManager.listAllUsers();
	}

	/*********** Web UI Test Utility **********/
	/**
	 * This method provide a simple web UI for you to test the different
	 * functionalities used in this web service.
	 */
	@RequestMapping(value = "/cs480/home", method = RequestMethod.GET)
	ModelAndView getUserHomepage() {
		ModelAndView modelAndView = new ModelAndView("home");
		modelAndView.addObject("users", listAllUsers());
		return modelAndView;
	}
	
	/*********** Web UI Test Utility **********/
	/**
	 * This method provide a simple web UI for you to test the different
	 * functionalities used in this web service.
	 */
	@RequestMapping(value = "/cs480", method = RequestMethod.GET)
	ModelAndView getUserHomepaged() {
		ModelAndView modelAndView = new ModelAndView("home");
		modelAndView.addObject("users", listAllUsers());
		return modelAndView;
	}
	
	@RequestMapping(value = "/Frienduler", method = RequestMethod.GET)
	ModelAndView Frienduler() {
		ModelAndView modelAndView = new ModelAndView("Frienduler");
		modelAndView.addObject("users", listAllUsers());
		return modelAndView;
	}
	@RequestMapping(value = "/Frienduler/Table", method = RequestMethod.GET)
	ModelAndView Table() {
		ModelAndView modelAndView = new ModelAndView("MainPG");
		modelAndView.addObject("users", listAllUsers());
		return modelAndView;
	}
	
	@RequestMapping(value = "/Frienduler/addfriend", method = RequestMethod.GET)
	ModelAndView AddFriend() {
		ModelAndView modelAndView = new ModelAndView("addfriend");
		modelAndView.addObject("users", listAllUsers());
		return modelAndView;
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