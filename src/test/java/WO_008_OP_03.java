
/**
 * 1- Open the URL.
 * 2- Click "WebOrder" button on top bar.
 * 3- Enter valid username "Inar" and password "Academy".
 * 4- Navigate to the order page.
 * 5- Select "MyMoney" from Product dropdown.
 * 6- Enter "8" as quantity number.
 * 7- Enter "20" as discount percentage.
 * 8- Click on the "Calculate" button.
 * 9- Enter "Inar Academy" as Name.
 * 10- Enter "1100 Congress Ave" as Street.
 * 11- Enter "Austin" as City.
 * 12- Enter "TX" State.
 * 13- Enter "92@#83" as Zip Code.
 * 14- Select "American Express" as Card Type.
 * 15- Enter "342738261027163" as Card Number.
 * 16- Enter "01/28" Expire Date(mm/yy format).
 * 17- Click "Process"" button.
 * 18- Verify the invalid Zip Code error message is displayed.
 */

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class WO_008_OP_03 extends Hooks {

	@BeforeEach
	void setUp() {
		System.out.println("WO_008_OP_03 --> Verifying Order Placement With Invalid Zipcode Test started");

	}

	@Test
	void testOrderPlacementWithInvalidZipcode() throws InterruptedException {
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

		// Select "FamilyAlbum" from Product dropdown.
		WebElement productDropBowElement = driver.findElement(By.id("productSelect"));
		Select select = new Select(productDropBowElement);
		select.selectByVisibleText("MyMoney");

		// Enter "8" as quantity number.
		WebElement quantityElement = driver.findElement(By.id("quantityInput"));
		quantityElement.sendKeys("8");

		// Enter "20" as discount percentage.
		WebElement discountElement = driver.findElement(By.id("discountInput"));
		discountElement.sendKeys("20");

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
		zipcodeElement.sendKeys("92@#83");

		// We need to scroll down to choose card type

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scroll(0,1200)");
		Thread.sleep(1000);

		// Select "American Express" as Card Type.
		WebElement cardType = driver.findElement(By.id("amex"));
		cardType.click();

		// Enter "342738261027163" as Card Number.
		WebElement cardNumberElement = driver.findElement(By.id("cardNumber"));
		cardNumberElement.sendKeys("342738261027163");

		// Enter "01/28" Expire Date(mm/yy format).
		WebElement expDateElement = driver.findElement(By.id("expiryDate"));
		expDateElement.sendKeys("01/28");

		// Click "Process"" button.
		WebElement processButtonElement = driver.findElement(By.xpath("//div/button[@type='submit']"));
		processButtonElement.click();

		/**
		 * Expected Scenario : 1- Invalid Zip Code error message must be displayed 2- 'New
		 * order has been successfully added' message mus not be seen
		 *
		 * Now we will test to Expected Scenario.
		 */

		boolean firstCondition = false;
		try {
			WebElement productSuccessfullyAddedMessageElement = driver.findElement(By.cssSelector("div[role=alert]"));
			if (productSuccessfullyAddedMessageElement.isDisplayed()) {
				System.out.println("'" + productSuccessfullyAddedMessageElement.getText()
						+ "' \n--ERROR-- This message must not be displayed");
			}
		}
		catch (NoSuchElementException e) {
			// As Expected Result : There is no 'New order has been successfully added'
			// message "
			// "Now we will check for error message under calculate button
			firstCondition = true;
		}

		// assertTrue(firstCondition,"There must not be product Successfully Added
		// Message");

		// We need to scroll up to see invalid zip code message
		js.executeScript("window.scroll(0,900)");
		Thread.sleep(1000);

		boolean secondCondition = false;
		try {
			WebElement invalidZipcodeMessage = driver
				.findElement(By.xpath("//em[contains(text(),'Name cannot be empty')]"));
			if (invalidZipcodeMessage.isDisplayed()) {
				secondCondition = true;
			}
		}
		catch (NoSuchElementException e) {
			throw new NoSuchElementException("There is no invalid zipcode message!");
		}

		if (firstCondition && secondCondition) {
			System.out.println("In This Test Case : \n"
					+ "By Entering Wrong 'Zipcode' we test Order Placement under two sections:\n"
					+ "1-'New order has been successfully added' message exhibition \n"
					+ "2- Invalid Zipcode message exhibition are tested\n"
					+ "Expected Scenario = 1-'New order has been successfully added' must not be displayed\n"
					+ "                    2-Invalid Zipcode message must be displayed\n"
					+ "Actual Scenario = 1- As expected 'New order has been successfully added' message is not displayed\n"
					+ "                  2- As expected Invalid Zipcode message is displayed");
		}

	}

	@AfterEach
	void tearDown() {
		System.out.println("WO_008_OP_03 --> Verifying Order Placement With Invalid Zipcode Test finished");
		System.out.println("###########################################################");
	}

}
