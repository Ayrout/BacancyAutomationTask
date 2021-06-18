package com.bacanyTechnology.refinedDataApp.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.DataProvider;

import com.bacanyTechnology.refinedDataApp.util.ExcelReader;
import com.bacanyTechnology.refinedDataApp.util.TestUtils;

public class TestBase 
{	
	public static WebDriver driver;
	public static Properties prop;
	
	public TestBase()
	{
		//Read the prop from config.prop file
		prop = new Properties();
		try {
			FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"/src/main/java/com/bacanyTechnology/"
					+ "refinedDataApp/config/config.properties");
			prop.load(fis);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//This method is used to initialize the driver as per user's choice
	public static void driverInitialization()
	{
		String browserType = prop.getProperty("browser");
		
		if(browserType.equalsIgnoreCase("Chrome"))
		{
			System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"\\Driver\\chromedriver.exe");
			driver = new ChromeDriver();
		}
		else if(browserType.equalsIgnoreCase("Firefox"))
		{
			System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"\\Driver\\geckodriver.exe");
			driver = new FirefoxDriver();
		}
		
		driver.manage().window().maximize();
		
		driver.manage().deleteAllCookies();
		
		driver.manage().timeouts().pageLoadTimeout(TestUtils.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		
		driver.manage().timeouts().implicitlyWait(TestUtils.IMPLICITLY_WAIT, TimeUnit.SECONDS);
		
	}
	
	public void failed(String testMethodName)
	{
		File srcfile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		TestUtils util = new TestUtils();
		try {
			FileUtils.copyFile(srcfile, new File(System.getProperty("user.dir")+"\\Screenshots\\"+testMethodName+"_"+util.timestamp()+"_"+".jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
