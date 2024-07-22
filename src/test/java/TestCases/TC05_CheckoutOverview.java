package TestCases;

import RetryAnalyser.MyRetry;
import io.qameta.allure.*;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.P04_CheckoutInfo;
import pages.P05_CheckoutOverview;

import static util.Utility.generateRandomFirstName;
import static util.Utility.generateRandomLastName;

@Feature("Checkout Page Feature")
@Epic("Checkout Page Epic")
public class TC05_CheckoutOverview extends TestBase {

    String fristName = generateRandomFirstName();
    String lastName = generateRandomLastName();
    String zipCode = faker.address().zipCode();

    
    // Generate a fake ZIP code
    @Story("Click On Checkout")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Click On Checkout")
    @Test(priority = 1, description = "Click On Checkout")
    public void CheckClickFinishbtn() throws InterruptedException {
//        WebDriver driver = getDriver(); // Assuming getDriver() method returns the WebDriver instance
        new P05_CheckoutOverview(driver).CheckThe_TotalAmountbySysAndTax_withFinalCalcAmount().Click_FINISH_BUTTON() ;

        String actualMessage = new P05_CheckoutOverview(driver).getMessageForCHECK_FINISH_ORDER();

        String expectedMessage = "THANK YOU FOR YOUR ORDER";
        System.out.println("Actual Message: " + actualMessage);

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(actualMessage, expectedMessage);
        softAssert.assertAll();

        System.out.println(actualMessage);


    }

}