import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WO_013_VAP_01 extends Hooks {

	/**
	 * 1. Open the URL 2. Click the "WebOrder" button on the top bar. 3. Enter valid
	 * username "Inar" and password "Academy". 4. Click view all product button 5. Verify
	 * that the button navigate to right page
	 */
	@BeforeEach
	void setUp() {
		System.out.println("WO_013_VAP_01 --> Verifying View All Product button Functionality Test started");

	}

	@Test
	void testViewAllProductButtonFunctionality() throws InterruptedException {
		// Click "WebOrder" button on top bar.
		WebElement webOrderButton = driver.findElement(By.xpath("//a[@href='/weborder']"));
		webOrderButton.click();

		// Enter valid username "Inar" and password "Academy".
		WebElement usernameElement = driver.findElement(By.id("login-username-input"));
		WebElement passwordElement = driver.findElement(By.id("login-password-input"));

		usernameElement.sendKeys("Inar");
		passwordElement.sendKeys("Academy");

		WebElement loginButtonElement = driver.findElement(By.id("login-button"));
		loginButtonElement.click();

		// Click view all product button
		WebElement viewAllProductButtonElement = driver.findElement(By.cssSelector("#view-products-tab > a"));
		viewAllProductButtonElement.click();

		// Verify that the button navigate to right page
		String actualURL = driver.getCurrentUrl();
		String expectedURL = "https://InarAcademy:Fk160621.@test.inar-academy.com/weborder/view-products";

		assertEquals(actualURL, expectedURL, "The does not navigate to the right page.");
		System.out.println("This Test Case is testing view all order button : \n" + "Expected Url = " + expectedURL
				+ "\nActual URL = " + actualURL);

	}

	@AfterEach
	void tearDown() {
		System.out.println("WO_013_VAP_01 --> Verifying View All Product button Functionality Test finished");
		System.out.println("###########################################################");
	}

}
