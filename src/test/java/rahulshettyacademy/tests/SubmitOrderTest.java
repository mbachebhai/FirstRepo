package rahulshettyacademy.tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.CheckoutPage;
import rahulshettyacademy.pageobjects.ConfirmationPage;
import rahulshettyacademy.pageobjects.OrderPage;
import rahulshettyacademy.pageobjects.ProductCatalogue;

public class SubmitOrderTest extends BaseTest {

	String productName = "ADIDAS ORIGINAL";

	@Test(dataProvider = "getData", groups = { "Purchase" })
	public void submitOrderTest(HashMap<String, String> input) throws IOException, InterruptedException {

		ProductCatalogue ProductCatalogue = landingPage.loginApplication(input.get("email"), input.get("password"));
		List<WebElement> products= ProductCatalogue.getProductList();
		ProductCatalogue.addProductToCart(input.get("product"));
		CartPage cartPage = ProductCatalogue.goToCartPage();
		boolean match = cartPage.verifyProductDiplay(input.get("product"));
		Assert.assertTrue(match);
		CheckoutPage checkoutPage = cartPage.goToCheckout();
		checkoutPage.selectCountry("india");
		ConfirmationPage confirmationPage = checkoutPage.submitOrder();
		String confirmationMessage = confirmationPage.verifyConfirmationMessage();
		Assert.assertTrue(confirmationMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));

	}

	@Test(dependsOnMethods = { "submitOrderTest" })
	public void OrderHistoryTest() {
		// to verifiy the "ADIDAS ORIGINAL" is Displaying on the order page
		ProductCatalogue ProductCatalogue = landingPage.loginApplication("maazshaikh95@gmail.com", "Manpower@007");
		OrderPage ordersPage = ProductCatalogue.goToOrderPage();
		Assert.assertTrue(ordersPage.verifyOrderDiplay(productName));
	}

	@DataProvider
	public Object[][] getData() throws IOException {

		List<HashMap<String, String>> data = getJsonDataToMap(
				System.getProperty("user.dir") + "\\src\\test\\java\\rahulshettyacademy\\data\\PurchaseOrder.json");

		return new Object[][] { { data.get(0) }, { data.get(1) } };

	}

}

/*
 * 2 sets Of Data HashMap<String, String> map = new HashMap<String, String>();
 * map.put("email", "maazshaikh95@gmail.com"); map.put("password",
 * "Manpower@007"); map.put("product", "ZARA COAT 3");
 * 
 * HashMap<String, String> map1 = new HashMap<String, String>();
 * map1.put("email", "shetty@gmail.com"); map1.put("password", "Iamking@000");
 * map1.put("product", "ADIDAS ORIGINAL");
 */
