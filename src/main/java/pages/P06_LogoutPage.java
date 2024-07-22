package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static org.junit.Assert.fail;
import static pages.PageBase.shortWait;

public class P06_LogoutPage {

   WebDriver driver;
    public static String customerId;
    public P06_LogoutPage(WebDriver driver) {
        this.driver = driver;
    }


    private final By BURGER_MENU = By.xpath("//div[@class=\"bm-burger-button\"]");
    private final By LOGOUT = By.xpath("//a[@id=\"logout_sidebar_link\"]");


    public P06_LogoutPage clickOnBurgerMenu() {
        try {
            shortWait(driver).until(ExpectedConditions.elementToBeClickable(this.BURGER_MENU));
        } catch (TimeoutException exception) {
            fail("Element not found");
        }
        driver.findElement(this.BURGER_MENU).click();
        return this;
    }

    public P06_LogoutPage clickOnLogOut() {
        try {
            shortWait(driver).until(ExpectedConditions.elementToBeClickable(this.LOGOUT));
        } catch (TimeoutException exception) {
            fail("Element not found");
        }
        driver.findElement(this.LOGOUT).click();
        return this;
    }

}
