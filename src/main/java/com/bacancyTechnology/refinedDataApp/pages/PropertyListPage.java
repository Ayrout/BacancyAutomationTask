package com.bacancyTechnology.refinedDataApp.pages;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import com.bacanyTechnology.refinedDataApp.base.TestBase;
import com.bacanyTechnology.refinedDataApp.util.TestUtils;

public class PropertyListPage extends TestBase
{
	@FindBy(xpath = "//*[contains(@class,'property-list-title')]")
	WebElement propertyListText;
	
	@FindBy(xpath = "//table[@id='propertyListTable']/tbody/tr")
	List<WebElement> countOfRows;
	
	@FindBy(xpath = "//table[@id='propertyListTable']/tbody/tr[1]/td")
	List<WebElement> countOfColumns;
	
	@FindBy(xpath = "//table[@id='propertyListTable']/tbody/tr[1]/td[8]/ul/li")
	List<WebElement> countOfInLinePropertyList;
	
	@FindBy(xpath = "//*[contains(text(),'Dashboard')]/..//select")
	WebElement selectDashboardOption;
	
	TestUtils util = new TestUtils();
	
	public PropertyListPage()
	{
		PageFactory.initElements(driver, this);
	}
	
	public boolean validatePropertyListTitle(String propertyListTitle)
	{
		boolean plTitleDisplayed = false;
		String actualTitle = propertyListText.getText();
		driver.manage().timeouts().implicitlyWait(TestUtils.IMPLICITLY_WAIT, TimeUnit.SECONDS);
		String expectedTitle = propertyListTitle;
		if(actualTitle.equals(expectedTitle))
			plTitleDisplayed = true;
		return plTitleDisplayed;
	}
	
	public int rowNum(String propertyNum)
	{
		WebElement rowWisedata = null;
		int row = 0;
		driver.manage().timeouts().implicitlyWait(TestUtils.IMPLICITLY_WAIT, TimeUnit.SECONDS);
		for(int i=1;i<=countOfRows.size();i++)
		{
			for(int j=1;j<=countOfColumns.size();j++)
			{
				rowWisedata = driver.findElement(By.xpath("//table[@id='propertyListTable']/tbody/tr["+i+"]/td["+j+"]"));
				if(rowWisedata.getText().contains(propertyNum))
					row = i;
				break;
			}
			break;
		}
		return row;
		
	}
	
	public void clickOnEnvironmental(String propertyNum)
	{
		WebElement inlineProperty = null;
		for(int i=1;i<=countOfInLinePropertyList.size();i++)
		{
			inlineProperty = driver.findElement(By.xpath("//table[@id='propertyListTable']/tbody/tr["+rowNum(propertyNum)+"]/td["+countOfColumns.size()+"]/ul/li["+i+"]/a"));
			if(inlineProperty.getAttribute("title").equals("Environmental"))
			{
				inlineProperty.click();
				util.staticWait();
				break;
			}
		}
	}
	
	public void selectDashboardOption(String dashboardOption)
	{
		Select select = new Select(selectDashboardOption);
		driver.manage().timeouts().implicitlyWait(TestUtils.IMPLICITLY_WAIT, TimeUnit.SECONDS);
		select.selectByVisibleText(dashboardOption);
	}
}
