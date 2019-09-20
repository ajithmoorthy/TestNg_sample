package org.atmecs.practo.helper;


import java.util.Set;
import org.atmecs.practo.extentreports.Extent;
import org.atmecs.practo.reports.LogReporter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import com.relevantcodes.extentreports.LogStatus;
/*
 * Class is created for the implement the reusablity
 * it is contains many method when ever we want we can access*/
public class ActionHelper extends Extent {
	LogReporter log=new LogReporter();
	By xpath,id,name,className,linkText,cssSelector,partialLink,tag;
	/**
	 * this method will perform the click operation ion web elements*/
	public void clickElement(String locators, WebDriver webdriver) {
		String[] input=locators.split(",");
		switch(input[0]){
		case "XPATH":
			xpath=By.xpath(input[1]);
			actionMethod(webdriver,xpath);
			break;
		case "ID":
			id=By.xpath(input[1]);
			actionMethod(webdriver,id);
			break;
		case "NAME":
			name=By.xpath(input[1]);
			actionMethod(webdriver,name);
			break;
		case "CSS_SELECTOR":
			cssSelector=By.xpath(input[1]);
			actionMethod(webdriver,cssSelector);
			break;
		case "CLASS":
			className=By.xpath(input[1]);
			actionMethod(webdriver,className);
			break;
		case "LINK_TEXT":
			linkText=By.xpath(input[1]);
			actionMethod(webdriver,linkText);
			break;
		case "PARTIAL_LINK_TEXT":
			partialLink=By.xpath(input[1]);
			actionMethod(webdriver,xpath);
			break;	
		case "TAG_NAME":
			tag=By.xpath(input[1]);
			actionMethod(webdriver,xpath);
			break;
		default:
			System.out.println("Locator type doesn't exist ");
			break;
		}
	}
	public void actionMethod(WebDriver webdriver,By locator) {
		try {
		WebDriverWait wait2 = new WebDriverWait(webdriver, 20);
		wait2.until(ExpectedConditions.elementToBeClickable(locator));
		WebElement click_operation = webdriver.findElement(locator);
		click_operation.click();
		}catch(Exception e) {
			System.out.println(e);
		}
	}
	public WebDriver dropDown(String locators, WebDriver webdriver, int index) {
		WebDriverWait wait2 = new WebDriverWait(webdriver, 20);
		wait2.until(ExpectedConditions.elementToBeClickable(By.xpath(locators)));
		WebElement dropdown = webdriver.findElement(By.xpath(locators));
		Select select = new Select(dropdown);
		select.selectByIndex(index);
		return webdriver;
	}
	public void sendKeys(String locators, WebDriver webdriver,String value) {
		WebDriverWait wait2 = new WebDriverWait(webdriver, 20);
		wait2.until(ExpectedConditions.elementToBeClickable(By.xpath(locators)));
		WebElement sendtext = webdriver.findElement(By.xpath(locators));
		sendtext.sendKeys(value);
	}
	//this the method for handle the windows in web driver
	public WebDriver winHandler(WebDriver driver) {
		String window_array[]=new String[5];
		Set<String> windows=driver.getWindowHandles();
		int initial=0;
		for (String win:windows)
		{
			window_array[initial]=win;
		}
		driver=driver.switchTo().window(window_array[0]);
		return driver;
	}
	//this method will check the url is correct or not
	public void correctUrlChecker(WebDriver Driver,String Expected_Url){
		try {
			Assert.assertEquals(Driver.getCurrentUrl(),Expected_Url);
			log.logReportMessage("Successfully Validated the correct Url is :"+ Driver.getCurrentUrl());
			logger.log(LogStatus.INFO,"Successfully Validated the correct Url is :" +Driver.getCurrentUrl());
		}catch(AssertionError e) {
			System.out.println("Navigate to wrong Webpage");
			log.logReportMessage("Navigate to wrong Webpage");
			logger.log(LogStatus.INFO, "Navigate to wrong Webpage");
		}	
	}
	//this method validate the page document title
		public void validateTitle(WebDriver driver, String documentTitle){
			try {
				Assert.assertEquals(driver.getTitle(), documentTitle);
				log.logReportMessage("Document title is validated :"+driver.getTitle());
				logger.log(LogStatus.INFO,"Document title is validated :" +driver.getTitle());
			}
			catch(AssertionError e)
			{
			System.out.println("Document title is not match with Expected :"+driver.getTitle());
			log.logReportMessage("Document title is not match with Expected :"+driver.getTitle());
			logger.log(LogStatus.INFO,"Document title is not match with Expected :"+driver.getTitle());	
			}
		}

}
