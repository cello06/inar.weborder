import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class WO_014_OP_05 extends Hooks {

	/**
	 * 1. Open the URL. 2. Click the "WebOrder" button on the top bar. 3. Enter valid
	 * username "Inar" and password "Academy". 4. Navigate to the order page. 5. Select
	 * "TechGadget" from Product dropdown. 6. Enter "10" as a quantity number. 7. Enter
	 * "25" as a discount percentage. 8. Click on the "Calculate" button. 9. Enter
	 * "Celalettin Aktürk" as Name. 10. Enter "Orhan Kemal" as Street. 11. Enter
	 * "Altındağ" as City. 12. Enter "Ankara" State. 13. Enter "60800" as Zip
	 * Code(number). 14. Select "Visa" as Card Type. 15. Enter "3938281746192845" as a
	 * Card Number. 16. Enter "11/28" Expire Date(mm/yy format). 17. Click the "Process""
	 * button. 18. Verify the confirmation message is not displayed. 19. Verify ‘Card
	 * number is not valid’ error message is displayed.
	 */
	@BeforeEach
	void setUp() {
		System.out.println("WO_014_OP_05 --> Verifying y Order Placement with wrong card number Test started");

	}

	@Test
	void testOrderPlacementWithWrongCardNumber() throws InterruptedException {
		// Click "WebOrder" button on top bar.
		WebElement webOrderButton = driver.findElement(By.xpath("//a[@href='/weborder']"));
		webOrderButton.click();

		// Enter valid username "Inar" and password "Academy".
		WebElement usernameElement = driver.findElement(By.id("login-username-input"));
		WebElement passwordElement = driver.findElement(By.id("login-password-input"));

		usernameElement.sendKeys("Inar");
		passwordElement.sendKeys("Academy");

		// Navigate to the order page.
		WebElement loginButtonElement = driver.findElement(By.id("login-button"));
		loginButtonElement.click();

		WebElement orderButtonElement = driver.findElement(By.xpath("//a[@href='/weborder/order']"));
		orderButtonElement.click();

		// Select "SportsEquipment" from Product dropdown.
		WebElement productDropBowElement = driver.findElement(By.id("productSelect"));
		Select select = new Select(productDropBowElement);
		select.selectByVisibleText("TechGadget");

		// Enter "10" as quantity number.
		WebElement quantityElement = driver.findElement(By.id("quantityInput"));
		quantityElement.sendKeys("10");

		// Enter "25" as discount percentage.
		WebElement discountElement = driver.findElement(By.id("discountInput"));
		discountElement.sendKeys("25");

		// Click on the "Calculate" button.
		WebElement calculateButtonElement = driver.findElement(By.xpath("//button[contains(text(),'Calculate')]"));
		calculateButtonElement.click();

		// Enter "Celalettin Aktürk" as Name.
		WebElement nameElement = driver.findElement(By.id("name"));
		nameElement.sendKeys("Celalettin Aktürk");

		// Enter "Orhan Kemal" as Street.
		WebElement streetElement = driver.findElement(By.id("street"));
		streetElement.sendKeys("Orhan Kemal");

		// Enter "Altındağ" as City.
		WebElement cityElement = driver.findElement(By.id("city"));
		cityElement.sendKeys("Altındağ");

		// Enter "Ankara" State.
		WebElement stateElement = driver.findElement(By.id("state"));
		stateElement.sendKeys("Ankara");

		// Enter "60800" as Zip Code(number).
		WebElement zipcodeElement = driver.findElement(By.id("zip"));
		zipcodeElement.sendKeys("60800");

		// We need to scroll down to choose card type

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scroll(0,1200)");
		Thread.sleep(1000);

		// Select "Visa" as Card Type.
		WebElement cardType = driver.findElement(By.id("visa"));
		cardType.click();

		// Enter "3938281746192845" as Card Number.
		WebElement cardNumberElement = driver.findElement(By.id("cardNumber"));
		cardNumberElement.sendKeys("3938281746192845");

		// Enter "11/28" Expire Date(mm/yy format).
		WebElement expDateElement = driver.findElement(By.id("expiryDate"));
		expDateElement.sendKeys("11/28");

		// Click "Process"" button.
		WebElement processButtonElement = driver.findElement(By.xpath("//div/button[@type='submit']"));
		processButtonElement.click();

		/**
		 * We expect to see 'Card number is not valid' error message And there must not be
		 * 'Product successfully added' message
		 */

		boolean firstCondition = false;

		try {
			WebElement cardTypeCannotBeEmptyErrorMessage = driver
				.findElement(By.xpath("//em[contains(text(),'Card number is not valid')]"));
			if (cardTypeCannotBeEmptyErrorMessage.isDisplayed()) {
				firstCondition = true;
			}
		}
		catch (NoSuchElementException e) {
			throw new NoSuchElementException(
					"'Card number is not valid' error message is not found! Although invalid card number input");
		}

		boolean secondCondition = false;

		try {
			WebElement newOrderSuccessfullyAddedMessageElement = driver.findElement(By.cssSelector("div[role=alert]"));
			if (newOrderSuccessfullyAddedMessageElement.isDisplayed()) {
				System.out.println("there must not be 'Product successfully added' message\n"
						+ "-ERROR- Although card type is not valid for 'visa' card type the order addition message displayed");
			}
		}
		catch (NoSuchElementException e) {
			// As we expect there is no 'Product successfully added' message!
			secondCondition = true;
		}

		assertTrue(firstCondition && secondCondition,
				"Although card number is not valid for card type, error message did not display!");

		System.out.println("In This Test Case : \n"
				+ "By entering invalid card number we test Order Placement under two sections:\n"
				+ "1-'New order has been successfully added' message exhibition \n"
				+ "2- 'Card number is not valid' message exhibition are tested\n"
				+ "Expected Scenario = 1-'New order has been successfully added' must not be displayed\n"
				+ "                    2-'Card number is not valid' message must be displayed\n"
				+ "Actual Scenario = 1- As expected 'New order has been successfully added' message is not displayed\n"
				+ "                  2- As expected 'Card number is not valid' message is displayed");
	}

	@AfterEach
	void tearDown() {
		System.out.println("WO_014_OP_05 --> Verifying y Order Placement with wrong card number Test finished");
		System.out.println("###########################################################");
	}

}
