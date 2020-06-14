package stepDefinitions;


import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.After;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class stepDefinition {
	
	static WebDriver driver1;
	public static ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();
	
	
	public void setChromeDriver(){
		System.setProperty("webdriver.chrome.driver", "Driver/chromedriver.exe"); //jar/chromedriver.exe
		this.driver1 = new ChromeDriver();
		driver.set(driver1);
		driver1.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
		driver1.manage().window().maximize();
	}
	
	
	public void setfDriver() {
		//System.setProperty("webdriver.chrome.driver", "C:\\Users\\Gokul  Kannan.B\\Downloads\\Jars\\chromeDriver_80\\chromedriver.exe"); //jar/chromedriver.exe
		System.setProperty("webdriver.gecko.driver","Driver/geckodriver.exe");
		this.driver1 = new FirefoxDriver();
		driver.set(driver1);
		driver().manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
		driver().manage().window().maximize();
	}
	
	@After
	public void afterscenario() {
		System.out.println("After scenario");
		driver().close();
	}
	
	public WebDriver driver() {
		return driver.get();
	}
	
	
	  @Given("^Open \"([^\"]*)\"$")
	  public void open_something(String browser) throws Throwable {
		  System.out.println(browser);
		  if(browser.equalsIgnoreCase("chrome")) {
			  setChromeDriver();
		//	  driver().close();
		  }

		  else {
			  setfDriver();	 
			//  driver().close();
		  }

	  }
	
	
	
	  @When("MakemyTrip Booking")
	  public void makemytrip_Booking(DataTable table) throws InterruptedException {
			driver().get("https://www.makemytrip.com/");

			Thread.sleep(5000);

			List<List<String>> data = table.asLists();
			
			
			String deptcity = data.get(0).get(0);

			String arrcity = data.get(0).get(1);

			driver().findElement(By.xpath("//input[@id=\"fromCity\"]")).click();

			Thread.sleep(2000);

			driver().findElement(By.xpath("//input[@placeholder=\"From\"]")).sendKeys(deptcity+Keys.RETURN);

			driver().findElement(By.xpath("//input[@placeholder=\"From\"]")).sendKeys(Keys.RETURN);

			//WebElement firstsug = driver().findElement(By.xpath("(//div[@class=\"makeFlex hrtlCenter\"])[1]"));

			Thread.sleep(2000);

			WebElement firstsug = driver().findElement(By.xpath("//li[@id=\"react-autowhatever-1-section-0-item-0\"]"));



			Actions actions = new Actions(driver());

			Thread.sleep(4000);
			//actions.moveToElement(firstsug);



			firstsug.click();


			Thread.sleep(2000);

			driver().findElement(By.xpath("//input[@placeholder=\"To\"]")).sendKeys(arrcity+Keys.RETURN);

			Thread.sleep(2000);

			WebElement secondsug = driver().findElement(By.xpath("//li[@id=\"react-autowhatever-1-section-0-item-0\"]"));

			//actions.moveToElement(secondsug).keyDown(Keys.RETURN).build().perform();

			Thread.sleep(2000);

			secondsug.click();

			//	driver().findElement(By.xpath("//input[@id=\"departure\"]")).click();



			for(int i = 0 ; i<12 ; i++) {
				String test = driver().findElement(By.xpath("(//div[@class=\"DayPicker-Caption\"])[2]")).getText();
				String test1 = driver().findElement(By.xpath("(//div[@class=\"DayPicker-Caption\"])[1]")).getText();
				if(!(driver().findElement(By.xpath("(//div[@class=\"DayPicker-Caption\"])[1]")).getText().contains("March") || driver().findElement(By.xpath("(//div[@class=\"DayPicker-Caption\"])[2]")).getText().contains("March"))) {
					driver().findElement(By.xpath("//span[@class=\"DayPicker-NavButton DayPicker-NavButton--next\"]")).click();
				} else {
					if(driver().findElement(By.xpath("(//div[@class=\"DayPicker-Caption\"])[1]")).getText().contains("March") ) {
						driver().findElement(By.xpath("(//div[@class=\"dateInnerCell\" and contains(.,\"17\")])[1]")).click();
					}

					if(driver().findElement(By.xpath("(//div[@class=\"DayPicker-Caption\"])[2]")).getText().contains("March") ) {
						driver().findElement(By.xpath("(//div[@class=\"dateInnerCell\" and contains(.,\"17\")])[2]")).click();
					}

					break;
				}

			}


			driver().findElement(By.xpath("//a[contains(.,\"Search\")]")).click();

			Thread.sleep(2000);


			try {
				driver().findElement(By.xpath("//span[@class=\"pointer\"]/span[contains(.,\"Price\")]/following::span[@class=\"down sort-arrow\"]")).isDisplayed();
			} catch (Exception E) {
				driver().findElement(By.xpath("//span[@class=\"up sort-arrow\"]")).click();
			}
			driver().findElement(By.xpath("(//button[@class=\"ViewFareBtn  text-uppercase \"])[1]")).click();
			driver().findElement(By.xpath("(//button[contains(.,\" Book Now \")])[1]")).click();

			ArrayList<String> tabs = new ArrayList<String> (driver().getWindowHandles());
			driver().switchTo().window(tabs.get(1));
			Thread.sleep(3000);

			//Verification
			driver().findElement(By.xpath("//h4[contains(.,\"Review your booking\")]")).isDisplayed();
			driver().findElement(By.xpath("//p[@class=\"dept-city\" and contains(.,\""+deptcity+"\")]")).isDisplayed();
			driver().findElement(By.xpath("//p[@class=\"arrival-city\" and contains(.,\""+arrcity+"\")]")).isDisplayed();
/*
			driver().close();
			Thread.sleep(3000);
			driver().switchTo().window(tabs.get(0));
*/		
	  }
	
	
	
	  @Then("Veriify the Page")
	  public void MMT_Validation() throws InterruptedException {
		  
		  ArrayList<String> tabs = new ArrayList<String> (driver().getWindowHandles());
			
			driver().close();
			Thread.sleep(3000);
			driver().switchTo().window(tabs.get(0));
		  
	  }
	
	  public static String mailid = "";
	
	  @When("Gillette India create account")
	  public void Create_Acc_India() throws Throwable {
		  
		  driver().get("https://www.gillette.co.in/en-in");
		  Thread.sleep(5000);
		  
		  driver().findElement(By.xpath("//a[@title=\"REGISTER\"]")).click();
		  
		  
		  driver().findElement(By.xpath("//input[@data-key=\"firstName\"]")).sendKeys("Test");
		  driver().findElement(By.xpath("//input[@data-key=\"lastName\"]")).sendKeys("Engineer");
			Random rand = new Random(); //instance of random class
			int upperbound = 1000;
			int generatedString = rand.nextInt(upperbound);
			mailid = "Test"+generatedString+"@gmail.com";	
			
		  driver().findElement(By.xpath("//input[@data-key=\"emailAddress\"]")).sendKeys(mailid);	
			
		  driver().findElement(By.xpath("//input[@data-key=\"newPassword\"]")).sendKeys("Test@123");
		  driver().findElement(By.xpath("//input[@id=\"phdesktopbody_0_grs_account[password][confirm]\"]")).sendKeys("Test@123");
		  driver().findElement(By.xpath("//select[@data-key=\"birthdate[dateselect_month]\"]")).click();
		  driver().findElement(By.xpath("//select[@data-key=\"birthdate[dateselect_month]\"]/option[@value=\"03\"]")).click();
		  
		  driver().findElement(By.xpath("//select[@data-key=\"birthdate[dateselect_year]\"]")).click();
		  driver().findElement(By.xpath("//select[@data-key=\"birthdate[dateselect_year]\"]/option[@value=\"1996\"]")).click();
		  driver().findElement(By.xpath("//input[@data-key=\"addressPostalCode\"]")).sendKeys("638402");
		  driver().findElement(By.xpath("//input[@id=\"phdesktopbody_0_submit\"]")).click();
	Thread.sleep(5000);	  
		//  driver().findElement(By.xpath("//input[@value=\"Sign In\"]")).click();
		  
		  /*driver().findElement(By.xpath("//input[@data-key=\"signInEmailAddress\"]")).sendKeys(mailid);
		  
		  driver().findElement(By.xpath("//input[@data-key=\"currentPassword\"]")).sendKeys("Test@123");
		
		  driver().findElement(By.xpath("//input[@value=\"Sign In\"]")).click();
		  
		  Thread.sleep(5000);
		  
		  driver().findElement(By.xpath("//a[@title=\"log out\"]")).click();
		  driver().findElement(By.xpath("//a[@title=\"LOG OUT\"]")).click();
		
		  driver().findElement(By.xpath("//a[@title=\"password?\"]")).click();
		  
		  driver().findElement(By.xpath("//input[@data-key=\"signInEmailAddress\"]")).sendKeys(mailid);
		  driver().findElement(By.xpath("//input[@value=\"Create Your New Password\"]")).click();
	*/  }
	
	
	  @And("Sign in and Sign out")
	  public void signin_india() throws Throwable {
	
		  driver().findElement(By.xpath("//input[@data-key=\"signInEmailAddress\"]")).sendKeys(mailid);
		  
		  driver().findElement(By.xpath("//input[@data-key=\"currentPassword\"]")).sendKeys("Test@123");
		
		  driver().findElement(By.xpath("//input[@value=\"Sign In\"]")).click();
		  
		  Thread.sleep(5000);
		  
		  driver().findElement(By.xpath("//a[@title=\"log out\"]")).click();
		  driver().findElement(By.xpath("//a[@title=\"LOG OUT\"]")).click();
	}
	
	  @And("forgot passwprd")
	  public void forgotpassword() throws Throwable {
		  driver().findElement(By.xpath("//a[@title=\"password?\"]")).click();
		  
		  driver().findElement(By.xpath("//input[@data-key=\"signInEmailAddress\"]")).sendKeys(mailid);
		  driver().findElement(By.xpath("//input[@value=\"Create Your New Password\"]")).click();
		  
	  }
	  @Then("Verify gillette india")
	  public void verify_Gillette() throws Throwable {
		  driver().findElement(By.xpath("//div[@id=\"phdesktopbody_0_afterSubmit\"]/div/h1")).getText();
		  assertEquals("THANK YOU!",driver().findElement(By.xpath("//div[@id=\"phdesktopbody_0_afterSubmit\"]/div/h1")).getText());
	  }
	  
	  
	  
	  
	  
	  @When("Gillette German create account")
	  public void Create_Acc_GerMan() throws Throwable {
		  
		  driver().get("https://www.gillette.de/");
		  Thread.sleep(5000);
		  
		  driver().findElement(By.xpath("//a[@class=\"responsiveAccountHeader_openAccountButton\"]")).click();
		  //driver().findElement(By.xpath("//a[@class=\"responsiveAccountHeader_accountLogin js-e2e-sign-in\"]")).click();
		  driver().findElement(By.xpath("//button[@class=\"accountLogin_newAccountButton\"]")).click();
		  
		  driver().findElement(By.xpath("//input[@id=\"customerName\"]")).sendKeys("Test Engineer");;
		
		  Random rand = new Random(); //instance of random class
			int upperbound = 1000;
			int generatedString = rand.nextInt(upperbound);
			mailid = "Test"+generatedString+"@gmail.com";
		
			
			driver().findElement(By.xpath("//input[@id=\"customerEmail\"]")).sendKeys(mailid);
			driver().findElement(By.xpath("//input[@id=\"confirmCustomerEmail\"]")).sendKeys(mailid);
			
			driver().findElement(By.xpath("//input[@id=\"customerPassword\"]")).sendKeys("Test@123");
			driver().findElement(By.xpath("//input[@id=\"confirmPassword\"]")).sendKeys("Test@123");
			JavascriptExecutor jse = (JavascriptExecutor)driver();
			jse.executeScript("window.scrollBy(0,400)");
			//jse.executeScript("arguments[0].scrollIntoView(true);",driver().findElement(By.xpath("//label[@class=\"accountSignUp_optOutLabel_RadioButtons\"]")));
			driver().findElement(By.xpath("//label[@class=\"accountSignUp_optOutLabel_RadioButtons\"]")).click();
			
			
			jse.executeScript("window.scrollBy(0,300)");
			driver().findElement(By.xpath("//button[@id=\"continue\"]")).click();
			
			
			
			
			
			
			
			//driver().findElement(By.xpath("//button[@title=\"Account menu\"]")).click();
			//driver().findElement(By.xpath("//a[@class=\"responsiveAccountHeader_accountLogin js-e2e-sign-in\"]")).click();
			
			//String validation = driver().findElement(By.xpath("//p[contains(.,\"Anweisung \")]")).getText();
			
			//assertEquals("Anweisung zur Zurücksetzung deines Passwortes wurden an die folgende Adresse gesendet",driver().findElement(By.xpath("//p[contains(.,\"Anweisung \")]")).getText());
			//assert
			
			
			
			
			
	 
			}
	
	  
	  
	  @And("Sign in and Sign out - German")
	  public void signin_india_G() throws Throwable {
	
		  driver().findElement(By.xpath("//button[@class=\"myAccount_logOutButton js-tag-sign-out\"]")).click();
			//driver().findElement(By.xpath("//button[@title=\"Account menu\"]")).click();
			//driver().findElement(By.xpath("//a[@class=\"responsiveAccountHeader_accountLogin js-e2e-sign-in\"]")).click();
			driver().findElement(By.xpath("//a[@class=\"responsiveAccountHeader_openAccountButton\"]")).click();
			
			
			driver().findElement(By.xpath("//input[@id=\"username\"]")).sendKeys(mailid);
			driver().findElement(By.xpath("//input[@id=\"password\"]")).sendKeys("Test@123");
			driver().findElement(By.xpath("//button[@id=\"login-submit\"]")).click();
			
			
			driver().findElement(By.xpath("//button[@class=\"myAccount_logOutButton js-tag-sign-out\"]")).click();
	}
	
	  @And("forgot passwprd - German")
	  public void forgotpassword_G() throws Throwable {
		  driver().findElement(By.xpath("//a[@class=\"responsiveAccountHeader_openAccountButton\"]")).click();
			
			driver().findElement(By.xpath("//button[@class=\"forgottenPasswordModal_trigger\"]")).click();
			driver().findElement(By.xpath("//input[@id=\"forgotten-password-email-field\"]")).sendKeys(mailid);
			driver().findElement(By.xpath("//input[@value=\"PASSWORT ZURÜCKSETZEN\"]")).click();
			
			
			//p[contains(.,"Anweisung zur Zurücksetzung deines Passwortes wurden an die folgende Adresse gesendet")]
			Thread.sleep(5000);
		  
	  }
	  @Then("Verify gillette - German")
	  public void verify_Gillette_G() throws Throwable {
		  assertTrue(driver().findElement(By.xpath("//p[contains(.,\"Anweisung \")]")).isDisplayed());
		  assertEquals(mailid,driver().findElement(By.xpath("//span[@class=\"forgottenPasswordModal_dialog_success_email\"]")).getText());
	  }

	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  @When("Gillette France create account")
	  public void gillette_France_create_account() throws InterruptedException {
		  //Account creation
		  driver().get("https://www.gillette.fr/");
		  Thread.sleep(5000);
		  
		  driver().findElement(By.xpath("//a[@data-action-detail=\"Mon compte\"]")).click();
		  
		  Random rand = new Random(); //instance of random class
			int upperbound = 1000;
			int generatedString = rand.nextInt(upperbound);
			mailid = "Test"+generatedString+"@gmail.com";
			
		  driver().findElement(By.xpath("//input[@id=\"email_create\"]")).sendKeys(mailid);
		  
		  driver().findElement(By.xpath("//button[@id=\"SubmitCreate\"]")).click();
		
  
		  driver().findElement(By.xpath("//input[@id=\"customer_firstname\"]")).sendKeys("Test");;

		  driver().findElement(By.xpath("//input[@id=\"customer_lastname\"]")).sendKeys("Engineer");;
		
		  driver().findElement(By.xpath("//input[@id=\"email\"]")).sendKeys(mailid);
		
		  driver().findElement(By.xpath("//input[@id=\"passwd\"]")).sendKeys("Test@123");
			
		  JavascriptExecutor jse = (JavascriptExecutor)driver();
			jse.executeScript("window.scrollBy(0,100)");
		  	
		  driver().findElement(By.xpath("//select[@id=\"years\"]")).click();
		  
		  driver().findElement(By.xpath("//select[@id=\"years\"]/option[@value=\"1996\"]")).click();
		  
		  driver().findElement(By.xpath("//select[@id=\"months\"]")).click();
		  
		  driver().findElement(By.xpath("//select[@id=\"months\"]/option[@value=\"3\"]")).click();
		  
		  driver().findElement(By.xpath("//select[@id=\"days\"]")).click();
		  
		  driver().findElement(By.xpath("//select[@id=\"days\"]/option[@value=\"5\"]")).click();
		  
		  jse.executeScript("window.scrollBy(0,300)");
		  
		  driver().findElement(By.xpath("//div[@id=\"uniform-newsletter\"]")).click();
		  
		  jse.executeScript("window.scrollBy(0,400)");
		  
		  Actions actions = new Actions(driver());
//		  actions. moveToElement(driver(). findElement(By.xpath("//div[@class=\"recaptcha-checkbox-checkmark\"]")), 0, 0).click().build().perform();
		//  actions. moveByOffset(xCoordinate, yCoordinate). click(). build(). perform();
		  //driver().findElement(By.xpath("//div[@class=\"recaptcha-checkbox-checkmark\"]")).click();
		  //driver().findElement(By.xpath("//span[@class=\"recaptcha-checkbox goog-inline-block recaptcha-checkbox-unchecked rc-anchor-checkbox\"]")).click();
		  
		  /*
		  driver().findElement(By.xpath("//div[@class=\"recaptcha-checkbox-border\"]")).click();
		  
//		  Point location = driver().findElement(By.xpath("//span[@class=\"recaptcha-checkbox goog-inline-block recaptcha-checkbox-unchecked rc-anchor-checkbox\"]")).getLocation();
//		  actions. moveByOffset(location.getX(), location.getY()). click(). build(). perform();
//		  location.getX();
		//div[@class="recaptcha-checkbox-borderAnimation"]
		  Thread.sleep(4000);
		//div[@class="recaptcha-checkbox-border"]		  
		  driver().findElement(By.xpath("//button[@name=\"submitAccount\"]")).click();
		  
		  //sdgjsd@gmail.com
		    
		  //signout signin
		  driver().findElement(By.xpath("//a[@id=\"logout_link\"]")).click();
		  
		  Thread.sleep(4000);
		
		  driver().findElement(By.xpath("//a[@data-action-detail=\"Mon compte\"]")).click();
		  
		  driver().findElement(By.xpath("//input[@id=\"email\"]")).sendKeys(mailid);
		
		  driver().findElement(By.xpath("//input[@id=\"passwd\"]")).sendKeys("Test@123");
		  
		  assertTrue(driver().findElement(By.xpath("//a[@id=\"logout_link\"]")).isDisplayed());
			
		  driver().findElement(By.xpath("//a[@id=\"logout_link\"]")).click();
		  
		  
		  //forgot password
		  driver().findElement(By.xpath("//a[@data-action-detail=\"Mon compte\"]")).click();
		  
		  driver().findElement(By.xpath("//input[@id=\"email\"]")).sendKeys(mailid);
		  
		  Thread.sleep(4000);
		  
		  driver().findElement(By.xpath("//a[@class=\"lost_password grey\"]")).click();
		  
		  
		  //validation
		  driver().findElement(By.xpath("//input[@id=\"email\"]")).sendKeys(mailid);
		  
		  driver().findElement(By.xpath("//button[@class=\"password button\"]")).click();
		  
		  assertTrue(driver().findElement(By.xpath("//p[@class=\"warning\" and contains(.,\"Si le compte d'utilisateur existe, un email de confirmation a été envoyé à cette adresse e-mail.\")]")).isDisplayed());
		*/
		
					  
		  
	  }

	  @When("Sign in and Sign out - France")
	  public void sign_in_and_Sign_out_France() throws InterruptedException {
		  driver().findElement(By.xpath("//a[@id=\"logout_link\"]")).click();
		  
		  Thread.sleep(4000);
		
		  driver().findElement(By.xpath("//a[@data-action-detail=\"Mon compte\"]")).click();
		  
		  driver().findElement(By.xpath("//input[@id=\"email\"]")).sendKeys(mailid);
		
		  driver().findElement(By.xpath("//input[@id=\"passwd\"]")).sendKeys("Test@123");
		  
		  assertTrue(driver().findElement(By.xpath("//a[@id=\"logout_link\"]")).isDisplayed());
			
		  driver().findElement(By.xpath("//a[@id=\"logout_link\"]")).click();
		

	  }

	  @When("forgot passwprd - France")
	  public void forgot_passwprd_France() throws InterruptedException {

		  driver().findElement(By.xpath("//a[@data-action-detail=\"Mon compte\"]")).click();
		  
		  driver().findElement(By.xpath("//input[@id=\"email\"]")).sendKeys(mailid);
		  
		  Thread.sleep(4000);
		  
		  driver().findElement(By.xpath("//a[@class=\"lost_password grey\"]")).click();
		  
	  }

	  @Then("Verify gillette - France")
	  public void verify_gillette_France() {
		  
		  driver().findElement(By.xpath("//input[@id=\"email\"]")).sendKeys(mailid);
		  
		  driver().findElement(By.xpath("//button[@class=\"password button\"]")).click();
		  
		  assertTrue(driver().findElement(By.xpath("//p[@class=\"warning\" and contains(.,\"Si le compte d'utilisateur existe, un email de confirmation a été envoyé à cette adresse e-mail.\")]")).isDisplayed());
		

	  }



	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	
	
	@Given("User is on login page")  //We are defining action for "User is on login page" declared in login feature file. The parameter should be same as given in login feature file 
	public void User_is_on_login_page() {   //There is no nessasary to keep method name as same as declared in given in feature file
		// load the kohls.com
		
		System.out.println("Inside Given");
		driver().get("https://www.google.com/");
	}
	//button[@title="Account menu"]
	
	
	
	
	
	
	
	
	@Given("User is on gift login page") //The stepdefinition may have more than undeclared given,then,when in login feature file
	public void User_is_on_gift_login_page() {
		
	}
	
	@When("User login into application wit username and password  (selenium/appium/api code)")
	public void loginwith_Password() {   //There is no nessasary to keep method name as same as declared in given in feature file
		//login using username and password
		System.out.println("Inside When");
	}
	
	
	@Then("Homepage is populated")
	public void Verify_the_Homepage() {   //There is no nessasary to keep method name as same as declared in given in feature file
		//Verify wheater the home page og loged in user is getting displayed.
		System.out.println("Inside Then");
	}
	
	@And("content displayed")
	public void homePage_Content() {
		//Verification code of homepage content
		System.out.println("Inside And Then");
		assertEquals(1, 1);
		
	}
	
	
	
	
	@When("User login into application wit username and password")
	public void user_login_into_application_wit_username_and_password() {
	    // Write code here that turns the phrase above into concrete actions
		System.out.println("Inside When");
	}

	
}
