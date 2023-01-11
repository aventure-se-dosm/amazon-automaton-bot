package br.dev.marcelodeoliveira.amazonautomatonbot.pages;

import static br.dev.marcelodeoliveira.amazonautomatonbot.core.DriverFactory.getDriver;

import java.awt.Point;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class LogoutPage extends LoginPage {

	
	private By logoutLabelInviteText = By.xpath("//label[contains(text(), 'E-mail ou número de telefone celular')]");
	public LogoutPage() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String[] logout() {
		
		var hoverMenu = getDriver().findElement(navBarLoginStatus);
		var signOutButton = getDriver().findElement(navItemSignout);
		
		
		actions.moveToElement(hoverMenu)
				.moveToElement(signOutButton)
					.click().build().perform();

		
		redirectWait();
		scriptWait();
		var inviteLoginAgainLabel = getTrimmedText(logoutLabelInviteText);
				
				getDriver()
				.findElement(logoutLabelInviteText).getText();

		// criar num utils um método getNoParamUrlForm(...)"
		var logoutResultPageShortURL = getDriver().getCurrentUrl();
		logoutResultPageShortURL = logoutResultPageShortURL.substring(0, logoutResultPageShortURL.indexOf('?'));

		return new String[] { 
				logoutResultPageShortURL,
				getPageTitle(),
				inviteLoginAgainLabel,
		};

	}
	
	public void logout2() {
		//input[@id='ap_email']
		//getDriver().getCurrentUrl();
		getDriver().get("https://www.amazon.com.br/gp/flex/sign-out.html?path=%2Fgp%2Fyourstore%2Fhome&signIn=1&useRedirectOnSuccess=1&action=sign-out&ref_=nav_AccountFlyout_signout");
		//clickButton(logoutListElement);

	}
	
	
	private Point getCentralElementCoordinate (WebElement elem) {
		
		var x = elem.getLocation().x + (elem.getSize().width/2);
		var y = elem.getLocation().y + (elem.getSize().width/2);
		
		return new Point(x, y);
		
	}
	
	//public String[] logout3() throws AWTException, InterruptedException {
		
//		Robot robot = new Robot();
//		
//		var elemHoverTarget = getDriver().findElement(navBarLoginStatus);
//		var body = getDriver().findElement(By.id("pageContent"));
//		var x = getCentralElementCoordinate(elemHoverTarget).x;
//		var y = getCentralElementCoordinate(elemHoverTarget).y;
//		x += elemHoverTarget.getRect().x;
//		y += elemHoverTarget.getRect().y;
//		
//		
//		robot.mouseMove(x, y);
////		robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
////		robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
//		robot.mousePress(InputEvent.BUTTON3_DOWN_MASK);
//		robot.mouseRelease(InputEvent.BUTTON3_DOWN_MASK);
//		
//		waitForElementAndClick(navItemSignout);
//
//				
////		actions.moveToElement(getDriver().findElement(navBarLoginStatus))
////				.moveToElement(getDriver().findElement(navItemSignout)).click().build().perform();
////
//		//Thread.sleep(2000);
//
//		
//		var inviteLoginAgainLabel = getTrimmedText(logoutLabelInviteText);
//				
//				getDriver()
//				.findElement(logoutLabelInviteText).getText();
//
//		// criar num utils um método getNoParamUrlForm(...)"
//		var logoutResultPageShortURL = getDriver().getCurrentUrl();
//		logoutResultPageShortURL = logoutResultPageShortURL.substring(0, logoutResultPageShortURL.indexOf('?'));
//
//		return new String[] { 
//				logoutResultPageShortURL,
//				getPageTitle(),
//				inviteLoginAgainLabel,
//	// label[contains(text(), 'E-mail ou número de telefone celular')]
//		};

//	}




}
