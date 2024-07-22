package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.fail;
import static pages.PageBase.longWait;
import static pages.PageBase.shortWait;
import static util.Utility.*;

public class P02_Products {

   WebDriver driver;
    public static float total;
    int temp;

    public P02_Products(WebDriver driver) {

        this.driver = driver;
    }
    private final By CART = By.xpath("//a[@href='./cart.html']");
    private final By CART_TITLE = By.xpath("//div[@class='subheader']");
    public P02_Products clickOnCart() {
        try {
            longWait(driver).until(ExpectedConditions.visibilityOfElementLocated(CART));
        } catch (TimeoutException exception) {
            fail("Element not found");
        }
        driver.findElement(CART).click();
        return this;
    }

    public boolean VerifyYourCartProducts_Sucessfully() {
        return driver.findElement(this.CART_TITLE).getText().contains("Your Cart");
    }
    public boolean  VerifyYourCartProducts_isVisible() {
        return driver.findElement(this.CART_TITLE).isDisplayed();
//        return driver.findElement(this.ADD_TO_CART_Button1).isEnabled();
//        return driver.findElement(this.ADD_TO_CART_Button1).isSelected();//Checkbox
    }
    public String  VerifyYourCartProducts_ByAssertEqual() {
        return driver.findElement(this.CART_TITLE).getText();
    }

    public P02_Products addRandomProducts(int count) throws InterruptedException {
        temp = count;
        List<Integer> uniqueRandomNumbers = generateUniqueRandomNumbers(count);
        total = 0;
        // 1,4,6,2,3
        for (int i = 1; i <= count; i++) {
            // click "add to card"
            driver.findElement(By.xpath("(//button[@class='btn_primary btn_inventory'])[" + uniqueRandomNumbers.get(i - 1) + "]")).click();
            // store price
            new PageBase(driver).scrollDown();
            total += parsePriceFromString(driver.findElement(By.xpath("(//div[@class='inventory_item_price'])[" + uniqueRandomNumbers.get(i - 1) + "]")).getText());
        }
        new PageBase(driver).scrollDown();
        System.out.println("Total Price is :" + total);
        return this;
    }
    public static float Total_Price_SelectedAmountByuser;
    ArrayList<Integer> ArrayProducts;

    public P02_Products DeleteProduct2() throws InterruptedException {
        // Get the size of elements
        int numberOfItemsAbleToRemove = driver.findElements(By.xpath("//button[@class='btn_secondary btn_inventory']")).size();
        int removeRandomItem_id = getRandomNumberBetween1andN(numberOfItemsAbleToRemove);
        // Wait for the specific item price to be visible
        WebElement itemPriceElement = driver.findElement(By.xpath("(//div[@class='inventory_item_price'])[" + removeRandomItem_id + "]"));
        String removedItem_Price = itemPriceElement.getText();
        WebElement itemNameElement = driver.findElement(By.xpath("(//div[@class='inventory_item_name'])[" + removeRandomItem_id + "]"));
        String removedItem_Name = itemPriceElement.getText();
        // Wait for the specific remove button to be clickable
        WebElement removeButton = driver.findElement(By.xpath("(//button[@class='btn_secondary btn_inventory'])[" + removeRandomItem_id + "]"));
        removeButton.click();
        // Parsing the price
        String[] parts = removedItem_Price.split("\\$");
        String amount = parts[1]; // Amount without the symbol
        System.out.println("Total_Price_SelectedAmountByuser before removed: " + Total_Price_SelectedAmountByuser);
        Total_Price_SelectedAmountByuser -= Float.parseFloat(amount);
        System.out.println("The deleted Product name:" + removedItem_Name + " ,price:" + amount);
        System.out.println("Total_Price_SelectedAmountByuser after removed: " + Total_Price_SelectedAmountByuser);
        return this;
    }


}
