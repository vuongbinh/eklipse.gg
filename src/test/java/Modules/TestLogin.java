package Modules;

import Pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestLogin extends BaseTest {
    @Test(testName = "Verify that user can logged in with valid credentials")
    void loginSuccess(){
        pros = spPros.load(locFile);
        LoginPage lg = new LoginPage(driver);
        lg.open();
        lg.login();
        Assert.assertTrue(driver.getCurrentUrl().contains(pros.getProperty("Home")));
    }
}
