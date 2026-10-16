package TestComponents;

import Utils.DriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class BaseTest {
    public WebDriver driver;
    @BeforeMethod
    public void initializer() throws IOException {

        Properties prop = new Properties();
        FileInputStream path = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\resources\\GlobalData.properties");
        prop.load(path);
        String browserName = prop.getProperty("browser");

        if(browserName.equalsIgnoreCase("chrome")){
            DriverManager.setDriver(new org.openqa.selenium.chrome.ChromeDriver());
            driver = DriverManager.getDriver();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
            driver.manage().window().maximize();
        }
        else if(browserName.equalsIgnoreCase("firefox")){
            DriverManager.setDriver(new org.openqa.selenium.firefox.FirefoxDriver());
            driver = DriverManager.getDriver();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
            driver.manage().window().maximize();
        }
        else if(browserName.equalsIgnoreCase("edge")){
            DriverManager.setDriver(new org.openqa.selenium.edge.EdgeDriver());
            driver = DriverManager.getDriver();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
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
        String dirPath = System.getProperty("user.dir") + "\\test-outputs\\Screenshots\\";
        File dir = new File(dirPath);
        if(!dir.exists()){
            dir.mkdirs();
        }
        File fullPath = new File(dirPath + testCaseName + ".png");
        FileUtils.copyFile(source,fullPath);
        return fullPath.getAbsolutePath();
    }

}
