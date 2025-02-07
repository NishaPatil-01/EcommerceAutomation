package com.tkb.profile;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class AmazonTitleTest {
	Properties pro = null;
	FileInputStream fis = null;

	public String readProperty(String key) throws IOException {
		pro = new Properties();
		fis = new FileInputStream(
				"C:\\Users\\user\\eclipse-workspace\\Ecommerce Website\\src\\main\\resources\\configration.pro");
		pro.load(fis);

		String valueOfkey = pro.getProperty(key);
		return valueOfkey;
	}
    WebDriver driver;

    @BeforeClass
    public void setUp() {
        // Set the path to chromedriver if not added to system PATH
    	System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\user\\Downloads\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void verifyAmazonTitle() throws IOException {
        // Navigate to Amazon
    	driver.get(readProperty("url"));

        // Expected Title
        String expectedTitle = "Online Shopping site in India: Shop Online for Mobiles, Books, Watches, Shoes and More - Amazon.in";

        // Get Actual Title
        String actualTitle = driver.getTitle();

        // Print titles for debugging
        System.out.println("Expected Title: " + expectedTitle);
        System.out.println("Actual Title: " + actualTitle);

        // Assertion to compare titles
        Assert.assertEquals(actualTitle, expectedTitle, "Title does not match!");
    }

    @AfterClass
    public void tearDown() {
//        if (driver != null) {
//            driver.quit();
//        }
    }
} 