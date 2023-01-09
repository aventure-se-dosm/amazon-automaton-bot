package br.dev.marcelodeoliveira.amazonautomatonbot.pages;

import static br.dev.marcelodeoliveira.amazonautomatonbot.core.DriverFactory.getDriver;

import java.time.Duration;

import org.openqa.selenium.By;

import br.dev.marcelodeoliveira.amazonautomatonbot.core.BasePage;

public class CartPage extends BasePage {

	private By searchBar = By.xpath("//*[@id='twotabsearchtextbox']");
	private By addToCartButton = By.xpath("//*[@id='add-to-cart-button']");

	private By declineExtendedWarrantyButton = By.xpath("//*[@id='attachSiAddCoverage']");
	private By acceptExtendedWarrantyButton = By.xpath("//*[@id='attachSiAddCoverage']/span");

	private By finishOrderButton = By.xpath("//*[@id='sc-buy-box-ptc-button']//input");
	// *[@id="attach-popover-lgtbox"]/..

	private By extendedWarrantyFrame = By.xpath("//div[@id='attach-desktop-sideSheet']");
	private By totalOfItemsAddedToCartSpan = By.xpath("//*[@id='sc-subtotal-label-activecart']");
	// private By extendedWarrantyFrame =
	// By.xpath("//*[@id='attachSiAddCoverage']");

	// Subtotal and Finish order
	private By subtotal1 = By.xpath("//*[@id='sc-subtotal-label-activecart']");
	private By subtotal2 = By.xpath("//*[@id='sc-subtotal-label-buybox']");
	private By checkCartButton = By.xpath("//*[@id='sw-gtc']//a");

	public void addToCart() {
		clickOnElement(addToCartButton);
	}

	public boolean isExtendedWarrantyOffered() {
		return isElementPresent(extendedWarrantyFrame);
	}

	public void declineExtendedWarrantyOffer() {

		redirectWait();
		scriptWait();

		// sactisfatory div wait time
		implicityWaitOf(Duration.ofSeconds(10));
		waitForElements(declineExtendedWarrantyButton);
		clickOnElement(declineExtendedWarrantyButton);
	}

	public void finishOrder() {
		clickOnElement(finishOrderButton);
	}

	public String getUrl() {
		return String.join("", getDriver().getCurrentUrl());
	}

	public int getTotalItems() {

		// ^ negação! estranho ele vir DEPOIS do colchete
		var subtotal = getElementsText(subtotal1);

		if (subtotal.equals(getElementsText(subtotal2))) {
			return Integer.parseInt(subtotal.replaceAll("[^\\d]", ""));
		}

		throw new IllegalStateException();
		// return -1;
	}

	public void checkCart() {

		clickButton(checkCartButton);

	}

}
