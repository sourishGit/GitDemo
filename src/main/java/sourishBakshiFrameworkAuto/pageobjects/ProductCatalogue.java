package sourishBakshiFrameworkAuto.pageobjects;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import sourishBakshiFrameworkAuto.Utils.Utils;

public class ProductCatalogue extends Utils {
	
	WebDriver driver;
	
	public ProductCatalogue(WebDriver driver)
	{	super(driver);
		this.driver= driver;
		PageFactory.initElements(driver, this);
	}
	
	By productsBy = By.cssSelector(".mb-3");	
	//By addToCart = By.xpath("//button[2]");
	By addToCart = By.cssSelector(".card-body button:last-of-type");
	By toastMessage = By.cssSelector("#toast-container");
	
	
	@FindBy(css=".mb-3")
	List<WebElement> productList;
	
	@FindBy(css=".ng-animating")
	WebElement animation;
	
	@FindBy(xpath="//button[contains(@routerlink,'cart')]")
	WebElement cartIcon;
	

	
	
	
	public List<WebElement> getProductList()
	{
		waitforElementToAppear(productsBy);
		return productList;
	}
	
	public WebElement getProductByName(String productName)
	{
		WebElement prod = getProductList().stream().filter(s->
		s.findElement(By.tagName("b")).getText().equalsIgnoreCase(productName)).findAny().orElse(null);
		System.out.println(prod);
		return prod;
	}
	
	public CommonPage addProductToCart(String productName)
	{
		WebElement prod = getProductByName(productName);
		prod.findElement(addToCart).click();
		waitforElementToAppear(toastMessage);
		waitforElementToDisappear(animation);
		return new CommonPage(driver);
	}
	
	

}
