package br.dev.marcelodeoliveira.amazonautomatonbot.pages;

import static br.dev.marcelodeoliveira.amazonautomatonbot.core.DriverFactory.getDriver;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import br.dev.marcelodeoliveira.amazonautomatonbot.core.BasePage;

public class RequestItemPage extends BasePage {

	
	
	By searchBar = By.xpath("//*[@id='twotabsearchtextbox']");
	By searchButton = By.xpath("//*[@id='nav-search-submit-text']");

	// -------------------------- RESULTS --------------------------

	By singlePageResultFrames = By.xpath("//div[@class = 'a-section a-spacing-base']/../../../parent::*");
	By singlePageResultTitles = By.xpath("//*[@class='a-size-base-plus a-color-base a-text-normal']");
	
	//By singlePageResultImageFrame = By.xpath("//*[@class='a-section aok-relative s-image-square-aspect']/parent::*");

//	By singlePageResultTitlesPartialXPATH = By.xpath(".//*[@class='a-size-base-plus a-color-base a-text-normal']");
	By singlePageResultTitlesPartialXPATH = By.xpath("//*[@class='a-size-base-plus a-color-base a-text-normal']");

	By singlePageResultImageFramePartialXPATH = By
			.xpath(".//*[@class='a-section aok-relative s-image-square-aspect']/../../..");

	
	By resultTitleLinks = By.xpath("//*[@data-component-type='s-search-result']//h2");
	By selectAddressLink = By.xpath("//*[@id='contextualIngressPtLabel']");
	
	By selectZipCodeLink = By.xpath("//*[id='contextualIngressPtLabel_deliveryShortLine']");
	By BrazilianZipCodePreffix = By.xpath ("//*[@id='GLUXZipUpdateInput_0']");
	By BrazilianZipCodeSuffix = By.xpath ("//*[@id='GLUXZipUpdateInput_1']");
	By SubmitZipCodeConfirmation = By.xpath("//*[@id='GLUXZipUpdate']/..");
	
//	@Override
//	@Before
//	public void startDriver() {}
	
	public RequestItemPage() {
		super();
	}
	
	
	public void selectZipCode() {
		clickOnElement(selectZipCodeLink);	
	}
	
//	public void addZipCode (String zipCode) {
//		
//		clickOnElement(selectAddressLink);
//		redirectWait();
//		scriptWait();
//		
//		
//		
//		//must be flexible to regional implementation rules
//		//magic numbers! refine it
//		//Should'nt you validate "invalid cpf" or "cpf regex match" cases?
//		writeTextOnElementField(BrazilianZipCodePreffix, zipCode.substring(0,5));
//		writeTextOnElementField(BrazilianZipCodePreffix, zipCode.substring(5,zipCode.length()));
//		clickButton(singlePageResultImageFramePartialXPATH);
//		
//	}

	
	private void highlightElements (List<WebElement> welemList) {
		// formatar

		welemList.stream().map(p-> p.findElement(singlePageResultImageFramePartialXPATH)).
		forEach(elem-> {
				executeJS(
						
						"arguments[0].style.border = arguments[1]",
						elem,
						
						//PQP! a ordem dos fatores altera o produto! Em css!
						"solid 4px fuchsia"
				);
	
				scriptWait();
			}
		);
		
		scriptWait();

	}
	
	public WebElement searchAngGatherTheFirstResultItem (String searchQueryText) {
		redirectWait();
		writeTextOnElementField(searchBar, searchQueryText);

		actionMoveToWebElementAndClick(searchButton);
		
		scriptWait();
		redirectWait();
		
		
		
		return null;
	}
	
	private void startSearch (String searchQueryString) {
		System.out.println("\n\nO valor da String passada é:\n\n" + searchQueryString);

		redirectWait();
		writeTextOnElementField(searchBar, searchQueryString);

		actionMoveToWebElementAndClick(searchButton);
		
		scriptWait();
		redirectWait();
	}
	
	private List<WebElement> searchAndGatherItems(String searchQueryText, boolean highLight) {
		
		startSearch(searchQueryText);
		var relevantElements = getRelevantResults(searchQueryText.trim().toLowerCase());

		if (highLight)highlightElements(relevantElements);

		//clickOnElement(selectAddressLink);
		
		return relevantElements;

	}
	
	public List<WebElement> searchAndGatherItems(String searchQueryText) {
		
		return searchAndGatherItems(searchQueryText, false);

	}
	
	public List<WebElement> searchAndGatherItemsAndHighlightResults(String searchQueryText) {
		
		return searchAndGatherItems(searchQueryText, true);

	}

	public List<WebElement> searchAndHighlighElements(String searchQueryText) {
		
		var relevantElements = searchAndGatherItems(searchQueryText);
		redirectWait();
		highlightElements(relevantElements);
		//System.out.println(relevantElements.get(2).getText());
	//	System.out.println(elem.toString());

				//clickOnElement(selectAddressLink);
		
		
		
		return relevantElements;

	}



	private List<WebElement> getRelevantResults(String searchQuery) {

		redirectWait();
		

		var searchQueryWithTrimAndCaseLowered = searchQuery.trim().toLowerCase();
		
		
		Predicate<WebElement> containsExactMatch = welem -> welem.findElement(singlePageResultTitlesPartialXPATH).getText()
				.toLowerCase().trim().contains(searchQueryWithTrimAndCaseLowered);
		
//		Predicate<WebElement> startsWithKeyword = welem -> welem.findElement(singlePageResultTitlesPartialXPATH).getText()
//				.toLowerCase().trim().contains(searchQueryWithTrimAndCaseLowered.split(" ")[0]);

//		Predicate<WebElement> isRelevant = welem -> welem.findElement(singlePageResultTitlesPartialXPATH).getText()
//				.toLowerCase().trim().startsWith(searchQueryWithTrimAndCaseLowered.split(" ")[0]);

		
		// List<WebElement> results =
		// getDriver().findElements(singlePageResultFrames).stream().filter(isTitleRelevant).collect(Collectors.toList());

		
		
		var l = getDriver().findElements(singlePageResultFrames).stream().filter(containsExactMatch)
				.collect(Collectors.toList());

		redirectWait();


		return l;
	}



//	public void startSearch(String searchQueryString) {
//		//redirectWait();
//		writeTextOnElementField(searchBar, searchQueryString);
//		
//		moveToWebElementAndClick(searchButton);
//		
//	}
}
