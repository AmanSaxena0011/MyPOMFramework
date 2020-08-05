package com.qa.hubspot.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.hubspoot.base.BasePage;
import com.qa.hubspot.utils.Constants;
import com.qa.hubspot.utils.ElementUtil;

import io.qameta.allure.Step;

public class HomePage extends BasePage {
	
	ElementUtil elementutil;
	
	By homepageheader = By.cssSelector("h1.dashboard-selector__title");
	By accountname = By.cssSelector("span.account-name ");
	
	By contactPrimaryLink = By.id("nav-primary-contacts-branch");
	By contactSecondaryLink = By.id("nav-secondary-contacts");
	
	public HomePage(WebDriver driver) {
	elementutil = new ElementUtil(driver);
		this.driver = driver;
	}
	
	@Step("Get the HomePage Title")
	public String getHomePageTitle() {
		return elementutil.getPageTitle(10, Constants.HomePageTitle);
	}
	
	@Step("Get the HomePage Header")
	public String getHomePageheader() {
		if (elementutil.doIsDisplayed(homepageheader)) {
			return elementutil.doGetText(homepageheader);
		}
		return null;
	}
	
	
	@Step("Get the Account name logged in")
	public String getAccountname() {
		
		if (elementutil.doIsDisplayed(accountname)) {
			 return elementutil.doGetText(accountname);
		}
		return null;
		}

	public ContactPage gotoContactPage() {
		clickonContacts();
		return new ContactPage(driver);
	}
	
	private void clickonContacts() {
		elementutil.clickWhenReady(10, contactPrimaryLink);
		elementutil.clickWhenReady(10, contactSecondaryLink);
		
		
	}
}
