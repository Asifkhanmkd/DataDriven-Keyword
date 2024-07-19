package testcases;

import static org.testng.Assert.assertTrue;
import static org.testng.Assert.fail;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import base.BaseTest;

public class LoginTest extends BaseTest{
	
	
	@Test(dataProvider="dp")
	public void loginTest(String username, String password) {
		click("signInLink_XPATH");
		type("username_CSS", username);


		type("password_XPATH", password);
		
		
		click("signInBtn_XPATH");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String text=getText("welcome_XPATH");
		org.testng.Assert.assertTrue("Welcome, muh asif!".contains(text));
	    log.info("User logged in successfully"); 
	}
		
	

	@DataProvider(name="dp")
	public Object[][] getData(){
	String sheetName="LoginTest";
	int rowCount= excel.getRowCount(sheetName);
	int colCount= excel.getColumnCount(sheetName);
		
		Object[][] data= new Object[rowCount-1][colCount];
		
		for(int row=2;row<=rowCount;row++) {
			for(int col=0;col<colCount;col++) {
				
				data[row-2][col]=excel.getCellData(sheetName, col, row);
				
				
			}
		}
		
		return data;
	}

}
