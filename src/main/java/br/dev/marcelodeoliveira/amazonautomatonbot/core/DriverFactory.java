package br.dev.marcelodeoliveira.amazonautomatonbot.core;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DriverFactory {

	private static WebDriver driver;
	
	private DriverFactory() {};
	
	private static boolean isDriverNull() {
		return (driver == null);
	}
	
	private static void  setDriver() {
		driver = new ChromeDriver();
	}
	
	
	public static WebDriver getDriver() {
		if (isDriverNull()) {
			setDriver();
		}
		
		return driver;
		
	}
	
	public static void killDriver() {
		if (getDriver() == null) {
			driver.quit();
		}
		
		driver = null;
	}
}
