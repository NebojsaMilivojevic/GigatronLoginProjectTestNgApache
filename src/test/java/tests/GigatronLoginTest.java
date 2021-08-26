package tests;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.GigatronHomePage;
import pages.GigatronLoginFormPage;
import pages.GigatronUserPage;

import java.io.FileInputStream;
import java.io.IOException;

public class GigatronLoginTest extends BaseTest
{
    @BeforeTest
    public void openHomePage ()
    {
        GigatronHomePage homePage = new GigatronHomePage(driver);
        homePage.navigateToHomePage();

    }

    @Test
    public void successfulLogin () throws IOException {

        //  SoftAssert softAssert = new SoftAssert();

        GigatronHomePage homePage = new GigatronHomePage(driver); //  ide anotacija BeforeTest za otvaranje homePage
        homePage.prijavaButton();


        for (int i = 1; i <= 3; i++) {

            GigatronLoginFormPage loginFormPage = new GigatronLoginFormPage(driver);

            String email;
            String password;
            HSSFWorkbook workbook;

            FileInputStream fileInputStream = new FileInputStream("kredencijaliGigatron.xls");
            workbook = new HSSFWorkbook(fileInputStream);

            Sheet sheet = workbook.getSheetAt(0);
            Row row = sheet.getRow(i);

            Cell cellUserName = row.getCell(0);
            email = cellUserName.toString();
            System.out.println("Email je: " + email);
            Cell cellPassword = row.getCell(1);
            password = cellPassword.toString();

            loginFormPage.logIn(email, password);

            GigatronUserPage gigatronUserPage = new GigatronUserPage(driver);
            boolean ok = gigatronUserPage.getUserEmail().equals(email);
            Assert.assertTrue(ok);



            gigatronUserPage.logOut();
            homePage.clickPrijavaButton();
        }



    }


    @Test
    public void unseccessfulLogin () throws IOException {


        GigatronHomePage homePage = new GigatronHomePage(driver);
        homePage.clickPrijavaButton();

        for (int i=1; i<=5; i++)
        {
            GigatronLoginFormPage loginFormPage = new GigatronLoginFormPage(driver);

            String email;
            String password;
            HSSFWorkbook workbook;

            FileInputStream fileInputStream = new FileInputStream("invalidCredentials.xls");
            workbook = new HSSFWorkbook(fileInputStream);

            Sheet sheet = workbook.getSheetAt(0);
            Row row = sheet.getRow(i);
            Cell cellEmail = row.getCell(0);
            email = cellEmail.toString();
            Cell cellPassword = row.getCell(1);
            password = cellPassword.toString();

            loginFormPage.logIn(email, password);

            if (password.length() >= 6 && email.length() >=8) {
                String message = "Došlo je do greške. Korisnik nije pronađen ili je neispravna lozinka.";
                boolean ok = loginFormPage.getErrorMessage().contains(message);
                Assert.assertTrue(ok, "nisu iste error poruke");
                driver.navigate().refresh();
            }

            else {
                wdWait.until(ExpectedConditions.presenceOfElementLocated(By.className("error")));
                //   WebElement redPassword = driver.findElement(By.className("error"));
                driver.navigate().refresh();
            }
        }

    }

}
