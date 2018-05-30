package calcGroupId;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Base {
    final String PATH_VAR = "path"; //Var to derive driver's file location in the data.properties file
    public WebDriver driver;
    Properties properties;
    FileInputStream fileInputStream;


    public void loadPropertiesFiles(){
        try {
            properties = new Properties();
            fileInputStream = new FileInputStream("/home/alexz/Desktop/git/calcArtifact/src/main/java/data.properties");
            properties.load(fileInputStream);
        }catch (IOException e){
            System.out.println("loadpropertiesFiles is failing");
        }
    }



    public WebDriver initializeDriver(){
        loadPropertiesFiles();
        String browser;
        String browser_path;

        browser=properties.getProperty("browser");
        System.out.println("Browser: "+browser);


        if (browser.equals("chrome")){
            System.out.println("working on chrome");
            browser_path=properties.getProperty("chrome_path");
            System.setProperty("webdriver.chrome.driver",browser_path);
            driver=new ChromeDriver();
        }
        else if (browser.equals("firefox")){
            browser_path=properties.getProperty("firefox_path");

            System.setProperty("webdriver.gecko.driver",browser_path);
            driver=new FirefoxDriver();
        }

        else if(browser.equals("IE")){
            browser_path=properties.getProperty("IE_path");
            System.setProperty("webdriver.ie.driver",browser_path);
            driver=new InternetExplorerDriver();
        }

        //driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);//skips the "I am not a robot"

        return driver;
    }
}
