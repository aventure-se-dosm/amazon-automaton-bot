package br.dev.marcelodeoliveira.amazonautomatonbot.core;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ItemDepartmentPage extends BasePage {

	By openDepartmentMenu = By.xpath("//*[@id='nav-hamburger-menu']");

	// locale br"
	By expandItemButtons = By
			.xpath("//div[contains(.,'ver tudo')]/ancestor::a[@class='hmenu-item hmenu-compressed-btn']");

	By InformaticDepartment = By.xpath("//div[.='Computadores e Informática']");

	private String getStringCategoryXpath(String category) {
//		return "//a[contains(@class,'hmenu-item')]/div[contains(.,'"+category+"')]/..";
		return "//a[contains(@class,'hmenu-item') and contains(.,'" + category + "')]";

		// *[@id="hmenu-content"]//a[contains(class,'"+category+"')]
	}

	By singleGatheredItemDivs = By.xpath(
			"//*[@class='a-section aok-relative s-image-square-aspect']/ancestor::div[@class='a-section a-spacing-base']");

	public void openCategoryMenu() {

		
		waitForElementAndClick(openDepartmentMenu);
		// fluentWait(Duration.ofSeconds(2), Duration.ofMillis(250));

	}

	private void openAllVisibleSeeMoreExpanderButton() {

//		var divWait = fluentWait(Duration.ofSeconds(30), Duration.ofMillis(250));
//
//		divWait.until(ExpectedConditions.elementToBeClickable(expandItemButtons));

		var expandButtons = getWebElements(expandItemButtons);

		expandButtons.forEach(w -> {

			implicityWait(Duration.ofSeconds(1));
			if (!w.isDisplayed())
				scrollIntoView(w);
			if (!w.isDisplayed())
				return;

			divWait.until(ExpectedConditions.elementToBeClickable(w));
			
			clickOnElement(w);
			//w.click();
			

		});

	}

	private void categoryClick(String... categoryStrings) {
//		redirectWait();
//		scriptWait();
//		clickOnElement(By.xpath(getStringCategoryXpath(cat)));
		var divWait = fluentWait(Duration.ofSeconds(15), Duration.ofMillis(250));

		for (String categoryString : categoryStrings) {

			var category = getWebElement(By.xpath(getStringCategoryXpath(categoryString)));
			// var category = getWebElement(By.xpath(categoryString));

			implicityWait(Duration.ofSeconds(1));
			if (!category.isDisplayed())
				scrollIntoView(category);
			if (!category.isDisplayed())
				return;

			divWait.until(ExpectedConditions.elementToBeClickable(category)).click();
			//category.click();

		}
		;
	}
	

	public void highlightGatheredItemDivElements () {
		// formatar
		
		
		highlightElements(getWebElements(singleGatheredItemDivs));

	}

	private void setupSearchByCategory() {
		redirectWait();
		scriptWait();
		openCategoryMenu();
		openAllVisibleSeeMoreExpanderButton();
	}

	public void findCategory(String[] category) {
		setupSearchByCategory();

		categoryClick(category);
	}
}
