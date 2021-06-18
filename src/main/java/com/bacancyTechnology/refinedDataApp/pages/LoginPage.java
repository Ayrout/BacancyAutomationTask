package com.bacancyTechnology.refinedDataApp.pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.bacanyTechnology.refinedDataApp.base.TestBase;
import com.bacanyTechnology.refinedDataApp.util.TestUtils;

public class LoginPage extends TestBase
{
	@FindBy(xpath = "//*[@id='logonForm']//label[text()='Username']/..//div//input[@name='username']")
	WebElement username;
	
	@FindBy(xpath = "//*[@id='logonForm']//label[text()='Password']/..//div//input[@name='password']")
	WebElement password;
	
	@FindBy(xpath = "//*[@id='logonForm']//button[text()='Sign in']")
	WebElement signInBtn;
	
	WebDriverWait wait = new WebDriverWait(driver,60);
	
	public LoginPage()
	{
		PageFactory.initElements(driver, this);
	}
	
	public PropertyListPage signInWithValidCredentials(String usr, String pwd)
	{
		wait.until(ExpectedConditions.visibilityOf(username));
		username.click();
		username.sendKeys(usr);
		wait.until(ExpectedConditions.visibilityOf(password));
		password.click();
		password.sendKeys(pwd);
		
		signInBtn.click();
		
		return new PropertyListPage();
	}

}
