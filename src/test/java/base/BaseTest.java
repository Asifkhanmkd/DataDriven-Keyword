package base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.google.inject.Key;

import bsh.This;
import utilities.ExcelReader;

/* We keep/initialise the following
 * WebDriver
 * TestNG
 * Screenshot
 * Log4j
 * Properties
 * Database	
 * JavaMail
 * Keywords
 * Extent Report
 * Listeners
 */

public class BaseTest {
	

	public static WebDriver driver;
	public static Logger log = Logger.getLogger(BaseTest.class.getName());
	public static Properties OR = new Properties();
	public static Properties Config = new Properties();
	public static FileInputStream fis;
	public static ExcelReader excel = new ExcelReader(".\\src\\test\\resources\\excel\\testdata.xlsx");
	public static WebDriverWait wait;

	public static void click(String key) {
		if (key.endsWith("_XPATH")) {
			driver.findElement(By.xpath(OR.getProperty(key))).click();

		} else if (key.endsWith("_CSS")) {
			driver.findElement(By.cssSelector(OR.getProperty(key))).click();

		} else if (key.endsWith("_ID")) {
			driver.findElement(By.id(OR.getProperty(key))).click();
		}

		log.info("Clicked on Element " + key);

	}

	public static void type(String key, String value) {
		if (key.endsWith("_XPATH")) {
			driver.findElement(By.xpath(OR.getProperty(key))).sendKeys(value);

		} else if (key.endsWith("_CSS")) {
			driver.findElement(By.cssSelector(OR.getProperty(key))).sendKeys(value);

		} else if (key.endsWith("_ID")) {
			driver.findElement(By.id(OR.getProperty(key))).sendKeys(value);
		}

		log.info("typed in field " + key);

	}

	public static boolean isElementPresent(String key) {
		try {
			if (key.endsWith("_XPATH")) {
				driver.findElement(By.xpath(OR.getProperty(key)));

			} else if (key.endsWith("_CSS")) {
				driver.findElement(By.cssSelector(OR.getProperty(key)));

			} else if (key.endsWith("_ID")) {

				driver.findElement(By.id(OR.getProperty(key)));

			}

			log.info("Element found " + key);
			return true;

		} catch (Throwable t) {
			log.info("Couldn't find the Element " + key + " Error log is" + t.getMessage());
			return false;

		}

	}

	public static String getText(String key) {
		 String text = null;
		
		try {
			
			if (key.endsWith("_XPATH")) {
				text=driver.findElement(By.xpath(OR.getProperty(key))).getText();
				
				
			} else if (key.endsWith("_CSS")) {
				text=driver.findElement(By.cssSelector(OR.getProperty(key))).getText();
				
			} else if (key.endsWith("_id")) {
				text=driver.findElement(By.id(OR.getProperty(key))).getText();
				
			} 
			 
			log.info("text returned "+text);
			System.out.println(text);
			
		} catch (Throwable t) {
			log.info("Couldn't find the Element" + key + " Error log is" + t.getMessage());
			
		}
		return text;
		

	}

	

	

	@BeforeSuite
	public void setup() {
		if (driver == null) {

			PropertyConfigurator.configure(".\\src\\test\\resources\\properties\\log4j.properties");

			try {
				fis = new FileInputStream(".\\src\\test\\resources\\properties\\config.properties");

			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				Config.load(fis);
				log.info("OR Properties Loaded !!!");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			try {
				fis = new FileInputStream(".\\src\\test\\resources\\properties\\or.properties");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			try {
				OR.load(fis);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			if (Config.getProperty("browser").equals("chrome")) {
				// WebDriverManager.chromedriver().setup();
				// WebDriverManager.firefoxdriver().version("0.29.1").setup();
				driver = new ChromeDriver();
				log.info("Chrome browser launched");

			} else if (Config.getProperty("browser").equals("firefox")) {
				// WebDriverManager.firefoxdriver().setup();
				driver = new FirefoxDriver();

				log.info("FireFox browser launched");
			}
			driver.manage().window().maximize();
			driver.get(Config.getProperty("siteUrl"));
			log.info("Navigated to " + Config.getProperty("siteUrl"));
			driver.manage().timeouts()
					.implicitlyWait(Duration.ofSeconds(Integer.parseInt(Config.getProperty("implicit.wait"))));
			wait = new WebDriverWait(driver, Duration.ofSeconds(Integer.parseInt(Config.getProperty("explicit.wait"))));
		}

	}

	@AfterSuite
	public void tearDown() {

		driver.quit();
		log.info("Execution of tests completed");
	}

}
