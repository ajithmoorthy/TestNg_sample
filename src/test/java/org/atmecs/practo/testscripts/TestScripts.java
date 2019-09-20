package org.atmecs.practo.testscripts;

import java.io.IOException;

import org.atmecs.practo.constants.FileConstants;
import org.atmecs.practo.utils.ExcelReader;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

public class TestScripts {
	ExcelReader excelReader=new ExcelReader();	
	@DataProvider(name="sample")
	public String[][] dataProvider() throws IOException{
		String[][] dataProvider=excelReader.excelDataProviderArray(FileConstants.CART_PAGE_PATH);
		return dataProvider;
	}
	
	@Test(dataProvider="sample")
	public void testngExample(String str1,String str2,String str3,String str4,String str5,String str6,String str7,String str8,String str9) {
		System.out.println(str1);
		System.out.println(str2);
		System.out.println(str3);
		System.out.println(str4);
		System.out.println(str5);
		System.out.println(str6);
		System.out.println(str7);
		System.out.println(str8);
		System.out.println(str9);
	}
	
	@Factory
	public Object[] tomCat() throws IOException{
		
		return new Object[] {new TestScripts(),new TestScripts()};
	
			}


}
