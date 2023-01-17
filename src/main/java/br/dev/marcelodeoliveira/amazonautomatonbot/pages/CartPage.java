package br.dev.marcelodeoliveira.amazonautomatonbot.pages;

import static br.dev.marcelodeoliveira.amazonautomatonbot.core.DriverFactory.getDriver;

import org.openqa.selenium.By;

import br.dev.marcelodeoliveira.amazonautomatonbot.core.BasePage;

public class CartPage extends BasePage {

	private By addToCartButton = By.xpath("//*[@id='add-to-cart-button']");

	private By declineExtendedWarrantyButton = By.xpath("//input[@aria-labelledby='attachSiNoCoverage-announce']");

	private By finishOrderButton = By.xpath("//*[@id='sc-buy-box-ptc-button']//input");
	// *[@id="attach-popover-lgtbox"]/..

	private By extendedWarrantyFrame = By.xpath("//div[@id='attach-desktop-sideSheet']");

	private By subtotal1 = By.xpath("//*[@id='sc-subtotal-label-activecart']");
	private By subtotal2 = By.xpath("//*[@id='sc-subtotal-label-buybox']");
	private By checkCartButton = By.xpath("//*[@id='sw-gtc']//a");
	private By onlyOneForItemMessage = By.xpath("//*[@data-feature-id='single-imb-message']");
	private By selectItemQuantity = By.xpath("//select[@id='quantity']");

	private By singleItemOnCart = By.xpath("//span[@class='a-list-item']/a[contains(@href, 'gp/product')]");
	private By fullPrice = By.xpath("//*[@id='sc-subtotal-amount-activecart']");



	private By integerItemPrice = By.xpath("//span[@class='a-price-whole']");

	private By fractionaryItemPrice = By.xpath("//span[@class='a-price-fraction']");

	private final float getFractionalUnityByTen = 100;

	public float getNumericPrice() {
		return conversionUtils.strToFLoatTest(getText(integerItemPrice).replaceAll(".,", ""),
				getText(fractionaryItemPrice));
	}

	public String[] addToCart() {
		var url = getUrl();
		System.out.println(url);

		movingDivWait();
		clickOnElement(addToCartButton);

		if (isExtendedWarrantyOffered()) {

			declineExtendedWarrantyOffer();
		}

		scriptWait();
		redirectWait();
		checkCart();

		System.out.println();
		var split = url.split("/");
		System.out.println(split[5]);

		return split;
	
	}

	public float addToCartAndReturnNumericPrice() {
		addToCart();


		return getNumericPrice();
	}

	public float addToCartAndReturnItemTitle() {
		addToCart();


		return getNumericPrice();
	}

	public boolean isExtendedWarrantyOffered() {
		return isElementPresent(extendedWarrantyFrame);
	}

	public void declineExtendedWarrantyOffer() {

		redirectWait();
		scriptWait();

		// sactisfatory div wait time
		// implicityWaitOf(Duration.ofSeconds(10));
		// waitForElements(declineExtendedWarrantyButton);

//		Wait<WebDriver> fwait = new FluentWait<>(getDriver()).withTimeout(Duration.ofSeconds(10))
//				.pollingEvery(Duration.ofMillis(250)).ignoring(NoSuchElementException.class);

		movingDivWait();
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

		waitForElementAndClick(checkCartButton);

	}

	public boolean changeQuantityTo(String quantityString) {

		redirectWait();
		scriptWait();

		selectCombo(selectItemQuantity, quantityString);

		if (isElementDisplayed(onlyOneForItemMessage))
		// the quantity did't change
		{
			return false;
		}

		return true;

	}

	public float getItemNumericalItemPrice() {
		
		return conversionUtils.strToFLoatTest(getText(integerItemPrice).replaceAll(".,", ""),
				getText(fractionaryItemPrice));
	}

	public float getItemNumericalItemPrice(float quantity) {
		
		return quantity * (Float.valueOf(getText(fullPrice).replaceAll("[^\\d]", ""))) / getFractionalUnityByTen;
	}

	public String getItemUrlOnTheCart() {
		return getElementAttribute(singleItemOnCart, "href");
	}

	public String[] removeFromCart(String urlProdId) {

		

		var itemFrame = getDriver().findElement(By.xpath("//div[@class='sc-item-content-group']"));

		var link = itemFrame.findElement(By.xpath(".//a[contains(@href,'" + urlProdId + "')]")).getAttribute("href");

		var excludeItemButton = itemFrame.findElement(By.xpath(".//input[ @data-action='delete']"));

		var title = itemFrame.findElement(By.xpath(".//span[@class='a-truncate-full a-offscreen']")).getText();
		;

		clickOnElement(excludeItemButton);

		return new String[] { link, title, };
	}

}
