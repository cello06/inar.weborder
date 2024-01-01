
/**1- Open the URL.
 * 2- Click "WebOrder" button on top bar.
 * 3- Enter valid username "Inar" and password "Academy".
 * 4- Navigate to the order page.
 * 5- Select "FamilyAlbum" from Product dropdown.
 * 6- Enter "3" as quantity number.
 * 7- Enter "17" as discount percentage.
 * 8- Enter "Inar Academy" as Name.
 * 9- Enter "1100 Congress Ave" as Street.
 * 10- Enter "Austin" as City.
 * 11- Enter "TX" State.
 * 12- Enter "78701" as Zip Code(number).
 * 13- Select "Mastercard" as Card Type.
 * 14- Enter "5162738261027163" as Card Number.
 * 15- Enter "11/28" Expire Date(mm/yy format).
 * 16- Click "Process"" button.
 * 17- Verify the invalid Product Information error message is displayed.*/

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class WO_007_0P_02 extends Hooks {

	@BeforeEach
	void setUp() {
		System.out.println("WO_007_OP_02 --> Verifying Order Placement Without Calculation Test started");
	}

	@Test
	void testOrderPlacementWithoutCalculation() throws InterruptedException {
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
		select.selectByVisibleText("FamilyAlbum");

		// Enter "3" as quantity number.
		WebElement quantityElement = driver.findElement(By.id("quantityInput"));
		quantityElement.sendKeys("3");

		// Enter "17" as discount percentage.
		WebElement discountElement = driver.findElement(By.id("discountInput"));
		discountElement.sendKeys("17");

		/** Intentionally not click to calculate button to test it! */

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

		// Enter "78701" as Zip Code(number).
		WebElement zipcodeElement = driver.findElement(By.id("zip"));
		zipcodeElement.sendKeys("78701");

		// We need to scroll down to choose card type

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scroll(0,1200)");
		Thread.sleep(1000);

		// Select "Mastercard" as Card Type.
		WebElement cardType = driver.findElement(By.id("mastercard"));
		cardType.click();

		// Enter "5162738261027163" as Card Number.
		WebElement cardNumberElement = driver.findElement(By.id("cardNumber"));
		cardNumberElement.sendKeys("5162738261027163");

		// Enter "11/28" Expire Date(mm/yy format).
		WebElement expDateElement = driver.findElement(By.id("expiryDate"));
		expDateElement.sendKeys("11/28");

		// Click "Process"" button.
		WebElement processButtonElement = driver.findElement(By.xpath("//div/button[@type='submit']"));
		processButtonElement.click();

		/**
		 * We expect not to see 'New order has been successfully added' message! So first
		 * we check this message Then check error message under calculate button
		 */

		boolean passingToSecondTestCondition = false;
		try {
			WebElement productSuccessfullyAddedMessageElement = driver.findElement(By.cssSelector("div[role=alert]"));
			System.out.println(
					productSuccessfullyAddedMessageElement.getText() + " --ERROR-- This message must not be displayed");
		}
		catch (NoSuchElementException e) {
			// As Expected Result : There is no 'New order has been successfully added'
			// message "
			// "Now we will check for error message under calculate button

			passingToSecondTestCondition = true;
		}

		// We need to scroll up the page
		js.executeScript("window.scroll(0,500)");
		Thread.sleep(1000);
		boolean isTestPassed = false;
		// Find error message
		WebElement errorMessage = driver.findElement(By.xpath("//em[contains(text(),'Fix errors')]"));
		if (errorMessage.isDisplayed() && passingToSecondTestCondition) {
			isTestPassed = true;
		}

		assertTrue(isTestPassed,
				"Test Fail! \n" + "Expected Result : 1-) We must see Error Message about order part\n"
						+ "                  2-) We must not see successfully added message\n"
						+ "Actual Result   : Error message can not be found!");

		System.out.println("Verifying Order Placement Without Calculation Test \n"
				+ "Expected Result : 1-) We must see Error Message about order part\n"
				+ "                  2-) We must not see successfully added message\n"
				+ "Actual Result : 1-) As Expected we saw Error Message about order part\n"
				+ "                2-) As Expected we did not see successfully added message");

	}

	@AfterEach
	void tearDown() {
		System.out.println("WO_006_OP_01 --> Verifying Order Placement Test finished");
		System.out.println("###########################################################");
	}

}
