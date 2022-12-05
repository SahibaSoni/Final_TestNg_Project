package test;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

//import junit.framework.Assert;
import page.AddCategoryPage;
import util.Base;
import util.BrowserFactory;

public class AddCategoryTest extends Base{
	
	WebDriver driver;
	
	@BeforeMethod
	public void initBrowser() {
		driver = BrowserFactory.init();
		driver.get("https://techfios.com/test/101/");
	}

	String actualName;
	String expectedName;

	@Test(priority = 1)
	public void addCategoryAndValidate() throws InterruptedException {
		AddCategoryPage addCateg = PageFactory.initElements(driver, AddCategoryPage.class);
		actualName = addCateg.addCategory("Selenium1" +generateRandomNum(999));
		System.out.println("Actual Name : " + actualName);
		Thread.sleep(3000);
		addCateg.clickAddCategory();
		Thread.sleep(3000);

		List<WebElement> allElements = driver.findElements(By.xpath("//div[@class = 'controls']//span"));
		int sizeOfList = allElements.size();
		System.out.println("Size = " + sizeOfList);

		expectedName = allElements.get(sizeOfList - 1).getText();
		System.out.println("Expected Name " + expectedName);

		// Assert.assertEquals(actualName, expectedName);
		Assert.assertEquals(actualName, expectedName, "Element is not available");

	}

	@Test(priority = 2)
	public void checkDuplicateCategory() throws InterruptedException {
		AddCategoryPage addCateg = PageFactory.initElements(driver, AddCategoryPage.class);
		actualName = addCateg.addCategory("Selenium1");
		System.out.println("Actual Name : " + actualName);
		Thread.sleep(3000);
		addCateg.clickAddCategory();
		Thread.sleep(3000);

		String warningText = ("The category you want to add already exists: " + actualName);
		//Another way of writing the xpath------------------
		List<WebElement> allElements = driver.findElements(By.xpath("//body[contains(text(),warningText)]"));
		
		if(allElements.size()>0)
		{
			System.out.println("Text: "+ warningText + " is present.");
			
		}
		else
		{
			System.out.println("nothing");
		}

	}
	
	@Test(priority = 3)
	public void monthDropDown()
	{
		AddCategoryPage addCateg = PageFactory.initElements(driver, AddCategoryPage.class);
		String[] expectedMonth = {"None" , "Jan" , "Feb" , "Mar" , "Apr" , "May" , "Jun" , "Jul" , "Aug" , "Sep" , "Oct" , "Nov" , "Dec"};
		List monthList = Arrays.asList(expectedMonth);
		System.out.println(monthList);
		for(WebElement dropDownOption : addCateg.dropDown())
		{
			
			String actualMonth = dropDownOption.getText();
			
			int index = monthList.indexOf(actualMonth);
			System.out.println(index);
			System.out.println("DropDownOption : "+actualMonth);
			
			Assert.assertEquals(actualMonth, monthList.get(index), "Month is not avaialabe");
		}
		
		
		
		
		
	}

	@AfterMethod
	public void closeBrowser() {
		BrowserFactory.teardown();
	}

}
