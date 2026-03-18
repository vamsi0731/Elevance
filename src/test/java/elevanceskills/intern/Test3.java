package elevanceskills.intern;
import java.time.Duration;
import java.time.LocalTime;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class Test3 {
  @Test
  public void log_in() {           
	  LocalTime now   = LocalTime.now();                  
		LocalTime start = LocalTime.of(12, 0);
		LocalTime end   = LocalTime.of(15, 0);
		
		if(now.isBefore(start) || (now.isAfter(end))) {
			System.out.println("this will run in between 12PM to 3PM");
			return;
		}
	 	 System.out.println("Execution time is Valid");
		
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.amazon.in");
		driver.manage().window().maximize();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement signin = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"nav-link-accountList-nav-line-1\"]")));
	
		signin.click();
		 driver.findElement(By.id("ap_email_login")).sendKeys("6301002440");
		 driver.findElement(By.xpath("//*[@id=\"continue\"]/span/input")).click();
		 WebElement password=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"ap_password\"]")));
		 password.sendKeys("vamsi0731");
		 driver.findElement(By.xpath("//*[@id=\"signInSubmit\"]")).click();
	 	 System.out.println("Signin Success");
		 String fulltext =driver.findElement(By.xpath("//*[@id=\"nav-link-accountList-nav-line-1\"]")).getText();
		 String username = fulltext.replace("Hello,", "").trim();//remove hello
		 System.out.println("username:" + username);
		 if(username.contains(".*[ACGILK]*.")) { //
			 System.out.println("TEST FAIL user name should not contain ACGILK");
		 }
		 else {
			 System.out.println("user name is vaild");
		 	 System.out.println("TEST SUCCESS");
		 }
		 driver.close();
	}

}



