package nagarro;

import java.util.Hashtable;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import nagarro.LoginPage;
import nagarro.BasePage;
import nagarro.main.utility.UtilityFunctions;
import nagarro.SelectFlightPage;
import nagarro.FlightFinderPage;

public class TestMercuryLogin extends BasePage {
	private static LoginPage login = null;
	private static FlightFinderPage flightFinderPage = null;
	
	@Test(dataProvider = "TC_001DataProvider")
    	
	public void verifyValidLogin(Hashtable<String, String> testData){				
					
		try {
			test=extent.startTest("Login test case");
			
			 LoginPage login = new LoginPage(BasePage.prop.getProperty("Browser"), BasePage.prop.getProperty("TestSiteURL"));
			
			 //Call the method		
			
			 flightFinderPage = login.LoginToTours(BasePage.prop.getProperty("UserName"), BasePage.prop.getProperty("Password"));
			
	 
			 test.log(LogStatus.PASS, "Login test case"+ "Test Passed Sucessfully");
			 

			
		} catch (Exception e) {
			
			// TODO Auto-generated catch block
			test.log(LogStatus.FAIL, "Login test case"+ "Test Failed");
			e.printStackTrace();
			
		}	
		
		
	}


	@DataProvider
	public Object[][] TC_001DataProvider() {
	
		return UtilityFunctions.getData("TC_001", BasePage.xls);
	}

}
