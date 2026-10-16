package tests;

import base.BaseTest;
import base.Retry;
import constants.StandaloneTestData;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import pageObjects.LandingPage;

public class ErrorValidation extends BaseTest {
    String userInvalidEmailId = StandaloneTestData.USER_Invalid_EMAIL_ID;
    String user1Password = StandaloneTestData.USER_1_PASSWORD;
    String productName = StandaloneTestData.PRODUCT_NAME;
    String countryName = StandaloneTestData.COUNTRY_NAME;
    String expectedOrderConfirmationPageHeaderText = StandaloneTestData.EXPECTED_ORDER_CONFIRMATION_PAGE_HEADER_TEXT;

    @Test(retryAnalyzer = Retry.class)
    public void loginFailValidation(){
        LandingPage landingPage = new LandingPage(driver);
        landingPage.goTo();
        landingPage.loginWithInvalidCredInApplication(userInvalidEmailId,user1Password);
        landingPage.waitUntilLoginErrorMessageDisplay();
    }
}
