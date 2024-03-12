package Pages;

import Supporter.SupportProperty;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Properties;

import static java.lang.Thread.sleep;

public abstract class BasePage {
    public static final int TIMEOUT = 60;
    protected final String userAccountPATH = "src/test/java/Supporter/resources/userAccount.properties";
    public final String locFile = "locators";
    public final String userFile = "userAccount";
    protected WebDriver driver;
    Properties pros = new Properties();
    public SupportProperty spPros = new SupportProperty();
    protected WebElement elem;
    public static WebDriverWait wait;

    protected BasePage(WebDriver driver) {
        this.driver = driver;
    }

    protected abstract void open();
    public void closeInitialStep(){
        pros = spPros.load(locFile);
        By btnExplore_on_my_own = By.xpath(pros.getProperty("xpath_btnExploreMyOwn"));
        elem = new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT)).until(ExpectedConditions.elementToBeClickable(btnExplore_on_my_own));
        driver.findElement(btnExplore_on_my_own).click();
        new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT)).until(ExpectedConditions.invisibilityOfElementLocated(btnExplore_on_my_own));
    }
    public void completeInitilaStep(){

    }
    public void openUserSetting (){
        pros = spPros.load(locFile);
        By icoUser = By.xpath(pros.getProperty("xpath_icoUser"));
        elem = new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions
                        .elementToBeClickable(icoUser));
        driver.findElement(icoUser).click();
    }

    public void selectLogout(){
        pros = spPros.load(locFile);
        By btnLogout = By.xpath(pros.getProperty("xpath_btnLogout"));
        elem = new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions
                        .visibilityOfElementLocated(btnLogout));
        driver.findElement(btnLogout).click();
    }
    public void selectAccountSetting(){
        pros = spPros.load(locFile);
        wait = new WebDriverWait(driver,Duration.ofSeconds(TIMEOUT));
        By btnAccSetting = By.xpath(pros.getProperty("xpath_btnAccoutSetting"));
        elem = new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions
                        .visibilityOfElementLocated(btnAccSetting));
        driver.findElement(btnAccSetting).click();
    }

    public void waitFor(int timeout){
        try {
            sleep(timeout);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
