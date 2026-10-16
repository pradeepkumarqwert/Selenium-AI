package pageObjects;

import abstractComponents.AbstractComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class LandingPage extends AbstractComponent {
    WebDriver driver;
    public LandingPage(WebDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(id = "userEmail")
    private WebElement USER_MAIL_ID;

    @FindBy(id = "userPassword")
    private WebElement USER_PASSWORD;

    @FindBy(id = "login")
    private WebElement SUBMIT;

    @FindBy(xpath = "(//button[contains(@class,'btn-custom')])[2]")
    private WebElement GLOBAL_CART_BUTTON;

    @FindBy(css = "[class*='ng-trigger-flyInOut']")
    private WebElement LOGIN_ERROR_MESSAGE;

    public void goTo() throws IOException {
        Properties prop = new Properties();
        FileInputStream fs = new FileInputStream(System.getProperty("user.dir")+"src\\main\\java\\resources\\GlobalData.properties");
        prop.load(fs);
        String url = System.getProperty("url") != null? System.getProperty("url"):prop.getProperty("url");
        driver.get(url);
    }




    public void loginApplication(String userName, String password) {
        USER_MAIL_ID.sendKeys(userName);
        USER_PASSWORD.sendKeys(password);
        SUBMIT.click();
    }

    public OrderPage goToOrderpage(String userName, String password) {
        USER_MAIL_ID.sendKeys(userName);
        USER_PASSWORD.sendKeys(password);
        SUBMIT.click();
        return navigateToOrderPage(GLOBAL_CART_BUTTON);
    }

    public void loginWithInvalidCredInApplication(String userName, String password) {
        USER_MAIL_ID.sendKeys(userName);
        USER_PASSWORD.sendKeys(password);
        SUBMIT.click();
    }

    public void waitUntilLoginErrorMessageDisplay(){
        waitForVisibilityOfElementByWebElement(LOGIN_ERROR_MESSAGE);
    }

}
