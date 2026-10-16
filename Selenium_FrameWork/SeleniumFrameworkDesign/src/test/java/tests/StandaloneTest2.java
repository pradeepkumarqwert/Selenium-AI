package tests;

import base.BaseTest;
import base.Retry;
import constants.StandaloneTestData;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import pageObjects.*;
import org.testng.Assert;

import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

public class StandaloneTest2 extends BaseTest {
    String user1EmailId = StandaloneTestData.USER_1_EMAIL_ID;
    String user1Password = StandaloneTestData.USER_1_PASSWORD;
    String productName = StandaloneTestData.PRODUCT_NAME;
    String countryName = StandaloneTestData.COUNTRY_NAME;
    String expectedOrderConfirmationPageHeaderText = StandaloneTestData.EXPECTED_ORDER_CONFIRMATION_PAGE_HEADER_TEXT;


    @Test(retryAnalyzer = Retry.class)
    public void run1() throws InterruptedException, IOException {
            //Landing Page
            LandingPage landingPage = new LandingPage(driver);
            landingPage.goTo();
            landingPage.loginApplication(user1EmailId, user1Password);

            //Product Catalogue page
            Productcatelogue productcatelogue = new Productcatelogue(driver);
            productcatelogue.addProductIntoCart(productName);
            productcatelogue.waitForToasterMessageDisplay();
            productcatelogue.waitForLoaderToDisappear();
            productcatelogue.navigateToCartPage();

            //Cart Page
            CartPage cartPage = new CartPage(driver);
            cartPage.waitForCartPageDisplay();
            cartPage.waitForCartPageDisplay();
            Assert.assertTrue(cartPage.checkAddedProductInCartProducts(productName),"Product not found in cart");
            cartPage.hoverOnAddedProductBuyButtonInCartProducts();
            cartPage.clickOnAddedProductBuyButton();

            //Payment Page
            PaymentPage paymentPage = new PaymentPage(driver);
            Assert.assertTrue(paymentPage.selectCountryAndPlaceOrder(countryName).equalsIgnoreCase(expectedOrderConfirmationPageHeaderText));
    }

    @Test(retryAnalyzer = Retry.class)
    public void validatingPlacedOrder() throws IOException {
        LandingPage landingPage = new LandingPage(driver);
        landingPage.goTo();
        OrderPage orderPage = landingPage.goToOrderpage(user1EmailId, user1Password);
        Assert.assertTrue(orderPage.checkOrderedProductDisplay(productName));
    }

}
