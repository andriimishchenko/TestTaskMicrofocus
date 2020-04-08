package com.testProject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


import java.util.List;

public class ResPage extends BasePage{

    public ResPage(WebDriver driver) {
        super(driver);
    }

    public void verifyResults(){
        List<WebElement> listOfResults = driver.findElements(By.xpath(".//div[@class=\"sr_item  sr_item_new sr_item_default sr_property_block  sr_flex_layout          \"]"));
            for (WebElement elem:listOfResults) {
                String substringPrice=elem.findElement(By.xpath(".//*[@class=\"bui-price-display__value prco-inline-block-maker-helper\"]")).getText().substring(2);

                int price=Integer.parseInt(substringPrice);
                float raiting=Float.parseFloat(elem.getAttribute("data-score"));

                if(raiting>8.1&&price<205){
                    System.out.println("Test passed");
                    break;
                }
            }
    }
}
