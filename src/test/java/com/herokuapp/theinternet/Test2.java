package com.herokuapp.theinternet;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Test2 {
    @Test
    public void invalidUsername(){
        System.setProperty("webdriver.chrome.driver","C:/Automation/Selenium Maven/src/main/resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        String url = "https://the-internet.herokuapp.com/";
        driver.get(url);

        driver.manage().window().maximize();

        driver.findElement(By.xpath("//*[@id=\"content\"]/ul/li[21]/a")).click();
        driver.findElement(By.name("username")).sendKeys("invalid");
        driver.findElement(By.name("password")).sendKeys("SuperSecretPassword!");
        driver.findElement(By.className("radius")).click();

        WebElement errorMessage = driver.findElement(By.className("error"));

        String actualError = errorMessage.getText();
        String expectedError = "Your username is invalid!";

        Assert.assertTrue(actualError.contains(expectedError),"Invalid Error message");

        String expectedURL = "https://the-internet.herokuapp.com/login";
        String actualURL = driver.getCurrentUrl();

        Assert.assertEquals(expectedURL,actualURL);

        driver.quit();

    }

    @Test
    public void invalidPassword(){
        System.setProperty("webdriver.chrome.driver","C:/Automation/Selenium Maven/src/main/resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        String url = "https://the-internet.herokuapp.com/";
        driver.get(url);

        driver.manage().window().maximize();

        driver.findElement(By.xpath("//*[@id=\"content\"]/ul/li[21]/a")).click();
        driver.findElement(By.name("username")).sendKeys("tomsmith");
        driver.findElement(By.name("password")).sendKeys("SuperSecretPassword");
        driver.findElement(By.className("radius")).click();

        WebElement errorMessage = driver.findElement(By.className("error"));

        String actualError = errorMessage.getText();
        String expectedError = "Your password is invalid!";

        Assert.assertTrue(actualError.contains(expectedError),"Invalid Error message");

        String expectedURL = "https://the-internet.herokuapp.com/login";
        String actualURL = driver.getCurrentUrl();

        Assert.assertEquals(expectedURL,actualURL);

        driver.quit();

    }
}
