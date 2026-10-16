package abstractComponents;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageObjects.OrderPage;

import java.time.Duration;

public class AbstractComponent {
    WebDriver driver;
    public AbstractComponent(WebDriver driver){
        this.driver = driver;
    }

    public void waitForVisibilityOfElement(By findBy) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
    }

    public void mouseHoverOnElement(By element){

    }


    public void waitForVisibilityOfElementByWebElement(WebElement element){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void waitForInVisibilityOfElement(WebElement findBy) throws InterruptedException {
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(6));
//        wait.until(ExpectedConditions.invisibilityOf(findBy));
        Thread.sleep(3000);
    }

    public void hoverOnElement(WebElement element){
        Actions a = new Actions(driver);
        a.moveToElement(element);
    }

    public void enterInputViaActionClass(WebElement element, String countryName){
        Actions a = new Actions(driver);
        a.sendKeys(element, countryName).build().perform();
    }

    public void scrollByJS(WebElement element){
        JavascriptExecutor js = (JavascriptExecutor) driver;

        js.executeScript(
                "arguments[0].scrollIntoView({block: 'center'});",
                element
        );
    }

    public void scrollByJSByByElement(By element){
        JavascriptExecutor js = (JavascriptExecutor) driver;

        js.executeScript(
                "arguments[0].scrollIntoView({block: 'center'});",
                driver.findElement(element)
        );
    }

    public OrderPage navigateToOrderPage(WebElement GLOBAL_CART_BUTTON){
        GLOBAL_CART_BUTTON.click();
        return new OrderPage(driver);
    }
}
