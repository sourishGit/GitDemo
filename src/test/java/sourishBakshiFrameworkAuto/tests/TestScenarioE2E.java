package sourishBakshiFrameworkAuto.tests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import sourishBakshiFrameworkAuto.pageobjects.LandingPage;

public class TestScenarioE2E {
	
	@Test
	public void testScenario() throws InterruptedException
	{
		String productName = "ZARA COAT 3";
		System.setProperty("webdriver.chrome.driver", "C:/Users/SOURISH/eclipse-workspace/SeleniumFrameworkDesignSb/driver/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		driver.manage().window().maximize();
		driver.get("https://rahulshettyacademy.com/client");
		driver.findElement(By.id("userEmail")).sendKeys("alb@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Abc@1234");
		driver.findElement(By.id("login")).click();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
		
		List<WebElement> items = driver.findElements(By.cssSelector(".mb-3"));
		WebElement prod = items.stream().filter(s->
		s.findElement(By.tagName("b")).getText().equalsIgnoreCase(productName)).findFirst().orElse(null);
		
		prod.findElement(By.xpath("//button[2]")).click();
		
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		driver.findElement(By.xpath("//button[contains(@routerlink,'cart')]")).click();
		
		List<WebElement> cartItems = driver.findElements(By.xpath("//div[@class='cartSection']/h3")); 
		boolean match = cartItems.stream().anyMatch(s-> s.getText().equalsIgnoreCase(productName));
		Assert.assertTrue(match);
		
		driver.findElement(By.cssSelector(".totalRow button")).click();
		
		Actions act = new Actions(driver);
		act.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")), "India").build().perform();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-item")));
		driver.findElement(By.cssSelector(".ta-item:last-of-type")).click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ta-item:last-of-type")));
		//Thread.sleep(2000);
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("window.scrollBy(0,2000)");
		js.executeScript("window.scrollBy(0,2000)");
		js.executeScript("window.scrollBy(0,2000)");
		driver.findElement(By.xpath("//a[contains(@class,'submit')]")).click();
		String confirmationMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();
		Assert.assertTrue(confirmationMessage.equalsIgnoreCase("Thankyou for the order."));
	}

}
