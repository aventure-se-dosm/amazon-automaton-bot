package br.dev.marcelodeoliveira.amazonautomatonbot.core;

import static br.dev.marcelodeoliveira.amazonautomatonbot.core.DriverFactory.getDriver;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;

public class BasePage {

	protected String path;
	protected Actions actions;

	protected By navLinkAccountList = By.xpath("//*[@id='nav-link-accountList']");
	protected By navBarLoginStatus = By.xpath("//*[@id='nav-link-accountList-nav-line-1']");
	protected By navFlyOut = By.xpath("//*[@id='nav-flyout-accountList']");
	protected By logoutListElement = By.xpath("//*[@id='nav-item-signout']/*[.='Sair da conta']");
	protected By navItemSignout = By.xpath("//*[@id='nav-item-signout']");

	protected BasePage() {
		this.path = CoreProperties.BASE_PATH;
		this.actions = new Actions(getDriver());
	}

	public Actions getActions() {
		return actions;
	}

	@Before
	public void startDriver() {

		getDriver().get(CoreProperties.BASE_PATH.toString());
		// getDriver().manage().window().setSize(new Dimension(1366, 796));

	}

	/*********
	 * Element Presence
	 * 
	 * @return
	 *********/
	public boolean isElementPresent(By xpath) {
		return getDriver().findElements(xpath).size() > 0;
	}

	/********* TextField and TextArea ************/

	public void writeTextOnElementField(By by, String text) {
		// getDriver().switchTo().defaultContent();

		getDriver().findElement(by).clear();

		getDriver().findElement(by).sendKeys(text);

		// getDriver().switchTo().defaultContent();
	}

	public void writeTextOnElementField(String fieldId, String text) {
		writeTextOnElementField(By.id(fieldId), text);
	}

	public String getFieldValue(String fieldId) {
		return getDriver().findElement(By.id(fieldId)).getAttribute("value");
	}

	/********* Radio e Check ************/

	public void clickOnElement(WebElement webElement) {
		webElement.click();
	}

	public void clickOnElement(By by) {
		getDriver().findElement(by).click();
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

	public String getElementValue(String id) {
		return getDriver().findElement(By.id(id)).getAttribute("value");
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
	
	public String getText(By by) {
		var joinedElems = getDriver().findElement(by).getText();
		return joinedElems;
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

		JavascriptExecutor js = (JavascriptExecutor) getDriver();

		js.executeScript(jsCode, args);
		scriptWait();

	}

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

	public String moveToWebElementAndClick(By clickableElement) {
		return moveToWebElementAndClick(getDriver().findElement(clickableElement));
	}

	public String moveToWebElementAndClick(WebElement clickableElement) {
		var clickableElementText = getText(clickableElement);
		actions.moveToElement(clickableElement).click().perform();
		return clickableElementText;
	}

	private String getText(WebElement clickableElement) {
		return clickableElement.getText();
	}

	public String getFieldValue(By xpath) {

		return getDriver().findElement(xpath).getAttribute("value");
	}

	public String getPageTitle() {
		return getDriver().getTitle();
	}

	/********* Waits ************/

	public void redirectWait() {
		getDriver().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(100l));
	}

	public void resultGatheringWait() {
		getDriver().manage().timeouts().scriptTimeout(Duration.ofMinutes(1l));
		// switchToFrame(path);
	}

	public void scriptWait() {
		getDriver().manage().timeouts().scriptTimeout(Duration.ofSeconds(60));

	}

	public void waitForElements(By xpath) {

		Wait<WebDriver> fwait = new FluentWait<>(getDriver()).withTimeout(Duration.ofSeconds(10))
				.pollingEvery(Duration.ofMillis(250)).ignoring(NoSuchElementException.class);

		fwait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(xpath));
	}

	@After
	public static void finaliza() {
		getDriver().close();
	}

}
