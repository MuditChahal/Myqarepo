package nagarro;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

public class SelectFlightPage extends BasePage {

	// Elements Locators
	private String xPath_btnContinue = "//*[@name='reserveFlights']";

	// Page Title Text
	public static String titleSelectFlightPageHeader = "Select a Flight: Mercury Tours";

	// -------------------------------------------------------------------------------------------------------------------------------------
	// verify Select Flight Page is displayed
	public boolean checkSelectFlightPageIsDisplayed() {
		// wait till Flight Finder page is not displayed for max 60 sec

		if (BasePage.driver.findElement(By.xpath(xPath_btnContinue)).isDisplayed()) {
			return true;
		}

		return false;
	}

	// -------------------------------------------------------------------------------------------------------------------------------------
	// Click on COntinue button
	public BookFlightPage clickOnContinue() {
		driver.findElement(By.xpath(xPath_btnContinue)).click();
		return new BookFlightPage();
	}

}
