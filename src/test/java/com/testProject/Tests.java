package com.testProject;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.Calendar;
import java.util.List;
import java.util.concurrent.TimeUnit;


public class Tests {
        private WebDriver driver;
        String url = "https://www.booking.com/";


        @BeforeTest
        public void setUp() {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        }

        @Test
        public void testis() throws InterruptedException {
            driver.get(url);
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

            //Set language: "English (US)"
            driver.findElement(By.xpath("//*[@id=\"user_form\"]/ul/li[2]")).click();
            driver.findElement(By.xpath(".//li[@class=\"lang_en-us\"]")).click();
            Thread.sleep(2000);

            //Set currency: "Euro"
            driver.findElement(By.xpath("//*[@id=\"user_form\"]/ul/li[1]")).click();
            driver.findElement(By.xpath(".//li[@class=\"currency_EUR\"]")).click();
            Thread.sleep(2000);

            //Destination: Kiev, Ukraine
            driver.findElement(By.id("ss")).clear();
            driver.findElement(By.id("ss")).sendKeys("Kiev, Ukraine");
            Assert.assertEquals(driver
                    .findElement(By.xpath("//*[@id=\"frm\"]/div[1]/div[1]/div[1]/div[1]/ul[1]/li[1]"))
                    .getAttribute("data-label"),
                    "Kiev, Ukraine");

            //Check-in: current-month-last-day - Check-out: next-month-first-day
            driver.findElement(By.xpath("//*[@id=\"frm\"]/div[1]/div[1]/div[1]/div[1]/ul[1]/li[1]")).click();

            String[] monthName = {"January", "February",
                    "March", "April", "May", "June", "July",
                    "August", "September", "October", "November",
                    "December"};

            Calendar cal = Calendar.getInstance();
            String month = monthName[cal.get(Calendar.MONTH)];
            int year = Calendar.getInstance().get(Calendar.YEAR);
            String date=month+" "+year;

            Assert.assertEquals(driver.findElement(By.className("bui-calendar__month")).getText(),date);

            driver.findElement(By.xpath("//*[@id=\"frm\"]/div[1]/div[2]/div[2]/div/div/div[3]/div[1]/table/tbody/tr[last()]/td[@class=\"bui-calendar__date\"] [last()]")).click();
            driver.findElement(By.xpath("//*[@id=\"frm\"]/div[1]/div[2]/div[2]/div/div/div[3]/div[2]/table/tbody/tr[1]/td[@class=\"bui-calendar__date\"]")).click();
            System.out.println(driver.findElement(By.className("bui-calendar__month")).getText());

            //Adults: 1 adult
            driver.findElement(By.xpath("//*[@id=\"frm\"]/div[1]/div[3]")).click();
            Thread.sleep(5000);
            while(Integer.parseInt(driver.findElement(By.xpath("//*[@id=\"xp__guests__inputs-container\"]/div/div/div[1]/div/div[2]/span[1]")).getText())!=1){
                if(Integer.parseInt(driver.findElement(By.xpath("//*[@id=\"xp__guests__inputs-container\"]/div/div/div[1]/div/div[2]/span[1]")).getText())>1)
                driver.findElement(By.xpath("//*[@id=\"xp__guests__inputs-container\"]/div/div/div[1]/div/div[2]/button[1]")).click();
                else driver.findElement(By.xpath("//*[@id=\"xp__guests__inputs-container\"]/div/div/div[1]/div/div[2]/button[2]")).click();
            }
            Thread.sleep(2000);

            //Children: 1 child
            while(Integer.parseInt(driver.findElement(By.xpath("//*[@id=\"xp__guests__inputs-container\"]/div/div/div[2]/div/div[2]/span[1]")).getText())!=1){
                if(Integer.parseInt(driver.findElement(By.xpath("//*[@id=\"xp__guests__inputs-container\"]/div/div/div[2]/div/div[2]/span[1]")).getText())>1)
                    driver.findElement(By.xpath("//*[@id=\"xp__guests__inputs-container\"]/div/div/div[2]/div/div[2]/button[1]")).click();
                else driver.findElement(By.xpath("//*[@id=\"xp__guests__inputs-container\"]/div/div/div[2]/div/div[2]/button[2]")).click();
            }
            Thread.sleep(2000);

            //Rooms: 2 rooms
            while(Integer.parseInt(driver.findElement(By.xpath("//*[@id=\"xp__guests__inputs-container\"]/div/div/div[3]/div/div[2]/span[1]")).getText())!=2){
                if(Integer.parseInt(driver.findElement(By.xpath("//*[@id=\"xp__guests__inputs-container\"]/div/div/div[3]/div/div[2]/span[1]")).getText())>2)
                    driver.findElement(By.xpath("//*[@id=\"xp__guests__inputs-container\"]/div/div/div[3]/div/div[2]/button[1]")).click();
                else driver.findElement(By.xpath("//*[@id=\"xp__guests__inputs-container\"]/div/div/div[3]/div/div[2]/button[2]")).click();
            }
            Thread.sleep(2000);

            //Children Age: 5 years old
            WebElement selectElem = driver.findElement(By.name("age"));
            Select select = new Select(selectElem);
            select.selectByVisibleText("5 years old");
            Thread.sleep(2000);

            //I'm traveling for work: yes
            if (driver.findElement(By.xpath("//*[@id=\"frm\"]/div[2]")).isSelected()){
                driver.findElement(By.xpath("//*[@id=\"frm\"]/div[1]/div[4]/div[2]")).click();
            }else{
                driver.findElement(By.xpath("//*[@id=\"frm\"]/div[2]")).click();
                driver.findElement(By.xpath("//*[@id=\"frm\"]/div[1]/div[4]/div[2]")).click();
            }

        }
    @Test
    public void testis2() throws InterruptedException {
    /*
        List<WebElement> listOfResults = driver.findElements(By.className("sr_item  sr_item_new sr_item_default sr_property_block  sr_flex_layout"));
        for (WebElement elem:listOfResults) {
            if(Float.parseFloat(elem.getAttribute("data-score"))>8.1&&driver.findElement(By.className("bui-u-sr-only"))){

            }
        }


    */
        }

        @AfterTest
        public void tearDown() {
            if (driver != null) {
                driver.quit();
            }
        }
    }


