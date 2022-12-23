package br.dev.marcelodeoliveira.amazonautomatonbot.pages;


import org.openqa.selenium.WebElement;

import br.dev.marcelodeoliveira.amazonautomatonbot.core.BasePage;

public class LoginPage extends BasePage {
	
	public LoginPage() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void moveToWebElementAndClick (WebElement clickableElement) {
		actions.moveToElement(clickableElement).click().perform();
	}
	

}
