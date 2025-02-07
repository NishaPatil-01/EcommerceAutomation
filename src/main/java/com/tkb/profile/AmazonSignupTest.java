package com.tkb.profile;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;

public class AmazonSignupTest {
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

		// Navigate to Signup Page
		driver.findElement(By.id("nav-link-accountList")).click();
		driver.findElement(By.id("createAccountSubmit")).click();
        String email = readProperty("email");
        String username = readProperty("username");
        String password = readProperty("password");
		// Fill in Signup Form
		driver.findElement(By.id("ap_customer_name")).sendKeys(username);
		driver.findElement(By.id("ap_phone_number")).sendKeys("9876543210");
		driver.findElement(By.id("ap_password")).sendKeys(email);

		driver.findElement(By.id("continue")).click();

		// Get Actual Title
		String actualTitle = driver.getTitle();

		System.out.println("Actual Title: " + actualTitle);
		// Expected Title
		String expectedTitle = "Amazon Registration";
		// Assertion to compare titles
		System.out.println("Expected Title: " + expectedTitle);
		Assert.assertEquals(actualTitle, expectedTitle, "Title does not match!");

		
	}

	// Capture screenshot on test failure
	@AfterMethod
	public void takeScreenshotOnFailure(ITestResult result) {
		if (ITestResult.FAILURE == result.getStatus()) {
			takeScreenshot(result.getName());
		}
	}

	// Method to take a screenshot
	public void takeScreenshot(String testName) {
		File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

		// Define folder path
		String folderPath = "./screenshots/";
	
		// Create folder if it doesn't exist
		File directory = new File(folderPath);
		if (!directory.exists()) {
			directory.mkdir();
		}
	

		// Format file name with timestamp
		String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
		String filePath = folderPath + testName + "_" + timestamp + ".png";
		   try {
	            FileUtils.copyFile(srcFile, new File(filePath));
	            System.out.println("Screenshot saved: " + filePath);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	}

	@AfterClass
	public void teardown() {
		// driver.quit();
	}
}
