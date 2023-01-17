package br.dev.marcelodeoliveira.amazonautomatonbot.tests;

import static br.dev.marcelodeoliveira.amazonautomatonbot.core.DriverFactory.getDriver;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import br.dev.marcelodeoliveira.amazonautomatonbot.core.BaseTest;
import br.dev.marcelodeoliveira.amazonautomatonbot.pages.LoginPage;

public class LoginTest extends BaseTest {

	private LoginPage page = new LoginPage();

	@Ignore
	@Test
	public void Test_0_GetTitle() {
		var expectedText = "Amazon.com.br | Tudo pra você, de A a Z.";
		var actualText = getDriver().getTitle();

		Assert.assertEquals(expectedText, actualText);
	}

	@Test
	public void ID_0001_LoginTest() {

		String[] expectedResults, actualResults;

		expectedResults = new String[] { "Acessar Amazon", "Olá, Automation" };

		actualResults = new String[] { page.startLogin(),

				page.login(TestProperties.getUserEmail(), TestProperties.getUserPassword())

		};
		Assert.assertArrayEquals(expectedResults, actualResults);

	}

}
