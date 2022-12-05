package page;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.Select;

public class AddCategoryPage {
	
	WebDriver driver;
	
	public AddCategoryPage()
	{
		this.driver = driver;
	}
	
	@FindBy(how = How.NAME, using = "categorydata")
	WebElement addCategoryElement;
	
	@FindBy(how = How.XPATH, using = "//input[@value = 'Add category']")
	WebElement addCategoryButton;
	
	@FindBy(how = How.XPATH,using = "//select[@name = 'due_month']")
	WebElement monthDropDownElement;
		
	public String addCategory(String categoryName)
	{
		addCategoryElement.sendKeys(categoryName);
		return categoryName;
	}
	public void clickAddCategory()
	{
		addCategoryButton.click();
		
	}
	
	public List<WebElement> dropDown()
	{
		
		Select sel = new Select(monthDropDownElement);
		List<WebElement> option = sel.getOptions();
		return option;
	}

}
