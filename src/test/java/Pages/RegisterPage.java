package Pages;

import Objects.User;
import Supporter.FileSupport;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.time.Duration;

public class RegisterPage extends BasePage{
    public RegisterPage(WebDriver driver) {
        super(driver);
    }
    @Override
    public void open() {
        pros = spPros.load(locFile);
        driver.get(pros.getProperty("Register"));
        new WebDriverWait(driver, Duration.ofSeconds(60))
                .until(webDriver -> ((JavascriptExecutor) webDriver)
                        .executeScript("return document.readyState")
                        .equals("complete"));
    }

    public void registerUser(){
        RegisterPage rp = new RegisterPage(driver);
        rp.fillRandomInfo();
        rp.submitSignUp();
    }

    public void fillRandomInfo(){
        User user = new User();
        pros = spPros.load(locFile);
        By tbxName = By.id(pros.getProperty("id_tbxName"));
        By tbxEmail = By.id(pros.getProperty("id_tbxEmail"));
        By tbxPassword = By.id(pros.getProperty("id_tbxPassword"));
        By tbxPwdConfirm = By.id(pros.getProperty("id_tbxPasswordConfirm"));

        driver.findElement(tbxName).sendKeys(user.getName());
        driver.findElement(tbxEmail).sendKeys(user.getEmail());
        driver.findElement(tbxPassword).sendKeys(user.getPassword());
        driver.findElement(tbxPwdConfirm).sendKeys(user.getPassword());

        FileSupport file = new FileSupport();
        try {
            file.saveToFile(user.getEmail(), user.getPassword(),user.getPassword(), userAccountPATH);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void submitSignUp (){
        pros = spPros.load(locFile);
        By btnSignup = By.xpath(pros.getProperty("xpath_btnSignUp"));

        driver.findElement(btnSignup).click();
        elem = new WebDriverWait(driver, Duration.ofSeconds(30)).until(ExpectedConditions.visibilityOfElementLocated(By.id((pros.getProperty("id_Dashboard")))));
    }
    public void logout(){
        pros = spPros.load(locFile);
        By icoUser = By.xpath(pros.getProperty("xpath_icoUser"));
        By btnLogout = By.xpath(pros.getProperty("xpath_btnLogout"));
        elem = new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions
                        .elementToBeClickable(btnLogout));
        driver.findElement(icoUser).click();
        elem = new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions
                        .elementToBeClickable(btnLogout));
        driver.findElement(btnLogout).click();
    }
}
