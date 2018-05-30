package calcGroupId;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PageObjects {
    private WebDriver driver;
            ;
    private final String URL="http://calc-time.online";

    By startHour = By.id("fromHour");
    By startMinutes = By.id("fromMinute");
    By startDate = By.id("fromDate");;
    By endHour = By.id("toHour");;
    By endMinutes = By.id("toMinute");;
    By endDate = By.id("toDate");;
    By submitButton = By.tagName("button");
    By resultField = By.id("result");


    public PageObjects(WebDriver driver){
     this.driver=driver;
    }




    /*GETTERS*/


    public WebElement getstartHour(){
        return driver.findElement(startHour);
    }
    public WebElement getstartMinutes(){
        return driver.findElement(startMinutes);
    }

    public WebElement getstartDate(){
        return driver.findElement(startDate);
    }

    public WebElement getEndHour(){
        return driver.findElement(endHour);
    }

    public WebElement getEndMinutes(){
        return driver.findElement(endMinutes);
    }

    public WebElement getEndDate(){
        return driver.findElement(endDate);
    }

    public WebElement getSubmitButton(){
        return driver.findElement(submitButton);
    }

    public WebElement getResultField(){
        return driver.findElement(resultField);
    }



    public String getURL(){
        return URL;
    }
}
