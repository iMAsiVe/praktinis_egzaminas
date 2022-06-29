import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import drivers.DriverSingleton;
import pages.LogInPage;
import utils.Constants;
import utils.FrameworkProperties;

public class NewUserRegistration {
	static FrameworkProperties frameworkProperties;
	static WebDriver driver;
	static LogInPage logInPage;

	@BeforeClass
	public static void initializeObjects() {
		frameworkProperties = new FrameworkProperties();
		DriverSingleton.getInstance(frameworkProperties.getProperty(Constants.BROWSER));
		driver = DriverSingleton.getDriver();
		logInPage = new LogInPage();
	}

	@Test
	public void testRegistrationPositive() {
		driver.get(Constants.URL);
		logInPage.registerUser(frameworkProperties.getProperty(Constants.NEW_USERNAME),
				frameworkProperties.getProperty(Constants.PASSWORD));
		assertThat(logInPage.getAutherisedUsername(),
				containsString(frameworkProperties.getProperty(Constants.NEW_USERNAME)));
		logInPage.logOut();

	}

	@Test
	public void testRegistationNegative() {
		driver.get(Constants.URL);
		logInPage.registerUser(frameworkProperties.getProperty(Constants.USERNAME),
				frameworkProperties.getProperty(Constants.WRONG_PASSWORD));
		assertEquals(Constants.USER_EXISTS_ERROR_TEXT, logInPage.userExistsText());

	}

	@AfterClass
	public static void closeObjects() {
		driver.close();
	}
}
