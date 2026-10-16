package Tests;

import TestComponents.BaseTest;
import TestComponents.RetryAnalizer;
import org.testng.annotations.Test;

public class Script1 extends BaseTest {

    @Test(retryAnalyzer = RetryAnalizer.class)
    public void run1(){
        driver.get("https://www.flipkart.com");
    }
}
