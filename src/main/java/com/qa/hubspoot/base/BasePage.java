package com.qa.hubspoot.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.qa.hubspot.utils.OptionManager;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BasePage {
	
	public WebDriver driver;
	public Properties prop;
	public OptionManager optionmanager;
	
	public static ThreadLocal<WebDriver> tldriver = new ThreadLocal<WebDriver>();
	
	
	public WebDriver init_driver(Properties prop) {
		
		String browser = prop.getProperty("browser").trim();
		
		System.out.println("The browser which is going to be launched is: "+ browser);
		optionmanager = new OptionManager(prop);
		
		if (browser.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			tldriver.set(new ChromeDriver(optionmanager.getChromeoptions()));
		}
		else if (browser.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();

			tldriver.set(new FirefoxDriver(optionmanager.getFirefoxoptions()));
		}
		else {
			System.out.println("The provided browser name:" + browser + " is not available in our system.");
		}
		
		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		getDriver().manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		getDriver().get(prop.getProperty("url"));
		getDriver().manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
		return getDriver();
	}
	
	public static synchronized	WebDriver getDriver() {
		return tldriver.get();
	}
	
	
	
	
	public Properties init_properties() {
		
		prop = new Properties();
		String env = null;
		String path = null;
		
		try {
			
			env = System.getProperty("env");
			System.out.println("Running of the current: --->> "+ env + "Environment");
			if (env == null) {
				path = "./src/main/java/com/qa/hubspot/config/config.properties";
			}
			
			else {
				switch (env) {
				case "dev":
					path = "./src/main/java/com/qa/hubspot/config/dev.config.properties";
					break;
				case "qa":
					path = "./src/main/java/com/qa/hubspot/config/qa.config.properties";
					break;
				case "stag":
					path = "./src/main/java/com/qa/hubspot/config/stag.config.properties";
					break;

				default:
					System.out.println("Please provide correect env");
					break;
				}
				
			}
			
			
			FileInputStream inp = new FileInputStream(path);
		prop.load(inp);	
		} 
		catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop;
	}
	
	
//	Screenshot
	
	public String getScreenshot() {
		File src = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir")+ "/Screenshots/"+ System.currentTimeMillis() + ".png" ;
		File destination = new File(path);
		
		try {
			FileUtils.copyFile(src, destination);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return path;
	}
	
	

}
