package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import static Drivers.DriverHolder.getDriver;
import static org.junit.Assert.fail;

public class PageBase {

    WebDriver driver;

    public PageBase(WebDriver driver) {
        this.driver=driver;
    }
    private final By HOME_PAGE = By.xpath("//p//a[@href='Managerhomepage.php']");

    public PageBase scrollIntoView(By element){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(element));
        return this;
    }
    public PageBase handleAlert(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(1000));
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        alert.accept();
        return this;
    }

    // handel explicit wait
    public static void explicitWaitForClickable(WebDriver driver, By element) {
        // explicit wait - to wait for the compose button to be click-able
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public static void explicitWaitForVisibility(WebDriver driver, By element) {
        // explicit wait - to wait for the compose button to be click-able
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
        wait.until(ExpectedConditions.visibilityOfElementLocated(element));
    }
    // handel fluent wait
    public static void fluentWaitHandling(WebDriver driver, By element) {
        FluentWait wait = new FluentWait(driver)
                .withTimeout(Duration.ofSeconds(50))
                .pollingEvery(Duration.ofSeconds(5))
                .ignoring(Exception.class);
        wait.until(ExpectedConditions.visibilityOfElementLocated(element));
    }
    // long explicit wait
    public static WebDriverWait longWait(WebDriver driver) {
        return new WebDriverWait(driver, Duration.ofSeconds(25));
    }
    // short explicit wait
    public static WebDriverWait shortWait(WebDriver driver) {
        return new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    // TODO: hover over web element
    public void hoverWebElement(WebDriver driver, WebElement element) {
        // Creating object of an Actions class
        Actions action = new Actions(driver);
        // Performing the mouse hover action on the target element.
        action.moveToElement(element).perform();
    }
    // TODO: Capture Screenshot
    public static void captureScreenshot(WebDriver driver, String screenshotName) {
        TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
        try {
            FileHandler.copy(takesScreenshot.getScreenshotAs(OutputType.FILE), new File(System.getProperty("user.dir")
                    + "/src/test/resources/Screenshots/" + screenshotName + System.currentTimeMillis() + ".png"));
        } catch (WebDriverException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//wait for java script running
    public static void waitForPageLoad(WebDriver driver) {
        (new WebDriverWait(driver, Duration.ofSeconds(50))).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                JavascriptExecutor js = (JavascriptExecutor) d;
                String readyState = js.executeScript("return document.readyState").toString();
                //System.out.println("Ready State: " + readyState);
                return (Boolean) readyState.equals("complete");
            }
        });
    }

    public PageBase returnHomePage() {
        new PageBase(driver).scrollIntoView(this.HOME_PAGE);
        try {
            shortWait(driver).until(ExpectedConditions.visibilityOfElementLocated(this.HOME_PAGE));
        } catch (TimeoutException exception) {
            fail("Element not found");
        }
        driver.findElement(this.HOME_PAGE).click();
        return this;
    }

    public PageBase scrollDown() throws InterruptedException {

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,450)");
        Thread.sleep(1500);
        return this;

    }

    public static void NavigateBackmethod() {
        WebDriver driver = getDriver(); // Assuming getDriver() method returns the WebDriver instance
        getDriver().navigate().back();
        System.out.println("Back Dooooooooooone");
    }
}


