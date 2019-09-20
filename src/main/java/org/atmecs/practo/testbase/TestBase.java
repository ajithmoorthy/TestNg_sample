package org.atmecs.practo.testbase;

import java.io.IOException;
import java.util.Properties;

import org.atmecs.practo.constants.FileConstants;
import org.atmecs.practo.extentreports.Extent;
import org.atmecs.practo.utils.PropertiesReader;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.testng.annotations.BeforeTest;

/*this class will act as the base class for the test it will provide the browser based on the user choice */
public class TestBase extends Extent {
	PropertiesReader propertyReader = new PropertiesReader();
	/*
	 * this method will provide the browser driver based on the user need by using
	 * the switch case and properties file
	 */
	@BeforeTest
	public void baseSetup() throws IOException {
		Properties prop = propertyReader.KeyValueLoader(FileConstants.CONFIG_PATH);
		switch (prop.getProperty("webdrivername")) {
		case "CHROME":
			System.setProperty("webdriver.chrome.driver", FileConstants.CHROME_DRIVER_PATH);
			ChromeOptions chromeoptions = new ChromeOptions();
			chromeoptions.addArguments("--disable-notifications");
			chromeoptions.addArguments("disable-geolocation");
			driver = new ChromeDriver(chromeoptions);
			break;
		case "FIREFOX":
			System.setProperty("webdriver.gecko.driver", FileConstants.FIREFOX_DRIVER_PATH);
			FirefoxOptions fire = new FirefoxOptions();
			fire.addPreference("dom.webnotifications.enabled", false);
			driver = new FirefoxDriver(fire);
			break;
		case "IE":
			System.setProperty("webdriver.ie.driver", FileConstants.IE_DRIVER_PATH);
			InternetExplorerOptions ops = new InternetExplorerOptions();
		    ops.introduceFlakinessByIgnoringSecurityDomains();
			driver = new InternetExplorerDriver(ops);
			break;
		case "EDGE":
			System.setProperty("webdriver.edge.driver", FileConstants.EDGE_DRIVER_PATH);
			driver = new EdgeDriver();
			break;
		}
	}
}
