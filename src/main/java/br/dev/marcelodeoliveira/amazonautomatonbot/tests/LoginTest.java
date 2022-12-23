package br.dev.marcelodeoliveira.amazonautomatonbot.tests;

import static br.dev.marcelodeoliveira.amazonautomatonbot.core.DriverFactory.getDriver;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import br.dev.marcelodeoliveira.amazonautomatonbot.core.BasePage;

public class LoginTest extends BasePage{
	

	//@Ignore
	@Test
	public void Test_0_GetTitle() {
		var expectedText = "Amazon.com.br | Tudo pra você, de A a Z.";
		var actualText = getDriver().getTitle();
		
		Assert.assertEquals(expectedText, actualText);
	}
	
	@Ignore
	@Test
	public void loginTest() {
		
	}

}
