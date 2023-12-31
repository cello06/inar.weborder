import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WO_005_CF_02 extends Hooks {

	@BeforeEach
	void setUp() {
		System.out
			.println("WO_004_CF_01 --> Verifying Calculate Functionality in Order Page-(INVALID INPUT) Test started");
	}

	@Test
	void testCalculateFunctionalityWithInvalidInput() throws InterruptedException {
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
		 * We enter predefined values for testing Product : ScreenSaver Quantity Number :
		 * intentionally left blank Discount : 20
		 */

		WebElement productDropBox = driver.findElement(By.id("productSelect"));
		Select select = new Select(productDropBox);
		select.selectByVisibleText("ScreenSaver");

		WebElement discountBox = driver.findElement(By.id("discountInput"));
		discountBox.sendKeys("20");

		/**
		 * After entering predefined values we simply click calculate button
		 */

		WebElement calculateButton = driver.findElement(By.xpath("(//button[@type='submit'])[1]"));
		calculateButton.click();
		Thread.sleep(2000);

		/**
		 * We intentionally left quantity blank So total box value must be zero and "Field
		 * 'Quantity' must be greater than zero" error message must be displayed!
		 */

		WebElement totalResultElement = driver.findElement(By.id("totalInput"));
		String totalResult = totalResultElement.getAttribute("value");

		assertEquals(totalResult, "", "The total box must have been empty!");

		WebElement errorMessageElement = driver.findElement(By.id("quantityValidateError"));
		String errorMessage = errorMessageElement.getText();

		System.out.println("Testing Calculate Functionality in Order Page with INVALID INPUT: \n"
				+ "Product(input)  : 'ScreenSaver' \n" + "Quantity(input) : 'Intentionally left blank'\n"
				+ "Unit Price      : '20'\n" + "Discount(input) : '20'\n"
				+ "Expected Result : Field 'Quantity' must be greater than zero message must be displayed\n"
				+ "                  And Total box must be empty." + "\nActual Result   : '" + errorMessage
				+ "' error message is displayed \n" + "                    And Total box is empty.");

	}

	@AfterEach
	void tearDown() {
		System.out
			.println("WO_004_CF_01 --> Verifying Calculate Functionality in Order Page-(INVALID INPUT) Test finished");
		System.out.println("###########################################################");
	}

}
