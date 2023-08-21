package com.tutorialninja.testData;
import java.io.FileInputStream;
import java.io.IOException;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.DataProvider;

public class FetchExcelData {

	
	public static WebDriver driver;
	public static FileInputStream ip; 
	public static XSSFWorkbook workbook; 
	public static XSSFSheet sheet; 
	
	

	
	@DataProvider(name = "LoginData")
	public static Object[][] setTNLoginData() throws IOException {
		
			Object[][] data = getData("LoginTN");
			
			return data;
				
				
		
	}
	
	@DataProvider(name = "RegisterData")
	public static Object[][] setTNRegisterData() throws IOException {
		
		Object[][] data = getData("RegisterTN");
		return data;
			
	
}
	
	public static Object[][] getData(String sheetName) throws IOException {
	
		ip = new FileInputStream("/Users/simran/eclipse-workspace/Tutorials_Ninja_Full_Project/src/test/java/com/tutorialninja/testData/ExcelDataCode.xlsx");
		workbook =  new XSSFWorkbook(ip);
	
	
		
		
		
		sheet = workbook.getSheet(sheetName);
		
		int rows = sheet.getLastRowNum();
		int cells = sheet.getRow(0).getLastCellNum();
		Object[][] data = new Object[rows][cells];
		
		for(int i =0; i<rows; i++ ) {
			XSSFRow currentRow = sheet.getRow(i+1);
			for(int j =0; j<cells; j++ ) {
				XSSFCell cell  = currentRow.getCell(j);
			CellType  celltype =  cell.getCellType();
			
			switch(celltype) {
              			
			case NUMERIC:
				data[i][j] = Integer.toString((int)cell.getNumericCellValue());
				break;
				
			case STRING:
				data[i][j] = cell.getStringCellValue();
				break;
			case BOOLEAN:
				data[i][j] = cell.getBooleanCellValue();
				break;
				
			
			}
			
				
			}
		}
		
		return data;
		
		
		
	}
}