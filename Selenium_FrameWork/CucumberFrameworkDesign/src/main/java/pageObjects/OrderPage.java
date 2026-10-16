package pageObjects;

import abstractComponents.AbstractComponent;
import org.apache.hc.core5.util.Asserts;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class OrderPage extends AbstractComponent {
    WebDriver driver;
    public OrderPage(WebDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(css = "tr td:nth-of-type(2)")
    private List<WebElement> NAME_OF_PRODUCT_IN_ORDER_PAGE;

    public Boolean checkOrderedProductDisplay(String productName){
        List<WebElement> listOfProduct = NAME_OF_PRODUCT_IN_ORDER_PAGE;
        return listOfProduct.stream().anyMatch(product -> product.getText().equalsIgnoreCase(productName));

    }


}
