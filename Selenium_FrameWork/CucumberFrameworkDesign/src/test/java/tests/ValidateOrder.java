package tests;

import base.BaseTest;
import base.Retry;
import constants.StandaloneTestData;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.*;

public class ValidateOrder extends BaseTest {

    public static final String user1EmailId = StandaloneTestData.USER_1_EMAIL_ID;
    public static final String user1Password = StandaloneTestData.USER_1_PASSWORD;
    public static final String productName = StandaloneTestData.PRODUCT_NAME;
    public static final String countryName = StandaloneTestData.COUNTRY_NAME;


    @Test(retryAnalyzer = Retry.class)
    public void validatingPlacedOrder(){
        LandingPage landingPage = new LandingPage(driver);
        landingPage.goTo();
        OrderPage orderPage = landingPage.goToOrderpage(user1EmailId, user1Password);
        Assert.assertTrue(orderPage.checkOrderedProductDisplay(productName));
    }
}
