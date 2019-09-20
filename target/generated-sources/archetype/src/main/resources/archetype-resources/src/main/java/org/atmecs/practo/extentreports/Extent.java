#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package org.atmecs.practo.extentreports;
import java.io.File;
import org.apache.commons.io.FileUtils;
import org.atmecs.practo.constants.FileConstants;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

/**
 * This the Extent report class is for generate  the report for the project in web application manner 
 * and give pie chart of project application.  
 * @author ajith.periyasamy
 *
 */
public class Extent {
	public static  WebDriver driver;
	public static ExtentReports extent;
	public static  ExtentTest logger;

	@BeforeSuite
	public void startReport() {

		extent = new ExtentReports(FileConstants.extentfile, true);
		extent.loadConfig(new File(FileConstants.extentconfigfile));
	}

	public static String getScreenshot(WebDriver driver, String testname) throws Exception {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		String destination = FileConstants.pngfile + testname
				+".png";
		File finalDestination = new File(destination);
		FileUtils.copyFile(source, finalDestination);
		return destination;
	}
	@AfterMethod
	public static void tearDown(ITestResult result) throws Exception {

		if (result.getStatus() == ITestResult.FAILURE) {
			logger.log(LogStatus.FAIL, "TEST CASE FAILED IS " + result.getName()); // to add name in extent report
			logger.log(LogStatus.FAIL, "TEST CASE FAILED IS " + result.getThrowable()); // to add error/exception in
			// extent report

			String screenshotPath = Extent.getScreenshot(driver, result.getName());
			logger.log(LogStatus.FAIL, logger.addScreenCapture(screenshotPath)); // to add screenshot in extent
			// report
			// extentTest.log(LogStatus.FAIL, extentTest.addScreencast(screenshotPath));
			// //to add screenshot/video in extent report
		} else if (result.getStatus() == ITestResult.SKIP) {
			logger.log(LogStatus.SKIP, "Test Case SKIPPED IS " + result.getName());
		} else if (result.getStatus() == ITestResult.SUCCESS) {
			logger.log(LogStatus.PASS, "Test Case PASSED IS " + result.getName());
			String screenshotPath = Extent.getScreenshot(driver, result.getName());
			logger.log(LogStatus.PASS, logger.addScreenCapture(screenshotPath));

		}
		 extent.endTest(logger);

	}
	@AfterSuite
	public void endReport() {
	   driver.quit();
	   extent.flush();
	   extent.close();
	}
}