package pages;

import helpers.BaseHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import test.tmp.Base;

public class GigatronHomePage extends BaseHelper
{
    @FindBy(linkText = "Prijava")
    WebElement prijavaButton;

    WebDriver driver;

    public GigatronHomePage(WebDriver driver)
    {
        this.driver = driver;
        PageFactory.initElements (driver, this);
    }

    public void prijavaButton ()
    {
        //navigateToHomePage();
        cookieButton();
        clickPrijavaButton();
    }

    public void navigateToHomePage ()
    {
        driver.navigate().to("https://gigatron.rs/");
    }

    private void cookieButton ()
    {
        wdWait.until(ExpectedConditions.presenceOfElementLocated(By.className("primary"))).click(); // btn - first className
    }

    public void clickPrijavaButton ()
    {
        wdWait.until(ExpectedConditions.visibilityOf(prijavaButton)).click();
    }
}
