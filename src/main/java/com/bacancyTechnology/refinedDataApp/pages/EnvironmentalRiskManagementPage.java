package com.bacancyTechnology.refinedDataApp.pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.bacanyTechnology.refinedDataApp.base.TestBase;
import com.bacanyTechnology.refinedDataApp.util.TestUtils;

public class EnvironmentalRiskManagementPage extends TestBase
{
	@FindBy(xpath = "//div[contains(@class,'green-block-med')]//following-sibling::span")
	WebElement environmentalRiskManagementPageText;
	
	@FindBy(xpath = "//label[@id='current-property-label']//following-sibling::span")
	WebElement environmentalRiskManagementPropertyText;
	
	@FindBy(xpath = "//div[@class='navbar-header']//button[contains(@class,'hamburger-menu') and @ng-click='openLeftSideNav()']")
	WebElement menuBtn;
	
	@FindBy(xpath = "//span[contains(text(),'Environmental Reports')]//parent::li[@class='ng-scope']")
	WebElement menuOption_EnvironmentalReports;
	
	@FindBy(xpath = "//div[@class='ng-scope']/label[contains(text(),'Type')]//following-sibling::select")
	WebElement typeDropDown;
	
	@FindBy(xpath = "//input[@class='form-control']")
	WebElement filterBox;
	
	@FindBy(xpath = "//table[@id='environmentalDocumentListTable']//tr/td[3]/span")
	WebElement filterTypeResult;
	
	@FindBy(xpath = "//div[contains(@class,'toast-success')]/div[@class='toast-message']/div")
	WebElement notificationMsgLocator;
	
	@FindBy(xpath = "//div[@aria-labelledby='deleteEnvironmentalRecordLabel']//div[contains(@class,'modal-body')]")
	WebElement deleteDialogText;
	
	@FindBy(xpath = "//*[contains(@class,'navbar-fixed-top')]/div[contains(@ng-hide,'isRefinedRiskAddonScreen')]"
			+ "//child::button[contains(@class,'gear-menu')]")
	WebElement settingGearMenu;
	
	@FindBy(xpath = "//a[contains(@ng-click,'logout')]")
	WebElement logoutBtn;
	
	@FindBy(xpath = "//div[@aria-labelledby='deleteEnvironmentalRecordLabel']//button[contains(text(),'Delete')]")
	WebElement deleteDialogBtn;
	
	@FindBy(id = "add-btn")
	WebElement addBtn;
	
	@FindBy(id = "Save-btn")
	WebElement saveBtn;
	
	@FindBy(id = "Update-btn")
	WebElement updateBtn;
	
	@FindBy(id = "delete-btn")
	WebElement deleteBtn;
	
	@FindBy(xpath = "//button[contains(@class,'close-button')]")
	WebElement closeNotificationBtn;
	
	@FindBy(xpath = "//a[@class='pointer']")
	WebElement switchToPointer;
	
	
	WebDriverWait wait = new WebDriverWait(driver,60);
	TestUtils util = new TestUtils();
	
	public EnvironmentalRiskManagementPage()
	{
		PageFactory.initElements(driver, this);
	}
	
	public boolean validateEnvironmentalRiskManagementTitle(String environmentalRiskManagementPageTitle)
	{
		boolean ermTitleDisplayed = false;
		//wait.until(ExpectedConditions.visibilityOf(environmentalRiskManagementPageText));
		String actualTitle = environmentalRiskManagementPageText.getText();
		String expectedTitle = environmentalRiskManagementPageTitle;
		if(actualTitle.equals(expectedTitle))
			ermTitleDisplayed = true;
		return ermTitleDisplayed;
	}
	
	public boolean validateEnvironmentalRiskManagementPropertCheck(String property)
	{
		boolean propertyDisplayed = false;
		//driver.manage().timeouts().implicitlyWait(TestUtils.IMPLICITLY_WAIT, TimeUnit.SECONDS);
		//wait.until(ExpectedConditions.visibilityOf(environmentalRiskManagementPropertyText));
		String actualTitle = environmentalRiskManagementPropertyText.getText();
		String expectedTitle = property;
		if(actualTitle.contains(expectedTitle))
			propertyDisplayed = true;
		return propertyDisplayed;
	}
	
