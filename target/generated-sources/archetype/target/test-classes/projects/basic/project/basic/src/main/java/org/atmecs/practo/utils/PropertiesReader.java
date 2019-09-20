package org.atmecs.practo.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * 
 * @author ajith.periyasamy
 *this class read the properties file using the property method
 */

public class PropertiesReader {
	// property method read the value based on the key value pair
	public  Properties property(String path) throws IOException {
		File file = new File(path);
		Properties prop = new Properties();
		FileInputStream input = new FileInputStream(file);
		prop.load(input);
		return prop;
	}
}
