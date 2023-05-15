package Test_ng;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class datafrom_Sql {
	WebDriver driver;
	
	String url="https://opensource-demo.orangehrmlive.com/web/index.php/auth/login";
	
	@Test
	public void getdata() throws SQLException, InterruptedException {
	WebDriverManager.edgedriver().setup();
	driver=new EdgeDriver();
	driver.get(url);
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
	 Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/testingdata","root","Ajay@1998");
     Statement st=con.createStatement();
     ResultSet result = st.executeQuery("select * from userdetail where serial=1");
     result.next();
     String username=result.getString("Name");
     String password=result.getString("password");
     
     driver.findElement(By.xpath("//input[@name='username']")).sendKeys(username);
	 driver.findElement(By.xpath("//input[@name='password']")).sendKeys(password);
	 driver.findElement(By.xpath("//button[@type='submit']")).click();
	}
	
	
}
