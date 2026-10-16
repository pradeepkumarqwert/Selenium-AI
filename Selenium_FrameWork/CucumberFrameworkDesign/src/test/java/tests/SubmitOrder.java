package tests;

import base.BaseTest;
import base.Retry;
import constants.StandaloneTestData;
import pageObjects.*;
import org.testng.Assert;

import org.testng.annotations.Test;

public class SubmitOrder extends BaseTest {
    String user1EmailId = StandaloneTestData.USER_1_EMAIL_ID;
    String user1Password = StandaloneTestData.USER_1_PASSWORD;
    String productName = StandaloneTestData.PRODUCT_NAME;
    String countryName = StandaloneTestData.COUNTRY_NAME;
    String expectedOrderConfirmationPageHeaderText = StandaloneTestData.EXPECTED_ORDER_CONFIRMATION_PAGE_HEADER_TEXT;


    @Test(retryAnalyzer = Retry.class)
    public void run1() throws InterruptedException {
            //Landing Page
        Productcatelogue productcatelogue = landingPage.loginApplication(user1EmailId, user1Password);
            //Product Catalogue page
        productcatelogue.addProductIntoCart(productName);
        productcatelogue.waitForToasterMessageDisplay();
        productcatelogue.waitForLoaderToDisappear();
        CartPage cartPage = productcatelogue.navigateToCartPage();

            //Cart Page
        cartPage.waitForCartPageDisplay();
        cartPage.waitForCartPageDisplay();
        Assert.assertTrue(cartPage.checkAddedProductInCartProducts(productName),"Product not found in cart");
        cartPage.hoverOnAddedProductBuyButtonInCartProducts();
        PaymentPage paymentPage = cartPage.clickOnAddedProductBuyButton();

            //Payment Page
        Assert.assertTrue(paymentPage.selectCountryAndPlaceOrder(countryName).equalsIgnoreCase(expectedOrderConfirmationPageHeaderText));
    }

    @Test(retryAnalyzer = Retry.class)
    public void validatingPlacedOrder(){

        OrderPage orderPage = landingPage.goToOrderpage(user1EmailId, user1Password);
        Assert.assertTrue(orderPage.checkOrderedProductDisplay(productName));
    }

}
