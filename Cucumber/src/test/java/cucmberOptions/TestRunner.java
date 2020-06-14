package cucmberOptions;



import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

import org.junit.runner.RunWith;



@RunWith(Cucumber.class)       
@CucumberOptions(
		features =  "src/test/java/Feature",
		glue = "stepDefinitions", //we need to pass information about feature file and stepsDefined for that feature in testrunner
		 
		monochrome = true)
///pretty:target/cucumber-htmlreport.text", "json:target/cucmber-report.json"}
public class TestRunner {

	
	
}
/*Scenario Outline: Make My Trip Flight Booking  
Given Open "<Browser>"
When MakemyTrip Booking
Then Veriify the Page
Examples: 
| Browser |
| Chrome  |
| FFox    |*/