package tests;

import helpers.BaseHelper;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class BaseTest extends BaseHelper
{
    @BeforeClass

    //public void getHomePage()       {driver.get("https://gigatron.rs/login");}

    public void testInit() {driver.manage().window().maximize();  }

    @AfterClass
    public void testTearDown() {
        driver.close();
        driver.quit();
    }
}
