package com.qa.hubspot.tests;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.qa.hubspoot.base.BaseTest;
import com.qa.hubspot.pages.LoginPage;
import com.qa.hubspot.testlisteners.TestAllureListener;
import com.qa.hubspot.utils.Constants;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;


@Epic("REQ_01_LOGIN")
@Feature("Functional Login Page Testing")
@Listeners(TestAllureListener.class)
public class LoginPagetest extends BaseTest{
	
	
	@Description("Verify the title of login Page")
	@Severity(SeverityLevel.MINOR)
	@Test(priority = 1)
	public void verifyTitle(){
		loginpage = new LoginPage(driver);
		String actualtitle = loginpage.getPageTitle();
		System.out.println("The actual Tested title of the application is: "+ actualtitle);
		Assert.assertEquals(actualtitle, Constants.LoginPageTitle);
	}
	
	@Description("Verify the Signup Link of login Page")
	@Severity(SeverityLevel.CRITICAL)
	@Test(priority = 2)
	public void verifySignupLinkisdisplayed() {
		Assert.assertEquals((boolean)loginpage.isSignupDisplayed(), true);
	}
	@Description("Verify the Login Procedure of login Page")
	@Severity(SeverityLevel.BLOCKER)
	@Test(priority = 3)
	public void verifyLogin() {
		loginpage.login(prop.getProperty("username"),prop.getProperty("password"));
	}
	
}
