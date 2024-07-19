package testcases;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import base.BaseTest;
import utilities.ExcelReader;

public class LoginTest extends BaseTest{
	
	
	@Test(dataProvider="dp")
	public void loginTest(String username, String password) {
		click("signInLink_XPATH");
		type("username_CSS", username);
		type("password_id", password);
		click("signInBtn_XPATH");
		
		
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
