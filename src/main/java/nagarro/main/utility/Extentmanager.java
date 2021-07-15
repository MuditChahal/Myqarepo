package nagarro.main.utility;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import com.relevantcodes.extentreports.ExtentReports;

public class Extentmanager {

 public static ExtentReports extent;

 public static ExtentReports getInstance() {
  if (extent == null) {
   Date d=new Date();
   String fileName= d.toString().replace(":", "_").replace(" ", "_")+".html";
   UtilityFunctions.isFolderExistAtPath(System.getProperty("user.dir")+"\\TestReports");
   System.out.println(fileName);
  try {
    UtilityFunctions.deleteOldFiles(System.getProperty("user.dir")+"\\TestReports");
   } 
  catch (IOException e)
   {
    e.printStackTrace();
   }
  UtilityFunctions.isFolderExistAtPath(System.getProperty("user.dir")+"\\TestReports");
   extent = new ExtentReports(System.getProperty("user.dir")+"\\TestReports\\"+fileName);
   extent.loadConfig(new File(System.getProperty("user.dir")+"\\ReportsConfig.xml"));
  }
  return extent;
 }
}
