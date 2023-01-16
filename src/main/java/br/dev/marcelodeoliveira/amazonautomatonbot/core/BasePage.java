package br.dev.marcelodeoliveira.amazonautomatonbot.core;

import static br.dev.marcelodeoliveira.amazonautomatonbot.core.DriverFactory.getDriver;

import java.nio.charset.Charset;
import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver.Timeouts;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;

import br.dev.marcelodeoliveira.amazonautomatonbot.utils.ConversionUtils;

public class BasePage {

	protected String path;
	protected Actions actions;

	protected By navLinkAccountList = By.xpath("//*[@id='nav-link-accountList']");
	protected By navBarLoginStatus = By.xpath("//*[@id='nav-link-accountList-nav-line-1']");
	protected By navFlyOut = By.xpath("//*[@id='nav-flyout-accountList']");
	protected By logoutListElement = By.xpath("//*[@id='nav-item-signout']/*[.='Sair da conta']");
	protected By navItemSignout = By.xpath("//*[@id='nav-item-signout']");

	protected By singlePageResultPartialXPATH = By.xpath(
			"//*[@class='a-section aok-relative s-image-square-aspect']/ancestor::div[@class='a-section a-spacing-base']");

	protected ConversionUtils conversionUtils = new ConversionUtils();

	private JavascriptExecutor js;

	protected BasePage() {
		this.path = CoreProperties.BASE_PATH;
		this.actions = new Actions(getDriver());
	}

	public Actions getActions() {
		return actions;
	}

	protected Wait<WebDriver> divWait = fluentWait(Duration.ofSeconds(15), Duration.ofMillis(250));

//	@Before
//	public void startDriver() {
//
//		getDriver().get(CoreProperties.BASE_PATH.toString());
//		// getDriver().manage().window().setSize(new Dimension(1366, 796));
//
//	}

	/*********
	 * Element Presence
	 * 
	 * @return
	 *********/
	public boolean isElementPresent(By xpath) {
		return getDriver().findElements(xpath).size() > 0;
	}

	public boolean isElementDisplayed(By xpath) {// *[@id='GLUXZipError']//i/following-sibling::*vi(By xpath) {
		return getDriver().findElement(xpath).isDisplayed();
	}

	/********* TextField and TextArea ************/

	public void writeTextOnElementField(By by, String text) {
		var fwait = fluentWait();
		try {
		fwait.until(ExpectedConditions.presenceOfElementLocated(by));
		} catch (Exception n) {
			n.printStackTrace();
			return;		}
		getDriver().findElement(by).clear();

		getDriver().findElement(by).sendKeys(text);

		// getDriver().switchTo().defaultContent();
	}

	public void writeTextOnElementField(String fieldId, String text) {
		var fwait = fluentWait();
		writeTextOnElementField(By.id(fieldId), text);
	}

	public String getFieldValue(String fieldId) {
		return getDriver().findElement(By.id(fieldId)).getAttribute("value");
	}

	/********* Radio e Check ************/

	public void clickOnElement(WebElement webElement) {
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		webElement.click();
	}

	public void clickOnElement(By by) {
		clickOnElement(getDriver().findElement(by));
	
	}

	public boolean isRadioChecked(String id) {
		return getDriver().findElement(By.id(id)).isSelected();
	}

	public void selectCheckbox(String id) {
		getDriver().findElement(By.id(id)).click();
	}

	public boolean isCheckboxSelected(String id) {
		return getDriver().findElement(By.id(id)).isSelected();
	}

	/********* Combo ************/

	public void selectCombo(String id, String value) {
		WebElement element = getDriver().findElement(By.id(id));
		Select combo = new Select(element);
		combo.selectByVisibleText(value);

	}

	public void selectCombo(By by, String value) {
		WebElement element = getDriver().findElement(by);
		Select combo = new Select(element);
		combo.selectByVisibleText(value);

	}

