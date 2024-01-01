import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WO_006_OP_01 extends Hooks {

	@BeforeEach
	void setUp() {
		System.out.println("WO_006_OP_01 --> Verifying Order Placement Test started");
	}

	@Test
	void testOrderPlacement() throws InterruptedException {

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
		 * We enter valid values expected from user to create Order.
		 */

		WebElement orderButton = driver.findElement(By.xpath("//a[@href = '/weborder/order']"));
		orderButton.click();
		/**
		 * We enter predefined values for Product Information : MyMoney Quantity Number :
		 * 8 Discount : 20
		 */
		// OUR OWN CREDENTIALS
		List<String> ownCredentials = new ArrayList<>();
		// Adding product select to our own list
		ownCredentials.add("MyMoney");
		// Adding quantity to our own list
		ownCredentials.add("8");
		// Adding date to our list
		ownCredentials.add(DateTimeFormatter.ofPattern("MM/dd/yy").format(LocalDate.now()));
		// Adding street to our list
		ownCredentials.add("1100 Congress Ave");
		// Adding city to our list
		ownCredentials.add("Austin");
		// Adding state to our list
		ownCredentials.add("TX");
		// Adding zip code to our list
		ownCredentials.add("78701");
		// Adding card type
		ownCredentials.add("Visa");
		// Adding card number
		ownCredentials.add("4938281746192845");
		// Adding expire date
		ownCredentials.add("11/28");

		WebElement productDropBox = driver.findElement(By.id("productSelect"));
		Select select = new Select(productDropBox);
		select.selectByVisibleText("MyMoney");

		WebElement quantityNumberBox = driver.findElement(By.id("quantityInput"));
		quantityNumberBox.sendKeys("8");

		WebElement discountBox = driver.findElement(By.id("discountInput"));
		discountBox.sendKeys("20");

		/**
		 * After entering predefined values we simply click calculate button
		 */

		WebElement calculateButton = driver.findElement(By.xpath("(//button[@type='submit'])[1]"));
		calculateButton.click();

		/**
		 * We enter predefined values for Customer Information : Name : Inar Academy
		 * Street : 1100 Congress Ave City : Austin State :TX Zipcode : 78701
		 */

		WebElement nameElement = driver.findElement(By.id("name"));
		nameElement.sendKeys("Inar Academy");

		WebElement streetElement = driver.findElement(By.id("street"));
		streetElement.sendKeys("1100 Congress Ave");

		WebElement cityElement = driver.findElement(By.id("city"));
		cityElement.sendKeys("Austin");

		WebElement stateElement = driver.findElement(By.id("state"));
		stateElement.sendKeys("TX");

		WebElement zipcodeElement = driver.findElement(By.id("zip"));
		zipcodeElement.sendKeys("78701");

		/**
		 * We enter predefined Payment Information : Card Type : Visa Card Number :
		 * 4938281746192845 Expire Date : 11/28
		 */

		WebElement cardTypeRadioButtonElement = driver.findElement(By.id("visa"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView();", cardTypeRadioButtonElement);
		Thread.sleep(1000);
		// I used this part to scroll down on page to access the other element
		cardTypeRadioButtonElement.click();

		WebElement cardNumberElement = driver.findElement(By.id("cardNumber"));
		cardNumberElement.sendKeys("4938281746192845");

		WebElement expireDateElement = driver.findElement(By.id("expiryDate"));
		expireDateElement.sendKeys("11/28");

		/**
		 * After entering predefined VALID values now click PROCESS button
		 */

		WebElement processButtonElement = driver.findElement(By.xpath("(//button[@type='submit'])[2]"));
		processButtonElement.click();
		Thread.sleep(4000);

		WebElement successfullyAdditionMessageElement = driver.findElement(By.xpath("//div[@role='alert']"));
		System.out.println(successfullyAdditionMessageElement.getText());
		// After showing successfully addition message we close message

		WebElement closeMessageElement = driver.findElement(By.xpath("(//div/button[@type='button'])[2]"));
		closeMessageElement.click();
		js.executeScript("window.scrollTo(0, 0);");
		Thread.sleep(1000);

		/**
		 * Now to check if order has been created successfully we navigate to all orders
		 * page
		 */

		WebElement allOrdersPageElement = driver.findElement(By.xpath("//div/a[@href='/weborder/view-orders']"));
		allOrdersPageElement.click();

		List<WebElement> columnsOfLastRow = driver.findElements(By.xpath("//tbody/tr[1]"));

		for (int i = 0; i < columnsOfLastRow.size() - 1; i++) {

			assertEquals(columnsOfLastRow.get(i + 1).getText(), ownCredentials.get(i),
					"Product is not successfully created!");

		}

	}

	@AfterEach
	void tearDown() {
		System.out.println("WO_006_OP_01 --> Verifying Order Placement Test finished");
		System.out.println("###########################################################");
	}

}
