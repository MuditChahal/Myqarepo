package nagarro;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.testng.Assert;

//import com.sun.java.util.jar.pack.Package.File;

import nagarro.FlightFinderPage;


public class LoginPage extends BasePage {
	
	// Text Fields

	private final String xPath_txtUserName = "//*[@id=\"email\"]";
	private final String xPath_txtPassword = "//*[@id=\"passwd\"]";
	private final String class_txtSignOut = "logout";
	
	// Buttons
	private final String xPath_signIn = "login";
	private final String xPath_btnLoginButton = "SubmitLogin";
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;		
	}
	
	// Parameterized constructor. Will take Browser Name and the url of the
	// applications(as set in the configuration file)
	public LoginPage(String browser, String appURL) {
		super(browser, appURL);
	}
	
	// Login the application with the provided User name and Password
	public FlightFinderPage LoginToTours(String uid, String pwd) throws Exception {
		
		try {
			
			driver.findElement(By.className(xPath_signIn)).click();
			driver.findElement(By.xpath(xPath_txtUserName)).clear();
			driver.findElement(By.xpath(xPath_txtUserName)).sendKeys(uid);
			driver.findElement(By.xpath(xPath_txtPassword)).clear();
			driver.findElement(By.xpath(xPath_txtPassword)).sendKeys(pwd);

			// click on login button 
			driver.findElement(By.name(xPath_btnLoginButton)).click();
			
			//Verify whether login is successful
			WebElement herLink = driver.findElement(By.className("logout"));
			java.io.File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(screenshot, new java.io.File("D:\\screenshots1.jpg"));
		      
			Assert.assertEquals(true, herLink.isDisplayed());
			
			return new FlightFinderPage();
	
		} catch (Exception e) {
			e.printStackTrace();
			
			return null;
		}
		
	}
	


}
