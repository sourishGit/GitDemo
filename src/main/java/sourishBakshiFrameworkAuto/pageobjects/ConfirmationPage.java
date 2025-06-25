package sourishBakshiFrameworkAuto.pageobjects;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import sourishBakshiFrameworkAuto.Utils.Utils;

public class ConfirmationPage extends Utils {
	
	WebDriver driver;
	
	public ConfirmationPage(WebDriver driver)
	{
		super(driver);
		this.driver= driver;
		PageFactory.initElements(driver, this);
	}
	
	
	
	@FindBy(css=".hero-primary")
	WebElement confirmationMessage;
	
	
	
	public String confirmationText()
	{
		return confirmationMessage.getText();
	}
	
	
	

}
