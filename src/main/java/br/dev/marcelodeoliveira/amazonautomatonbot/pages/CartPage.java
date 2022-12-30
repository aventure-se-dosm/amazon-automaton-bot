package br.dev.marcelodeoliveira.amazonautomatonbot.pages;

import org.openqa.selenium.By;

import br.dev.marcelodeoliveira.amazonautomatonbot.core.BasePage;

public class CartPage extends BasePage {

	

	private By searchBar = By.xpath("//*[@id='twotabsearchtextbox']");
	private By addToCartButton = By.xpath("//*[@id='add-to-cart-button']");
	
	private By declineExtendedWarrantyButton = By.xpath("//*[@id='attachSiNoCoverage']/span");
	private By acceptExtendedWarrantyButton = By.xpath("//*[@id='attachSiAddCoverage']//input");
	
	private By finishOrderButton = By.xpath("//*[@id='sc-buy-box-ptc-button']//input");
	//*[@id="attach-popover-lgtbox"]/..

	private By extendedWarrantyFrame = By.xpath("//div[@id='attach-desktop-sideSheet']");

	public void addToCart () {
		clickOnElement(addToCartButton);
	}
	
	public boolean isExtendedWarrantyOffered () {
		return isElementPresent(extendedWarrantyFrame);
		
	}
	
	public void declineExtendedWarrantyOffer() {
		redirectWait();
		scriptWait();
		switchToActiveElement(By.xpath("//*[@id='attach-popover-lgtbox']/.."));
		clickOnElement(declineExtendedWarrantyButton);
	}
	
	public void finishOrder() {
		clickOnElement(finishOrderButton);
	}
	
	

}