	public void deselectCombo(By xpath, String... values) {

		WebElement element = getDriver().findElement(xpath);
		Select combo = new Select(element);
		for (String value : values)
			combo.deselectByValue(value);

	}

	public String getComboOptionValue(String id) {
		WebElement element = getDriver().findElement(By.id(id));
		Select combo = new Select(element);
		return combo.getFirstSelectedOption().getText();
	}

	public List<String> getAllComboOptionValues(String id) {

		Select combo = new Select(getDriver().findElement(By.id(id)));
		return combo.getAllSelectedOptions().stream().map(op -> op.getText()).collect(Collectors.toList());

	}

	public int getComboOptionsQuantity(String id) {
		WebElement element = getDriver().findElement(By.id(id));
		Select combo = new Select(element);
		return combo.getOptions().size();

	}

	public boolean isComboOptionPresent(String id, String opcao) {

		Select combo = new Select(getDriver().findElement(By.id(id)));

		return combo.getOptions().stream().map(p -> p.getText().equals(opcao)).findAny().isPresent();

	}

	/********* Botao ************/

	public void clickButton(String id) {
		getDriver().findElement(By.id(id)).click();
	}

	public void clickButton(By xpath) {
		getDriver().findElement(xpath).click();
	}

	public String getElementValue(By by) {
		return getDriver().findElement(by).getAttribute("text");
	}

	public String getElementAttribute(By by, String attribute) {
		return getDriver().findElement(by).getAttribute(attribute);
	}

	/********* Link ************/

	public void clickOnLink(String link) {
		getDriver().findElement(By.linkText(link)).click();
	}

	/********* Textos ************/

	public String getElementsText(By by) {
		var joinedElems = getDriver().findElements(by).stream().map(e -> e.getText()).filter(txt -> !txt.isEmpty())
				.collect(Collectors.joining("\n"));

		System.out.println(joinedElems);
		return joinedElems;
	}

	protected List<WebElement> getWebElements(By by) {
		return getDriver().findElements(by);
	}

	protected WebElement getWebElement(By by) {
		return getDriver().findElement(by);
	}

	public String getElementsText(WebElement elem) {
		return getText(elem);
//
//		System.out.println(text);
		// return new String(rawText.getBytes(Charset.forName("utf-8");
	}

	public String getText(By by) {
		return getElementsText(getDriver().findElement(by));
		/**
		 * Código encontrado na internet:
		 * 
		 * Fonte:
		 * 
		 * https://stackoverflow.com/questions
		 * /16913972/selenium-web-driver-and-multillanguage /24449050#24449050
		 * 
		 * new String(tmp.getBytes(Charset.forName("utf-8")));
		 */
		// return new String(rawText.getBytes(Charset.forName("utf-8")));
	}

	public String getTrimmedText(By by) {
		return getText(getDriver().findElement(by)).trim();
	}

	public String getText(String id) {
		return getText(By.id(id));
	}

	public String getText1(By xpath) {
		return getDriver().findElement(xpath).getText();
	}

	/********* Alerts ************/

	public String getAlertText() {
		Alert alert = getDriver().switchTo().alert();
		return alert.getText();
	}

	public String getAlertTextAndAccept() {
		Alert alert = getDriver().switchTo().alert();
		String value = alert.getText();
		alert.accept();
		// switchToDefaultContent();
		return value;

	}

	public String getAlertTextAndDismiss() {
		Alert alert = getDriver().switchTo().alert();
		String value = alert.getText();
		alert.dismiss();
		return value;

	}

	public void writeOnAlert(String value) {
		Alert alert = getDriver().switchTo().alert();
		alert.sendKeys(value);
		alert.accept();
	}

	/********* Frames and Windows ************/

	public void switchToActiveElement(By xpath) {
		getDriver().switchTo().activeElement().findElement(xpath);
	}

	public void switchToFrame(String id) {
		getDriver().switchTo().frame(id);
	}

