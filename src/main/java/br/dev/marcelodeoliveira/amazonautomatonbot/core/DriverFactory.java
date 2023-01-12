package br.dev.marcelodeoliveira.amazonautomatonbot.core;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;


public class DriverFactory {

	private static WebDriver driver;
	
	private DriverFactory() {};
	
	private static boolean isDriverNull() {
		return (driver == null);
	}
	
	static void  setDriver() {
		
		ChromeOptions co = new ChromeOptions();
		co.addArguments("--disable-notifications");
		co.setCapability("intl.accept_languages", "language");
		driver = new ChromeDriver(co);
		driver.manage().window().fullscreen();

		
		
		
		
		
		
	}
	
	
	public static WebDriver getDriver() {
		if (isDriverNull()) {
			setDriver();
		}
		
		return driver;
		
	}
	
	public static void closeDriver() {
		if (getDriver() != null) {
			driver.close();
		}
		
		driver = null;
	}
	public static void killDriver() {
		if (getDriver() != null) {
			driver.quit();
		}
		
		driver = null;
	}
}
