package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;

import static org.junit.Assert.fail;
import static pages.PageBase.shortWait;

public class P04_CheckoutInfo {

   WebDriver driver;
    public P04_CheckoutInfo(WebDriver driver) {
        this.driver = driver;
    }

    private final By FIRSTName_TXT = By.xpath("//input[@id='first-name']");
    private final By LastName_TXT = By.xpath("//input[@id='last-name']");
    private final By PostalCode_TXT = By.xpath("//input[@id='postal-code']");
    private final By Continue_BUTTON = By.xpath("//input[@type='submit']");

    private final By YourCheckoutOverView_Title = By.xpath("//div[@class='subheader']");


    public P04_CheckoutInfo Insert_FIRSTName_TXT(String fristName) {
        try {
            shortWait(driver).until(ExpectedConditions.visibilityOfElementLocated(this.FIRSTName_TXT));
        } catch (TimeoutException exception) {
            fail("Element not found");
        }
        driver.findElement(this.FIRSTName_TXT).sendKeys(fristName);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1000));

        return this;
    }


    public P04_CheckoutInfo Insert_LastName_TXT(String lastName) {
        try {
            shortWait(driver).until(ExpectedConditions.visibilityOfElementLocated(this.LastName_TXT));
        } catch (TimeoutException exception) {
            fail("Element not found");
        }
        driver.findElement(this.LastName_TXT).sendKeys(lastName);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1000));
        return this;
    }
    public P04_CheckoutInfo Insert_PostalCode_TXT(String zipCode) {
        try {
            shortWait(driver).until(ExpectedConditions.visibilityOfElementLocated(this.PostalCode_TXT));
        } catch (TimeoutException exception) {
            fail("Element not found");
        }
        driver.findElement(this.PostalCode_TXT).sendKeys(zipCode);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1000));

        return this;
    }
    public P04_CheckoutInfo Click_Continue_BUTTON() {
        try {
            shortWait(driver).until(ExpectedConditions.visibilityOfElementLocated(this.Continue_BUTTON));
        } catch (TimeoutException exception) {
            fail("Element not found");
        }
        driver.findElement(this.Continue_BUTTON).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1000));

        return this;
    }

    public boolean VerifyYourCheckoutOverViewPageSucessfully() {
        return driver.findElement(this.YourCheckoutOverView_Title).getText().contains("Checkout: Overview");
    }

    }







