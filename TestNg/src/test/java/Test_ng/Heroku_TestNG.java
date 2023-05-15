package Test_ng;

import java.time.Duration;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

  public class Heroku_TestNG {
	   WebDriver driver;
  @BeforeTest
  public void Get_The_App() {
	  WebDriverManager.edgedriver().setup();
	  driver=new EdgeDriver();
	  String url="https://the-internet.herokuapp.com/";
	  driver.get(url);
	  driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
	  driver.manage().window().maximize();
}

  @Test(priority=1,enabled = false)
  public void Multiple_Windows() {
	  driver.findElement(By.partialLinkText("Multiple")).click();
	  driver.findElement(By.partialLinkText("Click")).click();
	 
	  Set<String> id=driver.getWindowHandles();
      Iterator<String> itr=id.iterator();
      String parent=itr.next();
      String child=itr.next();
    // for child window
      driver.switchTo().window(child);
      String s=driver.findElement(By.xpath("//div[@class='example']/h3")).getText();
      System.out.println(s);
    // for parent window
      driver.switchTo().window(parent);
      String s1=driver.findElement(By.xpath("//div[@class='example']/h3")).getText();
      System.out.println(s1);
   
}

  @Test(priority=2,enabled = true)
  public void Nested_Frames() {
	 driver.findElement(By.linkText("Frames")).click();
	 driver.findElement(By.linkText("Nested Frames")).click();
     driver.switchTo().frame("frame-top");
     driver.switchTo().frame("frame-middle");
     String f=driver.findElement(By.tagName("body")).getText();
     System.out.println(f);
     driver.switchTo().defaultContent();

}

    @Test(priority=3,enabled = false)
    public void Drag_Drop() throws InterruptedException {
    	
	     driver.findElement(By.linkText("Drag and Drop")).click();
	     Actions drag_drop=new Actions(driver);
	     WebElement drag=driver.findElement(By.id("column-a"));
	     WebElement drop=driver.findElement(By.id("column-b"));
	     drag_drop.dragAndDrop(drag,drop).build().perform();
	     driver.switchTo().defaultContent();
}

    @Test(priority=4,enabled = false)
         public void Hover() {
	     List<WebElement> ls=driver.findElements(By.tagName("a"));
	     for(int i=0;i<ls.size();i++) {
		 String link=ls.get(i).getAttribute("href");
		 System.out.println(link);
 }
	     driver.findElement(By.linkText("Hovers")).click();
	     Actions ac=new Actions(driver);
         WebElement hover=driver.findElement(By.xpath("//div[@class='figure'][1]"));
         ac.moveToElement(hover).build().perform();
         driver.switchTo().defaultContent();
         driver.findElement(By.linkText("View profile")).click();
         String ts=driver.findElement(By.tagName("h1")).getText();
         
         System.out.println(ts);
         
           }

//    @AfterTest
//    public void last() {
//	      driver.quit();
//          }

}
