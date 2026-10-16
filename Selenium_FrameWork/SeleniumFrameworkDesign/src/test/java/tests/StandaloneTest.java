package tests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class StandaloneTest {
    public static void main(String[] args) throws InterruptedException {
        // TODO Auto-generated method stub

        String productName = "ZARA COAT 3";
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get("https://rahulshettyacademy.com/client");
        driver.findElement(By.id("userEmail")).sendKeys("dummykannapa2@gmail.com");
        driver.findElement(By.id("userPassword")).sendKeys("Password@123");
        driver.findElement(By.id("login")).click();
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(6));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
        List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));

        WebElement prod =	products.stream().filter(product->
                product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
        prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();


        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
        //ng-animating
        wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
        Thread.sleep(2000);
        driver.findElement(By.cssSelector("[routerlink*='cart']")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".cartSection h3")));
        List <WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3"));
        Boolean match = 	cartProducts.stream().anyMatch(cartProduct-> cartProduct.getText().equalsIgnoreCase(productName));
        Assert.assertTrue(match);
        Actions a = new Actions(driver);
        a.moveToElement(driver.findElement(By.cssSelector(".cartSection .btn-primary")));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".cartSection .btn-primary")));
        driver.findElement(By.cssSelector(".cartSection .btn-primary")).click();


        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".form-group input")));
//        a.sendKeys(driver.findElement(By.cssSelector(".form-group input")), "india").build().perform();
        driver.findElement(By.cssSelector(".form-group input")).click();
        driver.findElement(By.cssSelector(".form-group input")).sendKeys("india");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));

//        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//button[contains(@class,'ta-item')])[2]")));
//        driver.findElement(By.xpath("(//button[contains(@class,'ta-item')])[2]")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//section[contains(@class,'ng-star-inserted')]//button[contains(@type,'button')])[2]//span")));
        // Scroll until element is visible
        JavascriptExecutor js = (JavascriptExecutor) driver;

        js.executeScript(
                "arguments[0].scrollIntoView({block: 'center'});",
                driver.findElement(By.cssSelector(".action__submit"))
        );
        Thread.sleep(2000);
        a.moveToElement(driver.findElement(By.cssSelector(".action__submit")));
        driver.findElement(By.xpath("(//section[contains(@class,'ng-star-inserted')]//button[contains(@type,'button')])[2]//span")).click();


        driver.findElement(By.cssSelector(".action__submit")).click();

        String confirmMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();
        Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
        driver.close();
    }

}
