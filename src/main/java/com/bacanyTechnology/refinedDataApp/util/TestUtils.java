package com.bacanyTechnology.refinedDataApp.util;

import java.text.SimpleDateFormat;
import java.util.Date;


import com.bacanyTechnology.refinedDataApp.base.TestBase;

public class TestUtils extends TestBase
{
	public TestUtils() 
	{
		super();
	} 
	
	public static long PAGE_LOAD_TIMEOUT = 20;
	public static long IMPLICITLY_WAIT = 20;
	public static String username = prop.getProperty("username");
	public static String password = prop.getProperty("password");
	public static String url = prop.getProperty("url");
	
	public void staticWait()
	{
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String timestamp() {
        return new SimpleDateFormat("yyyy-MM-dd HH-mm-ss").format(new Date());
}
	
}
