package com.dice;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DiceJobSearch {

	public static void main(String[] args) {
		//set up chrome driver path
		WebDriverManager.chromedriver().setup();
		//envoke selenuim webdriver 
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();   //or .fullscreen();
		//set universal wait time in case web page is slow
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		String url ="https://dice.com";
		driver.get(url);
		
		String actualTitle = driver.getTitle();
		String expectedTitle= "Job search for Technology Professionals | Dice.com";
		if(actualTitle.equals(expectedTitle)) {
			System.out.println("Step PASS. Dice homepage loaded");
		}else {
			System.out.println("Step FAIL. Dice homepage did not  load successfully");
			throw new RuntimeException("Step FAIL. Dice homepage did not  load successfully ");
		}
		
		String keyword ="Java developer";
		driver.findElement(By.id("search-field-keyword")).clear();
		driver.findElement(By.id("search-field-keyword")).sendKeys(keyword);
		String location ="22101";
		driver.findElement(By.id("search-field-location")).clear();
		driver.findElement(By.id("search-field-location")).sendKeys(location);
		driver.findElement(By.id("findTechJobs")).click();
		
		String count = driver.findElement(By.id("posiCountMobileId")).getText();
		System.out.println(count);
		// insure count is more then 0;
		int countResult = Integer.parseInt(count.replaceAll(",", ""));
		if(countResult>0) {
			System.out.println("Step PASS: Keyword :"+ keyword + "search returned "+ countResult+ "results in "+location);
		}else {
			System.out.println("Step FAIL: Keyword :"+ keyword + "search returned "+ countResult+ "results in "+location);
		}
		driver.close();
	}

}
