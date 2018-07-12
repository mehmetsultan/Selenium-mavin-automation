package com.dice;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DiceJobSearch {

	public static void main(String[] args) throws IOException {
		// set up chrome driver path
		WebDriverManager.chromedriver().setup();
		// envoke selenuim webdriver
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize(); // or .fullscreen();
		// set universal wait time in case web page is slow
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		String url = "https://dice.com";
		driver.get(url);

		String actualTitle = driver.getTitle();
		String expectedTitle = "Job Search for Technology Professionals | Dice.com";

		if (actualTitle.equals(expectedTitle)) {
			System.out.println("Step PASS. Dice homepage loaded");
		} else {
			System.out.println("Step FAIL. Dice homepage did not  load successfully");
			throw new RuntimeException("Step FAIL. Dice homepage did not  load successfully ");
		}
		List<String> lst = new ArrayList<String>();
		FileReader fr = new FileReader("Job_List.txt");
		BufferedReader br = new BufferedReader(fr);
		String jobTitle = "";
		while ((jobTitle = br.readLine()) != null) {
			lst.add(jobTitle);
		}
		driver.findElement(By.name("q")).clear();
		String job = "";
		List<String> total = new ArrayList<String>();
		for (String string : lst) {
			driver.findElement(By.id("search-field-keyword")).clear();
      		driver.findElement(By.id("search-field-keyword")).sendKeys(string);
			String zip = "22101";
			driver.findElement(By.id("search-field-location")).clear();
			driver.findElement(By.id("search-field-location")).sendKeys(zip);
			driver.findElement(By.id("findTechJobs")).click();
			String str = driver.findElement(By.id("posiCountId")).getText();
     		System.out.println(str);
			int count = Integer.parseInt(str.replace(",", ""));
			driver.navigate().back();
			total.add(string + ": " + count);
		}
		System.out.println(total);
		
		for (String string : total) {
			System.out.println(string);
		}

		driver.close();
	}
}
//
//		String count = driver.findElement(By.id("posiCountMobileId")).getText();
//		// insure count is more then 0;
//		int countResult = Integer.parseInt(count.replaceAll(",", ""));
//		if (countResult > 0) {
//			System.out.println(
//					"Step PASS: Keyword :" + keyword + "search returned " + countResult + "results in " + location);
//		} else {
//			System.out.println(
//					"Step FAIL: Keyword :" + keyword + "search returned " + countResult + "results in " + location);
//		}
//		System.out.println("Test COMPLETED--:" + LocalDateTime.now());
	

