package br.dev.marcelodeoliveira.amazonautomatonbot.pages;

import static br.dev.marcelodeoliveira.amazonautomatonbot.core.DriverFactory.getDriver;

import org.openqa.selenium.By;

import br.dev.marcelodeoliveira.amazonautomatonbot.core.BasePage;
import br.dev.marcelodeoliveira.amazonautomatonbot.pages.utils.CssTools;

public class ZipCodePage extends BasePage {
	public ZipCodePage() {
		super();
	}

	By selectZipCodeLink = By.xpath("//*[@id='contextualIngressPtLabel_deliveryShortLine']");
	By BrazilianZipCodePreffix = By.xpath("//*[@id='GLUXZipUpdateInput_0']");
	By BrazilianZipCodeSuffix = By.xpath("//*[@id='GLUXZipUpdateInput_1']");
	By zipCodeFrame = By.xpath("//*['GLUXZipInputSection']/div");
	By SubmitZipCodeConfirmation = By.xpath("//*[@id='GLUXZipUpdate']");

	private By zipCodeError = By.xpath("//*[@id='GLUXZipError']/*");

	By ShippingAndDeliveryInformationsFrame = By.xpath("//*[@id='deliveryBlockMessage']");
	By ShippingAndDeliveryInformationsSpans = By.xpath("//*[@id='deliveryBlockContainer']");
	// By.xpath("//*[@id='deliveryBlockContainer']//div/span[contains(@class,
	// data-csa-c)]");
	private CssTools cssTools = new CssTools();

	public String addZipCode(String zipCode) {

		//redirectWait();
		scriptWait();

		// must be flexible to regional implementation rules
		// magic numbers! refine it
		// Should'nt you validate "invalid cpf" or "cpf regex match" cases?
		clickButton(selectZipCodeLink);
		//redirectWait();
		scriptWait();

		// switchToPopUp();
		waitForElements(SubmitZipCodeConfirmation);

		// smelling like a cadaver! make a zipcode dedicaated method in some utils
		// package!
		// seizing the localization packs as well!
		writeTextOnElementField(BrazilianZipCodePreffix, zipCode.substring(0, 5));
		writeTextOnElementField(BrazilianZipCodeSuffix, zipCode.substring(6, zipCode.length()));
		clickButton(SubmitZipCodeConfirmation);
		// switchToDefaultContent();
		scriptWait();
		redirectWait();

		return isZipCodeInvalid();

	}

	public String getDeliveryInfos() {

		var infos = getElementsText(ShippingAndDeliveryInformationsSpans);

		cssTools.webElementHighlight(ShippingAndDeliveryInformationsFrame);

		//scriptWait();

		return infos;
	}

	public String isZipCodeInvalid() {
		return getDriver().findElement(zipCodeError).getText();
	}
}