	public void clickOnEnvironmentalReports()
	{
//		driver.manage().timeouts().implicitlyWait(TestUtils.IMPLICITLY_WAIT, TimeUnit.SECONDS);
		//wait.until(ExpectedConditions.visibilityOf(settingGearMenu));
//		if(switchToPointer.isDisplayed())
//		{
//			switchToPointer.click();
//		}
		//util.staticWait();
		driver.manage().timeouts().setScriptTimeout(10, TimeUnit.SECONDS);
		menuBtn.click();
		menuOption_EnvironmentalReports.click();
	}
	
	public void clickOnAddBtn()
	{
		addBtn.click();
		driver.manage().timeouts().implicitlyWait(TestUtils.IMPLICITLY_WAIT, TimeUnit.SECONDS);
	}
	
	public void selectType(String type)
	{
		Select selectType = new Select(typeDropDown);
		driver.manage().timeouts().implicitlyWait(TestUtils.IMPLICITLY_WAIT, TimeUnit.SECONDS);
		selectType.selectByVisibleText(type);
	}
	
	public void clickOnSaveBtnAndCheckNotification(String notificationMsg)
	{
		saveBtn.click();
		driver.manage().timeouts().implicitlyWait(TestUtils.IMPLICITLY_WAIT, TimeUnit.SECONDS);
		Assert.assertTrue(notificationMsgLocator.getText().contains(notificationMsg),"Saved successfully!!!");
		wait.until(ExpectedConditions.visibilityOf(notificationMsgLocator));
	}
	
	public void enterFilterDetails(String type)
	{
		filterBox.sendKeys(type);
		driver.manage().timeouts().implicitlyWait(TestUtils.IMPLICITLY_WAIT, TimeUnit.SECONDS);
	}
	
	public void verifyTheFilterResultAndClickOnTheRecord(String type)
	{
		if(filterTypeResult.getText().equals(type))
		{
			filterTypeResult.click();
			driver.manage().timeouts().implicitlyWait(TestUtils.IMPLICITLY_WAIT, TimeUnit.SECONDS);
		}
	}
	
	public void clickOnUpdateBtnAndCheckNotification(String notificationMsg)
	{
		updateBtn.click();
	//	driver.manage().timeouts().implicitlyWait(TestUtils.IMPLICITLY_WAIT, TimeUnit.SECONDS);
		wait.until(ExpectedConditions.visibilityOf(notificationMsgLocator));
		Assert.assertTrue(notificationMsgLocator.getText().contains(notificationMsg),"Updated successfully!!!");
	}
	
	public void clickOnDeleteBtnAndCheckNotification(String deleteDialogMsg,String notificationMsg)
	{
		deleteBtn.click();
		driver.manage().timeouts().implicitlyWait(TestUtils.IMPLICITLY_WAIT, TimeUnit.SECONDS);
		String dialogText = deleteDialogText.getText();
		if(dialogText.equals(deleteDialogMsg))
		{
			deleteDialogBtn.click();
		}
		wait.until(ExpectedConditions.visibilityOf(notificationMsgLocator));
		Assert.assertTrue(notificationMsgLocator.getText().contains(notificationMsg),"Deleted successfully!!!");
	}
	
//	public void clickOnSwitchPointer()
//	{
//		if(switchToPointer.isDisplayed())
//		{
//			switchToPointer.click();
//		}
//	}
	
	public void logout()
	{
		//wait.until(ExpectedConditions.elementToBeClickable(By.xpath(closeNotificationBtn.toString())));
		util.staticWait();
//		closeNotificationBtn.click();
//		driver.manage().timeouts().implicitlyWait(TestUtils.IMPLICITLY_WAIT, TimeUnit.SECONDS);
		settingGearMenu.click();
		logoutBtn.click();
	}
}
