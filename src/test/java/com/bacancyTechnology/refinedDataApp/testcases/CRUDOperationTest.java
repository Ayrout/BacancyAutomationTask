package com.bacancyTechnology.refinedDataApp.testcases;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.bacancyTechnology.refinedDataApp.pages.EnvironmentalRiskManagementPage;
import com.bacancyTechnology.refinedDataApp.pages.LoginPage;
import com.bacancyTechnology.refinedDataApp.pages.PropertyListPage;
import com.bacanyTechnology.refinedDataApp.base.TestBase;
import com.bacanyTechnology.refinedDataApp.util.CustomListener;
import com.bacanyTechnology.refinedDataApp.util.ExcelReader;
import com.bacanyTechnology.refinedDataApp.util.TestUtils;

@Listeners(CustomListener.class)
public class CRUDOperationTest extends TestBase
{
	LoginPage loginPage;
	PropertyListPage propertyListPage;
	EnvironmentalRiskManagementPage environmentalRiskManagementPage;
	
	
	public CRUDOperationTest() 
	{
		super();
	}
	
	@BeforeClass
	public void setUp()
	{
		driverInitialization();
	}
	
	@BeforeMethod
	public void launchAndLogin()
	{
		driver.get(TestUtils.url);
		loginPage = new LoginPage();
		propertyListPage = new PropertyListPage();
		loginPage.signInWithValidCredentials(TestUtils.username, TestUtils.password);
		propertyListPage.selectDashboardOption("Property List");
		Assert.assertTrue(propertyListPage.validatePropertyListTitle("Property List"), "Verify Property List Title");
	}
	
	
	@Test(priority = 1,dataProvider = "testdata",dataProviderClass = ExcelReader.class)
	public void createAnEnvironmentalReport(Map<String, String> map)
	{
		environmentalRiskManagementPage = new EnvironmentalRiskManagementPage();
		//System.out.println(map.get("Property"));
		propertyListPage.clickOnEnvironmental("01");
		Assert.assertTrue(environmentalRiskManagementPage.validateEnvironmentalRiskManagementTitle("Environmental Risk Management"), "Verify Environmental Risk Management Title");
		Assert.assertTrue(environmentalRiskManagementPage.validateEnvironmentalRiskManagementPropertCheck("01"), "Verify Property List Title");
		environmentalRiskManagementPage.clickOnEnvironmentalReports();
		environmentalRiskManagementPage.clickOnAddBtn();
		environmentalRiskManagementPage.selectType("Annual H&S Survey");
		environmentalRiskManagementPage.clickOnSaveBtnAndCheckNotification("Successfully saved record");
	}
	
	@Test(priority = 2,dataProvider = "testdata",dataProviderClass = ExcelReader.class)
	public void updateEnvironmentalReport(Map<String, String> map)
	{
		environmentalRiskManagementPage = new EnvironmentalRiskManagementPage();
		propertyListPage.clickOnEnvironmental("01");
		environmentalRiskManagementPage.clickOnEnvironmentalReports();
		environmentalRiskManagementPage.enterFilterDetails("Annual H&S Survey");
		environmentalRiskManagementPage.verifyTheFilterResultAndClickOnTheRecord("Annual H&S Survey");
		environmentalRiskManagementPage.selectType("Groundwater Monitoring");
		environmentalRiskManagementPage.clickOnUpdateBtnAndCheckNotification("Successfully updated record");
	}
	
	@Test(priority = 3,dataProvider = "testdata",dataProviderClass = ExcelReader.class)
	public void deleteEnvironmentalReport(Map<String, String> map)
	{
		environmentalRiskManagementPage = new EnvironmentalRiskManagementPage();
		propertyListPage.clickOnEnvironmental("01");
		environmentalRiskManagementPage.clickOnEnvironmentalReports();
		environmentalRiskManagementPage.enterFilterDetails("Groundwater Monitoring");
		environmentalRiskManagementPage.verifyTheFilterResultAndClickOnTheRecord("Groundwater Monitoring");
		environmentalRiskManagementPage.clickOnDeleteBtnAndCheckNotification("Are you sure you want to delete this record?","Successfully deleted record");
	}
	
	@AfterMethod
	public void logout()
	{
		environmentalRiskManagementPage.logout();
	}
	
	@AfterClass
	public void quit()
	{
		driver.quit();
	}
}
