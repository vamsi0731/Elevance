package elevanceskills.intern;

import java.time.Duration;
import java.time.LocalTime;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class Test5 {
  @Test
  public void conforming_order() throws InterruptedException {
	  LocalTime now   = LocalTime.now();                  
		LocalTime start = LocalTime.of(18, 0);
		LocalTime end   = LocalTime.of(19, 0);
		
		if(now.isBefore(start) || (now.isAfter(end))) {
			System.out.println("this will run in between 6PM to 7PM");
			return;
		}
	 	 System.out.println("Execution time is Valid"); 
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.amazon.in");
		driver.manage().window().maximize();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		//signin
		 WebElement sign=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"nav-link-accountList\"]/a")));
		 sign.click();
	    driver.findElement(By.id("ap_email_login")).sendKeys("6301002440");
	    driver.findElement(By.xpath("//*[@id=\"continue\"]/span/input")).click();
	    WebElement password=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"ap_password\"]")));
	    password.sendKeys("vamsi0731");
	    driver.findElement(By.xpath("//*[@id=\"signInSubmit\"]")).click();
	    // remove products in cart
	    driver.findElement(By.xpath("//*[@id=\"nav-cart-count-container\"]")).click();
	
		while(driver.findElements(By.xpath("//input[@value='Delete']")).size()>0) {
			driver.findElement(By.xpath("//input[@value='Delete']")).click();
			Thread.sleep(1000);
		}
	   
		WebElement searchbox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("twotabsearchtextbox")));
		searchbox.sendKeys("earbuds");
		String inputtext = searchbox.getAttribute("value");
		System.out.println("Search product:" + inputtext);
		searchbox.submit();
		Thread.sleep(4000);
		String title = driver.findElement(By.xpath("//a/h2/span")).getText();
		System.out.println("product :" + title);
		//ratting
		WebElement rattingelement=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//span[@class='a-size-small a-color-base']")));
		String ratting = rattingelement.getText();
     	System.out.println("ratting of the product :" + ratting);
		//add to cart
		WebElement addtocart=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"a-autoid-5-announce\"]")));
		addtocart.click();
		 System.out.println("Sucessfully Product is added into cart");
		//go to cart
		WebElement gotocart=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"ewc-compact-actions-container\"]/div/div[2]/span/span/a")));
		gotocart.click();
		//get price tag
		 WebElement totalamount=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"sc-subtotal-amount-activecart\"]/span")));
		 String totaltext=totalamount.getText(); 
		 totaltext = totaltext.replaceAll("[^0-9.]","");//it removes every thing except digits
		 double totalprice = Double.parseDouble(totaltext);
		 System.out.println("price of the product:" + totalprice);
		    
		 if(totalprice >= 500) {
			    System.out.println("TEST PASS");
		        System.out.println("total amount is more than 500"); 
		        //CHECTOUT
		        driver.findElement(By.xpath("//*[@id=\"sc-buy-box-ptc-button\"]")).click();
				 System.out.println("Order is conformed successfully");
				 System.out.println("TEST SUCCESS ");
				  
		    }
		 else {
		    	 System.out.println("TEST IS FAILED ");
		    	 System.out.println("price should be more than 500");
		    	 System.out.println("select another product");
		    }
		
		  driver.close();
	}
       
  }
  


