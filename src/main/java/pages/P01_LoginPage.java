package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import static pages.PageBase.shortWait;
import static org.junit.Assert.fail;

public class P01_LoginPage {

    WebDriver driver;

    public P01_LoginPage(WebDriver driver) {

        this.driver = driver;
    }

    private final By UserNAME_TXT = By.xpath("//input[@id='user-name']");
    private final By PassWord_TXT = By.xpath("//input[@id='password']");
    private final By LOGIN_BUTTON = By.xpath("//input[@id='login-button']");
    private final By Product_Title = By.xpath("//div[@class='product_label']");


    public P01_LoginPage Insert_UserNAME_TXT(String email) {
        try {
            shortWait(driver).until(ExpectedConditions.visibilityOfElementLocated(UserNAME_TXT));
        } catch (TimeoutException exception) {
            fail("Element not found");
        }
        driver.findElement(this.UserNAME_TXT).sendKeys(email);
        return this;
    }

    public P01_LoginPage Insert_PassWord_TXT(String Paswword) {
        try {
            shortWait(driver).until(ExpectedConditions.visibilityOfElementLocated(PassWord_TXT));
        } catch (TimeoutException exception) {
            fail("Element not found");
        }
        driver.findElement(this.PassWord_TXT).sendKeys(Paswword);
        return this;
    }

    public P01_LoginPage clickLOGIN_BUTTON() {
        try {
            shortWait(driver).until(ExpectedConditions.visibilityOfElementLocated(LOGIN_BUTTON));
        } catch (TimeoutException exception) {
            fail("Element not found");
        }
        driver.findElement(this.LOGIN_BUTTON).click();
        return this;
    }

    public boolean VerifyLoginSucessfully() throws InterruptedException {
        try {
            shortWait(driver).until(ExpectedConditions.visibilityOfElementLocated(Product_Title));
        } catch (TimeoutException exception) {
            fail("Element not found");
        }
        return driver.findElement(this.Product_Title).getText().contains("Products");
    }

    public String  VerifyLogin_ByAssertEqual() {

        try {
            shortWait(driver).until(ExpectedConditions.visibilityOfElementLocated(Product_Title));
        } catch (TimeoutException exception) {
            fail("Element not found");
        }

        return driver.findElement(this.Product_Title).getText();
    }

    public boolean  VerifyADDtoCart_isVisible() {
        try {
            shortWait(driver).until(ExpectedConditions.visibilityOfElementLocated(Product_Title));
        } catch (TimeoutException exception) {
            fail("Element not found");
        }
        return driver.findElement(this.Product_Title).isDisplayed();
    }

}
