package com.tkb.profile;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyFix {

	public static void main(String[] args) throws IOException {
		Properties pro=new Properties();
		FileInputStream fis= new FileInputStream ("\"C:\\\\Users\\\\user\\\\eclipse-workspace\\\\Ecommerce Website\\\\src\\\\main\\\\resources\\\\configration.pro\"");
pro.load(fis);
System.out.println(pro.getProperty("url"));
System.out.println(pro.getProperty("username"));
System.out.println(pro.getProperty("password"));
	}

}
