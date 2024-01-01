import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * 1- Open the URL. 2- Click "WebOrder" button on top bar. 3- Enter valid username "Inar"
 * and password "Academy". 4- Navigate to the order page. 5- Select "SportsEquipment" from
 * Product dropdown. 6- Enter "1" as quantity number. 7- Enter "10" as discount
 * percentage. 8- Click on the "Calculate" button. 9- Enter "Inar Academy" as Name. 10-
 * Enter "1100 Congress Ave" as Street. 11- Enter "Austin" as City. 12- Enter "TX" State.
 * 13- Enter "78701" as Zip Code(number). 14- Enter "493822019284565" as Card Number. 15-
 * Enter "09/26" Expire Date(mm/yy format). 16- Click "Process"" button. 17- Verify the
 * Card Type error message is displayed.
 */
public class WO_009_OP_04 extends Hooks {

	@BeforeEach
	void setUp() {
		System.out.println("WO_009_OP_04 --> Verifying Order Placement Without Card Type Test started");

	}

	@Test
	void testOrderPlacementWithoutCardType() throws InterruptedException {
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
		select.selectByVisibleText("SportsEquipment");

		// Enter "1" as quantity number.
		WebElement quantityElement = driver.findElement(By.id("quantityInput"));
		quantityElement.sendKeys("1");

		// Enter "10" as discount percentage.
		WebElement discountElement = driver.findElement(By.id("discountInput"));
		discountElement.sendKeys("10");

		// Click on the "Calculate" button.
		WebElement calculateButtonElement = driver.findElement(By.xpath("//button[contains(text(),'Calculate')]"));
		calculateButtonElement.click();

		// Enter "Inar Academy" as Name.
		WebElement nameElement = driver.findElement(By.id("name"));
		nameElement.sendKeys("Inar");

		// Enter "1100 Congress Ave" as Street.
		WebElement streetElement = driver.findElement(By.id("street"));
		streetElement.sendKeys("1100 Congress Ave");

		// Enter "Austin" as City.
		WebElement cityElement = driver.findElement(By.id("city"));
		cityElement.sendKeys("Austin");

		// Enter "TX" State.
		WebElement stateElement = driver.findElement(By.id("state"));
		stateElement.sendKeys("TX");

		// Enter "92@#83" as Zip Code(number).
		WebElement zipcodeElement = driver.findElement(By.id("zip"));
		zipcodeElement.sendKeys("78701");

		// We need to scroll down to choose card type

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scroll(0,1200)");
		Thread.sleep(1000);

		// We intentionally left blank 'Card Type'

		// Enter "493822019284565" as Card Number.
		WebElement cardNumberElement = driver.findElement(By.id("cardNumber"));
		cardNumberElement.sendKeys("493822019284565");

		// Enter "09/26" Expire Date(mm/yy format).
		WebElement expDateElement = driver.findElement(By.id("expiryDate"));
		expDateElement.sendKeys("09/26");

		// Click "Process"" button.
		WebElement processButtonElement = driver.findElement(By.xpath("//div/button[@type='submit']"));
		processButtonElement.click();

		/**
		 * We expect to see 'Card type cannot be empty' error message And there must not
		 * be 'Product successfully added' message
		 */

		boolean firstCondition = false;

		try {
			WebElement cardTypeCannotBeEmptyErrorMessage = driver
				.findElement(By.xpath("//em[contains(text(),'Card type cannot be empty')]"));
			if (cardTypeCannotBeEmptyErrorMessage.isDisplayed()) {
				firstCondition = true;
			}
		}
		catch (NoSuchElementException e) {
			throw new NoSuchElementException("'Card type cannot be empty' error message is not found!");
		}

		boolean secondCondition = false;

		try {
			WebElement newOrderSuccessfullyAddedMessageElement = driver.findElement(By.cssSelector("div[role=alert]"));
			if (newOrderSuccessfullyAddedMessageElement.isDisplayed()) {
				System.out.println("there must not be 'Product successfully added' message\n"
						+ "-ERROR- Although card type did not selected the order addition message displayed");
			}
		}
		catch (NoSuchElementException e) {
			// As we expect there is no 'Product successfully added' message!
			secondCondition = true;
		}

		assertTrue(firstCondition && secondCondition,
				"Although card type selection is not happen, error message did not display!");

		System.out.println("In This Test Case : \n"
				+ "By not entering Card Type we test Order Placement under two sections:\n"
				+ "1-'New order has been successfully added' message exhibition \n"
				+ "2- Invalid Zipcode message exhibition are tested\n"
				+ "Expected Scenario = 1-'New order has been successfully added' must not be displayed\n"
				+ "                    2-'Card type cannot be empty' message must be displayed\n"
				+ "Actual Scenario = 1- As expected 'New order has been successfully added' message is not displayed\n"
				+ "                  2- As expected 'Card type cannot be empty' message is displayed");

	}

	@AfterEach
	void tearDown() {
		System.out.println("WO_009_OP_04 --> Verifying Order Placement Without Card Type Test finished");
		System.out.println("###########################################################");
	}

}
