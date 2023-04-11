package com.herokuapp.theinternet;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Test1 {
    @Test
    public void login() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver","C:/Automation/Selenium Maven/src/main/resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        String url = "https://the-internet.herokuapp.com/";
        driver.get(url);
        System.out.println("Opened url");
        driver.manage().window().maximize();

        driver.findElement(By.xpath("//*[@id=\"content\"]/ul/li[21]/a")).click();
        driver.findElement(By.name("username")).sendKeys("tomsmith");
        driver.findElement(By.name("password")).sendKeys("SuperSecretPassword!");
        driver.findElement(By.className("radius")).click();


        String expectedUrl = "https://the-internet.herokuapp.com/secure";
        String actualUrl = driver.getCurrentUrl();

        Assert.assertEquals(actualUrl,expectedUrl,"Different url");

        Sleep(1000);

        System.out.println("logged in");

        WebElement logoutButton = driver.findElement(By.xpath("//div[@id='content']//a[@href='/logout']"));
        Assert.assertTrue(logoutButton.isDisplayed(),"No logout button bro");

        WebElement successMessage = driver.findElement(By.className("flash"));
        String actualMessage = successMessage.getText();
        String expectedMessage = "You logged into a secure area!";

        Assert.assertTrue(actualMessage.contains(expectedMessage),"Different message");

        logoutButton.click();
        driver.quit();
    }

    private static void Sleep(int m) {
        try {
            Thread.sleep(m);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
