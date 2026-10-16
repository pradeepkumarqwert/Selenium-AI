package pageObjects;

import abstractComponents.AbstractComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

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

    public void goTo(){
        driver.get("https://rahulshettyacademy.com/client/#/auth/login");
    }




    public Productcatelogue loginApplication(String userName, String password) {
        USER_MAIL_ID.sendKeys(userName);
        USER_PASSWORD.sendKeys(password);
        SUBMIT.click();
        return new Productcatelogue(driver);
    }

    public OrderPage goToOrderpage(String userName, String password) {
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
