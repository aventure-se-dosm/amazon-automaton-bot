package br.dev.marcelodeoliveira.amazonautomatonbot.pages;

import java.time.Duration;

import org.openqa.selenium.By;

import br.dev.marcelodeoliveira.amazonautomatonbot.core.BasePage;

public class NavBarPage extends BasePage {
	
	private By navBarLoginStatus =  By.xpath( "//*[@id='nav-link-accountList-nav-line-1']");
	
	private By navSignInToolType = By.xpath("//*[@id='nav-signin-tooltip']");
	
	
	public String getNavBarStatus() {
		
		implicityWait(Duration.ZERO);
		try {
		waitForElementPresence(navBarLoginStatus);
		return getText(navBarLoginStatus);
		} catch (Exception e) {
			return "";
		}
		

	}
	
	

	
	


}
