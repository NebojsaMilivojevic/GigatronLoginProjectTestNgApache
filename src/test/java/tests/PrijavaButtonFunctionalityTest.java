package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.GigatronHomePage;
import pages.GigatronLoginFormPage;

public class PrijavaButtonFunctionalityTest extends BaseTest
{
    @Test
    public void prijavaButtonFunctionlityTest ()
    {
        GigatronHomePage homePage = new GigatronHomePage(driver);
        homePage.prijavaButton();

        GigatronLoginFormPage loginFormPage = new GigatronLoginFormPage(driver);
        String expectedUrl = "https://gigatron.rs/login";
        boolean ok = loginFormPage.getGigatronLoginUrl().contains(expectedUrl);
        Assert.assertTrue(ok);

    }
}
