package pages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import drivers.DriverSingleton;
import utils.Constants;
import utils.Utils;

public class LogInPage {
	private WebDriver driver;

	public LogInPage() {
		this.driver = DriverSingleton.getDriver();
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "body > div.container > form > div > input:nth-child(2)")
	private WebElement logInInputField;

	@FindBy(css = "body > div.container > form > div > input:nth-child(3)")
	private WebElement password;

	@FindBy(css = "body > div.container > form > div > button")
	private WebElement loginInButton;

	@FindBy(css = "body > div.container > form > div > h4 > a")
	private WebElement createNewUserLink;

	@FindBy(id = "username")
	private WebElement newUsername;

	@FindBy(id = "password")
	private WebElement newUserPassword;

	@FindBy(id = "passwordConfirm")
	private WebElement newUserPasswordConfirmation;

	@FindBy(css = "#userForm > button")
	private WebElement createNewUserConfirmationButton;

	@FindBy(css = "body > nav > div > ul.nav.navbar-nav.navbar-right > a")
	private WebElement autherisedUsername;

	@FindBy(id = "username.errors")
	private WebElement userExistsError;

	@FindBy(css = "body > div > form > div > span:nth-child(4)")
	private WebElement wrongUserLoginPass;

	public String getAutherisedUsername() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(Constants.TIMEOUT));
		wait.until(ExpectedConditions.elementToBeClickable(autherisedUsername));
		return autherisedUsername.getText();
	}

	public String wrongUserMessage() {
		return wrongUserLoginPass.getText();
	}

	public String userExistsText() {
		return userExistsError.getText();
	}

	public Boolean logoutCheckText(String text) {
		return createNewUserLink.getText().equals(text);
	}

	public void logIn(String userName, String passwd) {
		logInInputField.sendKeys(userName);
		password.sendKeys(Utils.decode64(passwd));
		loginInButton.click();
	}

	public void registerUser(String userName, String passwd) {
		createNewUserLink.click();
		newUsername.sendKeys(userName);
		newUserPassword.sendKeys(passwd);
		newUserPasswordConfirmation.sendKeys(passwd);
		createNewUserConfirmationButton.click();
	}

	public void logOut() {
		if (autherisedUsername.isDisplayed()) {
			autherisedUsername.click();
		}
	}
}