	public void switchToDefaultContent() {
		getDriver().switchTo().defaultContent();
	}

	public void switchToPopUp() {
		getDriver().switchTo().window((String) getDriver().getWindowHandles().toArray()[1]);
	}

	public void switchToWindow(int index) {
		getDriver().switchTo().window((String) getDriver().getWindowHandles().toArray()[index]);
	}

	/********* JavaScript ************/

	public void executeJS(String jsCode, Object... args) {

		js = (JavascriptExecutor) getDriver();
		js.executeScript(jsCode, args);

	}

	public void highlightElements(List<WebElement> welemList) {
		// formatar
		if(welemList.isEmpty()) return;
		
		welemList.stream().forEach(elem -> {
			executeJS(

					"arguments[0].style.border = arguments[1]", elem,

					// PQP! a ordem dos fatores altera o produto! Em css!
					"solid 4px fuchsia");

			scriptWait();
		});

		scriptWait();
		
		scrollIntoView(welemList.get(0));

	}

	public void scrollIntoView(WebElement welem) {
//		executeJS("window.scrollBy(0, arguments[0]);", elemVerticalPosition);
		executeJS("(arguments[0]).scrollIntoView();", welem);

	}

	public void scrollIntoView(By by) {
//		executeJS("window.scrollBy(0, arguments[0]);", elemVerticalPosition);
		scrollIntoView(getWebElement(by));

	}

	public void scrollIntoViewAndClick(WebElement welem) {

		implicityWait(Duration.ofSeconds(2));

		if (!welem.isDisplayed()) {
			scrollIntoView(welem);

			if (!welem.isDisplayed())
				return;

			divWait.until(ExpectedConditions.visibilityOfAllElements(welem));
			welem.click();
		}
	}

	public void scrollIntoViewAndClick(By by) {

		var elems = getWebElements(by);
		for (WebElement w : elems)
			scrollIntoViewAndClick(w);

	}

//	public void scrollVertically(int elemVerticalPosition) {
////		executeJS("window.scrollBy(0, arguments[0]);", elemVerticalPosition);
//		executeJS("arguments[0].scrollIntoView();", elemVerticalPosition);
//
//	}

	//// div[@class = 'a-section a-spacing-base']

	public void ItemlementFormatter(By elementXpath) {

	}

	/********* table ************/

	/**
	 * Objetivos
	 * 
	 * Obter índices de linha e coluna para criar um xpath que clique no botão
	 * correspondente ao nome.
	 * 
	 * Testar a mensagem aberta. só pelo classpath montado que o botão
	 * correspondente sertá clicado. indexação html: 1 a n; n >=1.
	 */

//	private int obterIndiceColuna(WebElement table, String attribute) {
//
//		List<WebElement> colunas = table.findElements(By.xpath("./thead//th"));
//		var col = colunas.indexOf(colunas.stream().filter(tr -> tr.getText().equals(attribute)).findFirst().get());
//
//		// ++col; // incremento html;
//
//		return (++col);
//	}

	private int getRowIndex(WebElement table, String value) {

		List<WebElement> rows = table.findElements(By.xpath("./tbody/tr"));
		Assert.assertEquals(rows.size(), 5);

		WebElement elem = table.findElement(By.xpath("./tbody/tr/td[.='" + value + "']/.."));

		Assert.assertTrue(rows.contains(elem));

		return (rows.indexOf(elem) + 1);

	}

	public String interactTable(String value, String elemType, String tabelaId) {

		var table = getDriver().findElement(By.xpath("//table[@id='" + tabelaId + "']"));

		int indiceLin = getRowIndex(table, value);

		var button = table.findElement(By.xpath("./tbody/tr[" + indiceLin + "]/td/input[@type='" + elemType + "']"));

		Assert.assertEquals("input", button.getTagName());
		Assert.assertEquals("button", button.getAttribute("type"));
		button.click();

		return getAlertTextAndDismiss();

	}

