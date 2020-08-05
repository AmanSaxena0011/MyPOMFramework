package com.qa.hubspot.utils;

//import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
//import java.util.NoSuchElementException;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

//import implicitandExplicitWait.FluentWait;

public class ElementUtil {
	
	private WebDriver driver;
	
public ElementUtil(WebDriver driver) {
	this.driver = driver;
}

/**
 * This method is used to getElement by providng the locator
 * @param locator
 * @return WebElement
 */
public WebElement getElement(By locator) {
	WebElement element = null;
	try {
		element = driver.findElement(locator);
	} catch (Exception e) {
	System.out.println("The locator could not be read: "+ locator);
	}
	return element;
}

public void doClick(By locator) {
	getElement(locator).click();
}

public void doSendKeys(By locator, String Value) {
	getElement(locator).sendKeys(Value);
}

public String doGetText(By locator) {
	return getElement(locator).getText();
}

public boolean doIsDisplayed(By locator) {
	return getElement(locator).isDisplayed();
}


//****************Dropdown**********************

		public void  doSelectValueByVisibleText(By locator,String Value) {
		Select select = new Select(driver.findElement(locator));
		select.selectByVisibleText(Value);
	}	
		
		public void  doSelectValueByIndex(By locator,int Index) {
			Select select = new Select(driver.findElement(locator));
			select.selectByIndex(Index);
		}
			
		public void  doSelectValueByValue(By locator,String Value) {
				Select select = new Select(driver.findElement(locator));
				select.selectByValue(Value);
			}
		//****************Dropdown GetOptions**********************
		public List<String> doGetOptions(By locator) {
			List<String> CountryArrayList = new ArrayList<String>();
			Select select = new Select(driver.findElement(locator));
			List<WebElement> CountList = select.getOptions();
			
			System.out.println(CountList.size());
			
			for (int i = 0; i < CountList.size(); i++) {
				String text = CountList.get(i).getText();
				CountryArrayList.add(text);
			}
				return CountryArrayList;
		}
		//****************Dropdownwithoutselect**********************
		
		public  void getdropdownselected(By locator,String value) {
			List<WebElement> DayOption = driver.findElements(locator);
			for (int i = 0; i < DayOption.size(); i++) {
			String Text = DayOption.get(i).getText();
			if (Text.equals(value)) {
			DayOption.get(i).click();
			break;						}
					}
				}
		
//		*******************Action send and click methods **********************
		
		public void doActionsclick(By locator) {
			Actions ac = new Actions(driver);
			ac.click(getElement(locator)).perform();
		}

		public void doActionsend(By locator, String value) {
			Actions ac = new Actions(driver);
			ac.sendKeys(getElement(locator), value).perform();
		}

//    *******************Right click and capture all options **********************

		public void getrightclicked(WebElement element) {
			Actions ac = new Actions(driver);
//			ac.contextClick().perform();
			ac.contextClick(element).perform();
		}
		
		public void getrightclickOptions(By locator,String Value) {
			
			List<WebElement> rightClickOptions  =	driver.findElements(locator);
			System.out.println(rightClickOptions.size());
			
			for (int i = 0; i < rightClickOptions.size(); i++) {
				String text = rightClickOptions.get(i).getText();
				System.out.println(text);
				if (text.equalsIgnoreCase(Value)) {
					rightClickOptions.get(i).click();
					break;
				}
			}
			
			driver.switchTo().alert().accept();
		}

//======================WebTable preceding/following sibiling used in Cricbuzz scorecard==============================


		public void getBowlerName(String batsmanname) {
			String name = driver.findElement(By.xpath("(//a[text()='"+batsmanname+"'])[1]")).getText();
			System.out.println("Player name is: "+ name);
			String bowlername =  driver.findElement(By.xpath("(//a[text()='"+batsmanname+"'])[1]/..//following-sibling::td/span/span")).getText();
			System.out.println("The bowler who bowled him out is: " +bowlername);
			
		}
		
		public void getScoreCard(String batsmanname) {
			List<String> detailArray = new ArrayList<String>();
			List<WebElement> details=  driver.findElements(By.xpath("(//a[text()='"+batsmanname+"'])[1]/..//following-sibling::td//following-sibling::td"));
			System.out.println("The score card is below  ");
			
			for (int i = 0; i < details.size(); i++) {
				String text = details.get(i).getText();
				detailArray.add(text);
			}
				System.out.println(detailArray);
		
		}
//		-------------------------Wait Utilities-----------------------------------
		
//	-------------------------Explicitwait dependent gettitle-----------------------------------
		
		public String getPageTitle(int timeout,String titlecontains) {
			WebDriverWait wait = new WebDriverWait(driver,timeout);
			wait.until(ExpectedConditions.titleContains(titlecontains));
			System.out.println(driver.getTitle());
			return driver.getTitle();
		}
		
		public String getCurrentUrl( int timeout,String urlcontains) {
			WebDriverWait wait = new WebDriverWait(driver,timeout);
			wait.until(ExpectedConditions.urlContains(urlcontains));
			 System.out.println(driver.getCurrentUrl());
			 return driver.getCurrentUrl();
		}
		
		public WebElement waitForElementPresent(int timeout,By locator) {
			WebDriverWait wait = new WebDriverWait(driver,timeout);
			return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		}

		public WebElement waitForElementToBeClickable(int timeout,By locator) {
			WebDriverWait wait = new WebDriverWait(driver,timeout);
			return wait.until(ExpectedConditions.elementToBeClickable(locator));
		}
		
		public WebElement waitFOrElementToBeVisible(int timeout,By locator) {
			WebDriverWait wait = new WebDriverWait(driver,timeout);
			return wait.until(ExpectedConditions.visibilityOf(getElement(locator)));
		}
		
		public List<WebElement> visibilityofAllElements(int timeout,By locator) {
			WebDriverWait wait = new WebDriverWait(driver,timeout);
			return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
		}
		
		public void clickWhenReady(int timeout,By locator) {
			WebDriverWait wait = new WebDriverWait(driver,timeout);
			wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
		}
		
		public Alert waitForAlertPresent(int timeout) {
			WebDriverWait wait = new WebDriverWait(driver,10);
			return wait.until(ExpectedConditions.alertIsPresent());
		}
		
//****************************Custom Wait functionwrittenwithoutusing selenium Wait fn ******************************


		public boolean isElementDisplayed(By locator, int timeout) {
			Boolean flag = false;
			WebElement element = null;
			
			for (int i = 0; i < timeout; i++) {
				try {
				element = driver.findElement(locator);
				element.isDisplayed();
				break;
				}
				catch (Exception e) {
					System.out.println("waiting for element to be loaded for past: "+ i + "Secs");
				}
				try {
					Thread.sleep(1000);
				} catch (Exception e) {
				}
			}
			return flag;
			
		}
		
//============================= Fluentwait not working , copy pasted from naveen code=====================		
		
//		public static WebElement waitForElementWithFluentWaitConcept(WebDriver driver, By locator, int timeOut){
//			Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
//					.withTimeout(Duration.ofSeconds(timeOut))
//					.pollingEvery(Duration.ofSeconds(2))
//					.ignoring(NoSuchElementException.class);
//			
//			return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
//			
//		}
		
		
		
		
		
		
}

