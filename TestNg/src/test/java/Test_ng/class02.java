package Test_ng;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import io.github.bonigarcia.wdm.WebDriverManager;

 public class class02 {
	public  static SoftAssert softassert=new SoftAssert(); // object of assert class
public	String url="https://opensource-demo.orangehrmlive.com/web/index.php/auth/login";

	WebDriver driver;
//	@BeforeTest
//	public void GettheWebsite() throws InterruptedException {
//    WebDriverManager.edgedriver().setup();
//    driver=new EdgeDriver();
//	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
//	//driver.get(url);
//	driver.manage().window().maximize();
//	
//	}
	@BeforeMethod
	public void GetUrl() {
		   WebDriverManager.edgedriver().setup();
		    driver=new EdgeDriver();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
			driver.get(url);
			driver.manage().window().maximize();
      	
	}
    @Test(dataProvider = "getData",enabled = false)
    public void ValidCredentials(String username,String password) {
    driver.findElement(By.xpath("//input[@name='username']")).sendKeys(username);
    driver.findElement(By.xpath("//input[@name='password']")).sendKeys(password);
    driver.findElement(By.xpath("//button[@type='submit']")).click();
    softassert.assertTrue(driver.findElement(By.linkText("PIM")).isDisplayed());
    softassert.assertAll();
    driver.close(); // for closing the window
}
    @Test(enabled = true)
    public void InvalidPassword() throws InterruptedException {
    driver.findElement(By.xpath("//input[@name='username']")).sendKeys("Admin");
	driver.findElement(By.cssSelector("input[placeholder='Password']")).sendKeys("admin1234");
    driver.findElement(By.xpath("//button[@type='submit']")).click();
    //Thread.sleep(5000);
    System.out.println(driver.findElement(By.className("oxd-text--p")).getText());
}
    @Test(enabled=false)
    public void InvalidUserName() {
    driver.findElement(By.xpath("//input[@name='username']")).sendKeys("Adminn");
	driver.findElement(By.cssSelector("input[placeholder='Password']")).sendKeys("admin123");
    driver.findElement(By.xpath("//button[@type='submit']")).click();
    System.out.println(driver.findElement(By.className("oxd-text--p")).getText());
}

    @Test(priority = 1,enabled = false)
    public void AddingtheEmployee() {
	driver.findElement(By.linkText("PIM")).click();
	driver.findElement(By.className("bi-plus")).click();
	driver.findElement(By.xpath("//input[@name='firstName']")).sendKeys("Ajay");
	driver.findElement(By.xpath("//input[@name='middleName']")).sendKeys("Kumar");
	driver.findElement(By.xpath("//input[@name='lastName']")).sendKeys("Dhakad");
	driver.findElement(By.className("oxd-input--active")).clear();
	driver.findElement(By.className("oxd-input--active")).sendKeys("1234");
	driver.findElement(By.className("oxd-button--medium")).click();
}

    @Test(priority = 2,enabled = false)
    public void Logout() {
	driver.findElement(By.className("oxd-userdropdown-tab")).click();
	driver.findElement(By.linkText("Logout")).click();
}
    
    @DataProvider
    public  Object [][]getData(){
    	Object[][] data=new Object[2][2];
//    	Object[][] data= {{"Admin","admin123"},
//    			          {"Admin","admin123"}}; 
    	
    	// or 
    	
    	data[0][0]="Admin";
    	data[0][1]="admin123";
    	data[1][0]="Admin";
    	data[1][1]="admin1234";
    	

    	return data;
    }
    
    @AfterTest
    public void End() {
    driver.quit();
    }



}
