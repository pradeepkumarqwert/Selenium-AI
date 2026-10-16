package base;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utils.DriverManager;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class BaseTest {
    public WebDriver driver;
    @BeforeMethod
    public void initialization() throws IOException {
        Properties prop = new Properties();
        FileInputStream fs = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\resources\\GlobalData.properties");
        prop.load(fs);
        String browserName = System.getProperty("browser")!=null ? System.getProperty("browser") : prop.getProperty("browser");

        if(browserName.equalsIgnoreCase("chrome")){
            ChromeOptions options = new ChromeOptions();
            if(browserName.contains("headless")) {
                options.addArguments("headless");
            }
            options.addArguments("--disable-notifications");
            DriverManager.setDriver(new org.openqa.selenium.chrome.ChromeDriver(options));
            driver = DriverManager.getDriver();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            driver.manage().window().maximize();
        }
        else if(browserName.equalsIgnoreCase("firefox")){
            FirefoxOptions options = new FirefoxOptions();
            if(browserName.contains("headless")) {
                options.addArguments("--headless");
            }
            options.addArguments("--disable-notifications");
            DriverManager.setDriver(new org.openqa.selenium.firefox.FirefoxDriver(options));
            driver = DriverManager.getDriver();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            driver.manage().window().maximize();

        }
        else if(browserName.equalsIgnoreCase("edge")){
            EdgeOptions options = new EdgeOptions();
            if(browserName.contains("headless")) {
                options.addArguments("--headless");
            }
            options.addArguments("--disable-notifications");
            DriverManager.setDriver(new org.openqa.selenium.edge.EdgeDriver(options));
            driver = DriverManager.getDriver();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            driver.manage().window().maximize();
        }



    }
    @AfterMethod
    public void tearDown(){
        if(DriverManager.getDriver() != null){
            DriverManager.getDriver().quit();
            DriverManager.unload();
        }else {
            System.out.println("Driver Initialization Failed");
        }
    }

    public String getScreenshot(String testCaseName, WebDriver driver) throws IOException {
        TakesScreenshot ss = (TakesScreenshot) driver;
        File source = ss.getScreenshotAs(OutputType.FILE);
        File path = new File(System.getProperty("user.dir")+"\\test-outputs\\screenshots\\"+testCaseName+".png");

        FileUtils.copyFile(source,path);
        return System.getProperty("user.dir")+"\\test-outputs\\screenshots\\"+testCaseName+".png";
    }

}
