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

public class AmazonCheckoutTest {
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


    	
    	 @Test(priority = 1)
    	    public void testAmazonSignIn() throws IOException, InterruptedException {
    		 driver.get(readProperty("url"));

    	        // Click on 'Sign in' button
    	        driver.findElement(By.id("nav-link-accountList")).click();

    	        String email = readProperty("email");
    	        String password = readProperty("password");

    	     
    	        driver.findElement(By.id("ap_email")).sendKeys(email);
    	        driver.findElement(By.id("continue")).click();
    	        

    	        // Enter password
    	        driver.findElement(By.id("ap_password")).sendKeys(password);
    	        driver.findElement(By.id("signInSubmit")).click();
    	        
    	        Thread.sleep(40000);
    	 

    	        // Verify if Sign-In was successful
    	        WebElement accountElement = driver.findElement(By.id("nav-link-accountList-nav-line-1"));
    	        String accountName = accountElement.getText();
    	        Assert.assertTrue(accountName.contains("Hello"), "Sign-In failed!");

    	        System.out.println("User successfully signed in!");
    	    }

    	    @Test(priority = 2)
    	    public void testSearchAndAddToCart() throws IOException {
    	    //	driver.get(readProperty("url"));
    	        // Search for a product
    	        WebElement searchBox = driver.findElement(By.id("twotabsearchtextbox"));
    	        searchBox.sendKeys("Laptop");
    	        searchBox.submit();

    	        // Click on first product result
    	        driver.findElement(By.id("a-autoid-1-announce")).click();
    	   
    	        // Click 'Add to Cart' button
    	        driver.findElement(By.id("nav-cart-count")).click();

    	        // Verify item added to cart
    	        WebElement cartConfirm = driver.findElement(By.id("sc-buy-box"));
    	        Assert.assertTrue(cartConfirm.isDisplayed(), "Product not added to cart!");

    	        System.out.println("Product added to cart!");
    	    }

    	


    @AfterClass
    public void teardown() {
   //  driver.quit();
    }
}
