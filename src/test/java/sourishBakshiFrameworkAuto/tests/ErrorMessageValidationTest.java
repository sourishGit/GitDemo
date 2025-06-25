package sourishBakshiFrameworkAuto.tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import sourishBakshiFrameworkAuto.TestComponents.BaseTest;
import sourishBakshiFrameworkAuto.TestComponents.Retry;
import sourishBakshiFrameworkAuto.pageobjects.CartPage;
import sourishBakshiFrameworkAuto.pageobjects.CheckOutPage;
import sourishBakshiFrameworkAuto.pageobjects.CommonPage;
import sourishBakshiFrameworkAuto.pageobjects.ConfirmationPage;
import sourishBakshiFrameworkAuto.pageobjects.ProductCatalogue;

public class ErrorMessageValidationTest extends BaseTest{
	
	@Test(groups= {"ErrorHandling"}, retryAnalyzer=Retry.class)
	public void errorValidation() throws InterruptedException, IOException
	{
		
		String userEmail = "alb@gmail.com";
		String passWord = "Abc@1234s";
		String errMsg = null;
		
		
		landingPage.loginApplication(userEmail, passWord);
		
		 errMsg = landingPage.getErrorMessage();
		
		
		Assert.assertEquals(errMsg, "Incorrect email or password.", "Its not matched");	
		driver.quit();
		
	}
	
	@Test
	public void verifyMismatchedProduct() throws InterruptedException, IOException
	{
		String productName = "ZARA COAT 3";
		String userEmail = "alb@gmail.com";
		String passWord = "Abc@1234";
		//String country = "India";
		
		ProductCatalogue productCatalogue = landingPage.loginApplication(userEmail, passWord);
		CommonPage commonPage = productCatalogue.addProductToCart(productName);
				
		CartPage cartPage = commonPage.clickOnCartIcon();
				
		boolean match = cartPage.verifyCartItems(productName+"7");
		
		Assert.assertFalse(match);
		
		
	}

}
