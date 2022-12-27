package br.dev.marcelodeoliveira.amazonautomatonbot.tests;

import static br.dev.marcelodeoliveira.amazonautomatonbot.core.DriverFactory.getDriver;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;

import br.dev.marcelodeoliveira.amazonautomatonbot.core.BaseTest;
import br.dev.marcelodeoliveira.amazonautomatonbot.pages.LogoutPage;

public class LogoutTest extends BaseTest {

	protected By navLinkAccountList = By.xpath("//*[id='nav-link-accountList']");

	private LogoutPage page = new LogoutPage();

	@Ignore
	@Test
	public void Test_0_GetTitle() {
		var expectedText = "Amazon.com.br | Tudo pra você, de A a Z.";
		var actualText = getDriver().getTitle();

		Assert.assertEquals(expectedText, actualText);

	}

	@Test
	public void logoutTest() {
		// page.startLogin();

		String[] expectedResults, actualResults;

		// trocar estes valores por constantes!
		expectedResults = new String[] { 
				"https://www.amazon.com.br/ap/signin",
				"Acessar Amazon",
				"E-mail ou número de telefone celular",

		};

		// Alterar as constrantes: definir a mensagem como contatenação de properties
		// por region!

		// var actualTestPart01 =
		page.startLogin();

		//getDriver().switchTo().defaultContent();
		// var actualTestPart02 =
		page.login(TestProperties.getUserEmail(), TestProperties.getUserPassword());
		// actualResults = new String[] { actualTestPart01, actualTestPart02, };

		// [1]. fazer um hover até o 'Olá, automaton';

		actualResults = page.logout();

		Assert.assertArrayEquals(expectedResults, actualResults);

	}

}
