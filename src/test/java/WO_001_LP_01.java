import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class WO_001_LP_01 extends Hooks {

	@BeforeEach
	void setUp() {
		System.out.println("WO_001_LP_01 --> Verifying Login Functionality Test started");
	}

	@Test
	void testLoginFunctionality() throws InterruptedException {
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

		WebElement welcomeMessage = driver.findElement(By.id("welcome-heading"));
		String actualWelcomeMessage = welcomeMessage.getText();

		/**
		 * If we can access to welcome message it means that we successfully log in to new
		 * page.
		 */
		System.out
			.println("User enter valid credentials \n" + "Username(input) : 'Inar'\n" + "Password(input) : 'Academy'\n"
					+ "Expected Result : User successfully login to page and see welcome message.");
		System.out.println(
				"Actual Result : User successfully log in to new page\n" + "Welcome Message : " + actualWelcomeMessage);

	}

	@AfterEach
	void tearDown() {
		System.out.println("WO_001_LP_01 --> Verifying Login Functionality Test finished");
		System.out.println("###########################################################");
	}

}
