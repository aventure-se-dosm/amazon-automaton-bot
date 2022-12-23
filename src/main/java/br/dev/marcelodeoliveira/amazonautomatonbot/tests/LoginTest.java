package br.dev.marcelodeoliveira.amazonautomatonbot.tests;

import static br.dev.marcelodeoliveira.amazonautomatonbot.core.DriverFactory.getDriver;

import java.time.Duration;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import br.dev.marcelodeoliveira.amazonautomatonbot.core.BasePage;
import br.dev.marcelodeoliveira.amazonautomatonbot.core.BaseTest;
import br.dev.marcelodeoliveira.amazonautomatonbot.pages.LoginPage;

public class LoginTest extends BaseTest{
	
	private BasePage page;
	
	LoginTest(){
		super();
		this.page = new LoginPage();
	}
	

	@Ignore
	@Test
	public void Test_0_GetTitle() {
		var expectedText = "Amazon.com.br | Tudo pra você, de A a Z.";
		var actualText = getDriver().getTitle();
		
		Assert.assertEquals(expectedText, actualText);
	}
	
	
	@Test
	public void loginTest() {
		
		Actions hover = new Actions(getDriver());
		
		WebElement elem = getDriver().findElement(By.xpath("//*[@id='nav-signin-tooltip']"));
		Wait<WebDriver> fwait = new FluentWait<>(getDriver())
				.withTimeout(Duration.ofSeconds(10))
				  .pollingEvery(Duration.ofSeconds(2))
				  .ignoring(NoSuchElementException.class);
		
		/*
		 * //shortpath fwait.until(ExpectedConditions.visibilityOf(elem));
		 * 
		 * //longpath creating a function
		 * 
		 * 
		 * 
		 */
		
		page.moveToWebElementAndClick(elem);
		//elem.click();
		
		Assert.assertTrue(getDriver().getTitle().toLowerCase().contains("login"));
		var loginH1 = By.xpath("//h1[contains(., 'login')]");
		
		Assert.assertTrue(page.getText(loginH1).toLowerCase().contains("login"));
		var userMailInput = By.xpath("//input[@id='ap_email']");
		
		
		
		
		
	}

	
	

}
