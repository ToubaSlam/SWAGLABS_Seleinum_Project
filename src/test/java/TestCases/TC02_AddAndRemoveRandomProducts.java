package TestCases;

import RetryAnalyser.MyRetry;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.P02_Products;
import util.Utility;

@Feature("Check Add and Remove Random Products Feature")
@Epic("Check Add and Remove Random Products Epic")
public class TC02_AddAndRemoveRandomProducts extends TestBase {


    // create new customer
    @Story("Add Random Products To Cart")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Add Random Products To Cart")
    @Test(priority = 1, description = "Add Random Products To Cart")
    public void AddRandomProducts() throws InterruptedException {
        int number_Of_Products= Utility.generateUniqueRandomNumbers(4).get(3);
        System.out.println(number_Of_Products);
        new P02_Products(driver).addRandomProducts(number_Of_Products);
    }

    @Story("Remove Random Products To Cart")
    @Severity(SeverityLevel.NORMAL)
    @Description("Remove Random Products To Cart")
    @Test(priority = 2, description = "Remove Random Products To Cart")
    public void RemoveRandomProducts() throws InterruptedException {
        new P02_Products(driver).DeleteProduct2();
    }

    @Story("Click on Cart Button")
    @Severity(SeverityLevel.NORMAL)
    @Description("Click on Cart Button")
    @Test(priority = 3, description = "Click on Cart Button")
    public void ClickCartButton() throws InterruptedException {
        new P02_Products(driver).clickOnCart();
        Assert.assertTrue(new P02_Products(driver).VerifyYourCartProducts_Sucessfully());
        System.out.println(new P02_Products(driver).VerifyYourCartProducts_Sucessfully());
        System.out.println("Navigated successfully to Cart");

    }
}