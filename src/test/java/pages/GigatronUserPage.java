package pages;

import helpers.BaseHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class GigatronUserPage extends BaseHelper
{
    WebDriver driver;

    public GigatronUserPage(WebDriver driver)
    {
        this.driver = driver;
        PageFactory.initElements (driver, this);
    }

    public String getUserEmail ()
    {
        wdWait.until(ExpectedConditions.presenceOfElementLocated(By.className("truncate")));
        WebElement email = driver.findElement(By.className("truncate"));
        String userEmail = email.getText().substring(8);
        System.out.println("GetUserEmail: " + userEmail);
        return userEmail;
    }

    public void logOut ()
    {
        actionMoveToUser();
        clickOdjaviSeButton();
    }

    private void actionMoveToUser ()
    {
        wdWait.until(ExpectedConditions.presenceOfElementLocated(By.className("fa-user")));
        WebElement user = driver.findElement(By.className("fa-user"));
        Actions action = new Actions(driver);
        action.moveToElement(user).perform();
    }

    private void clickOdjaviSeButton ()
    {
        wdWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Odjavi se")));
        WebElement odjaviSeButton = driver.findElement(By.linkText("Odjavi se"));
        odjaviSeButton.click();
    }
}
