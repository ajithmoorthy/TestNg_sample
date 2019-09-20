package it.pkg.testbase;

import java.io.IOException;
import java.util.Properties;

import it.pkg.constants.FileConstants;
import it.pkg.extentreports.Extent;
import it.pkg.reports.LogReporter;
import it.pkg.utils.ExcelReader;
import it.pkg.utils.PropertiesReader;
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
	PropertiesReader propread = new PropertiesReader();
	ExcelReader excelreader = new ExcelReader();
	LogReporter log = new LogReporter();

	/*
	 * this method will provide the browser driver based on the user need by using
	 * the switch case and properties file
	 */
	@BeforeTest
	public void baseSetup() throws IOException {
		Properties prop = propread.property(FileConstants.config_file);
		switch (prop.getProperty("webdrivername")) {
		case "chrome":
			System.setProperty("webdriver.chrome.driver", FileConstants.chromefile);
			ChromeOptions chromeoptions = new ChromeOptions();
			chromeoptions.addArguments("--disable-notifications");
			chromeoptions.addArguments("disable-geolocation");
			driver = new ChromeDriver(chromeoptions);
			log.logReport("Open chorme browser");
			break;
		case "firefox":
			System.setProperty("webdriver.gecko.driver", FileConstants.firefoxfile);
			FirefoxOptions fire = new FirefoxOptions();
			fire.addPreference("dom.webnotifications.enabled", false);
			driver = new FirefoxDriver(fire);
			log.logReport("Open firefox browser");
			break;
		case "ie":
			System.setProperty("webdriver.ie.driver", FileConstants.Iefile);
			InternetExplorerOptions ops = new InternetExplorerOptions();
		    ops.introduceFlakinessByIgnoringSecurityDomains();
			driver = new InternetExplorerDriver(ops);
			log.logReport("Open Ie browser");
			break;
		case "edge":
			System.setProperty("webdriver.edge.driver", FileConstants.edgefile);
			driver = new EdgeDriver();
			log.logReport("Open Edge browser");
			break;
		}
	}
}
