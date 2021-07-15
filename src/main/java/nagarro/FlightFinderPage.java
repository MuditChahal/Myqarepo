package nagarro;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

public class FlightFinderPage extends BasePage {

	// Elements Locators
	private String xPath_btnContinue = "//*[@name='findFlights']";
	private String xPath_radOneWay = "//*[@value='oneway']";
	private String xPath_radRoundTrip = "//*[@value='roundtrip']";
	private String xPath_ddlDepartFrom = "//*[@name='fromPort']";
	private String xPath_ddlArriveIn = "//*[@name='toPort']";
	private String xPath_radFirstclass = "//*[@value='First']";
	private String xPath_radBusinessclass = "//*[@value='Business']";
	private String xPath_radEconomyclass = "//*[@value='Coach']";

	// Page Title Text
	public static String titleFlightFinderPageHeader = "Find a Flight: Mercury Tours:";

	// -------------------------------------------------------------------------------------------------------------------------------------
	// verify Flight Finder Page is displayed
	public boolean checkFlightFinderPageIsDisplayed() {
		// wait till Flight Finder page is not displayed for max 60 sec
		if (BasePage.driver.findElement(By.xpath(xPath_btnContinue)).isDisplayed()) {
			return true;
		}

		return false;
	}

	// -------------------------------------------------------------------------------------------------------------------------------------
	// Click on Radio button
	public void clickRadiobox(String name) {
		if (name.equalsIgnoreCase("OneWay"))
			driver.findElement(By.xpath(xPath_radOneWay)).click();
		else if (name.equalsIgnoreCase("Round"))
			driver.findElement(By.xpath(xPath_radRoundTrip)).click();
		else if (name.equalsIgnoreCase("First"))
			driver.findElement(By.xpath(xPath_radFirstclass)).click();
		else if (name.equalsIgnoreCase("Business"))
			driver.findElement(By.xpath(xPath_radBusinessclass)).click();
		else if (name.equalsIgnoreCase("Economy"))
			driver.findElement(By.xpath(xPath_radEconomyclass)).click();
	}

	// -------------------------------------------------------------------------------------------------------------------------------------
	// Select value from Drop Down
	public void selectValueFromDropDown(String dropDownType, String dropDownValue) {
		String temp = "";
		if (dropDownType.equalsIgnoreCase("DepartFrom"))
			temp = xPath_ddlDepartFrom;
		else if (dropDownType.equalsIgnoreCase("ArriveIn"))
			temp = xPath_ddlArriveIn;

		Select sel = new Select(driver.findElement(By.xpath(temp)));
		sel.selectByVisibleText(dropDownValue);

	}

	// -------------------------------------------------------------------------------------------------------------------------------------
	// Click on COntinue button
	public SelectFlightPage clickOnContinue() {
		driver.findElement(By.xpath(xPath_btnContinue)).click();
		return new SelectFlightPage();
	}

}
