package Test_ng;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Parallel_testing {
	WebDriver driver;
	public String url="https://opensource-demo.orangehrmlive.com/web/index.php/auth/login";
//@Parameters("mybrowser")
@BeforeTest
public void settingUpBrowser(String mybrowser) {
	if(mybrowser.equalsIgnoreCase("firefox")) {
		WebDriverManager.firefoxdriver().setup();
		driver=new FirefoxDriver();
		driver.navigate().to(mybrowser);
		
	}else if(mybrowser.equalsIgnoreCase("edge")) {
		WebDriverManager.firefoxdriver().setup();
		driver=new EdgeDriver();
		driver.get(mybrowser);
	}
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
}

@Test
public void valid() {
	driver.findElement(By.xpath("//input[@name='username']")).sendKeys("Admin");
    driver.findElement(By.xpath("//input[@name='password']")).sendKeys("admin123");
    driver.findElement(By.xpath("//button[@type='submit']")).click();
}
}
