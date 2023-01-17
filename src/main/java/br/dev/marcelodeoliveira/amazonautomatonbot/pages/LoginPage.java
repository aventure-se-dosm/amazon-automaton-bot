package br.dev.marcelodeoliveira.amazonautomatonbot.pages;

import static br.dev.marcelodeoliveira.amazonautomatonbot.core.DriverFactory.getDriver;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import br.dev.marcelodeoliveira.amazonautomatonbot.core.BasePage;

public class LoginPage extends BasePage {

	protected By navSignInToolType = By.xpath("//*[@id='nav-signin-tooltip']");
	protected By emailLoginFormMailSendButton = By.xpath("//*[@id='continue']");
	protected By passwordLoginFormMailSendButton = By.xpath("//*[@id='signInSubmit']");

	protected By userMailInput = By.xpath("//input[@id='ap_email']");

	protected By userPasswordInput = By.xpath("//input[@id='ap_password']");

	protected NavBarPage navBarPage = new NavBarPage();

	protected By capchaTextField = By.xpath("//input[@id='auth-captcha-guess']");

	public LoginPage() {
		super();

	}

	public String startLogin() {
		getDriver().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));

		redirectWait();
		scriptWait();

		WebElement elem = getDriver().findElement(By.xpath("//*[@id='nav-signin-tooltip']"));
		Wait<WebDriver> fwait = new FluentWait<>(getDriver()).withTimeout(Duration.ofSeconds(10))
				.pollingEvery(Duration.ofMillis(250)).ignoring(NoSuchElementException.class);

		fwait.until(ExpectedConditions.visibilityOf(elem));
		moveToWebElementClick(elem);

		return getPageTitle();

	}

	public void loginWriteUserEmail(String email) {

		writeTextOnElementField(userMailInput, email);
		clickButton(emailLoginFormMailSendButton);
	}

	public void loginWriteUserPassword(String password) {

		writeTextOnElementField(userPasswordInput, password);
		implicityWait(Duration.ofSeconds(1));
		clickButton(passwordLoginFormMailSendButton);
	}

	public String login(String email, String password) {

		implicityWait(Duration.ofSeconds(2));

		loginWriteUserEmail(email);

		loginWriteUserPassword(password);

		return navBarPage.getNavBarStatus();

	}

	public void moveToWebElementClick(By clickableElement) {
		actions.moveToElement(getDriver().findElement(clickableElement)).click().perform();

	}

	public void moveToWebElementClick(WebElement clickableElement) {
		actions.moveToElement(clickableElement).click().perform();

	}

}
