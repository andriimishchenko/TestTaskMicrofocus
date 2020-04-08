package com.testProject;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;


public class Tests {
        private WebDriver driver;
        private String url = "https://www.booking.com/";

    @FindBy(xpath = ".//li[@class=\"lang_en-us\"]")
    private WebElement langEnUS;
    @FindBy(xpath = ".//li[@class=\"currency_EUR\"]")
    private WebElement currEuro;


        @Test
        public void inputDataAndVerifyRes() throws InterruptedException {
          /*
          WebDriverWait myWaitVar = new WebDriverWait(driver, 4);
          myWaitVar.until(ExpectedConditions.visibilityOfElementLocated(By.id(“username”)));
          */
            MainPage mainPage = new MainPage(driver);
            mainPage.selectLang(langEnUS);
            mainPage.selectCur(currEuro);
            mainPage.inputDestination("Kiev, Ukraine");
            mainPage.selectChInChOut();
            mainPage.selectAddOptions(1,1,2,"5 years old");
            mainPage.submitRes();

            ResPage resPage=new ResPage(driver);
            resPage.verifyResults();
            }

        @BeforeTest
        public void setUp() {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
            //driver = new FirefoxDriver();
            driver.manage().window().maximize();
            driver.get(url);
            }
        @AfterTest
        public void tearDown() {
            if (driver != null) {
                driver.quit();
            }
        }
}


