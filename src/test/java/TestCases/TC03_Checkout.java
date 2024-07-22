package TestCases;

import RetryAnalyser.MyRetry;
import io.qameta.allure.*;
import org.testng.annotations.Test;
import pages.*;

import static TestCases.TC01_Login.password;
import static TestCases.TC01_Login.userId;

@Feature("Check out Page Feature")
@Epic("Check out Page Epic")
public class TC03_Checkout extends TestBase {


    @Story("Click On Checkout")
    @Severity(SeverityLevel.NORMAL)
    @Description("Click On Checkout")
    @Test(priority = 1, description = "Click On Checkout")
    public void ClickCheckoutButton() throws InterruptedException {
    new P03_ShoppingCart(driver).checkoutBtn();
    new P04_CheckoutInfo(driver).VerifyYourCheckoutOverViewPageSucessfully();
        System.out.println(new P04_CheckoutInfo(driver).VerifyYourCheckoutOverViewPageSucessfully());
        System.out.println("Iteams added successfully to Cart");

    }


    @Test(priority = 2, description = "NavigateBack")
    public void CheckNavigateBack() throws InterruptedException {
        new P03_ShoppingCart(driver).ClickNavigateBack();
    }
}