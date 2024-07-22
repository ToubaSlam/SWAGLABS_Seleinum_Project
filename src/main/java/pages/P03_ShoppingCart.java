package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static org.junit.Assert.fail;
import static pages.PageBase.NavigateBackmethod;
import static pages.PageBase.shortWait;

public class P03_ShoppingCart {

   WebDriver driver;
    public static String customerId;
    public P03_ShoppingCart(WebDriver driver) {
        this.driver = driver;
    }

    private final By CONTINUE_SHOPPING = By.xpath("//p[text()='Edit Customer Form']");
    private final By CHECKOUT = By.xpath("//a[@class=\"btn_action checkout_button\"]");
    private final By CUSTOMER_ID = By.xpath("//input[@name='cusid']");
    private final By SUBMIT_BTN = By.xpath("//input[@name='AccSubmit']");


//    public boolean verifyEditCustomerForm(String msg){
//        return driver.findElement(this.editCustomerMsg).getText().contains(msg);
//    }
    public P03_ShoppingCart CONTINUE_SHOPPING() {
        try {
            shortWait(driver).until(ExpectedConditions.visibilityOfElementLocated(this.CONTINUE_SHOPPING));
        } catch (TimeoutException exception) {
            fail("Element not found");
        }
        driver.findElement(this.CONTINUE_SHOPPING).click();
        return this;
    }

    public P03_ShoppingCart enterCustomerId(String customerIdValue) {
        new PageBase(driver).scrollIntoView(this.CUSTOMER_ID);
        driver.findElement(this.CUSTOMER_ID).sendKeys(customerIdValue);
        return this;
    }

    public P03_ShoppingCart checkoutBtn() {
        try {
            shortWait(driver).until(ExpectedConditions.visibilityOfElementLocated(this.CHECKOUT));
        } catch (TimeoutException exception) {
            fail("Element not found");
        }
        driver.findElement(this.CHECKOUT).click();
        return this;
    }


    public P03_ShoppingCart ClickNavigateBack() throws InterruptedException {
        Thread.sleep(5000);
        NavigateBackmethod();
        return this;
    }



}
