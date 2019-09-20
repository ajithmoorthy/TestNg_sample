#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.constants;
/* file constants are created for the user can run any system the program should run 
 * and the file location is access is make easier using this class */
public class FileConstants {
	//creating file constants for the config properties file paths
	public static final String config_file = "./src/test/resources/testdata/config/config.properties";
	
	//creating file constants for the browser .exe file paths
	public static final String chromefile ="./libs/chromedriver.exe";
	public static final String firefoxfile ="./libs/geckodriver.exe";
	public static final String Iefile="./libs/IEDriverServer.exe";
	public static final String edgefile="./libs/msedgedriver.exe";
	
	//creating file constants for the log4j file path
	public static final String logfile ="./src/test/resources/log4j/log4j.properties";
	
	//creating file constants for the extend report file paths
	public static final String extentfile ="./src/test/resources/extent/extent.html";
	public static final String pngfile=System.getProperty("user.dir")+"/src/test/resources/extent/screenshot/";
	public static final String extentconfigfile ="./src/test/resources/extent/extent-config.xml";
	
	}
