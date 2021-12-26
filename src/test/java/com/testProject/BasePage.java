package com.testProject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public abstract class BasePage {
    protected WebDriver driver;

    //comments from QA Group 24.10.2021
    //comments from QA Group 28.11.2021
    //comments from QA Best Group 26.12.2021
    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }
}
