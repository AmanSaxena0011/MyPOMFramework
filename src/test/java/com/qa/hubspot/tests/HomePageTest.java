package com.qa.hubspot.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.qa.hubspoot.base.BaseTest;
import com.qa.hubspot.pages.HomePage;
import com.qa.hubspot.testlisteners.TestAllureListener;
import com.qa.hubspot.utils.Constants;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;


@Epic("REQ_02_Home")
@Feature("Functional HomePage Testing")
@Listeners(TestAllureListener.class)
public class HomePageTest extends BaseTest{

	HomePage homepage;
	
	@BeforeClass
	public void homePageSetup() {
	homepage = loginpage.login(prop.getProperty("username"),prop.getProperty("password"));
	}
	
	@Description("Verifying the HomePageTitle")
	@Severity(SeverityLevel.MINOR)
	@Test(priority = 1)
	public void verifyHomePageTitle()  {
		String actualHPTitle = homepage.getHomePageTitle();
		System.out.println("Actual HomePage Title is: " + actualHPTitle);
		Assert.assertEquals(actualHPTitle, Constants.HomePageTitle);
	}
	
	@Description("Verifying the HomePageHeader")
	@Severity(SeverityLevel.MINOR)
	@Test(priority = 2)
	public void verifyHomePageHeader() {
		String actualHPHeader = homepage.getHomePageheader();
		System.out.println("Actual HomePage Header is: " + actualHPHeader);
		Assert.assertEquals(actualHPHeader, Constants.HomePageHeader);
	}
	
	@Description("Verifying the HomePage LoggedInAccount")
	@Severity(SeverityLevel.TRIVIAL)
	@Test(priority = 3)
	public void verifyLoggedinAccountName() {
		String actualAccountname = homepage.getAccountname();
		System.out.println("Actual Logged in Account name  is: " + actualAccountname);
		Assert.assertEquals(actualAccountname, prop.getProperty("loggedinaccountname"));
	}	
}
