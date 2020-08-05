package com.qa.hubspot.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.hubspoot.base.BaseTest;
import com.qa.hubspot.pages.ContactPage;
import com.qa.hubspot.pages.HomePage;
import com.qa.hubspot.utils.Constants;
import com.qa.hubspot.utils.ExcelUtil;

public class ContactPageTest extends BaseTest{

	
HomePage homepage;
ContactPage contactpage;
	
	@BeforeClass
	public void ContactPageSetup() {
	homepage = loginpage.login(prop.getProperty("username"),prop.getProperty("password"));
	contactpage = homepage.gotoContactPage();
	}
	
	@Test(priority = 1)
	public void verifyContactPageTitle() {
		
		String CPtitle = contactpage.getContactPageTitle();
		System.out.println("The Contact Page Title is: " + CPtitle);
		Assert.assertEquals(CPtitle, Constants.ContactPageTitle);
	}
	
	@Test(priority = 2)
	public void verifyContactPageHeader() {
		String CPheader = contactpage.getContactPageHeader();
		System.out.println("The Contact Page Header is: " + CPheader);
		Assert.assertEquals(CPheader, Constants.ContactPageHeader);
	}
	
	
	@DataProvider()
	public Object[][] getContactsTestData() {
		Object data [][] = ExcelUtil.getTestData(Constants.ContactSheetName);
		return data;
	}
	
	
	@Test(priority = 3,dataProvider = "getContactsTestData")
	public void verifyContactCreation(String email,String firstname, String lastname, String jobtitle) {
		contactpage.createContact(email,firstname,lastname,jobtitle);

		
		//		Assert.assertEquals(a, "Kman Saxena");enabled = false)
	}
}
