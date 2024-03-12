package Pages;

import Supporter.FileSupport;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class AccountSettingPage extends BasePage {
    public AccountSettingPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public void open() {
        LoginPage lp = new LoginPage(driver);
        pros = spPros.load(locFile);
        lp.open();
        lp.login();
        lp.openUserSetting();
        lp.selectAccountSetting();
        lp.waitFor(5000);
    }

    public void inputName(String name) {
        pros = spPros.load(locFile);
        By tbxName = By.name(pros.getProperty("name_InputNamefield"));
        elem = driver.findElement(tbxName);
        new Actions(driver)
                .scrollToElement(elem)
                .perform();
        elem.clear();
        elem.sendKeys(name);
    }

    public void saveChanges() {
        pros = spPros.load(locFile);
        By btnSaveChanges = By.xpath(pros.getProperty("xpath_btnSaveChanges"));
        By popupSuccess = By.xpath(pros.getProperty("xpath_popUpdateSuccess"));
        By btnContinue = By.xpath(pros.getProperty("xpath_btnContinue"));
        elem = driver.findElement(btnSaveChanges);
        new Actions(driver)
                .scrollByAmount(0, 500)
                .perform();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        elem.click();
        new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT)).until(ExpectedConditions.visibilityOfElementLocated(popupSuccess));
        new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT)).until(ExpectedConditions.elementToBeClickable(btnContinue));
    }

    public void openPopupChangePwd() {
        pros = spPros.load(locFile);
        By btnChangePwd = By.xpath(pros.getProperty("xpath_btnChangePwd"));
        elem = driver.findElement(btnChangePwd);
        new Actions(driver)
                .scrollByAmount(0,500)
                .perform();
        wait.until(ExpectedConditions.elementToBeClickable(elem));
        elem.click();
    }
    public void fillFormChangePwd(String curPwd,String newPwd){
        pros = spPros.load(locFile);
        By tbxCurrentPwd = By.name(pros.getProperty("name_tbxCurrentPwd"));
        By tbxNewPwd = By.name(pros.getProperty("name_tbxNewPwd"));
        By tbxConfirmPwd = By.name(pros.getProperty("name_tbxConfirmNewPwd"));

        wait.until(ExpectedConditions.visibilityOfElementLocated(tbxCurrentPwd));

        driver.findElement(tbxCurrentPwd).sendKeys(curPwd);
        driver.findElement(tbxNewPwd).sendKeys(newPwd);
        driver.findElement(tbxConfirmPwd).sendKeys(newPwd);

        FileSupport file = new FileSupport();
        pros = spPros.load(userFile);
        try {
            file.saveToFile(pros.getProperty("email"), pros.getProperty("password"), newPwd,userAccountPATH);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void submitChangePwd(){
        pros = spPros.load(locFile);
        By btnSaveChange = By.xpath(pros.getProperty("xpath_btnSaveChangeInPopup"));
        By popupSuccess = By.xpath(pros.getProperty("xpath_popUpdateSuccess"));
        By btnContinue = By.xpath(pros.getProperty("xpath_btnContinue"));
        driver.findElement(btnSaveChange).click();
        new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT))
                .until(ExpectedConditions
                        .visibilityOfElementLocated(popupSuccess));
        new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT))
                .until(ExpectedConditions
                        .elementToBeClickable(btnContinue));

        FileSupport file = new FileSupport();
        pros = spPros.load(userFile);
        try {
            file.saveToFile(pros.getProperty("email"), pros.getProperty("newPassword"), pros.getProperty("newPassword"),userAccountPATH);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void openDeleteAccountPopup(){
        pros = spPros.load(locFile);
        By btnDelAcc = By.xpath(pros.getProperty("xpath_btnDeleteAccount"));
        elem=driver.findElement(btnDelAcc);
        new Actions(driver).scrollByAmount(0,700).perform();
        wait.until(ExpectedConditions.elementToBeClickable(elem));
        elem.click();
    }
    public void fillAccountDeletionPage1st(String sat, String ser, String rec){
        pros = spPros.load(locFile);
        By rbSatisfied = By.xpath(pros.getProperty("xpath_rdSatisfiedSelection")+sat+pros.getProperty("endPath"));
        By rbCS = By.xpath(pros.getProperty("xpath_rdServiceSelection")+ser+pros.getProperty("endPath"));
        By rbRecommend  = By.xpath(pros.getProperty("xpath_rdRecommendSelection")+rec+pros.getProperty("endPath"));

        wait.until(ExpectedConditions.visibilityOfElementLocated(rbSatisfied));

        driver.findElement(rbSatisfied).click();
        driver.findElement(rbCS).click();
        driver.findElement(rbRecommend).click();
    }
    public void clickBtnContinue(){
        pros = spPros.load(locFile);
        By btnContinue = By.xpath(pros.getProperty("xpath_btnContinue"));
        driver.findElement(btnContinue).click();
    }
    public void fillAccountDeletionPage2nd(String[] res){
        pros = spPros.load(locFile);
        for (int i = 0; i < res.length; i++) {
            By cbReason = By.xpath(pros.getProperty("xpath_cbReasons")+res[i]+pros.getProperty("endPath"));
            wait.until(ExpectedConditions.elementToBeClickable(cbReason));
            driver.findElement(cbReason).click();
        }
    }
    public void confirmDELETE(){
        pros = spPros.load(locFile);
        By txbDELETE = By.xpath(pros.getProperty("xpath_tbxDeleteConfirm"));
        By btnDelAccount = By.cssSelector(pros.getProperty("css_btnDeleteConfirm"));

        wait.until(ExpectedConditions.visibilityOfElementLocated(txbDELETE));

        driver.findElement(txbDELETE).sendKeys("DELETE");
        driver.findElement(btnDelAccount).click();
        wait.until(ExpectedConditions.urlToBe(pros.getProperty("DeletedPage")));
    }
}
