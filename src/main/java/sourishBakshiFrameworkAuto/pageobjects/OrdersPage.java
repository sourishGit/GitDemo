package sourishBakshiFrameworkAuto.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrdersPage  {
	
	WebDriver driver;

	public OrdersPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//tr/td[2]")
	List<WebElement> listofOrder;
	
	
	public boolean getOrderItemNameMatchValue(String productName)
	{
		boolean match = listofOrder.stream().anyMatch(s->s.getText().equalsIgnoreCase(productName));
		return match;
		
	}
	
	
	

}
