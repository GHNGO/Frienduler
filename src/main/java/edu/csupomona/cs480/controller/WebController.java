package edu.csupomona.cs480.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import edu.csupomona.cs480.App;
import edu.csupomona.cs480.data.User;
import edu.csupomona.cs480.data.provider.UserManager;
import sun.misc.IOUtils;
import sun.nio.ch.IOUtil;

import javax.imageio.IIOException;


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

	//Lloyd Zhang's A3
	@RequestMapping( value = "/cs480/lloyd", method = RequestMethod.GET )
	String returnGoodByeWorld()
	{
		return "Goodbye World!"
	}

	//Jack Zhang A3
	@RequestMapping(value = "/cs480/shakeNbake", method = RequestMethod.GET)
	String Dungo() {
		return("Bob it!");
	}

	//Connor Baskin A3
	@RequestMapping(value = "/cs480/baskin", method = RequestMethod.GET)
	@ResponseBody String snakeGame() {
		String returned = "<h1 style=\"font-size: 38px; font-family:'Comic Sans MS'\">Hi there, here's a snake game for when you get bored, you're welcome.</h1>\n\n" +
				"<script src=\"http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js\" type=\"text/javascript\"></script>\n" +
				"<script>function playSnake(){\n" +
				"    var canvas = $(\"#canvas\")[0];\n" +
				"    var ctx = canvas.getContext(\"2d\");\n" +
				"    var w = $(\"#canvas\").width();\n" +
				"    var h = $(\"#canvas\").height();\n" +
				"    var cw = 10;\n" +
				"    var d;\n" +
				"    var food;\n" +
				"    var score;\n" +
				"\n" +
				"    //Lets create the snake now\n" +
				"    var snake_array; //an array of cells to make up the snake\n" +
				"\n" +
				"    function init()\n" +
				"    {\n" +
				"        d = \"right\"; //default direction\n" +
				"        create_snake();\n" +
				"        create_food();\n" +
				"        score = 0;\n" +
				"\n" +
				"        if(typeof game_loop != \"undefined\") clearInterval(game_loop);\n" +
				"        game_loop = setInterval(paint, 60);\n" +
				"    }\n" +
				"    init();\n" +
				"\n" +
				"    function create_snake()\n" +
				"    {\n" +
				"        var length = 5; //Length of the snake\n" +
				"        snake_array = []; //Empty array to start with\n" +
				"        for(var i = length-1; i>=0; i--)\n" +
				"        {\n" +
				"            snake_array.push({x: i, y:0});\n" +
				"        }\n" +
				"    }\n" +
				"\n" +
				"    function create_food()\n" +
				"    {\n" +
				"        food = {\n" +
				"            x: Math.round(Math.random()*(w-cw)/cw),\n" +
				"            y: Math.round(Math.random()*(h-cw)/cw),\n" +
				"        };\n" +
				"    }\n" +
				"\n" +
				"    function paint()\n" +
				"    {\n" +
				"        ctx.fillStyle = \"white\";\n" +
				"        ctx.fillRect(0, 0, w, h);\n" +
				"        ctx.strokeStyle = \"black\";\n" +
				"        ctx.strokeRect(0, 0, w, h);\n" +
				"\n" +
				"        var nx = snake_array[0].x;\n" +
				"        var ny = snake_array[0].y;\n" +
				"        if(d == \"right\") nx++;\n" +
				"        else if(d == \"left\") nx--;\n" +
				"        else if(d == \"up\") ny--;\n" +
				"        else if(d == \"down\") ny++;\n" +
				"\n" +
				"        if(nx == -1 || nx == w/cw || ny == -1 || ny == h/cw || check_collision(nx, ny, snake_array))\n" +
				"        {\n" +
				"            alert(\"You died :(\");\n" +
				"            //restart game\n" +
				"            init();\n" +
				"            return;\n" +
				"        }\n" +
				"\n" +
				"        if(nx == food.x && ny == food.y)\n" +
				"        {\n" +
				"            var tail = {x: nx, y: ny};\n" +
				"            score++;\n" +
				"            //Create new food\n" +
				"            create_food();\n" +
				"        }\n" +
				"        else\n" +
				"        {\n" +
				"            var tail = snake_array.pop(); //pops out the last cell\n" +
				"            tail.x = nx; tail.y = ny;\n" +
				"        }\n" +
				"\n" +
				"        snake_array.unshift(tail); //puts back the tail as the first cell\n" +
				"\n" +
				"        for(var i = 0; i < snake_array.length; i++)\n" +
				"        {\n" +
				"            var c = snake_array[i];\n" +
				"            paint_cell(c.x, c.y);\n" +
				"        }\n" +
				"\n" +
				"        paint_cell(food.x, food.y);\n" +
				"        var score_text = \"Score: \" + score;\n" +
				"        ctx.fillText(score_text, 5, h-5);\n" +
				"    }\n" +
				"\n" +
				"    function paint_cell(x, y)\n" +
				"    {\n" +
				"        ctx.fillStyle = \"blue\";\n" +
				"        ctx.fillRect(x*cw, y*cw, cw, cw);\n" +
				"        ctx.strokeStyle = \"white\";\n" +
				"        ctx.strokeRect(x*cw, y*cw, cw, cw);\n" +
				"    }\n" +
				"\n" +
				"    function check_collision(x, y, array)\n" +
				"    {\n" +
				"        for(var i = 0; i < array.length; i++)\n" +
				"        {\n" +
				"            if(array[i].x == x && array[i].y == y)\n" +
				"                return true;\n" +
				"        }\n" +
				"        return false;\n" +
				"    }\n" +
				"\n" +
				"    $(document).keydown(function(e){\n" +
				"        var key = e.which;\n" +
				"        //We will add another clause to prevent reverse gear\n" +
				"        if(key == \"37\" && d != \"right\") d = \"left\";\n" +
				"        else if(key == \"38\" && d != \"down\") d = \"up\";\n" +
				"        else if(key == \"39\" && d != \"left\") d = \"right\";\n" +
				"        else if(key == \"40\" && d != \"up\") d = \"down\";\n" +
				"    })\n" +
				"}</script>\n" +
				"\n" +
				"\n" +
				"<button onclick=\"playSnake()\">Click here to play snake</button>\n" +
				"<canvas id=\"canvas\" width=\"450\" height=\"450\"></canvas>" +
		"<p>Original game from <a href=\"http://thecodeplayer.com/walkthrough/html5-game-tutorial-make-a-snake-game-using-html5-canvas-jquery\">http://thecodeplayer.com/walkthrough/html5-game-tutorial-make-a-snake-game-using-html5-canvas-jquery</a>; Edits by Connor Baskin</p> ";
		return returned;
	}

}