	public String actionMoveToWebElementAndClick(By clickableElement) {
		return actionMoveToWebElementAndClick(getDriver().findElement(clickableElement));
	}

	public String actionMoveToWebElementAndClick(WebElement clickableElement) {
		var clickableElementText = getText(clickableElement);
		actions.moveToElement(clickableElement).click().perform();
		return clickableElementText;
	}

	private String getText(WebElement clickableElement) {

		// return clickableElement.getText();
		return new String(clickableElement.getText().getBytes(Charset.forName("utf-8")));
	}

	public String getFieldValue(By xpath) {

		return getDriver().findElement(xpath).getAttribute("value");
	}

	public String getPageTitle() {
		return getDriver().getTitle();
	}

	/********* Waits ************/

	public void redirectWait() {
		getDriver().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(100L));
	}

	public void resultGatheringWait() {
		getDriver().manage().timeouts().scriptTimeout(Duration.ofMinutes(1L));
		// switchToFrame(path);
	}

//	public void implicityWaitOf(Duration duration) {
//		Timeouts x = getDriver().manage().timeouts().implicitlyWait(duration);
//		try {
//			x.wait();
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		// switchToFrame(path);
//	}

	public void scriptWait() {
		getDriver().manage().timeouts().scriptTimeout(Duration.ofSeconds(60));

	}

	public void waitForElements(By xpath) {

		Wait<WebDriver> fwait = new FluentWait<>(getDriver()).withTimeout(Duration.ofSeconds(10))
				.pollingEvery(Duration.ofMillis(250)).ignoring(NoSuchElementException.class);

		fwait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(xpath));

	}

	public void waitForElementPresence(By xpath) {

		Wait<WebDriver> fwait = new FluentWait<>(getDriver()).withTimeout(Duration.ofSeconds(10))
				.pollingEvery(Duration.ofMillis(250)).ignoring(NoSuchElementException.class);

		fwait.until(ExpectedConditions.presenceOfElementLocated(xpath));

	}

	public Wait<WebDriver> fluentWait() {

		return fluentWait(Duration.ofSeconds(3), Duration.ofMillis(250), new TimeoutException());

	}

	public <E extends Exception> Wait<WebDriver> fluentWait(E... e) {

		// smell: set both defaut timeout and pollig times on a propertu file!
		return fluentWait(Duration.ofSeconds(2), Duration.ofMillis(250), e);

		// wait.until(ExpectedConditions.visibilityOfElementLocated(xpath));

	}

	public <E extends Exception> Wait<WebDriver> fluentWait(Duration timeOut, Duration pollingTime, E... e) {

		return new FluentWait<>(getDriver()).withTimeout(timeOut).pollingEvery(pollingTime)
				.ignoreAll(Stream.of(e).map(excp -> excp.getClass()).distinct().collect(Collectors.toList()));
		// wait.until(ExpectedConditions.visibilityOfElementLocated(xpath));

	}

	public ExpectedCondition<WebElement> ElementIsVisible(By by) {
		return ExpectedConditions.visibilityOfElementLocated(by);
	}

	public ExpectedCondition<WebElement> ElementIsPresent(By by) {
		return ExpectedConditions.visibilityOfElementLocated(by);
	}

	public void waitForElementAndClick(By xpath) {

		waitForElementPresence(xpath);

		getDriver().findElement(xpath).click();
	}
	
	

	public void implicityWait(Duration duration) {
		getDriver().manage().timeouts().implicitlyWait(duration);
	}

	public void implicityWait2(Duration duration) {
		try {
			getDriver().manage().window().wait(5000, 0);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void movingDivWait() {

		// BAD Smell: Put the IL time onto a page/core/which W-ever property!
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
//	
	/********* url ************/

//	@AfterEach
//	public static void finaliza() {
//		DriverFactory.closeDriver();
//	}

}
