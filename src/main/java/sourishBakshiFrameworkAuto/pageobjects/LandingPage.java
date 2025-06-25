package sourishBakshiFrameworkAuto.pageobjects;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import sourishBakshiFrameworkAuto.Utils.Utils;

public class LandingPage extends Utils {
	
	WebDriver driver;
	
	public LandingPage(WebDriver driver)
	{
		super(driver);
		this.driver= driver;
		PageFactory.initElements(driver, this);
	}
	
		
	
	@FindBy(id="userEmail")
	WebElement userEmail;
	
	@FindBy(id="userPassword")
	WebElement passwordElement;
	
	@FindBy(id="login")
	WebElement loginButton;
	
	@FindBy(css=".ngx-toastr")
	WebElement errorMessage;
	
	
	
	public ProductCatalogue loginApplication(String userName, String password)
	{
		userEmail.sendKeys(userName);
		passwordElement.sendKeys(password);
		jsClick(loginButton);
		return new ProductCatalogue(driver);
		
	}
	
	public void navigateToUrl(String urlString)
	{
		
		driver.get(urlString);
	}
	
	public String getErrorMessage()
	{
		waitforElementToAppearEle(errorMessage);
		return errorMessage.getText();
		
	}
	

}
