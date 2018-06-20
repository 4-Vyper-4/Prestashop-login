package com.prestashop.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class NegativeLogIn {
	WebDriver driver;

	@BeforeMethod
	public void setUp() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
	}

	@AfterMethod
	public void tearDwon() {
		driver.close();
	}
	
	@Test
	public void wrongCredsTest() {
		driver.get("http://automationpractice.com/");
		driver.findElement(By.xpath("//a[@class = 'login']")).click();
		driver.findElement(By.xpath("//input[@id = 'email']")).sendKeys("wrongemail@test.com");
		driver.findElement(By.xpath("//input[@type ='password']")).sendKeys("Password1234");
		driver.findElement(By.xpath("//i[@class = 'icon-lock left']")).click();
		Assert.assertTrue((driver.findElement(By.xpath("//div[@class = 'alert alert-danger' ] //li")).getText()).contains("Authentication failed."));
	}
	
	@Test
	public void invalidEmailFormatTest() {
		driver.get("http://automationpractice.com/");
		driver.findElement(By.xpath("//a[@class = 'login']")).click();
		driver.findElement(By.xpath("//input[@id = 'email']")).sendKeys("wrongemail2test.com");
		driver.findElement(By.xpath("//input[@id = 'passwd']")).sendKeys("Password1234");		
		driver.findElement(By.xpath("//i[@class = 'icon-lock left']")).click();
		Assert.assertTrue((driver.findElement(By.xpath("//div[@class = 'alert alert-danger' ] //li")).getText()).contains("Invalid email address."));
	}
	
	@Test
	public void blankEmailTest() {
		driver.get("http://automationpractice.com/");
		driver.findElement(By.xpath("//a[@class = 'login']")).click();
		driver.findElement(By.xpath("//input[@id = 'passwd']")).sendKeys("Password1234");
		driver.findElement(By.xpath("//i[@class = 'icon-lock left']")).click();
		Assert.assertTrue((driver.findElement(By.xpath("//div[@class = 'alert alert-danger' ] //li")).getText()).contains("An email address required."));
	}
	
	@Test
	public void blankPassTest() {
		driver.get("http://automationpractice.com/");
		driver.findElement(By.xpath("//a[@class = 'login']")).click();
		driver.findElement(By.xpath("//input[@id = 'email']")).sendKeys("wrongemail@test.com");
		driver.findElement(By.xpath("//i[@class = 'icon-lock left']")).click();
		Assert.assertTrue((driver.findElement(By.xpath("//div[@class = 'alert alert-danger' ] //li")).getText()).contains("Password is required."));
	}
}
