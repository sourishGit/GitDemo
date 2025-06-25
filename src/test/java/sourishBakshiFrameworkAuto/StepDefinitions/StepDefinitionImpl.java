package sourishBakshiFrameworkAuto.StepDefinitions;

import java.io.IOException;

import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import sourishBakshiFrameworkAuto.TestComponents.BaseTest;
import sourishBakshiFrameworkAuto.pageobjects.CartPage;
import sourishBakshiFrameworkAuto.pageobjects.CheckOutPage;
import sourishBakshiFrameworkAuto.pageobjects.CommonPage;
import sourishBakshiFrameworkAuto.pageobjects.ConfirmationPage;
import sourishBakshiFrameworkAuto.pageobjects.LandingPage;
import sourishBakshiFrameworkAuto.pageobjects.ProductCatalogue;

public class StepDefinitionImpl extends BaseTest {
	
	public LandingPage landingPage;
	public ProductCatalogue productCatalogue;
	public CommonPage commonPage;
	ConfirmationPage confirmationPage;
	
	@Given("I landed on e-commerce website")
	public void land_on_ecommerce_web() throws IOException
	{
		landingPage = launchApplication();
	}
	
	@Given("^Logged in with username (.+) and password (.+)$")
	public void login_with_usrName_passwd(String userName, String password)
	{
		productCatalogue = landingPage.loginApplication(userName,password);
	}
	
	@When("^I add product (.+) to cart$")
	public void add_prod_to_cart(String productName)
	{
		commonPage = productCatalogue.addProductToCart(productName);
	}
	
	@When("^Checkout (.+) and submit order$")
	public void check_and_submit_order(String productName)
	{
		CartPage cartPage = commonPage.clickOnCartIcon();
		
		boolean match = cartPage.verifyCartItems(productName);
		
		Assert.assertTrue(match);
		
		CheckOutPage checkOutPage =  cartPage.clickCheckoutButton();
		
		checkOutPage.selectCountry("India");
		confirmationPage = checkOutPage.placeOrder();
	}
	
	@Then("{string} message is displayed on Confirmation page")
	public void message_displayed_conf_page(String confMsg)
	{
		String confirmationMessage = confirmationPage.confirmationText();
		
		
		Assert.assertTrue(confirmationMessage.equalsIgnoreCase(confMsg));
		//driver.quit();
	}
	
	@Then("{string} message is displayed")
	public void error_msg_is_displayed(String errorMsgexpected)
	{
		 String errMsg = landingPage.getErrorMessage();
			
			
			Assert.assertEquals(errMsg, errorMsgexpected, "Its not matched");	
			driver.quit();
	}
}
