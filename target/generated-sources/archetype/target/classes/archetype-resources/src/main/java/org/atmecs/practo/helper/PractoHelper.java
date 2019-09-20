#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
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
public class PractoHelper extends Extent {
	LogReporter log=new LogReporter();
	/**
	 * this method will perform the click operation ion web elements*/
	public void clickElement(String locators, WebDriver webdriver) {
		WebDriverWait wait2 = new WebDriverWait(webdriver, 20);
		wait2.until(ExpectedConditions.elementToBeClickable(By.xpath(locators)));
		WebElement click_operation = webdriver.findElement(By.xpath(locators));
		click_operation.click();
	}
	public WebDriver dropdown(String locators, WebDriver webdriver, int index) {
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
	public void correctUrlchecker(WebDriver driver,String expectedurl){
		try {
			Assert.assertEquals(driver.getCurrentUrl(),expectedurl);
			System.out.println("User landed or Reached the correct webpage");
			log.logReport("Successfully Validated the correct Url is :"+ driver.getCurrentUrl());
			logger.log(LogStatus.INFO,"Successfully Validated the correct Url is :" +driver.getCurrentUrl());
		}catch(AssertionError e) {
			log.logReport("Navigate to wrong Webpage");
			logger.log(LogStatus.INFO, "Navigate to wrong Webpage");
		}	
	}
	//this method validate the page document title
		public void validateTitle(WebDriver driver, String title){
			try {
				Assert.assertEquals(driver.getTitle(), title);
				log.logReport("Document title is validated :"+driver.getTitle());
				logger.log(LogStatus.INFO,"Document title is validated :" +driver.getTitle());
			}
			catch(AssertionError e)
			{
			System.out.println("Document title is not match with Expected :"+driver.getTitle());
				
			}
		}

}
