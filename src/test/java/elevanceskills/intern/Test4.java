package elevanceskills.intern;
import java.time.Duration;
import java.util.Properties;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

public class Test4 {
  @Test
  public void task4() {
	  WebDriver driver = new ChromeDriver();
		driver.get("https://www.amazon.in");
		driver.manage().window().maximize();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement searchbox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("twotabsearchtextbox")));
		searchbox.sendKeys("table");
		String inputtext = searchbox.getAttribute("value");
		System.out.println("Search product:" + inputtext);
		searchbox.submit();
		String title = driver.findElement(By.xpath("//a/h2/span")).getText();
		System.out.println("product 1:" + title);
		//ratting
		WebElement rattingelement=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//span[@class='a-size-small a-color-base']")));
		String ratting = rattingelement.getText();
     	System.out.println("ratting of the product :" + ratting);
		//add to cart
		WebElement addtocart=wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("a-autoid-3-announce")));
		addtocart.click();
		System.out.println("Sucessfully product is added into cart");
	    //go to cart
		WebElement gotocart=wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("nav-cart-count-container")));
		gotocart.click();
		//price tag
		WebElement totalamount=driver.findElement(By.xpath("//*[@id=\"sc-subtotal-amount-activecart\"]"));
		 String totalprice=totalamount.getText();
		 System.out.println("Product price found:" + totalprice);
		 String pricetag = totalprice.replaceAll("[^0-9.]", "");
		 double currentprice = Double.parseDouble(pricetag);
		 double threshold=1000.0;
		 String messageBody;
		 if(currentprice < threshold) {
			 messageBody="price is dropped";	 
		 	 System.out.println("Price is below threshold");
		 	 System.out.println("Buy now before the price is increase");
	
		 }
		 else {
			 System.out.println("product is still high");
			 messageBody="price is constant. Currentprice:" + currentprice;
		 }
		 driver.close();
		 sendEmail("vamsikrishna0731@gmail.com","bacldpxagevqqfup", "vamsikrishnavk0731@gmail.com", "amazon price alert", messageBody);
	}	 
public static void sendEmail(String from, String appPassword, String to, String subject, String body) {
			    
			  //  String to = "vamsikrishna0731@gmail.com";

			  
			  //  String from = "vamsikrishnavk 0731@gmail.com";

			   
			    //String host = "smtp.gmail.com";

			    // Mail server settings
			    Properties properties = System.getProperties();
			    properties.put("mail.smtp.host", "smtp.gmail.com");      // Which server to connect
			    properties.put("mail.smtp.port", "587");     // Port for TLS
			    properties.put("mail.smtp.auth", "true");    // Need login
			    properties.put("mail.smtp.starttls.enable", "true"); // Secure connection

			    // Login to sender account
			
			   Session session = Session.getInstance(properties, new jakarta.mail.Authenticator() {
			
			    protected jakarta.mail.PasswordAuthentication getPasswordAuthentication() {
			            // Replace with your Gmail + app password
			            return new jakarta.mail.PasswordAuthentication(from, appPassword);
			        }
			    });

			    try {
			        // Create the email
			        MimeMessage message = new MimeMessage(session);
			        message.setFrom(new InternetAddress(from));   // Who is sending
			        message.addRecipient(Message.RecipientType.TO, new InternetAddress(to)); // Who receives
			        message.setSubject(subject);           // Subject line
			        message.setText(body); // Email body
			        

			        // Send the email
			        Transport.send(message);
			        System.out.println("Notification mail sent successfully!");
			    	 System.out.println("TEST SUCCESS");
			    } catch (MessagingException e) {
			     	System.out.println("TEST IS FAILED");
			        e.printStackTrace();
			    }
			}
}
