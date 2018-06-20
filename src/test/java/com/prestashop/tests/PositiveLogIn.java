package com.prestashop.tests;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import io.github.bonigarcia.wdm.WebDriverManager;

public class PositiveLogIn {
	WebDriver driver;

	@BeforeMethod
	public void setUp() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
	}

	@AfterMethod
	public void tearDown() {
		driver.close();
	}

	@Test
	public void register() throws InterruptedException {

		String email = ("" + Faker.instance().pokemon().name() + "@gmail.com");
		String fn = "" + Faker.instance().name().firstName();
		String ln = "" + Faker.instance().name().lastName();
		String psw = "" + Faker.instance().gameOfThrones().character();
		String addy = "" + Faker.instance().address().streetAddress();
		String city = "" + Faker.instance().address().city();
		String post = "22030";
		String phone = "" + Faker.instance().phoneNumber().cellPhone();

		driver.get("http://automationpractice.com/");
		driver.findElement(By.xpath("//a[@class = 'login']")).click();
		driver.findElement(By.xpath("//input[@id = 'email_create']")).sendKeys(email);
		// driver.findElement(By.xpath("//button[@id ='SubmitCreate']")).click();
		driver.findElement(By.xpath("//i[@class = 'icon-user left']")).click();

		// String winHandleBefore = driver.getWindowHandle();

		Thread.sleep(3000);
		for (String winHandle : driver.getWindowHandles()) {
			driver.switchTo().window(winHandle);
		}

		driver.findElement(By.xpath("//*[@id=\"customer_firstname\"]")).sendKeys(fn);
		driver.findElement(
				By.xpath("//input[@class = 'is_required validate form-control' and @id ='customer_lastname']"))
				.sendKeys(ln);
		driver.findElement(By.xpath("//input[@class = 'is_required validate form-control' and @id ='passwd']"))
				.sendKeys(psw);
		driver.findElement(By.xpath("//input[@class ='form-control' and @id='firstname']")).sendKeys(fn);
		driver.findElement(By.xpath("//input[@class ='form-control' and @id='lastname']")).sendKeys(ln);
		driver.findElement(By.xpath("//input[@class ='form-control' and @id='address1']")).sendKeys(addy);
		driver.findElement(By.xpath("//input[@class ='form-control' and @id='city']")).sendKeys(city);

		Select dropdown = new Select(driver.findElement(By.id("id_state")));
		dropdown.selectByIndex(new Faker().number().numberBetween(0, 40));

		driver.findElement(By.id("postcode")).sendKeys(post);
		driver.findElement(By.xpath("//input[@class ='form-control' and @id='phone_mobile']")).sendKeys(phone);
		driver.findElement(By.xpath("//input[@class ='form-control' and @id='alias']")).sendKeys(addy);

		Thread.sleep(3000);
		driver.findElement(By.xpath("//button[@id ='submitAccount']")).click();

		Thread.sleep(3000);
		driver.findElement(By.xpath("//*[@id=\"header\"]/div[2]/div/div/nav/div[2]/a")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id=\"email\"]")).sendKeys(email);
		driver.findElement(By.xpath("//*[@id=\"passwd\"]")).sendKeys(psw);
		driver.findElement(By.xpath("//*[@id=\"SubmitLogin\"]/span")).click();
		Thread.sleep(2000);

		assertTrue((driver.findElement(By.xpath("//*[@id=\"header\"]/div[2]/div/div/nav/div[1]/a/span")).getText())
				.contains(("" + fn + " " + ln)));

	}

}
