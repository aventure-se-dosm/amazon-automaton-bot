package br.dev.marcelodeoliveira.amazonautomatonbot.core;

import static br.dev.marcelodeoliveira.amazonautomatonbot.core.DriverFactory.getDriver;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.Assert;
import org.junit.Before;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

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

	/********* TextField and TextArea ************/

	public void writeTextOnElementField(By by, String text) {
		// getDriver().switchTo().defaultContent();

		getDriver().findElement(by).clear();

		getDriver().findElement(by).sendKeys(text);
	}

	public void writeTextOnElementField(String fieldId, String text) {
		writeTextOnElementField(By.id(fieldId), text);
	}

	public String getFieldValue(String fieldId) {
		return getDriver().findElement(By.id(fieldId)).getAttribute("value");
	}

	/********* Radio e Check ************/

	public void clickOnElement(String id) {
		getDriver().findElement(By.id(id)).click();
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

	public String getElementValue(String id) {
		return getDriver().findElement(By.id(id)).getAttribute("value");
	}

	/********* Link ************/

	public void clickOnLink(String link) {
		getDriver().findElement(By.linkText(link)).click();
	}

	/********* Textos ************/

	public String getText(By by) {
		return getText(getDriver().findElement(by));
	}

	public String getTrimmedText(By by) {
		return getText(getDriver().findElement(by)).trim();
	}

	public String getText(String id) {
		return getText(By.id(id));
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
		switchToDefaultContent();
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

	public void switchToFrame(String id) {
		getDriver().switchTo().frame(id);
	}

	public void switchToDefaultContent() {
		getDriver().switchTo().defaultContent();
	}

	public void switchToWindow(String id) {
		getDriver().switchTo().window(id);
	}

	/********* JavaScript ************/

	public void executeJS(String jsCode, Object... args) {

		JavascriptExecutor js = (JavascriptExecutor) getDriver();

		js.executeScript(jsCode, args);

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

	public void clickButton(By xpath) {
		getDriver().findElement(xpath).click();

	}


	public String moveToWebElementAndClick(WebElement clickableElement) {
		actions.moveToElement(clickableElement).click().perform();
		return getText(clickableElement);
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

}
