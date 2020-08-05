package com.qa.hubspot.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.qa.hubspoot.base.BasePage;
import com.qa.hubspot.utils.Constants;
import com.qa.hubspot.utils.ElementUtil;

import io.qameta.allure.Step;



public class LoginPage extends BasePage {
	
	private WebDriver driver;
	ElementUtil elementutil;
//	By Locators
	By email = By.id("username");
	By password = By.id("password");
	By loginbtn = By.id("loginBtn");
	By Signuplink = By.linkText("Sign up");

//Constructor of page
public LoginPage(WebDriver driver) {
elementutil = new ElementUtil(driver);
this.driver	= driver;


}

//Page Actions/Methods

@Step("Getting the Page Title")
public String getPageTitle(){
	return elementutil.getPageTitle(10, Constants.LoginPageTitle);
}
@Step("Getting the SignupLink Title")	
public Boolean isSignupDisplayed() {
	return elementutil.doIsDisplayed(Signuplink);
}	

@Step("Getting the Login with the username: {0} and password as {1}")
public HomePage login(String username, String pwd) {
	elementutil.waitFOrElementToBeVisible(10, email);
	elementutil.doSendKeys(email, username);
	elementutil.doSendKeys(password, pwd);
	elementutil.doClick(loginbtn);
	
	return new HomePage(driver);
}
	
	
}
