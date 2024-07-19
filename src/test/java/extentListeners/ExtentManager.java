
package extentListeners;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.io.FileHandler;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import base.BaseTest;

public class ExtentManager {
  
	private static ExtentReports extent;
	public static String fileName;
	public static String targetPath;
	
	

	    public static ExtentReports createInstance(String fileName) {
	        ExtentSparkReporter htmlReporter = new ExtentSparkReporter(fileName);
	       
	        
	        htmlReporter.config().setTheme(Theme.STANDARD);
	        htmlReporter.config().setDocumentTitle(fileName);
	        htmlReporter.config().setEncoding("utf-8");
	        htmlReporter.config().setReportName(fileName);
	        
	        extent = new ExtentReports();
	        extent.attachReporter(htmlReporter);
	        extent.setSystemInfo("QA Engineer", "Muhammad asif");
	        extent.setSystemInfo("Organization", "JustATestWebSite");
	        extent.setSystemInfo("Build no", "Test.v.1.0");
	        
	        
	        return extent;
	    }

	    
		public static void captureScreenshot() throws IOException {
			
			Date d = new Date();
			 fileName = d.toString().replace(":", "_").replace(" ", "_")+".jpg";

			
			
				
				
				  File screeshot = ((TakesScreenshot)BaseTest.driver).getScreenshotAs(OutputType.FILE);
				  FileHandler.copy(screeshot, new File(".//reports//"+fileName));
				 
			 
				
				/*
				  File screenshot =
				  ((TakesScreenshot)BaseTest.driver).getScreenshotAs(OutputType.FILE);
				  
				  
				  
				  Path targetPath = Path.of(".//reports//" + fileName);
				  
				  
				  
				  // Copy the screenshot to the target path
				  
				  Files.copy(screenshot.toPath(), targetPath);
				  System.out.println("Screenshot saved successfully!");
				 
				  
				  
				  
				  
				  
				  
				 
		        
		    }
		
		
		

		public static void captureElementScreenshot(WebElement element) throws IOException {
			
			Date d = new Date();
			String fileName = d.toString().replace(":", "_").replace(" ", "_")+".jpg";

			
			
			/*
			 * File screeshot = ((TakesScreenshot)
			 * element).getScreenshotAs(OutputType.FILE); FileUtils.copyFile(screeshot, new
			 * File(".//screenshot//"+"Element_"+fileName));
			 */
			
			
			
		}


  
  
  }
  
  
  
 