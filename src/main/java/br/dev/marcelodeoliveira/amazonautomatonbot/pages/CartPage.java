package br.dev.marcelodeoliveira.amazonautomatonbot.pages;

import static br.dev.marcelodeoliveira.amazonautomatonbot.core.DriverFactory.getDriver;

import java.time.Duration;

import org.junit.After;
import org.openqa.selenium.By;

import br.dev.marcelodeoliveira.amazonautomatonbot.core.BasePage;
import br.dev.marcelodeoliveira.amazonautomatonbot.core.CoreProperties;
import br.dev.marcelodeoliveira.amazonautomatonbot.core.dtos.DTOItem;

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
	private By checkCartButton2 = By.xpath("//*[@id='submit.add-to-cart']");

	private By onlyOneForItemMessage = By.xpath("//*[@data-feature-id='single-imb-message']");
	private By selectItemQuantity = By.xpath("//select[@id='quantity']");

	private By popovercontent = By.xpath("//*[contains(@id, 'popover-content')]");

	private By singleItemOnCart = By.xpath("//span[@class='a-list-item']/a[contains(@href, 'gp/product')]");
	private By fullPrice = By.xpath("//*[@id='sc-subtotal-amount-activecart']");

	private By currencySymbol = By.xpath("//span[@class='a-price-symbol']");

	private By dtoItemTitle = By.xpath("//*[@id='productTitle']");

	private By integerItemPrice = By.xpath("//span[@class='a-price-whole']");

	private By fractionaryItemPrice = By.xpath("//span[@class='a-price-fraction']");

	private By cartItemTitles = By.xpath("//span[@class='a-truncate-full a-offscreen']");

	private final String replaceItemString = "_-_REPLACE_ITEM_STRING_ON_THIS_SPACE_-_";
	private String removeItemFromCartByXpathString = "%s";

	private float getFractionalUnityByTen() {
		// return (float)Math.pow(10, getText(fractionaryItemPrice).length());
		// return (float) Math.pow(10, 2);

		return 100;
	}

	public float getNumericPrice() {
		return conversionUtils.strToFLoatTest(getText(integerItemPrice).replaceAll(".,", ""),
				getText(fractionaryItemPrice));
	}

	// return itemInfos

	private String getItemTitle() {
		return getElementsText(dtoItemTitle);

	}

	private String getItemUrlId() {
		var onCartItemUrl = getUrl().replace(CoreProperties.BASE_PATH + "/gp/product/", "");

		return onCartItemUrl.substring(0, onCartItemUrl.indexOf('/'));
	}

	public String[] addToCart() {
		var url = getUrl();
		System.out.println(url);

		clickOnElement(addToCartButton);

		if (isExtendedWarrantyOffered()) {

			declineExtendedWarrantyOffer();
		}

		// var url = getItemUrlId();
		// obter preço do ítem (produto vezes quantidade)
		checkCart();

		System.out.println();
		var split = url.split("/");
		System.out.println(split[5]);

		scriptWait();
		redirectWait();
		return split;
		// return new DTOItem(url, getItemTitle(), getNumericPrice());

	}

	public float addToCartAndReturnNumericPrice() {
		addToCart();
		// obter preço do ítem (produto vezes quantidade)

		return getNumericPrice();
	}

	public float addToCartAndReturnItemTitle() {
		addToCart();
		// obter preço do ítem (produto vezes quantidade)

		return getNumericPrice();
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
		// TODO Auto-generated method stub
		return conversionUtils.strToFLoatTest(getText(integerItemPrice).replaceAll(".,", ""),
				getText(fractionaryItemPrice));
	}

	public float getItemNumericalItemPrice(float quantity) {
		// TODO Auto-generated method stub
		return quantity * (Float.valueOf(getText(fullPrice).replaceAll("[^\\d]", ""))) / getFractionalUnityByTen();
	}

	public String getItemUrlOnTheCart() {
		return getElementAttribute(singleItemOnCart, "href");
	}

	public String[] removeFromCart(String urlProdId) {

		/*
		 * Objetivo: gerar o xpath do elemento a ser excluido da lista.
		 * 
		 * 
		 * Isso exige a captura do título <h2> do ítem que será utili- zado na
		 * construção do valor da classe "aria-label" do ele- mentoto clicável de
		 * exclusão de tipo <input>.
		 * 
		 * 
		 * 
		 */

		// input[contains(@aria-label, 'Berimbau e outros poemas') and
		// @data-action='delete']

		var itemFrame = getDriver().findElement(By.xpath("//div[@class='sc-item-content-group']"));

		var link = itemFrame.findElement(By.xpath(".//a[contains(@href,'" + urlProdId + "')]")).getAttribute("href");

		var excludeItemButton = itemFrame.findElement(By.xpath(".//input[ @data-action='delete']"));

		var title = itemFrame.findElement(By.xpath(".//span[@class='a-truncate-full a-offscreen']")).getText();
		;

		clickOnElement(excludeItemButton);

		return new String[] { link, title, };
	}

}
