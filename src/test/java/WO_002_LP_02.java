import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class WO_002_LP_02 extends Hooks {

	@BeforeEach
	void setUp() {
		System.out.println("WO_002_LP_02 --> Verifying Login Failure Test started");
	}

	@Test
	void testLoginFailure() throws InterruptedException {
		WebElement webOrderButton = driver.findElement(By.xpath("//a[@href='/weborder']"));
		webOrderButton.click();
		/**
		 * We clicked to Weborder module Now we will enter invalid username(Cello) And
		 * invalid password(Zero)
		 */
		WebElement userNameElement = driver.findElement(By.cssSelector("#login-username-input"));
		userNameElement.sendKeys("Cello");

		WebElement passwordElement = driver.findElement(By.id("login-password-input"));
		passwordElement.sendKeys("Zero");

		/**
		 * After entering invalid credentials we simply click 'Login' button
		 */

		WebElement loginButton = driver.findElement(By.id("login-button"));
		loginButton.click();
		Thread.sleep(4000);

		WebElement errorMessageForInvalidUsername = driver.findElement(By.id("username-error-alert"));
		String invalidUsernameMessage = errorMessageForInvalidUsername.getText();

		WebElement errorMessageForInvalidPassword = driver.findElement(By.id("password-error-alert"));
		String invalidPasswordMessage = errorMessageForInvalidPassword.getText();

		System.out.println(
				"User enters invalid credentials : \n" + "Username(input) : 'Cello'\n" + "Password(input) : 'Zero'\n"
						+ "Expected Result : Error message displayed for username and password\n" + "Actual Result : '"
						+ invalidUsernameMessage + "' , '" + invalidPasswordMessage + "'  messages are displayed.");

	}

	@AfterEach
	void tearDown() {
		System.out.println("WO_002_LP_02 --> Verifying Login Failure Test finished");
		System.out.println("###########################################################");
	}

}
