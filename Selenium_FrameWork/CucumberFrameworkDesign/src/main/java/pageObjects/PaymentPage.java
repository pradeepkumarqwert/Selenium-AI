package pageObjects;


import abstractComponents.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PaymentPage extends AbstractComponent {
    WebDriver driver;
    public PaymentPage(WebDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = ".form-group input")
    private WebElement COUNTRY_DROPDOWN_IN_PAYMENT_PAGE;
    @FindBy(xpath = "(//section[contains(@class,'ng-star-inserted')]//button[contains(@type,'button')])[2]//span")
    private WebElement INDIA_OPTION_IN_DROPDOWN;
    @FindBy(css = ".action__submit")
    private WebElement PERSONAL_DETAILS_SUBMIT_BUTTON;
    @FindBy(css = ".hero-primary")
    private WebElement ORDER_CONFIRMATION_PAGE_HEADER;

    private final By COUNTRY_DROPDOWN_IN_PAYMENT_PAGE_BY= By.cssSelector(".form-group input");
    private final By SEARCH_INPUT_RESULT_IN_COUNTRY_DROPDOWN_BY = By.cssSelector(".ta-results");
    private final By INDIA_OPTION_IN_DROPDOWN_BY = By.xpath("(//section[contains(@class,'ng-star-inserted')]//button[contains(@type,'button')])[2]//span");


    public void waitForPaymentPageDisplay()  {
        waitForVisibilityOfElement(COUNTRY_DROPDOWN_IN_PAYMENT_PAGE_BY);
    }

    public void clickOnCountryDropDown(){
        COUNTRY_DROPDOWN_IN_PAYMENT_PAGE.click();
    }

    public void enterCountryName(String countryName){
        enterInputViaActionClass(COUNTRY_DROPDOWN_IN_PAYMENT_PAGE,countryName);
    }

    public void waitForEnteredCountryResultsDisplay()  {
        waitForVisibilityOfElement(SEARCH_INPUT_RESULT_IN_COUNTRY_DROPDOWN_BY);
    }

    public void untilIndiaDropdownDisplay() {
        waitForVisibilityOfElement(INDIA_OPTION_IN_DROPDOWN_BY);
    }

    public void scrollUntilPersonDetailSubmitButtonDisplay(){
        scrollByJS(PERSONAL_DETAILS_SUBMIT_BUTTON);
    }

    public void moveToSubmitButtonInPaymentPage() throws InterruptedException {
        hoverOnElement(PERSONAL_DETAILS_SUBMIT_BUTTON);
        Thread.sleep(2000);
    }
    public  void clickOnIndiaOptionInDropDown(){
        INDIA_OPTION_IN_DROPDOWN.click();
    }

    public void clickOnSubmitButtonInPaymentPage(){
        PERSONAL_DETAILS_SUBMIT_BUTTON.click();
    }

    public String fetchOrderConfirmationPageHeaderText(){
        return ORDER_CONFIRMATION_PAGE_HEADER.getText();
    }

    public String selectCountryAndPlaceOrder(String countryName) throws InterruptedException {
        waitForVisibilityOfElement(COUNTRY_DROPDOWN_IN_PAYMENT_PAGE_BY);
        COUNTRY_DROPDOWN_IN_PAYMENT_PAGE.click();
        enterInputViaActionClass(COUNTRY_DROPDOWN_IN_PAYMENT_PAGE,countryName);
        waitForVisibilityOfElement(SEARCH_INPUT_RESULT_IN_COUNTRY_DROPDOWN_BY);
        waitForVisibilityOfElement(INDIA_OPTION_IN_DROPDOWN_BY);
        scrollByJS(PERSONAL_DETAILS_SUBMIT_BUTTON);
        hoverOnElement(PERSONAL_DETAILS_SUBMIT_BUTTON);
        Thread.sleep(2000);
        INDIA_OPTION_IN_DROPDOWN.click();
        PERSONAL_DETAILS_SUBMIT_BUTTON.click();
        return ORDER_CONFIRMATION_PAGE_HEADER.getText();
    }
}
