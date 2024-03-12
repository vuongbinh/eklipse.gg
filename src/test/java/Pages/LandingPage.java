package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LandingPage extends BasePage{
    public LandingPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public void open() {
        pros = spPros.load(locFile);
        driver.get(pros.getProperty("LandingPage"));
        new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT*2))
                .until(webDriver -> ((JavascriptExecutor) webDriver)
                        .executeScript("return document.readyState")
                        .equals("complete"));
    }
    public void clickElement(By locator){
        elem = driver.findElement(locator);
        new Actions(driver)
                .scrollToElement(elem)
                .perform();
        elem.click();
    }

}
