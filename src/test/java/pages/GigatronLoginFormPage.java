package pages;

import helpers.BaseHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class GigatronLoginFormPage extends BaseHelper
{
    @FindBy(id = "email")
    WebElement emailField;

    @FindBy (id="password")
    WebElement passwordField;

    @FindBy (id = "loginSubmit")
    WebElement prijavaButton;

    WebDriver driver;

    public GigatronLoginFormPage(WebDriver driver)
    {
        this.driver = driver;
        PageFactory.initElements (driver, this);
    }

    public void logIn (String email, String password)
    {
        inputEmail(email);
        inputPassword(password);
        clickOnPrijavaSubmitButton();
    }

    private void inputEmail (String email)
    {
        wdWait.until(ExpectedConditions.visibilityOf(emailField));
        emailField.sendKeys(email);
    }

    private void inputPassword (String password)
    {
        wdWait.until(ExpectedConditions.visibilityOf(passwordField));
        passwordField.sendKeys(password);
    }

    public void clickOnPrijavaSubmitButton()
    {
        wdWait.until(ExpectedConditions.visibilityOf(prijavaButton));
        prijavaButton.click();
    }

    public String getGigatronLoginUrl ()
    {
        String currentUrl = driver.getCurrentUrl();
        return currentUrl;
    }

    public String getErrorMessage ()
    {
        wdWait.until(ExpectedConditions.presenceOfElementLocated(By.className("message-error")));
        WebElement messageError = driver.findElement(By.className("message-error"));
        String errorMessage = messageError.getText();
        return errorMessage;
    }
}
