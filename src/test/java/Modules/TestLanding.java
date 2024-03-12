package Modules;

import Pages.LandingPage;
import Supporter.FileSupport;
import org.apache.poi.ss.usermodel.Sheet;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.lang.reflect.Method;

public class TestLanding extends BaseTest {

    @Test(dataProvider = "hybrid")
    void verifyNavigation(String elm, String des){
        pros = spPros.load(locFile);
        LandingPage lp = new LandingPage(driver);
        lp.open();
        lp.clickElement(By.xpath(pros.getProperty(elm)));
        Assert.assertTrue(driver.getCurrentUrl().contains(pros.getProperty(des)));
        System.out.println("Actual: "+driver.getCurrentUrl());
        System.out.println("Expected: "+pros.getProperty(des));
    }
    @DataProvider(name = "hybrid")
    public static Object[][] getDataFromProvider(Method method)   {
        FileSupport file = new FileSupport();
        Sheet excelSheet = null;
        try {
            excelSheet = file.readExcelFile("src/test/java/Supporter/resources", "data.xlsx",
                    "verify_navigation");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return getDataFromFile(excelSheet);
    }

}
