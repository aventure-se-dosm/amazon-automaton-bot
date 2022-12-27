package br.dev.marcelodeoliveira.amazonautomatonbot.pages;

import static br.dev.marcelodeoliveira.amazonautomatonbot.core.DriverFactory.getDriver;

import org.openqa.selenium.By;

public class LogoutPage extends LoginPage {

	
	private By logoutLabelInviteText = By.xpath("//label[contains(text(), 'E-mail ou número de telefone celular')]");
	public LogoutPage() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String[] logout() {
		actions.moveToElement(getDriver().findElement(navBarLoginStatus))
				.moveToElement(getDriver().findElement(navItemSignout)).click().build().perform();

		var inviteLoginAgainLabel = obterTextoTrimado(logoutLabelInviteText);
				
				getDriver()
				.findElement(logoutLabelInviteText).getText();

		// criar num utils um método getNoParamUrlForm(...)"
		var logoutResultPageShortURL = getDriver().getCurrentUrl();
		logoutResultPageShortURL = logoutResultPageShortURL.substring(0, logoutResultPageShortURL.indexOf('?'));

		return new String[] { 
				logoutResultPageShortURL,
				getPageTitle(),
				inviteLoginAgainLabel,
	// label[contains(text(), 'E-mail ou número de telefone celular')]
		};

	}



}
