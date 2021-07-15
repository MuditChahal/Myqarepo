package nagarro;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import nagarro.main.utility.ExcelUtility;
import nagarro.main.utility.Extentmanager;
import nagarro.main.utility.UtilityFunctions;


/*BasePage class is the parent of all the Page classes. 
 This class is used to instantiate the driver and opens the application url in  the configured browser*/
public class BasePage {
	protected static WebDriver driver = null;
	// To read test data from properties file
	public static Properties prop = null;
	public static File file = null;
	public static FileInputStream fis = null;
	public static ExtentReports extent;
	public static ExtentTest test;
	
	// Left Navigation bar
	//	public static LeftPanel leftPanelNavigation = new LeftPanel();


	static {
		file = new File("D:\\maven\\MyMavenProject\\MyMavenProject\\src\\main\\java\\nagarro\\main\\utility\\TestConfig.properties");
		try {
			fis = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		prop = new Properties();
		try {
			prop.load(fis);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// To read test data from excel
	public static ExcelUtility xls = new ExcelUtility(
			System.getProperty("user.dir") + prop.getProperty("TestInputSheetPath"));

	// //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	// Default constructor
	public BasePage() {

	}

	// Parameterized constructor to initialize the the browsers

	public BasePage(String browser, String AppURL) {

		if ("Chrome".equalsIgnoreCase(browser)) {

			System.setProperty("webdriver.chrome.driver",
					"D:\\NAGP\\Selenium\\chromedriver_win32\\chromedriver.exe");

			driver = new ChromeDriver();

		} // End of If

		else if ("FF".equalsIgnoreCase(browser)) {

			System.setProperty("webdriver.gecko", "D:\\NAGP\\Selenium\\geckodriver-v0.19.1-win64\\geckodriver.exe");
			driver = new FirefoxDriver();
		}

		driver.navigate().to(AppURL);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

	} // End of If
	
	 @BeforeSuite(alwaysRun=true)
	  public void setUp() throws IOException
	  {
	   //initializing extent report
	   extent= Extentmanager.getInstance();
	  } 
	  
	  @BeforeMethod
	  public void beforeMethod() throws IOException
	  {	

    	} // End of If
	   
	  

	  
	  @AfterMethod
	  public void afterMethod(ITestResult result) throws IOException
	  {
	   //getResult(result);
	   
	   if(driver!=null)
	   {
	    driver.close();
	    driver.quit();
	   }
	  }
	  @AfterSuite
	  public void tearDown() throws Exception
	  {  
	   extent.flush();	   
	   UtilityFunctions.zipFolder();
	  }



}// End Of Constructor
