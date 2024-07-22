package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static org.junit.Assert.fail;
import static pages.PageBase.shortWait;

public class P05_CheckoutOverview {

   WebDriver driver;
    public P05_CheckoutOverview(WebDriver driver) {
        this.driver = driver;
    }
    private final By Total_AllPrice = By.xpath("//div[@class='summary_subtotal_label']");
    private final By Taxes = By.xpath("//div[@class='summary_tax_label']");
    private final By PriceAndTaxes = By.xpath("//div[@class='summary_total_label']");
    private final By FINISH_BUTTON = By.xpath("//a[text()='FINISH']");
    private final By YourFinish_Title = By.xpath("//div[@class='subheader']");
    public static float Total_Price_calcbySystem;
    private final By CHECK_FINISH_ORDER = By.xpath("//h2[@class='complete-header']");

    public String getMessageForCHECK_FINISH_ORDER() {

        return driver.findElement(CHECK_FINISH_ORDER).getText();
    }

    public P05_CheckoutOverview GetTotalprice() {
        String TotalpriceText = driver.findElement(this.Total_AllPrice).getText();

        String[] parts = TotalpriceText.split("\\$");
        String currencySymbol = parts[0]; // Currency symbol
        String amount = parts[1]; // Amount without the symbol
        Total_Price_calcbySystem = Float.parseFloat(amount);
        System.out.println("Total_Price_calcbySystem : " + Total_Price_calcbySystem);
        return this;
    }

    public static float Total_Taxes;

    public P05_CheckoutOverview Get_Taxes() {
        String TaxesText = driver.findElement(this.Taxes).getText();

        String[] parts = TaxesText.split("\\$");
        String currencySymbol = parts[0]; // Currency symbol
        String amount = parts[1]; // Amount without the symbol
        Total_Taxes = Float.parseFloat(amount);
        System.out.println("Total_Taxes only : " + Total_Taxes);
        return this;
    }

    public static float PriceAndTaxesAmount;

    public P05_CheckoutOverview Get_PriceAndTaxes() {
        String PriceAndTaxesText = driver.findElement(this.PriceAndTaxes).getText();

        String[] parts = PriceAndTaxesText.split("\\$");
        String currencySymbol = parts[0]; // Currency symbol
        String amount = parts[1]; // Amount without the symbol
        PriceAndTaxesAmount = Float.parseFloat(amount);
        System.out.println("PriceAndTaxesAmount : " + PriceAndTaxesAmount);
        return this;
    }



    public P05_CheckoutOverview CheckThe_TotalAmountbySysAndTax_withFinalCalcAmount() {
        if (PriceAndTaxesAmount == (Total_Taxes + Total_Price_calcbySystem)) {
            System.out.println(PriceAndTaxesAmount + "==" + "(" + Total_Taxes + "+" + Total_Price_calcbySystem + ")");
            System.out.println("Yes The_Total Final amount equal the CalcAmount by system and taxes");
        } else
            System.out.println("NO The_Total Final amount equal the CalcAmount by system and taxes");
        return this;
    }

    public P05_CheckoutOverview Click_FINISH_BUTTON() {
        driver.findElement(this.FINISH_BUTTON).click();
        return this;
    }

    public boolean VerifYourFINISHPageSucessfully() {
        return driver.findElement(this.YourFinish_Title).getText().contains("Finish");
    }
    public boolean  VerifYourFINISH_isVisible() {
        return driver.findElement(this.YourFinish_Title).isDisplayed();
//        return driver.findElement(this.ADD_TO_CART_Button1).isEnabled();
//        return driver.findElement(this.ADD_TO_CART_Button1).isSelected();//Checkbox
    }
    public String  VerifYYourFINISH_ByAssertEqual() {
        return driver.findElement(this.YourFinish_Title).getText();
    }

    }







