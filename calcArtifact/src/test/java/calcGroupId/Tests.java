package calcGroupId;


import org.apache.commons.exec.util.StringUtils;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;


import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Tests extends Base{
    private final int DATE_FORMAT_LENGTH = 10;
    private final int TIME_FORMAT_LENGTH=2;
    WebDriver driver=initializeDriver();
    PageObjects page = new PageObjects(driver);








    @Test
    public void openURL(){
        System.out.println("Running test");

/*
        System.setProperty("webdriver.chrome.driver","/home/alexz/Desktop/git/calcArtifact/src/Drivers/chromedriver");
        driver=new ChromeDriver();
*/

       // driver.get(page.getURL());
    }


    @Test
    /*
    This test makes sure that the default value in hours and minutes is according to the system
     */
    public void checkDefaultStartTime(){

        String expectedTimeStamp= calculateCurrentTime();
        String mm,hh,derivedTime,expectedTime;

        driver.get(page.getURL());
        //driver.manage().timeouts().implicitlyWait(05, TimeUnit.SECONDS);//skips the "I am not a robot"

        //derive the hours and minutes
         hh = page.getstartHour().getAttribute("value");
         mm = page.getstartMinutes().getAttribute("value");


         derivedTime = constructTime(hh,mm);
         expectedTime = constructTime(extractCurrentHH(expectedTimeStamp),extractCurrentMM(expectedTimeStamp));


//        System.out.println("from page: "+derivedTime);
//        System.out.println("from java: "+expectedTime);
        driver.close();
        Assert.assertEquals(expectedTime,derivedTime);

    }



    @Test
    /*
    This test makes sure that the default value in hours and minutes is according to the system
     */
    public void checkDefaultEndTime(){
        String expectedTimeStamp= calculateCurrentTime();
        String mm,hh,derivedTime,expectedTime;

        driver.get(page.getURL());
        //driver.manage().timeouts().implicitlyWait(05, TimeUnit.SECONDS);//skips the "I am not a robot"

        //derive the hours and minutes
        hh = page.getEndHour().getAttribute("value");
        mm = page.getEndMinutes().getAttribute("value");


        derivedTime = constructTime(hh,mm);
        expectedTime = constructTime(extractCurrentHH(expectedTimeStamp),extractCurrentMM(expectedTimeStamp));


//        System.out.println("from page: "+derivedTime);
//        System.out.println("from java: "+expectedTime);

        driver.close();
        Assert.assertEquals(expectedTime,derivedTime);

    }


    /*
    This test makes sure that the default date is today's date
     */
    @Test
    public void checkStartDate(){
        String expectedDate,actualDate;

        driver.get(page.getURL());
        actualDate=page.getstartDate().getAttribute("value");
        System.out.println(actualDate);
        expectedDate=extractCurrentDate(calculateCurrentTime());
        driver.close();
        Assert.assertEquals(expectedDate,actualDate);

    }
    @Test
    public void checkEndDate(){
        String expectedDate,actualDate;

        driver.get(page.getURL());
        actualDate=page.getEndDate().getAttribute("value");
        System.out.println(actualDate);
        expectedDate=extractCurrentDate(calculateCurrentTime());
        driver.close();
        Assert.assertEquals(expectedDate,actualDate);
    }


    /*
    * Calculates a simple difference without involving dates
    */
    @Test
    public void calculateDifference(){
        driver.get(page.getURL());
        String calculatedResult;

        inputTime(10,00,15,30);
        page.getSubmitButton().click();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);//skips the "I am not a robot"
        calculatedResult=page.getResultField().getAttribute("value");
        driver.close();
        Assert.assertEquals("5 hours, 30 Minutes",calculatedResult);


    }


    @Test
    /*Calculates the time including a date change*/
    public void calculateDifferenceNextDay(){
        driver.get(page.getURL());
        String calculatedResult;
        inputTime(10,00,15,55);
        inputDates("05/30/2018","06/01/2018");
        page.getSubmitButton().click();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);//skips the "I am not a robot"
        calculatedResult=page.getResultField().getAttribute("value");
        driver.close();
        Assert.assertEquals("2 Days, 53 hours, 55 Minutes",calculatedResult);

    }










    /*Helpfull methods*/

    /*Recieved ints and sends them  as the time*/
    private void inputTime(int startHH, int startMM, int endHH,int endMM){
        page.getstartHour().click();
        page.getstartHour().sendKeys(Integer.toString(startHH));
        page.getstartMinutes().click();
        page.getstartMinutes().sendKeys(Integer.toString(startMM));
        page.getEndHour().click();
        page.getEndHour().sendKeys(Integer.toString(endHH));
        page.getEndMinutes().click();
        page.getEndMinutes().sendKeys(Integer.toString(endMM));
    }


    private void inputDates(String startDate, String endDate){
        //MM/DD/YYY
        page.getstartDate().sendKeys(startDate);
        page.getEndDate().sendKeys(endDate);

    }


    public String calculateCurrentTime(){
        String timeStamp = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss").format(Calendar.getInstance().getTime());

        System.out.println(timeStamp);
        return timeStamp;

    }

    private String extractCurrentDate(String timeStamp){
        String date = timeStamp.substring(0,DATE_FORMAT_LENGTH);
        return date;
    }

    private String extractCurrentHH(String timeStamp){
        int start=DATE_FORMAT_LENGTH+1;
        int end= start+TIME_FORMAT_LENGTH;
        String hours = timeStamp.substring(start,end);
        return hours;
    }


    private String extractCurrentMM(String timeStamp){
        int start=DATE_FORMAT_LENGTH+1 + TIME_FORMAT_LENGTH+1;
        int end= start+TIME_FORMAT_LENGTH;
        String minutes = timeStamp.substring(start,end);
        return minutes;
    }

    private String constructTime(String hh, String mm){
        String derivedTime;

        //pad with 0 if number is <10
        if (hh.length()<TIME_FORMAT_LENGTH){
            hh="0".concat(hh);
        }
        if (mm.length()<TIME_FORMAT_LENGTH){
            mm="0".concat(mm);
        }

        //combine into a regular time;
        derivedTime = hh.concat(":"+mm);

        return derivedTime;
    }

}