package com.testProject;

import com.google.common.base.Verify;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.util.Calendar;

public class MainPage extends BasePage{

    @FindBy(xpath = ".//li[@data-id=\"language_selector\"]/a")
    private WebElement langListBtn;

    @FindBy(xpath = ".//li[@data-id=\"currency_selector\"]/a")
    private WebElement currListBtn;

    @FindBy(id = "ss")
    private WebElement inputDestination;

    @FindBy(xpath = "//*[@id=\"frm\"]/div[1]/div[1]/div[1]/div[1]/ul[1]/li[1]")
    private WebElement firstFoundDest;

    @FindBy(xpath = "//*[@id=\"frm\"]/div[1]/div[1]/div[1]/div[1]/ul[1]/li[1]")
    private WebElement dateField;

    @FindBy(xpath = "//*[@id=\"frm\"]/div[1]/div[2]/div[2]/div/div/div[3]/div[1]/table/tbody/tr[last()]/td[@class=\"bui-calendar__date\"] [last()]")
    private WebElement lastDayOfCurrMonth;

    @FindBy(xpath = "//*[@id=\"frm\"]/div[1]/div[2]/div[2]/div/div/div[3]/div[2]/table/tbody/tr[1]/td[@class=\"bui-calendar__date\"]")
    private WebElement firstDayOfNextMonth;

    @FindBy(xpath = "//*[@id=\"frm\"]/div[1]/div[3]")
    private WebElement otherOptions;

    @FindBy(xpath = "//*[@id=\"xp__guests__inputs-container\"]/div/div/div[1]/div/div[2]/span[1]")
    private WebElement adultsCount;

    @FindBy(xpath = "//*[@id=\"xp__guests__inputs-container\"]/div/div/div[1]/div/div[2]/button[1]")
    private WebElement minAdultsBtn;

    @FindBy(xpath = "//*[@id=\"xp__guests__inputs-container\"]/div/div/div[1]/div/div[2]/button[2]")
    private WebElement maxAdultsBtn;

    @FindBy(xpath = "//*[@id=\"xp__guests__inputs-container\"]/div/div/div[2]/div/div[2]/span[1]")
    private WebElement childCount;

    @FindBy(xpath = "//*[@id=\"xp__guests__inputs-container\"]/div/div/div[2]/div/div[2]/button[1]")
    private WebElement minChildBtn;

    @FindBy(xpath = "//*[@id=\"xp__guests__inputs-container\"]/div/div/div[2]/div/div[2]/button[2]")
    private WebElement maxChildBtn;

    @FindBy(xpath = "//*[@id=\"xp__guests__inputs-container\"]/div/div/div[3]/div/div[2]/span[1]")
    private WebElement roomCount;

    @FindBy(xpath = "//*[@id=\"xp__guests__inputs-container\"]/div/div/div[3]/div/div[2]/button[1]")
    private WebElement minRoomBtn;

    @FindBy(xpath = "//*[@id=\"xp__guests__inputs-container\"]/div/div/div[3]/div/div[2]/button[2]")
    private WebElement maxRoomBtn;

    @FindBy(name = "age")
    private WebElement childAgeSelection;

    @FindBy(xpath = "//*[@id=\"frm\"]/div[2]")
    private WebElement workOption;

    @FindBy(xpath = "//*[@id=\"frm\"]/div[1]/div[4]/div[2]")
    private WebElement submitBtn;





    public MainPage(WebDriver driver) {
        super(driver);
    }

    public void selectLang(String lang){
        langListBtn.click();
        driver.findElement(By.xpath(".//li[@class=\"lang_"+lang+"\"]")).click();
    }
    public void selectCur(String curr){
        currListBtn.click();
        driver.findElement(By.xpath(".//li[@data-lang=\""+curr+"\"]")).click();
    }
    public void inputDestination(String location){
        inputDestination.clear();
        inputDestination.sendKeys(location);
        Assert.assertEquals(firstFoundDest.getAttribute("data-label"), location);
    }
    public void selectChInChOut(){
        dateField.click();

        String[] monthName = {"January", "February",
                "March", "April", "May", "June", "July",
                "August", "September", "October", "November",
                "December"};

        Calendar cal = Calendar.getInstance();
        String currMonth = monthName[cal.get(Calendar.MONTH)];
        int currYear = Calendar.getInstance().get(Calendar.YEAR);
        String date=currMonth+" "+currYear;

        Assert.assertEquals(driver.findElement(By.xpath("/html/body/div[4]/div/div/div[2]/form/div[1]/div[2]/div[2]/div/div/div[3]/div[1]/div")).getText(),date);

        lastDayOfCurrMonth.click();
        firstDayOfNextMonth.click();
    }
    public void selectAddOptions(int adults, int children, int rooms, String childrenAge) throws InterruptedException {
        otherOptions.click();

        //Adults
        while(Integer.parseInt(adultsCount.getText())!=adults){
            if(Integer.parseInt(adultsCount.getText())>adults) minAdultsBtn.click();
            else maxAdultsBtn.click();
            Thread.sleep(2000);
        }

        //Children
        while(Integer.parseInt(childCount.getText())!=children){
            if(Integer.parseInt(childCount.getText())>children) minChildBtn.click();
            else maxChildBtn.click();
            Thread.sleep(2000);
        }

        //Rooms
        while(Integer.parseInt(roomCount.getText())!=rooms){
            if(Integer.parseInt(roomCount.getText())>rooms) minRoomBtn.click();
            else maxRoomBtn.click();
            Thread.sleep(2000);
        }

        //Children Age
        if (childAgeSelection.isDisplayed()) {
            Select select = new Select(childAgeSelection);
            select.selectByVisibleText(childrenAge);
        }
    }
    public void submitRes(){
        if (workOption.isSelected())submitBtn.click();
        else{
            workOption.click();
            submitBtn.click();
        }
    }
}
