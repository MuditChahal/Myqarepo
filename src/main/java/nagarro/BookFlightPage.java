package nagarro;

import java.util.Hashtable;

import org.openqa.selenium.By;

public class BookFlightPage extends BasePage {

	// Elements Locators
	private String xPath_btnSecurePurchase = "//*[@name='buyFlights']";
	private String xPath_txtFirstName = "//*[@name='passFirst0']";
	private String xPath_txtLastName = "//*[@name='passLast0']";
	private String xPath_txtCreditCardNumber = "//*[@name='creditnumber']";
	private String xPath_chkTicketLess = "//*[@name='ticketLess']";

	// Page Title Text
	private static String titleBookFlightPageHeader = "Book a Flight: Mercury Tours";

	// -------------------------------------------------------------------------------------------------------------------------------------
	// verify Select Flight Page is displayed
	public boolean checkBookFlightPageIsDisplayed() {
		// wait till Flight Finder page is not displayed for max 60 sec

		if (BasePage.driver.getTitle().equals(titleBookFlightPageHeader))
			return true;

		return false;
	}

	// -------------------------------------------------------------------------------------------------------------------------------------
	// Click on Secure Purchase button
	public BookFlightPage clickOnContinue() {
		driver.findElement(By.xpath(xPath_btnSecurePurchase)).click();
		return new BookFlightPage();
	}

	// -------------------------------------------------------------------------------------------------------------------------------------
	// Insert Values into Flight Details Form
	public void insertValuesInFlightDetailForm(Hashtable<String, String> testData) {
		if (testData.containsKey("FirstName"))
			driver.findElement(By.xpath(xPath_txtFirstName)).sendKeys(testData.get("FirstName"));
		if (testData.containsKey("LastName"))
			driver.findElement(By.xpath(xPath_txtLastName)).sendKeys(testData.get("LastName"));
		if (testData.containsKey("CreditCardNumber"))
			driver.findElement(By.xpath(xPath_txtCreditCardNumber)).sendKeys(testData.get("CreditCardNumber"));
		if (testData.containsKey("CreditCardNumber")) {
			if (testData.get("isTicketLess").equalsIgnoreCase("true"))
				driver.findElement(By.xpath(xPath_chkTicketLess)).click();
		}

	}
}
