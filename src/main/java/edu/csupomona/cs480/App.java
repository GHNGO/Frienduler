package edu.csupomona.cs480;

import edu.csupomona.cs480.data.Number;
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import edu.csupomona.cs480.data.provider.FSUserManager;
import edu.csupomona.cs480.data.provider.UserManager;
import edu.csupomona.cs480.database.DatabaseInterface;
import edu.csupomona.cs480.links.provider.LinkManager;

import java.sql.SQLException;
import java.util.ArrayList;

@Configuration
@EnableAutoConfiguration
@ComponentScan
public class App {

    /**
     * This is a good example of how Spring instantiates
     * objects. The instances generated from this method
     * will be used in this project, where the Autowired
     * annotation is applied.
     */
    @Bean
    public UserManager userManager() {
        UserManager userManager = new FSUserManager();
        return userManager;
    }

    @Bean
    public DescriptiveStatistics numberStats() {
        return new DescriptiveStatistics();
    }

    @Bean
    public ArrayList<Number> numbers() {
        return new ArrayList<Number>();
    }
    
    @Bean
    public LinkManager linkManager(){
    	return new LinkManager();
    }

    @Bean
    public static DatabaseInterface sqlInterface() {
        try {
            return new DatabaseInterface("jdbc:mysql://friendulerghngo.cu5actdqi4u0.us-west-2.rds.amazonaws.com:3306/GHNGOFrienduler?user=admin&password=9jxtza999rFxHfSRKspxJ7gr8");

        } catch (SQLException e) {
            System.err.println("SQLException: " + e.getMessage());
            System.err.println("SQLState: " + e.getSQLState());
            System.err.println("VendorError: " + e.getErrorCode());
            System.exit(-1);
            return null;
        }
    }

    /**
     * This is the running main method for the web application.
     * Please note that Spring requires that there is one and
     * ONLY one main method in your whole program. You can create
     * other main methods for testing or debugging purposes, but
     * you cannot put extra main method when building your project.
     */
    public static void main(String[] args) throws Exception {
        // Run Spring Boot
        SpringApplication.run(App.class, args);
    }
}
