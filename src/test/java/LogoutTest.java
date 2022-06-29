import static org.assertj.core.api.Assertions.assertThat;

import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import drivers.DriverSingleton;
import pages.LogInPage;
import utils.Constants;
import utils.FrameworkProperties;

public class LogoutTest {
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
	public void testingLogoutFunctionality() {
		driver.get(Constants.URL);
		logInPage.logIn(frameworkProperties.getProperty(Constants.USERNAME),
				frameworkProperties.getProperty(Constants.PASSWORD));
		logInPage.logOut();
		assertThat(logInPage.logoutCheckText(Constants.LOGIN_TEXT));
	}

//	@AfterClass
//	public static void closeObjects() {
//		driver.close();
//	}
}