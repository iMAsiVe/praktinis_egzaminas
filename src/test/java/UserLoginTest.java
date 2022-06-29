import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import drivers.DriverSingleton;
import pages.LogInPage;
import utils.Constants;
import utils.FrameworkProperties;

public class UserLoginTest {
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
	public void TestingAuthenticationPossitive() {
		driver.get(Constants.URL);
		logInPage.logIn(frameworkProperties.getProperty(Constants.USERNAME),
				frameworkProperties.getProperty(Constants.PASSWORD));
		assertThat(logInPage.getAutherisedUsername(), containsString(Constants.AUTHERAISED_USER));
	}

	@Test
	public void testingAuthenticationNegative() {
		driver.get(Constants.URL);
		logInPage.logIn(frameworkProperties.getProperty(Constants.USERNAME),
				frameworkProperties.getProperty(Constants.WRONG_PASSWORD));
		assertThat(logInPage.wrongUserMessage(), containsString(Constants.WRONG_LOGIN_MESSAGE));
	}

//	@AfterClass
//	public static void closeObjects() {
//		driver.close();
//	}
}
