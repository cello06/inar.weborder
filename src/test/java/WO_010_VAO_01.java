
/**1- Open the URL
 * 2- Click "WebOrder" button on top bar.
 * 3- Enter valid username "Inar" and password "Academy".
 * 4- Navigate to the view all order page.
 * 5- Click "Add More Data" "4" times.
 * 6- Click "Check All" button.
 * 7- Verify all orders selected.
 */
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class WO_010_VAO_01 extends Hooks {

	@BeforeEach
	void setUp() {
		System.out.println("WO_010_VAO_01 --> Verifying Check All Functionality in View All Order Page Test started");

	}

	@Test
	void testCheckAllFunctionalityInViewAllOrderPage() throws InterruptedException {
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

		// Click "Add More Data" "4" times.
		WebElement addMoreDataElement = driver.findElement(By.xpath("//div/button[contains(text(),'Add More Data')]"));
		for (int i = 0; i < 4; i++) {
			addMoreDataElement.click();
		}

		// Click "Check All" button.
		WebElement checkAllButtonElement = driver.findElement(By.xpath("//div/button[contains(text(),'Check All')]"));
		checkAllButtonElement.click();

		Thread.sleep(2000);

		// Get all Rows Element in Table
		List<WebElement> firstColumnElements = driver.findElements(By.className("form-check-input"));

		boolean testPassCondition = true;
		// Check First Columns if they are checked
		for (int i = 0; i < firstColumnElements.size(); i++) {
			if (!firstColumnElements.get(i).isSelected()) {

				testPassCondition = false;
				break;
			}
		}

		assertTrue(testPassCondition, "Check All Button Does not Function properly!");
		System.out.println("In This Test Case : \n" + "Check All Button Functionality Tested \n"
				+ "*Expected Result = All CheckBox Button in View All Orders Page are Checked\n"
				+ "after clicking 'Check All' Button.\n"
				+ "*Actual Result = As Expected All CheckBox Button in View All Orders Page"
				+ "\n checked after clicking 'Check All' Button");

	}

	@AfterEach
	void tearDown() {
		System.out.println("WO_010_VAO_01 --> Verifying Check All Functionality in View All Order Page Test finished");
		System.out.println("###########################################################");
	}

}
