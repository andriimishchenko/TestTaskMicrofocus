package com.testProject;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;


public class Tests {
        private WebDriver driver;
        private String url = "https://www.booking.com/";

        @Test
        public void inputData() throws InterruptedException {
            MainPage mainPage = new MainPage(driver);
            mainPage.selectLang();
            mainPage.selectCur();
            mainPage.inputDestination("Kiev, Ukraine");
            mainPage.selectChInChOut();
            mainPage.selectAddOptions(1,1,2,"5 years old");
            mainPage.submitRes();
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        }
    @Test
    public void assertResults() throws InterruptedException {
            ResPage resPage=new ResPage(driver);
            resPage.verifyResults();
        }


        @BeforeClass
        public void setUp() {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            driver.get(url);
    }
        @AfterClass
        public void tearDown() {
            if (driver != null) {
                driver.quit();
            }
        }
    }


