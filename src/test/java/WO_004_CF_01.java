import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WO_004_CF_01 extends Hooks {

	@BeforeEach
	void setUp() {
		System.out.println("WO_004_CF_01 --> Verifying Calculate Functionality in Order Page Test started");
	}

	@Test
	void testCalculateFunctionality() throws InterruptedException {
		WebElement webOrderButton = driver.findElement(By.xpath("//a[@href='/weborder']"));
		webOrderButton.click();
		/**
		 * We clicked to Weborder module Now we will enter valid username(Inar) And valid
		 * password(Academy)
		 */
		WebElement userNameElement = driver.findElement(By.cssSelector("#login-username-input"));
		userNameElement.sendKeys("Inar");

		WebElement passwordElement = driver.findElement(By.id("login-password-input"));
		passwordElement.sendKeys("Academy");

		/**
		 * After entering valid credentials we simply click 'Login' button
		 */

		WebElement loginButton = driver.findElement(By.id("login-button"));
		loginButton.click();
		Thread.sleep(4000);

		/**
		 * Now we are in Weborder module We will click order button to check calculate
		 * functionality
		 */

		WebElement orderButton = driver.findElement(By.xpath("//a[@href = '/weborder/order']"));
		orderButton.click();

		/**
		 * We enter predefined values for testing Product : HomeDecor Quantity Number : 5
		 * Discount : 15
		 */

		WebElement productDropBox = driver.findElement(By.id("productSelect"));
		Select select = new Select(productDropBox);
		select.selectByVisibleText("HomeDecor");

		WebElement quantityNumberBox = driver.findElement(By.id("quantityInput"));
		quantityNumberBox.sendKeys("5");

		WebElement discountBox = driver.findElement(By.id("discountInput"));
		discountBox.sendKeys("15");

		/**
		 * After entering predefined values we simply click calculate button
		 */

		WebElement calculateButton = driver.findElement(By.xpath("(//button[@type='submit'])[1]"));
		calculateButton.click();
		Thread.sleep(2000);

		/**
		 * Now we check if total box is showing true result!
		 */

		WebElement totalResult = driver.findElement(By.id("totalInput"));
		double actualResult = Double.parseDouble(totalResult.getAttribute("value"));
		// Unit price = 150, Quantity = 5, Discount = 15
		double expectedResult = Math.round((150 - (150 * 0.15)) * 5);
		System.out.println("Testing Calculate Functionality in Order Page: \n" + "Product(input)  : 'HomeDecor' \n"
				+ "Quantity(input) : '5'\n" + "Unit Price      : '150'\n" + "Discount(input) : '15'\n"
				+ "Expected Result : " + expectedResult + "\nActual Result   : " + actualResult);
		assertEquals(actualResult, expectedResult, "The message displayed but result is not true!");

	}

	@AfterEach
	void tearDown() {
		System.out.println("WO_004_CF_01 --> Verifying Calculate Functionality in Order Page Test finished");
		System.out.println("###########################################################");
	}

}
