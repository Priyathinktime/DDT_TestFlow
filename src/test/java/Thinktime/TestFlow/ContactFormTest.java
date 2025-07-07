package Thinktime.TestFlow;




import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ContactFormTest {

	  WebDriver driver;
	  @BeforeMethod
	  public void setUp() {
	      WebDriverManager.chromedriver().setup();
	      ChromeOptions options = new ChromeOptions();
	      options.addArguments("--remote-allow-origins=*");
	      options.addArguments("--headless=new"); // Enable headless mode (new headless for Chrome 109+)
	      options.addArguments("--window-size=1920,1080"); // Optional: for consistent layout
	      options.addArguments("--disable-gpu"); // Recommended for headless in some environments

	      driver = new ChromeDriver(options);
	      driver.manage().window().maximize();
	      driver.get("https://thinktime.in/");
	  }



	    @DataProvider(name = "formData")
	    public Object[][] getFormData() {
	        return ExcelUtils.readExcelData("D:\\Downloads\\TestData (1).xlsx", "Sheet1");

	    }

	    @Test(dataProvider = "formData")
	    public void testContactForm(String firstName, String lastName, String email, String subject, String message) throws InterruptedException {
	    	System.out.println(firstName + " | " + lastName + " | " + email  + " | " + subject + " | " + message  );

	    	driver.findElement(By.xpath("(//a[contains(text(),'Contact')])[1]")).click();
	    	 Thread.sleep(5000);
	    	
	    	WebElement getInTouchElement = driver.findElement(By.id("ff_7_names_first_name_"));

	    	((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", getInTouchElement);
	    	
	    	
	        driver.findElement(By.id("ff_7_names_first_name_")).sendKeys(firstName);
	        driver.findElement(By.id("ff_7_names_last_name_")).sendKeys(lastName);
	        driver.findElement(By.id("ff_7_email")).sendKeys(email);
	        driver.findElement(By.id("ff_7_subject")).sendKeys(subject);
	        driver.findElement(By.id("ff_7_message")).sendKeys(message);

//	        driver.findElement(By.xpath("//button[text()='Submit']")).click();

	        // Add your success validation or wait
	        Thread.sleep(2000); // for demo
	        
	        
	    	WebElement getInTouchElement1 = driver.findElement(By.xpath("//button[contains(text(),'Submit')]"));

	    	((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", getInTouchElement1);
	    }

	    @AfterMethod
	    public void tearDown() {
	        driver.quit();
	    }

}