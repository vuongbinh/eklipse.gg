package Modules;

import Objects.User;
import Pages.AccountSettingPage;
import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestAccountSetting extends BaseTest {
    @Test
    void changeNameSuccess() {
        pros = spPros.load(locFile);
        User user = new User();
        String expectedName = user.getName();
        AccountSettingPage acc = new AccountSettingPage(driver);
        acc.open();
        Assert.assertEquals(driver.getCurrentUrl(), pros.getProperty("Account_Setting"));
        acc.inputName(expectedName);
        acc.saveChanges();
        Assert.assertTrue(driver.findElement(By.xpath(pros.getProperty("xpath_popUpdateSuccess"))).isDisplayed());
        driver.navigate().refresh();
        Assert.assertEquals(driver.findElement(By.name(pros.getProperty("name_InputNamefield"))).getAttribute("value"), expectedName);
    }

    @Test
    void changePasswordSuccess() {
        pros = spPros.load(userFile);
        String curPwd = pros.getProperty("password");
        pros = spPros.load(locFile);
        Faker faker = new Faker();
        String newPwd = faker.internet().password(8, 25);
        System.out.println(newPwd);
        AccountSettingPage acc = new AccountSettingPage(driver);
        if (!driver.getCurrentUrl().contains(pros.getProperty("Account_Setting"))) {
            acc.open();
            Assert.assertEquals(driver.getCurrentUrl(), pros.getProperty("Account_Setting"));
        }
        acc.openPopupChangePwd();
        acc.fillFormChangePwd(curPwd, newPwd);
        acc.submitChangePwd();
        Assert.assertTrue(driver.findElement(By.xpath(pros.getProperty("xpath_popUpdateSuccess"))).isDisplayed());
    }


    /*NOTE: Below are all option for delete account need to call func "fillAccountDeletionPage1st" and "fillAccountDeletionPage2nd"
        1: How satisfied were you with our product? & How would you rate our Customer Services?
            - Not satisfied
            - Somewhat satisfied
            - Very satisfied
        2: Would you recommend Eklipse to others?
            - Yes, I would
            - No, I wouldn't
        3: Do you have any particular reasons to delete your Eklipse account?
            - I mistakenly sign up to Eklipse
            - I don't use it anymore
            - I duplicate my account
            - Eklipse wasn't really useful
     */
    @Test
    void deleteAccountSuccess() {
        pros = spPros.load(locFile);
        AccountSettingPage acc = new AccountSettingPage(driver);
        String[] reason = {"I mistakenly sign up to Eklipse","Eklipse wasn't really useful"};
        if (!driver.getCurrentUrl().contains(pros.getProperty("Account_Setting"))) {
            acc.open();
            Assert.assertEquals(driver.getCurrentUrl(), pros.getProperty("Account_Setting"));
        }
        acc.open();
        acc.openDeleteAccountPopup();
        acc.fillAccountDeletionPage1st("Somewhat satisfied","Not satisfied","No, I wouldn't");
        acc.clickBtnContinue();
        acc.fillAccountDeletionPage2nd(reason);
        acc.clickBtnContinue();
        acc.confirmDELETE();
        Assert.assertEquals(driver.getCurrentUrl(),pros.getProperty("DeletedPage"));
    }
}
