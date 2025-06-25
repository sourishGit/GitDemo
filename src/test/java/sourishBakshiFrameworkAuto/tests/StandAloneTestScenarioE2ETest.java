package sourishBakshiFrameworkAuto.tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import sourishBakshiFrameworkAuto.TestComponents.BaseTest;
import sourishBakshiFrameworkAuto.pageobjects.CartPage;
import sourishBakshiFrameworkAuto.pageobjects.CheckOutPage;
import sourishBakshiFrameworkAuto.pageobjects.CommonPage;
import sourishBakshiFrameworkAuto.pageobjects.ConfirmationPage;
import sourishBakshiFrameworkAuto.pageobjects.OrdersPage;
import sourishBakshiFrameworkAuto.pageobjects.ProductCatalogue;
import org.apache.commons.io.FileUtils;

public class StandAloneTestScenarioE2ETest extends BaseTest{
	
	
	
	@Test(dataProvider ="getData", groups = {"Purchase"} )
	public void submitOrder(HashMap<String,String> input) throws InterruptedException, IOException
	{
		
		
		
		
		ProductCatalogue productCatalogue = landingPage.loginApplication(input.get("email"),input.get("password") );
		CommonPage commonPage = productCatalogue.addProductToCart(input.get("productName"));
				
		CartPage cartPage = commonPage.clickOnCartIcon();
				
		boolean match = cartPage.verifyCartItems(input.get("productName"));
		
		Assert.assertTrue(match);
		
		CheckOutPage checkOutPage =  cartPage.clickCheckoutButton();
		
		checkOutPage.selectCountry(input.get("country"));
		ConfirmationPage confirmationPage = checkOutPage.placeOrder();
		
		String confirmationMessage = confirmationPage.confirmationText();
		
		
		Assert.assertTrue(confirmationMessage.equalsIgnoreCase("Thankyou for the order."));
	}

	@Test(dependsOnMethods= {"submitOrder"}, dataProvider="getData")
	public void orderHistoryTest(HashMap<String,String> input )
	{
		landingPage.loginApplication(input.get("email"),input.get("password"));
		CommonPage commonPage = new CommonPage(driver);
		OrdersPage ordersPage = commonPage.clickOnOrdersIcon();
		boolean match = ordersPage.getOrderItemNameMatchValue(input.get("productName"));
		Assert.assertTrue(match);
		
	}
	
	@DataProvider
	public Object[][] getData() throws IOException
	{
		
		 List<HashMap<String,String>> data = getJsonDataToMap(System.getProperty("user.dir")+"//src//test//java//sourishBakshiFrameworkAuto//Data//PurchaseOrder.json");

		 
		return new Object[][] {{data.get(0)},{data.get(1)}};
		 
	}
	
	/*
	 * @DataProvider public Object[][] getData() { return new Object[][]
	 * {{"alb@gmail.com", "Abc@1234",
	 * "ZARA COAT 3","India"},{"tei@gmail.com","Wer@1234","ADIDAS ORIGINAL","India"}
	 * }; }
	 */
	
//	 HashMap<String,String> map = new HashMap<String,String>();
//	 map.put("email", "alb@gmail.com");
//	 map.put("password", "Abc@1234");
//	 map.put("productName", "ZARA COAT 3");
//	 map.put("country", "India");
//	 
//	 HashMap<String,String> map1 = new HashMap<String,String>();
//	 map1.put("email", "tei@gmail.com");
//	 map1.put("password", "Wer@1234");
//	 map1.put("productName", "ADIDAS ORIGINAL");
//	 map1.put("country", "India");
	
}

