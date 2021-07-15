package nagarro;

import java.util.Hashtable;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.relevantcodes.extentreports.LogStatus;
import com.relevantcodes.extentreports.LogStatus;

import nagarro.main.utility.UtilityFunctions;
import nagarro.BookFlightPage;

public class TestBookFlight extends BasePage {
	
	private static LoginPage login = null;
	private static FlightFinderPage flightFinderPage = null;
	
	@Test(dataProvider = "TC_001DataProvider")
    	
	public void verifyValidLogin(Hashtable<String, String> testData){				
					
		try {
			
			 test=extent.startTest("Book Flight test case");
			 
			 LoginPage login = new LoginPage(BasePage.prop.getProperty("Browser"), BasePage.prop.getProperty("TestSiteURL"));
			
			 //Call the method		
			
			 flightFinderPage = login.LoginToTours(BasePage.prop.getProperty("UserName"), BasePage.prop.getProperty("UserName"));
			
			 // Click on Continue button
			 SelectFlightPage selectFlightPage = flightFinderPage.clickOnContinue();
			 
			// CLick on Continue button
			BookFlightPage bookFlightPage = selectFlightPage.clickOnContinue();

			bookFlightPage.insertValuesInFlightDetailForm(testData);

			// Click on Secure Payment button
			bookFlightPage.clickOnContinue();

			test.log(LogStatus.PASS, "Book Flight test case"+ "Test Passed Sucessfully");
			
		} catch (Exception e) {
			
			// TODO Auto-generated catch block
			test.log(LogStatus.FAIL, "Book Flight test case"+ "Test Failed ");
			e.printStackTrace();
			
		}	
		
		
	}


	@DataProvider
	public Object[][] TC_001DataProvider() {
	
		return UtilityFunctions.getData("TC_001", BasePage.xls);
	}

}
