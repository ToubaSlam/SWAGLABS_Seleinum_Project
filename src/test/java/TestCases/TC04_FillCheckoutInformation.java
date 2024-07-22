package TestCases;

import RetryAnalyser.MyRetry;
import io.qameta.allure.*;
import org.testng.annotations.Test;
import pages.P04_CheckoutInfo;

import static util.Utility.generateRandomFirstName;
import static util.Utility.generateRandomLastName;

@Feature("Information Page Feature")
@Epic("Information Page Epic")
public class TC04_FillCheckoutInformation extends TestBase {

    String fristName = generateRandomFirstName();
    String lastName = generateRandomLastName();
    String zipCode = faker.address().zipCode();


    // Generate a fake ZIP code
    @Story("Fill Needed Information")
    @Severity(SeverityLevel.NORMAL)
    @Description("Fill Needed Information")
    @Test(priority = 1, description = "Fill Needed Information")
    public void FillInformation() throws InterruptedException {
//        WebDriver driver = getDriver(); // Assuming getDriver() method returns the WebDriver instance
        new P04_CheckoutInfo(driver).Insert_FIRSTName_TXT(fristName).Insert_LastName_TXT(lastName).Insert_PostalCode_TXT(zipCode);
        System.out.println("Info added successfully to Cart");

    }

    @Story("Click Continue Shopping")
    @Severity(SeverityLevel.NORMAL)
    @Description("Click Continue Shopping")
    @Test(priority = 2, description = "Click On Continue Shopping")
    public void ClickContinuebtn() throws InterruptedException {
//        WebDriver driver = getDriver(); // Assuming getDriver() method returns the WebDriver instance
        new P04_CheckoutInfo(driver).Click_Continue_BUTTON() ;

    }
}