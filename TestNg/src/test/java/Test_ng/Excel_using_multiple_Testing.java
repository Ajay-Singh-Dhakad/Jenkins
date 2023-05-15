package Test_ng;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Excel_using_multiple_Testing {
	WebDriver driver;
	String username;
	String password;
@BeforeTest
public void setup() throws IOException {
	File excel=new File("C:\\Users\\dzine\\Book1.xlsx");
	FileInputStream fil=new FileInputStream(excel);
	XSSFWorkbook wk=new XSSFWorkbook(fil);
	XSSFSheet sheet=wk.getSheet("Sheet1");
	WebDriverManager.edgedriver().setup();
	
	int row_count=sheet.getLastRowNum();
	for(int i=0;i<=row_count;i++) {
		username=sheet.getRow(i).getCell(0).getStringCellValue();
		 password=sheet.getRow(i).getCell(1).getStringCellValue();
	    driver=new EdgeDriver();
	    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
	    driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
	
	
	}
}
@Test
public void valid(String username,String password) {
	   driver.findElement(By.xpath("//input[@name='username']")).sendKeys(username);
	    driver.findElement(By.xpath("//input[@name='password']")).sendKeys(password);
	    driver.findElement(By.xpath("//button[@type='submit']")).click();
	    driver.close();
}
}
