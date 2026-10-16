package pageObjects;

import abstractComponents.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class Productcatelogue extends AbstractComponent {
    WebDriver driver;
    public Productcatelogue(WebDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    @FindBy(css = ".mb-3")
    private List<WebElement> PRODUCTS;
    @FindBy(css = ".ng-animating")
    private WebElement LOADER_ANIMATION;
    @FindBy(css = "[routerlink*='cart']")
    private WebElement GLOBAL_CART_BUTTON;
    private final By PRODUCTS_BY = By.cssSelector(".mb-3");
    private final By TOASTER_MESSAGE_BY = By.cssSelector("#toast-container");
    private final By ADDPRODUCTINTOCARTBUTTON = By.cssSelector(".card-body button:last-of-type");

    public List<WebElement> getProductList() {
        waitForVisibilityOfElement(PRODUCTS_BY);
        return PRODUCTS;
    }

    public WebElement getProductByName(String productName)  {
        WebElement prod =	getProductList().stream().filter(product->
                product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
        return prod;
    }

    public void addProductIntoCart(String productName) throws InterruptedException {
        scrollByJSByByElement(ADDPRODUCTINTOCARTBUTTON);
        waitForVisibilityOfElement(ADDPRODUCTINTOCARTBUTTON);
        getProductByName(productName).findElement(ADDPRODUCTINTOCARTBUTTON).click();
    }

    public void waitForToasterMessageDisplay() {
        waitForVisibilityOfElement(TOASTER_MESSAGE_BY);
    }

    public void waitForLoaderToDisappear() throws InterruptedException {
        waitForInVisibilityOfElement(LOADER_ANIMATION);
    }

    public CartPage navigateToCartPage(){
        scrollByJS(GLOBAL_CART_BUTTON);
        GLOBAL_CART_BUTTON.click();
        return new CartPage(driver);
    }


}
