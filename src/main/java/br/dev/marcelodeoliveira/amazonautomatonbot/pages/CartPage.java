package br.dev.marcelodeoliveira.amazonautomatonbot.pages;

import static br.dev.marcelodeoliveira.amazonautomatonbot.core.DriverFactory.getDriver;

import java.time.Duration;

import org.openqa.selenium.By;

import br.dev.marcelodeoliveira.amazonautomatonbot.core.BasePage;

public class CartPage extends BasePage {

	private By searchBar = By.xpath("//*[@id='twotabsearchtextbox']");
	private By addToCartButton = By.xpath("//*[@id='add-to-cart-button']");

	private By declineExtendedWarrantyButton = By.xpath("//input[@aria-labelledby='attachSiNoCoverage-announce']");
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

	private By onlyOneForItemMessage = By.xpath("//*[@data-feature-id='single-imb-message']");
	private By selectItemQuantity = By.xpath("//select[@id='quantity']");

	private By popovercontent = By.xpath("//*[contains(@id, 'popover-content')]");

	private By fullPrice = By.xpath("//*[@id='sc-subtotal-amount-activecart']");
	
	private By currencySymbol = By.xpath("//span[@class='a-price-symbol']");
	private By integerItemPrice = By.xpath("//span[@class='a-price-whole']");

	private By fractionaryItemPrice = By.xpath("//span[@class='a-price-fraction']");

	private float getFractionalUnityByTen () {
		//return (float)Math.pow(10, getText(fractionaryItemPrice).length());
		return (float)Math.pow(10, 2);
	}
	
	
	
	public float addToCart() {
		clickOnElement(addToCartButton);
		// obter preço do ítem (produto vezes quantidade)

		return conversionUtils.strToFLoatTest(getText(integerItemPrice).replaceAll(".,", ""),
				getText(fractionaryItemPrice));
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

	public boolean changeQuantityTo(String quantityString) {
		selectCombo(selectItemQuantity, quantityString);

		if (isElementPresent(onlyOneForItemMessage))
		// the quantity did't change
		{
			return false;
		}

		return true;
		
		

	}

	public float getItemNumericalItemPrice() {
		// TODO Auto-generated method stub
		return conversionUtils.strToFLoatTest(
				getText(integerItemPrice).replaceAll(".,", ""),
				getText(fractionaryItemPrice)
		);
	}

	public float getItemNumericalItemPrice(float quantity) {
		// TODO Auto-generated method stub
		return quantity * Float.valueOf(getText(fullPrice).replaceAll("[^\\d]", ""))/getFractionalUnityByTen();
	}
	
	

}
