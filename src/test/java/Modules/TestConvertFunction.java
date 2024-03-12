package Modules;

import Pages.StudioPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestConvertFunction extends BaseTest {
    @Test(testName = "Verify that User can convert a video to TIKTOK")
    void accessHomePageSuccessfully(){
        pros=spPros.load(locFile);
        StudioPage hp = new StudioPage(driver);
        hp.open();
        Assert.assertEquals(driver.getCurrentUrl(),pros.getProperty("Home"));
    }
}
