package Modules;

import Pages.RegisterPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestRegister extends BaseTest{
    @Test(testName = "Verify that User can Register a new account")
    void registerSuccess()  {
        pros = spPros.load(locFile);
        RegisterPage rp = new RegisterPage(driver);
        rp.open();
        rp.registerUser();
        rp.closeInitialStep();
        Assert.assertTrue(driver.getCurrentUrl().contains(pros.getProperty("Home")));
    }
}
