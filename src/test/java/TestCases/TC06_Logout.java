package TestCases;

import RetryAnalyser.MyRetry;
import io.qameta.allure.*;
import org.testng.annotations.Test;
import pages.P06_LogoutPage;
import pages.PageBase;

@Feature("Check Logout Feature")
@Epic("Check Logout Epic")
public class TC06_Logout extends TestBase {

    // logout page
    @Story("logout page")
    @Severity(SeverityLevel.NORMAL)
    @Description("logout page")
    @Test(priority = 1, description = "logout page")
    public void logOut() {
        new P06_LogoutPage(driver).clickOnBurgerMenu();
        new P06_LogoutPage(driver).clickOnLogOut();
        new PageBase(driver).handleAlert();

    }

}