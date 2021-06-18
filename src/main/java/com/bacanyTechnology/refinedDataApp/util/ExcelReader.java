package com.bacanyTechnology.refinedDataApp.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;

import org.apache.commons.collections4.map.HashedMap;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

public class ExcelReader 
{	
	public static String testdataPath = System.getProperty("user.dir")+"\\TestData\\TestData.xlsx";

	@DataProvider(name = "testdata")
	public static Object[][] getTestData() throws IOException{

		FileInputStream fis = new FileInputStream(testdataPath);

		XSSFWorkbook wb = new XSSFWorkbook(fis);

		XSSFSheet sheet = wb.getSheetAt(0);

		wb.close();

		int lastRowNum = sheet.getLastRowNum();

		int lastCellNum = sheet.getRow(0).getLastCellNum();

		Object[][] testData = new Object[lastRowNum][1];

		for(int i=0;i<lastRowNum;i++)
		{
			Map<Object,Object> datamap = new HashedMap<>();
			for (int j = 0; j < lastCellNum; j++) {
				datamap.put(sheet.getRow(0).getCell(j).toString(), sheet.getRow(i+1).getCell(j).toString());
			}
			testData[i][0] = datamap;
		}
		return testData;
	}

	//	public static Object[][] getTestData(String sheetName) throws IOException
	//	{
	//		FileInputStream fis = null;
	//		DataFormatter formatter = new DataFormatter();
	//		fis = new FileInputStream(testdataPath);
	//		XSSFWorkbook wb = new XSSFWorkbook(fis);
	//		XSSFSheet sheet = wb.getSheetAt(0);
	//		int rowCount = sheet.getPhysicalNumberOfRows();
	//		XSSFRow row = sheet.getRow(0);
	//		int colCount = row.getLastCellNum();
	//		Object data[][] = new Object[rowCount-1][colCount];
	//		for(int i=0;i<rowCount-1;i++)
	//		{
	//			row=sheet.getRow(i+1);
	//			for(int j=0;j<colCount;j++)
	//			{
	//				XSSFCell cell = row.getCell(j);
	//
	//				data[i][j] = formatter.formatCellValue(cell);
	//			}
	//		}
	//		return data;
	//	}
}
