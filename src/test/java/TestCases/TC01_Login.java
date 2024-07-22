package TestCases;

import RetryAnalyser.MyRetry;
import pages.P01_LoginPage;

import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;

@Feature("Check Login Feature")
@Epic("Check Login Epic")
public class TC01_Login extends TestBase {

    static String userId = "standard_user";
    static String password = "secret_sauce";
    // check login positive scenarios
    @Story("Login Page")
    @Severity(SeverityLevel.BLOCKER)
    @Description("login with valid credential")
    @Test(priority = 1, description = "login with valid credential")
    public void Login() throws InterruptedException {
        new P01_LoginPage(driver).Insert_UserNAME_TXT(userId).
                Insert_PassWord_TXT(password).
                clickLOGIN_BUTTON();
        Assert.assertTrue(new P01_LoginPage(driver).VerifyLoginSucessfully());
        System.out.println("Login Successfully done");
    }



}