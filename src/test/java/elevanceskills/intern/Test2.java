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

public class Test2 {
  @Test
  public void task2() throws InterruptedException {//add multiple products
	  LocalTime now   = LocalTime.now();                  
		LocalTime start = LocalTime.of(18, 0);
		LocalTime end   = LocalTime.of(19, 0);
		
		if(now.isBefore(start) || (now.isAfter(end))) {
			System.out.println("this will run in between 6PM to 7PM");
			return;
		}
		System.out.println("Execution time is valid");
		
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.amazon.in");
		driver.manage().window().maximize();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		//product1
		WebElement inputbox1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("twotabsearchtextbox")));
		inputbox1.sendKeys("laptop");
		String inputtext1 = inputbox1.getAttribute("value");
		System.out.println("Search product 1 :" + inputtext1);
		inputbox1.submit();
		String title = driver.findElement(By.xpath("//a/h2/span")).getText();
		System.out.println("product 1:" + title);
     	driver.findElement(By.xpath("//*[@id=\"a-autoid-3-announce\"]")).submit();
		//product2
		WebElement searchbox2 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("twotabsearchtextbox")));
		searchbox2.sendKeys("mouse");
		String inputtext2 = searchbox2.getAttribute("value");
		System.out.println("Search product 2 :" + inputtext2);
		searchbox2.submit();
		String title1 = driver.findElement(By.xpath("//a/h2/span")).getText();
		System.out.println("product 2:" + title1);
		driver.findElement(By.xpath("//*[@id=\"a-autoid-3-announce\"]")).submit();
		System.out.println("Added 2 products");
		WebElement gotocart =wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"sw-gtc\"]/span/a")));//go to cart
	    gotocart.click();
	    Thread.sleep(4000);
	    //verify total amount
	    WebElement totalamount=driver.findElement(By.xpath("//*[@id=\"sc-subtotal-amount-activecart\"]/span"));
	    String totaltext=totalamount.getText();
	    Thread.sleep(1000);
	    System.out.println("totalprice: " + totaltext); 
	    totaltext = totaltext.replaceAll("[^0-9]", "");//it removes every thing except digits
	    double totalprice =Double.parseDouble(totaltext);
	    
	    
	    if(totalprice >= 2000) {
	    	  System.out.println("Price Validation is Passed ");
	    }
	    else {
	    	 System.out.println("TEST IS FAIL total amount must be above 2000");
	    }
	    //signin
	    driver.findElement(By.id("nav-link-accountList-nav-line-1")).click();             
	    driver.findElement(By.id("ap_email_login")).sendKeys("6301002440");
	    driver.findElement(By.xpath("//*[@id=\"continue\"]/span/input")).click();
	    WebElement password=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"ap_password\"]")));
	    password.sendKeys("vamsi0731");
	    driver.findElement(By.xpath("//*[@id=\"signInSubmit\"]")).click();
	    System.out.println("login Success");
	    String fulltext =driver.findElement(By.xpath("//*[@id=\"nav-link-accountList-nav-line-1\"]")).getText();
	    String username = fulltext.replace("Hello,", "").trim();//remove hello
	    System.out.println("username:" + username);
	    if(username.length()==10) {
	    	 System.out.println("User Name is Valid");
	     	 System.out.println("TEST SUCCESS");	    	 
	    }
	    else {
	   	 System.out.println("TEST IS FAIL username must contain 10 charecters");
	    }
	    driver.close();	    
	  
	}
  }


