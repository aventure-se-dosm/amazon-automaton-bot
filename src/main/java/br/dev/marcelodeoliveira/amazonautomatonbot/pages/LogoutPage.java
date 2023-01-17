package br.dev.marcelodeoliveira.amazonautomatonbot.pages;

import static br.dev.marcelodeoliveira.amazonautomatonbot.core.DriverFactory.getDriver;

import java.time.Duration;

import org.openqa.selenium.By;

public class LogoutPage extends LoginPage {

	private By logoutLabelInviteText = By.xpath("//label[contains(text(), 'E-mail ou número de telefone celular')]");

	public LogoutPage() {
		super();
	}

	public String[] ID_0002_Logout() {


		waitForElement(navBarLoginStatus);
		var hoverMenu = getDriver().findElement(navBarLoginStatus);
		var signOutButton = getDriver().findElement(navItemSignout);

		actions.moveToElement(hoverMenu).pause(Duration.ofMillis(300)).moveToElement(signOutButton)
				.pause(Duration.ofMillis(300)).click().build().perform();

		redirectWait();
		scriptWait();
		waitForElementPresence(logoutLabelInviteText);
		var inviteLoginAgainLabel = getTrimmedText(logoutLabelInviteText);

		getDriver().findElement(logoutLabelInviteText).getText();

		// criar num utils um método getNoParamUrlForm(...)"
		var logoutResultPageShortURL = getDriver().getCurrentUrl();
		logoutResultPageShortURL = logoutResultPageShortURL.substring(0, logoutResultPageShortURL.indexOf('?'));

		return new String[] { logoutResultPageShortURL, getPageTitle(), inviteLoginAgainLabel, };

	}

}
