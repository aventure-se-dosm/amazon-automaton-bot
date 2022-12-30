package br.dev.marcelodeoliveira.amazonautomatonbot.pages.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import io.netty.util.internal.shaded.org.jctools.queues.MessagePassingQueue.Consumer;
import static br.dev.marcelodeoliveira.amazonautomatonbot.core.DriverFactory.getDriver;

import java.time.Duration;

public class CssTools {

	private JavascriptExecutor js = (JavascriptExecutor) getDriver();

	public void scriptWait() {
		getDriver().manage().timeouts().scriptTimeout(Duration.ofSeconds(1));

	}

	public void pageLoadWait() {
		getDriver().manage().timeouts().pageLoadTimeout(Duration.ofMinutes(1));

	}

	public void webElementHighlight(By elem)

	{
		pageLoadWait();
		var webElem = getDriver().findElement(elem);

		// js.executeScript("arguments[0].style.border = arguments[1]", webElem, "solid
		// 4px fuchsia");
		// scriptWait();

		js.executeScript("arguments[0].setAttribute('style','border:5px red solid;background:yellow');", webElem);
		// scriptWait();

	}

}
