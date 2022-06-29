import org.openqa.selenium.WebDriver;

import drivers.DriverSingleton;
import pages.LogInPage;
import utils.Constants;
import utils.FrameworkProperties;

public class Main {

	public static void main(String[] args) {
		FrameworkProperties frameworkProperties = new FrameworkProperties();
		DriverSingleton driverSingleton = DriverSingleton
				.getInstance(frameworkProperties.getProperty(Constants.BROWSER));
		WebDriver driver = DriverSingleton.getDriver();
		driver.get(Constants.URL);

		LogInPage logInPage = new LogInPage();
		logInPage.registerUser(frameworkProperties.getProperty(Constants.NEW_USERNAME),
				frameworkProperties.getProperty(Constants.PASSWORD));

	}

}
