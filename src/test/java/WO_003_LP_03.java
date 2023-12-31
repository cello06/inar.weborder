import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

public class WO_003_LP_03 extends Hooks {

	@BeforeEach
	void setUp() {
		System.out.println("WO_003_LP_03 --> Verifying Logout Functionality Test started");
	}

	@Test
	void testLogoutFunctionality() throws InterruptedException {
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

		/** Now the user is in the Weborder Module and click 'Log out' button */

		WebElement logoutButton = driver.findElement(By.id("logout-button"));
		logoutButton.click();

		/**
		 * Now user should see Login page. And can click any other button If clicking to a
		 * specific button in login page is successfully handled then we can understand
		 * that logout process is successfully handled. If it is not the case it means
		 * there is a bug.
		 */

		Thread.sleep(1000);
		System.out.println("After Logout to test logout process we click Weborder module again\n"
				+ "Expected Result : Clicking to weborder button must be successful");
		try {
			WebElement webOrderButton2 = driver.findElement(By.xpath("//a[@href='/weborder']"));
			webOrderButton2.click();
			System.out.println("Actual Result : Successfully click to weborder button.");

		}
		catch (NoSuchElementException e) {
			System.out.println("Actual Result : We take NO SUCH ELEMENT EXCEPTION");
			throw new NoSuchElementException("Intentionally throw exception!");
		}

	}

	@AfterEach
	void tearDown() {
		System.out.println("WO_003_LP_03 --> Verifying Logout Functionality Test finished");
		System.out.println("###########################################################");
	}

}
