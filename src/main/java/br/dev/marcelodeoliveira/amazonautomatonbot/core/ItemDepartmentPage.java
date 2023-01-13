package br.dev.marcelodeoliveira.amazonautomatonbot.core;

import java.time.Duration;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ItemDepartmentPage extends BasePage {

	By openDepartmentMenu = By.xpath("//*[@id='nav-hamburger-menu']");

	// locale br"
	By expandItemButtons = By.xpath("//div[contains(.,'ver tudo')]/ancestor::a[@class='hmenu-item hmenu-compressed-btn']");

	By InformaticDepartment = By.xpath("//div[.='Computadores e Informática']");

	private String getStringCategoryXpath(String category) {
		return "//*[@id='hmenu-content']//a[contains(@class,'" + category + "')]";

		// *[@id="hmenu-content"]//a[contains(class,'"+category+"')]
	}

	public void openCategoryMenu() {

		clickOnElement(openDepartmentMenu);
		//fluentWait(Duration.ofSeconds(2), Duration.ofMillis(250));
		

	}

	private void openAllVisibleSeeMoreExpanderButton(String cat) {

		var divWait = fluentWait(Duration.ofSeconds(15), Duration.ofMillis(250));
		
		divWait.until(ExpectedConditions.elementToBeClickable(expandItemButtons));
		
		
		
		var expandButtons = getWebElements(expandItemButtons);

		expandButtons.forEach(w -> {


			if (!w.isDisplayed()) scrollIntoView(w);

			divWait.until(ExpectedConditions.elementToBeClickable(w));
			w.click();

		});
		
		

	}

	private void categoryClick(String cat) {
		redirectWait();
		scriptWait();
		clickOnElement(By.xpath(getStringCategoryXpath(cat)));

	}

	private void setupSearchByCategory(String cat) {
		redirectWait();
		scriptWait();
		openCategoryMenu();
		openAllVisibleSeeMoreExpanderButton(cat);
	}

	public void findCategory(String cat) {
		setupSearchByCategory(cat);
		// categoryClick(cat);
	}
}
