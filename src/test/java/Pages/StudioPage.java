package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class StudioPage extends BasePage{
    public StudioPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public void open() {
        pros = spPros.load(locFile);
        LoginPage lp = new LoginPage(driver);
        lp.open();
        lp.login();
    }

    public void convertToTikTok(){
        StudioPage hp = new StudioPage(driver);
        pros = spPros.load(locFile);
        By btnConvertToTiktok = By.xpath(pros.getProperty("xpath_btnConvertToTiktok"));
        By btnUpload = By.id(pros.getProperty("id_btnUpload"));
        By icoLoading = By.xpath(pros.getProperty("xpath_icoLoanding"));
        hp.open();
        String originalWindow = driver.getWindowHandle();
        elem = new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT))
                .until(ExpectedConditions
                        .elementToBeClickable(btnConvertToTiktok));
        driver.findElement(btnConvertToTiktok).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(TIMEOUT));
        for (String windowHandle : driver.getWindowHandles()) {
            if(!originalWindow.contentEquals(windowHandle)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }
        System.out.println(driver.getCurrentUrl());
        new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT))
                .until(ExpectedConditions
                        .invisibilityOfElementLocated(icoLoading));
        driver.findElement(btnUpload).click();
    }
}
