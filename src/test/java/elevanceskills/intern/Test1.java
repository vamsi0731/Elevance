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

public class Test1 {
	WebDriver driver;
  @Test
  public void Selecting_product() throws Exception {
		LocalTime now = LocalTime.now();
		LocalTime start = LocalTime.of(15, 0);
		LocalTime end = LocalTime.of(18, 0);
		
		if(now.isBefore(start) || now.isAfter(end)) {
			System.out.println("test will run only between 3 pm and 6 pm");
			return;
		}
		System.out.println("Execution time is valid" ); 
	    driver=new ChromeDriver();
	    try {
	    	driver.get("https://www.amazon.in");
			
			driver.manage().window().maximize();

			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			Thread.sleep(4000);
			WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("twotabsearchtextbox")));
			input.sendKeys("jogger jeans");
			String inputtext = input.getAttribute("value");
			System.out.println("Search product:" + inputtext);
			driver.findElement(By.id("nav-search-submit-button")).click();
			Thread.sleep(4000);
			List<WebElement> items = driver.findElements(By.cssSelector("div.s-main-slot div[data-components-type='s-search-result']"));
			//electronics keyword list
			
			String[] electronicsKeywords = {
					"mobile","laptop","rice cooker","tv","tablet","camera","headphone","earphone","smartphone","smartwatch","iphone","computer","desktop","monitor","trimmer","speaker","fan","electronics"
			};
			
			List<WebElement> products = driver.findElements(By.xpath("//a/h2/span"));
			
			for(WebElement product : products) {
				
			String name = product.getText();
			
			System.out.println("Selected product:" + name);
			
			boolean isElectronic = false;
		
			for(String keyword : electronicsKeywords) {

			//skip electronics + A,B,C,D 
			if(name.toLowerCase().contains(keyword)) {
				System.out.println("Skip electronics:" + name);
				System.out.println("Search for another product");
				isElectronic = true;
				return;
			}
		}	
		if(isElectronic) {
		
			continue;
		}
			
			char firstletter = Character.toUpperCase(name.charAt(0));
			if(firstletter == 'A' || firstletter == 'B' ||firstletter == 'C' || firstletter == 'D') {
			 System.out.println("Skip A,B,C,D");
			 System.out.println("Search another product");
			 	return;
			}
		    product.click();
			
			break;
			
	    }
			
			Thread.sleep(4000);
			//switch window
			for(String handle : driver.getWindowHandles()) {
				driver.switchTo().window(handle);
			}
			//verify product page
			WebElement title2=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@id='productTitle']")));
	
			String title=title2.getText();
			System.out.println("product name:" + title);
			char firstletter = Character.toUpperCase(title.charAt(0));
			if(firstletter == 'A' || firstletter == 'B' ||firstletter == 'C' || firstletter == 'D') {
			 System.out.println("Skip A,B,C,D");
			 System.out.println("Search another product");
			 	return;
			}
			WebElement rattingelement=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//span[@class='a-size-small a-color-base']")));
			String ratting = rattingelement.getText();
			
		
	     	System.out.println("ratting of the product:" + ratting);
			
			System.out.println("Test Success");
	 }		catch(Exception e) {
		 System.out.println("Test failed");
		 e.printStackTrace();
	 }
	    finally {
	    driver.quit();
		
	    }  
}			
}			

