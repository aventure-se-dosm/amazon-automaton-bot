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

	public LoginPage() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String startLogin() {
		getDriver().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));

		redirectWait();
		scriptWait();
		
		
//		WebElement elem = getDriver().findElement(By.xpath("//*[@id='nav-signin-tooltip']"));
//		Wait<WebDriver> fwait = new FluentWait<>(getDriver()).withTimeout(Duration.ofSeconds(10))
//				.pollingEvery(Duration.ofMillis(250)).ignoring(NoSuchElementException.class);
//
//		fwait.until(ExpectedConditions.visibilityOf(elem));
//		moveToWebElementClick(elem);
		
		
		waitForElementAndClick(By.xpath("//*[@id='nav-signin-tooltip']"));
		return getPageTitle();

	}

	public void loginWriteUserEmail(String email)  {

		writeTextOnElementField(userMailInput, email);
		clickButton(emailLoginFormMailSendButton);
	}

	public void loginWriteUserPassword(String password) {

		writeTextOnElementField(userPasswordInput, password);
		clickButton(passwordLoginFormMailSendButton);
	}

	public String login(String email, String password)  {

//esperar pag. carregar toda!
		redirectWait();
		getDriver().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));

		loginWriteUserEmail(email);

		loginWriteUserPassword(password);

//		var expectedWelcomeMessage = navBarPage.getNavBarStatus();

//		return new String[]{
//			loginTitle,
//			expectedWelcomeMessage
//			};

		return navBarPage.getNavBarStatus();

//		var loginH1 = By.xpath("//h1[contains(., 'login')]");
//		
//		Assert.assertTrue(getText(loginH1).toLowerCase().contains("login"));

	}

	public void moveToWebElementClick(By clickableElement) {
		actions.moveToElement(getDriver().findElement(clickableElement)).click().perform();

	}

	public void moveToWebElementClick(WebElement clickableElement) {
		actions.moveToElement(clickableElement).click().perform();

	}

}
