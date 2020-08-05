package com.qa.hubspot.utils;

import java.util.Properties;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

public class OptionManager {
	
	Properties prop;
	ChromeOptions co;
	FirefoxOptions fo;
	
	public OptionManager(Properties prop) {
		this.prop = prop;
	}
	
	
	public ChromeOptions getChromeoptions() {
		
		co = new ChromeOptions();
		if(Boolean.parseBoolean(prop.getProperty("headless"))) co.addArguments("--headless");
		if(Boolean.parseBoolean(prop.getProperty("incognito"))) co.addArguments("--incognito");
		return co;
}
	
	public FirefoxOptions getFirefoxoptions() {
		
		fo = new FirefoxOptions();
		if(Boolean.parseBoolean(prop.getProperty("headless"))) co.addArguments("--headless");
		if(Boolean.parseBoolean(prop.getProperty("incognito"))) co.addArguments("--incognito");
		return fo;
}
	
	
	
	
}