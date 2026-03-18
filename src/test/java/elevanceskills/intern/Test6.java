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

public class Test6 {
  @Test
  public void task6() throws InterruptedException {
	  LocalTime now   = LocalTime.now();                  
		LocalTime start = LocalTime.of(15, 0);
		LocalTime end   = LocalTime.of(18, 0);
		
		if(now.isBefore(start) || (now.isAfter(end))) {
			System.out.println("this will run in between 3PM to 6PM");
		     return;
		}
	 	 System.out.println("Execution time is valid");
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.amazon.in");
		driver.manage().window().maximize();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement searchbox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("twotabsearchtextbox")));
		searchbox.sendKeys("canon camera"); 
		String inputtext = searchbox.getAttribute("value");
		System.out.println("Search product:" + inputtext);
		searchbox.submit(); 
		//brand      
			WebElement brand=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"p_123/359121\"]/span/a/div/label/i")));	
	      	brand.click();
	      	System.out.println("Brand is selected");
		//price range
	      	WebElement price=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"p_36/dynamic-picker-0\"]/span/a/span")));
	      	price.click();
		    System.out.println("price range is selected");
		    WebElement product=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='RF 75-300mm F4-5.6 Telephoto Zoom Lens | Versatile Zoom for Wildlife, Sports & Portrait Photography']")));
		    product.click();
		    Thread.sleep(4000);
			//switch window
			for(String handle : driver.getWindowHandles()) {
				driver.switchTo().window(handle);
			}
			//verify product page
			WebElement title2=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@id='productTitle']")));
	
			String title=title2.getText();
			System.out.println("product name:" + title);
			WebElement rattingelement=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//span[@class='a-size-small a-color-base']")));
			String rattingtext = rattingelement.getText();
			double ratting = Double.parseDouble(rattingtext.split(" ")[0]);
			if(ratting > 4) {
	         	System.out.println("ratting of the product:" + ratting);
	         	System.out.println("TEST PASSED");
			}
			else {
				System.out.println("ratting of the product:" + ratting);
				System.out.println(" TEST FAIL search for another product");
			}
		   
      //add to cart
		WebElement addtocart=wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("add-to-cart-button")));
		addtocart.click();
		
	    //go to cart
		WebElement gotocart=wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("sw-gtc")));
		gotocart.click();
	
		if(title.toLowerCase().startsWith("c")) {
		 	
			System.out.println("product is vaild it is starting with 'C'");
			 System.out.println("TEST PASSED");
		}
		else {
		 	 System.out.println("TEST FAILED");
			System.out.println("product should start with letter 'C'");
		}
		
		 WebElement totalamount=driver.findElement(By.xpath("//*[@id=\"sc-subtotal-amount-activecart\"]/span"));
		 String totaltext=totalamount.getText();
		System.out.println("total price" + totaltext);
		 totaltext = totaltext.replaceAll("[^0-9]", "");//it removes every thing except digits
		 int totalprice =Integer.parseInt(totaltext);
		 
		    
		 if(totalprice >= 2000) {
		      
		        System.out.println("TEST SUCCESS"); 
		        
		    }
		 else {
			 System.out.println("TEST IS FAILED"); 
		    	 System.out.println("total amount must be above 2000");
		    }
		 driver.quit();
		 }
	
  }


