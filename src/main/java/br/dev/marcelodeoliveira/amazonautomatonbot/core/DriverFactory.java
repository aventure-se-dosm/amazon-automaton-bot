package br.dev.marcelodeoliveira.amazonautomatonbot.core;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import dev.failsafe.Timeout;

public class DriverFactory {

	private static WebDriver driver;

	private DriverFactory() {
	};

	private static boolean isDriverNull() {
		return (driver == null);
	}

	static void timeoutForWebDriverInitOrKill(int milis) {
		try {
			Thread.sleep(milis, 0);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static void timeoutForWebDriverInitOrKill() {
		try {
			Thread.sleep(1000, 0);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	static void setDriver() {

		ChromeOptions co = new ChromeOptions();
		co.addArguments("--disable-notifications");
		driver = new ChromeDriver(co);
		timeoutForWebDriverInitOrKill();

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
			timeoutForWebDriverInitOrKill();
		}

		driver = null;

	}

	public static void killDriver() {
		if (getDriver() != null) {
			driver.quit();
			timeoutForWebDriverInitOrKill();
		}

		driver = null;
		timeoutForWebDriverInitOrKill();
	}
}
