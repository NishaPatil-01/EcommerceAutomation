package com.tkb.profile;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class AmazonSignInTest  {
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
    public void setup() {
    	System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\user\\Downloads\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
        driver = new ChromeDriver();
      
        driver.manage().window().maximize();
    }

    @Test
    public void testAmazonSignup() throws IOException, InterruptedException {
    	driver.get(readProperty("url"));
    	String email = readProperty("email");
        String password = readProperty("password");
    	// Click on 'Sign in' button
        driver.findElement(By.id("nav-link-accountList")).click();
       
        Thread.sleep(1200);

        // Enter email or phone
        driver.findElement(By.id("ap_email")).sendKeys(email);
        driver.findElement(By.id("continue")).click();
       
        // Enter password
        driver.findElement(By.id("ap_password")).sendKeys(password);
        driver.findElement(By.id("signInSubmit")).click();
        
        // Verify if Sign-In was successful (Check Account Name)
        WebElement accountElement = driver.findElement(By.id("nav-link-accountList-nav-line-1"));
        String accountName = accountElement.getText();
        Assert.assertTrue(accountName.contains("Hello"), "Sign-In failed!");

        System.out.println("User successfully signed in!");
    }

    @AfterClass
    public void teardown() {
      driver.quit();
    }
}
