import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * 1- Open the URL 2- Click "WebOrder" button on top bar. 3- Enter valid username "Inar"
 * and password "Academy". 4- Navigate to the view all order page. 5- Click "Add More
 * Data" "6" times. 6- Click "Check All" button. 7- Verify all orders selected. 8- Click
 * "Uncheck All" button. 9- Verify all orders are unselected.
 */
public class WO_011_VAO_02 extends Hooks {

	@BeforeEach
	void setUp() {
		System.out.println("WO_011_VAO_02 --> Verifying Uncheck All Functionality in View All Order Page Test started");

	}

	@Test
	void testUncheckAllFunctionalityInViewAllOrderPage() {
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

		// Click "Add More Data" "6" times.
		WebElement addMoreDataElement = driver.findElement(By.xpath("//div/button[contains(text(),'Add More Data')]"));
		for (int i = 0; i < 6; i++) {
			addMoreDataElement.click();
		}
		// Click "Check All" button.
		WebElement checkAllButtonElement = driver.findElement(By.xpath("//div/button[contains(text(),'Check All')]"));
		checkAllButtonElement.click();

		// Get Check Boxes Column in Table
		List<WebElement> firstColumnElements = driver.findElements(By.className("form-check-input"));

		boolean verifyAllOrdersSelected = true;
		// Check First Columns if they are checked
		for (int i = 0; i < firstColumnElements.size(); i++) {
			if (!firstColumnElements.get(i).isSelected()) {

				verifyAllOrdersSelected = false;
				break;
			}
		}

		assertTrue(verifyAllOrdersSelected, "Check All Button Does not Function properly!");

		// Click "Uncheck All" button.
		WebElement uncheckAllButtonElement = driver
			.findElement(By.xpath("//div/button[contains(text(),'Uncheck All')]"));
		uncheckAllButtonElement.click();

		// Get Check Boxes Column in Table
		firstColumnElements = driver.findElements(By.className("form-check-input"));

		boolean verifyAllOrdersNotSelected = true;
		// Check First Columns if they are not checked
		for (int i = 0; i < firstColumnElements.size(); i++) {
			if (firstColumnElements.get(i).isSelected()) {

				verifyAllOrdersNotSelected = false;
				break;
			}
		}

		assertTrue(verifyAllOrdersNotSelected, "Uncheck All Button Does not Function properly!");
		System.out.println("In This Test Case : \n" + "Uncheck All Button Functionality Tested \n"
				+ "*Expected Result = All CheckBox Button in View All Orders Page are Unchecked\n"
				+ "after clicking 'Check All' Button.\n"
				+ "*Actual Result = As Expected All CheckBox Button in View All Orders Page"
				+ "\n unchecked after clicking 'Uncheck All' Button");

	}

	@AfterEach
	void tearDown() {
		System.out.println("WO_010_VAO_01 --> Verifying Check All Functionality in View All Order Page Test finished");
		System.out.println("###########################################################");
	}

}
