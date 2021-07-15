package nagarro.main.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.internal.selenesedriver.TakeScreenshot;

import com.google.common.io.Closeables;

public class UtilityFunctions {

	// --------------------------------------------------------------------------------------------------------------------------------
	// Read data from the excel file. Takes two arguments - Test Case Name and
	// XLS_Reader object. Reads the test data from the Test Data excel and
	// return the test data in 2 D object array form
	public static Object[][] getData(String TestCaseName, ExcelUtility xls) {
		// find test in the excel file
		// find number of columns in the test
		// find number of rows in the test
		// print the data of the test
		// put the data in object array

		// Get the start row index for the test data of the given Test Case name
		int testCaseStartIndex = 0;

		for (int rNum = 1; rNum <= xls.getRowCount("TestData"); rNum++) {
			if (TestCaseName.equals(xls.getCellData("TestData", 1, rNum))) {
				testCaseStartIndex = rNum;
				break;
			} // end of if
		} // end of for

		// Get the number of columns in the test data (which is available column
		// wise) for the given test case
		int testCaseDataColumnNamesStartIndex = testCaseStartIndex + 1;

		int colsOffset = 2;
		int Cols = colsOffset;
		while (!(xls.getCellData("TestData", Cols, testCaseDataColumnNamesStartIndex).equals(""))) {
			Cols++;
		} // end of while

		int numberOfTestDataColumns = Cols - colsOffset;

		int testCaseDataStartIndex = testCaseStartIndex + 2;

		int rows = 0;
		while (!xls.getCellData("TestData", colsOffset, (testCaseDataStartIndex + rows)).equals("")) {
			rows++;
		} // end of while
		int numberofTestDataSets = rows;

		// Store the test data sets of a single test case in an array of
		// HashTable. Each HasTable will contain one test data set
		Object[][] dataSetCollection = new Object[numberofTestDataSets][1];

		Hashtable<String, String> TestDataSet = null;
		String Datakey = "'";
		String Keyvalue = "";
		int index = 0;
		for (int r = testCaseDataStartIndex; r < (testCaseDataStartIndex + numberofTestDataSets); r++) {
			TestDataSet = new Hashtable<String, String>();

			for (int c = colsOffset; c < (numberOfTestDataColumns + colsOffset); c++) {

				Datakey = xls.getCellData("TestData", c, testCaseDataColumnNamesStartIndex);
				Keyvalue = xls.getCellData("TestData", c, r);
				TestDataSet.put(Datakey, Keyvalue);

			} // end of for
				// Once all the Column Name Value pair is stored in the
				// HashTable for a row, add this Hash Table in the 2 D array of
				// Objects
			dataSetCollection[index][0] = TestDataSet;
			index++;
		} // end of for

		return dataSetCollection;

	}// end of function
	
	public static void screenshot(WebDriver driver, String string)
	 {
	  
	 TakesScreenshot ts=(TakesScreenshot)driver; 
	 File source=ts.getScreenshotAs(OutputType.FILE);
	 try {
	  FileUtils.copyFile(source, new File(System.getProperty("user.dir")+"\\TestReport\\"+ string +".png")); 
	  }
	 catch(Exception e)
	  {
	  e.printStackTrace();
	  }
	 }	
	 
	 public static void zipFolder() throws IOException {	  
	  
	  String source= System.getProperty("user.dir")+"\\TestReports";
	  Date d=new Date();
	  String fileName= d.toString().replace(":", "_").replace(" ", "_")+".zip";
	    String dest=System.getProperty("user.dir")+"\\ArchiveResult\\"+fileName;
	    File inputDir=new File(source);
	    File output=new File(dest);
	    if (output.exists()) {
	          throw new IOException("File already exists: " + output);
	        }

	        FileOutputStream fos = null;
	        try {
	          fos = new FileOutputStream(output);
	          zip(inputDir, fos);
	        } finally {
	          Closeables.close(fos, false);
	        }
	   }
	   	    
	    public static void isFolderExistAtPath(String filePath)
	    {
	     File file = new File(filePath);
	     if (file.exists() && file.isDirectory()) {
	      System.out.println("Path exists" + filePath);;
	     }
	     else 
	     {
	      file.mkdir();
	     }
	    }

	    public static void deleteOldFiles(String directory) throws IOException {
	     File targetDir = new File(directory);
	     FileUtils.cleanDirectory(targetDir);
	    } 
	    	      
	      private static void zip(File inputDir, OutputStream writeTo) throws IOException {
	          ZipOutputStream zos = null;
	          try {
	            zos = new ZipOutputStream(writeTo);
	            addToZip(inputDir.getAbsolutePath(), zos, inputDir);
	          } finally {
	            Closeables.close(zos, false);
	          }
	        }
	      
	       private static void addToZip(String basePath, ZipOutputStream zos, File toAdd) throws IOException {
	           if (toAdd.isDirectory()) {
	             File[] files = toAdd.listFiles();
	             if (files != null) {
	               for (File file : files) {
	                 addToZip(basePath, zos, file);
	               }
	             }
	           } else {
	             FileInputStream fis = new FileInputStream(toAdd);
	             String name = toAdd.getAbsolutePath().substring(basePath.length() + 1);

	             ZipEntry entry = new ZipEntry(name.replace('\\', '/'));
	             zos.putNextEntry(entry);

	             int len;
	             byte[] buffer = new byte[4096];
	             while ((len = fis.read(buffer)) != -1) {
	               zos.write(buffer, 0, len);
	             }

	             fis.close();
	             zos.closeEntry();
	           }
	         }

}// end of class
