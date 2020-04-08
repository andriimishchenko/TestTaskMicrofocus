package com.testProject;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;


public class Tests{
    private WebDriver driver;
    private String url = "https://www.booking.com/";

    @Test
    public void inputDataAndVerifyRes() throws InterruptedException {
          /*
          WebDriverWait myWaitVar = new WebDriverWait(driver, 4);
          myWaitVar.until(ExpectedConditions.visibilityOfElementLocated(By.id(“username”)));
          */

        MainPage mainPage = new MainPage(driver);
        mainPage.selectLang("en-us");
        mainPage.selectCur("EUR");
        mainPage.inputDestination("Kiev, Ukraine");
        mainPage.selectChInChOut();
        mainPage.selectAddOptions(1, 1, 2, "5 years old");
        mainPage.submitRes();

        ResPage resPage = new ResPage(driver);
        resPage.verifyResults();
    }

    @BeforeTest
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        //driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.get(url);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @AfterTest
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}


