import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * 1. Open the URL 2. Click the "WebOrder" button on the top bar. 3. Enter valid username
 * "Inar" and password "Academy". 4. Navigate to the "View All Order" page. 5. Click "Add
 * More Data" 8 times. 6. Click the checkboxes for the 1st, 3rd, and 5th orders. 7. Click
 * the "Delete" button. 8. Verify that the orders are deleted.
 */
public class WO_012_VAO_03 extends Hooks {

	@BeforeEach
	void setUp() {
		System.out.println("WO_012_VAO_03 --> Verifying Delete Functionality in View All Order Page Test started");

	}

	@Test
	void testDeleteFunctionalityInViewAllOrderPage() throws InterruptedException {
		// Click "WebOrder" button on top bar.
		WebElement webOrderButton = driver.findElement(By.xpath("//a[@href='/weborder']"));
		webOrderButton.click();

		// Enter valid username "Inar" and password "Academy".
		WebElement usernameElement = driver.findElement(By.id("login-username-input"));
		WebElement passwordElement = driver.findElement(By.id("login-password-input"));

		usernameElement.sendKeys("Inar");
		passwordElement.sendKeys("Academy");

		// Navigate to the view all order page.
		WebElement loginButtonElement = driver.findElement(By.id("login-button"));
		loginButtonElement.click();

		WebElement viewAllOrdersButtonElement = driver.findElement(By.xpath("//a[@href='/weborder/view-orders']"));
		viewAllOrdersButtonElement.click();

		// Click "Add More Data" "8" times.
		WebElement addMoreDataElement = driver.findElement(By.xpath("//div/button[contains(text(),'Add More Data')]"));
		for (int i = 0; i < 8; i++) {
			addMoreDataElement.click();
		}
		// Scroll Down for clicking
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scroll(0,400)");
		Thread.sleep(1000);

		// Click the checkboxes for the 1st, 3rd, and 5th orders.
		WebElement firstCheckBoxElement = driver.findElement(By.xpath("//tbody/tr[1]/td[1]/div[1]/input[1]"));
		WebElement thirdCheckBoxElement = driver.findElement(By.xpath("//tbody/tr[3]/td[1]/div[1]/input[1]"));
		WebElement fifthCheckBoxElement = driver.findElement(By.xpath("//tbody/tr[5]/td[1]/div[1]/input[1]"));

		firstCheckBoxElement.click();
		thirdCheckBoxElement.click();
		fifthCheckBoxElement.click();

		// We need to register elements in 1st, 3rd and 5th elements to check if they
		// deleted
		String firstRowElement = driver.findElement(By.xpath("//tbody/tr[1]")).getText();
		String thirdRowElement = driver.findElement(By.xpath("//tbody/tr[3]")).getText();
		String fifthRowElement = driver.findElement(By.xpath("//tbody/tr[5]")).getText();

		// Click the "Delete" button.
		WebElement deleteButtonElement = driver.findElement(By.xpath("//button[contains(text(),'Delete')]"));
		deleteButtonElement.click();

		Thread.sleep(3000);

		// Verify that the orders are deleted.
		List<WebElement> rowsInTheTable = driver.findElements(By.xpath("//tbody/tr"));

		boolean testPassCondition = true;

		for (int i = 0; i < rowsInTheTable.size(); i++) {
			if (rowsInTheTable.get(i).getText().equals(firstRowElement)) {
				testPassCondition = false;
				break;
			}
			if (rowsInTheTable.get(i).getText().equals(thirdRowElement)) {
				testPassCondition = false;
				break;
			}
			if (rowsInTheTable.get(i).getText().equals(fifthRowElement)) {
				testPassCondition = false;
				break;
			}

		}

		assertTrue(testPassCondition, "Specified orders are not deleted as expected");
		System.out.println("In this Test Case :\n" + "We Checked Delete Button Functionality\n"
				+ "First check 3 order and delete them and control if orders deleted properly \n"
				+ "Expected Result : Specified products deleted properly\n"
				+ "Actual Result   : As expected Specified products deleted properly");

	}

	@AfterEach
	void tearDown() {
		System.out.println("WO_012_VAO_03 --> Verifying Delete Functionality in View All Order Page Test finished");
		System.out.println("###########################################################");
	}

}
