package com.qa.hubspot.pages;

import javax.swing.JButton;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.hubspoot.base.BasePage;
import com.qa.hubspot.utils.Constants;
import com.qa.hubspot.utils.ElementUtil;

public class ContactPage extends BasePage{
	
	ElementUtil elementutil;

// By Locators
	
	By header = By.xpath("//i18n-string[text()='Contacts']");
	By createContactPrimary = By.xpath("//span[text()='Create contact']");
	By email = By.xpath("//input[@data-field = 'email']");
	By firstname = By.xpath("//input[@data-field = 'firstname']");
	By lastname = By.xpath("//input[@data-field = 'lastname']");
	By jobtitle = By.xpath("//input[@data-field = 'jobtitle']");
	By createContactSecondary = By.xpath("(//span[text()='Create contact'])[position()=2]");
	By contactback = By.xpath("(//*[text()='Contacts'])[1]");
	
	By verifyname = By.xpath("//span[@data-selenium-test='highlightTitle']");


// Constructor
	
	public ContactPage(WebDriver driver) {
		this.driver = driver;
		elementutil = new ElementUtil(this.driver);
	}
	
//	Methods Actions
	
	public String getContactPageTitle() {
		return elementutil.getPageTitle(15,Constants.ContactPageTitle );
		}
	
	public String getContactPageHeader() {
		elementutil.waitForElementPresent(15, header);
		return elementutil.doGetText(header);
		}
	
	public void createContact(String email, String firstname, String lastname, String jobtitle) {
		elementutil.clickWhenReady(15,createContactPrimary);
		elementutil.doSendKeys(this.email, email);
		elementutil.doSendKeys(this.firstname, firstname);
		elementutil.doSendKeys(this.lastname, lastname);
		elementutil.waitForElementToBeClickable(10, this.jobtitle);
		elementutil.doSendKeys(this.jobtitle, jobtitle);
		elementutil.clickWhenReady(15,createContactSecondary);
		elementutil.waitFOrElementToBeVisible(10,contactback);
//		elementutil.waitForElementPresent(15, verifyname);
//		String createdName =elementutil.doGetText(verifyname);
//		System.out.println(createdName);
//		System.out.println(elementutil.waitForElementPresent(15, By.xpath("//*[@data-key='customerDataSidebar.aboutCard.title.CONTACT']")).getText());
//		elementutil.doActionsclick(contactback);
//		elementutil.clickWhenReady(10,contactback);
//		return createdName;
		
	}
		
}