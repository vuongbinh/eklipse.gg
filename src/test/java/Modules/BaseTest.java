package Modules;

import Supporter.SupportProperty;
import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

public class BaseTest {
    protected static WebDriverWait wait;
    protected static WebElement element;
    protected static WebDriver driver;
    protected final String locFile = "locators";
    protected final String userFile = "userAccount";
    protected Properties pros = new Properties();
    protected SupportProperty spPros = new SupportProperty();

    public static String getScreenshot(WebDriver driver, String screenshotName) throws Exception {
        //below line is just to append the date format with the screenshot name to avoid duplicate names
        String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        //after execution, you could see a folder "FailedTestsScreenshots" under src folder
        String destination = System.getProperty("user.dir") + "/reports/FailedTestsScreenshots/" + screenshotName + dateName + ".png";
        File finalDestination = new File(destination);
        FileUtils.copyFile(source, finalDestination);
        //Returns the captured file path
        return destination;
    }

    public static WebDriver openBrowser(String browserName) {
        if (browserName.equalsIgnoreCase("chrome")) {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("window-size=1920,1080");
            options.addArguments("--remote-allow-origins=*");
//            options.addArguments("--Headless");
            return new ChromeDriver(options);
        } else if (browserName.equalsIgnoreCase("firefox")) {
            FirefoxBinary firefoxBinary = new FirefoxBinary();
            FirefoxOptions options = new FirefoxOptions();
            options.setBinary(firefoxBinary);
            options.addArguments("--Headless");
            return new FirefoxDriver(options);
        } else if (browserName.equalsIgnoreCase("edge")) {
            EdgeOptions options = new EdgeOptions();
            options.addArguments("window-size=1920,1080");
            options.addArguments("--remote-allow-origins=*");
            options.addArguments("--Headless");
            return new EdgeDriver(options);
        } else throw new IllegalArgumentException("The Browser " + browserName + " does not support");
    }

    protected static Object[][] getDataFromFile(Sheet excelSheet) {
        int rowCOUNT = excelSheet.getLastRowNum() - excelSheet.getFirstRowNum();
        Object[][] object = new Object[rowCOUNT][2];
        for (int i = 0; i < rowCOUNT; i++) {
            Row row = excelSheet.getRow(i + 1);
            for (int j = 0; j < row.getLastCellNum(); j++) {
                object[i][j] = row.getCell(j).toString();
            }
        }
        return object;
    }

    @Parameters({"browserName"})
    @BeforeTest
    public void setup(String browserName) {
        driver = openBrowser(browserName);
    }


    @AfterTest
    public void cleanUp() {
        driver.quit();
    }
}

