package pageObjects;

import abstractComponents.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CartPage extends AbstractComponent {
    WebDriver driver;
    //Constructor
    public CartPage(WebDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(css = ".cartSection h3")
    private List<WebElement> CART_PRODUCTS_LIST;
    @FindBy(css = ".cartSection .btn-primary")
    private WebElement ADDED_PRODUCT_BUY_BUTTON;

    private final By CART_PAGE_HEADER_TEXT = By.cssSelector(".cartSection h3");
    private final By ADDED_PRODUCT_BUY_BUTTON_BY= By.cssSelector(".cartSection .btn-primary");

    public void waitForCartPageDisplay(){
        waitForVisibilityOfElement(CART_PAGE_HEADER_TEXT);
    }

    public List<WebElement> fetchCartProducts(){
        return CART_PRODUCTS_LIST;
    }

    public Boolean checkAddedProductInCartProducts(String productName){
        return fetchCartProducts().stream().anyMatch(cartProduct-> cartProduct.getText().equalsIgnoreCase(productName));
    }

    public void hoverOnAddedProductBuyButtonInCartProducts()  {
        hoverOnElement(ADDED_PRODUCT_BUY_BUTTON);
        waitForVisibilityOfElement(ADDED_PRODUCT_BUY_BUTTON_BY);
    }

    public void clickOnAddedProductBuyButton(){
        ADDED_PRODUCT_BUY_BUTTON.click();
    }
}
