package sourishBakshiFrameworkAuto.pageobjects;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import sourishBakshiFrameworkAuto.Utils.Utils;

public class CartPage extends Utils {
	
	WebDriver driver;
	
	public CartPage(WebDriver driver)
	{	super(driver);
		this.driver= driver;
		PageFactory.initElements(driver, this);
	}
	
	
	
	@FindBy(xpath="//button[contains(@routerlink,'cart')]")
	WebElement cartIcon;
	
	@FindBy(xpath="//div[@class='cartSection']/h3") 
	List<WebElement> cartItems;
	
	@FindBy(css=".totalRow button")
	WebElement checkOutButton;

		
		
	public  boolean verifyCartItems(String productName)
	{
		boolean match = cartItems.stream().anyMatch(s-> s.getText().equalsIgnoreCase(productName));
		return match;
	}
	
	public CheckOutPage clickCheckoutButton()
	{
		checkOutButton.click();
		return new CheckOutPage(driver);
	}

	

}
