package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage extends BasePage{
    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public void open() {
        pros = spPros.load(locFile);
        driver.get(pros.getProperty("Login"));
        new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT*2))
                .until(webDriver -> ((JavascriptExecutor) webDriver)
                        .executeScript("return document.readyState")
                        .equals("complete"));
    }

    public void fillLoginForm (String email, String pwd){
        pros = spPros.load(locFile);
        By tbxEmail = By.id(pros.getProperty("id_tbxUsername"));
        By tbxPassword = By.id(pros.getProperty("id_tbxPassword"));

        driver.findElement(tbxEmail).sendKeys(email);
        driver.findElement(tbxPassword).sendKeys(pwd);
    }

    public void submitLogin (){
        pros = spPros.load(locFile);
        By btnSubmit = By.xpath(pros.getProperty("xpath_btnLogin"));
        driver.findElement(btnSubmit).click();
        elem = new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT)).until(ExpectedConditions.visibilityOfElementLocated(By.id((pros.getProperty("id_Dashboard")))));
    }

    public void login(){
        pros = spPros.load(locFile);
        LoginPage lg = new LoginPage(driver);
        if(driver.getCurrentUrl().contains("/home")){
            lg.openUserSetting();
            lg.selectLogout();
        }
        pros = spPros.load(userFile);
        lg.fillLoginForm(pros.getProperty("email"), pros.getProperty("password"));
        lg.submitLogin();
    }
}
