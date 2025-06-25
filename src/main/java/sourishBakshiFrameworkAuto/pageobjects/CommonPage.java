package sourishBakshiFrameworkAuto.pageobjects;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import sourishBakshiFrameworkAuto.Utils.Utils;

public class CommonPage extends Utils {
	
	WebDriver driver;
	
	public CommonPage(WebDriver driver)
	{
		super(driver);
		this.driver= driver;
		PageFactory.initElements(driver, this);
	}
	
		
	
	@FindBy(xpath="//button[contains(@routerlink,'cart')]")
	WebElement cartIcon;
	
	@FindBy(css="[routerlink*='myorders']")
	WebElement ordersIcon;
	
	public CartPage clickOnCartIcon()
	{
		cartIcon.click();
		return new CartPage(driver);
	}
	
	public OrdersPage clickOnOrdersIcon()
	{
		ordersIcon.click();
		return new OrdersPage(driver);
	}
	

}
