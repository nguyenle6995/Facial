package PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.testng.AssertJUnit.assertTrue;

public class LoginPage {
    //Locator ----------------------------------------------
    public final By userName_Textbox = By.xpath("//input[@aria-label='User Name']");
    public final By password_Textbox = By.xpath("//input[@aria-label='Password']");
    public final By login_Button = By.xpath("//div[@class='v-card__text']//button");
    //Re-use variable--------------------------------------
    WebDriver driver ;
    BasePage base;
    public LoginPage(WebDriver driver)
    {
        this.driver = driver;
        base = new BasePage(driver);
    }

    WebDriverWait explicitWait;
    Boolean flag;
    Init init = new Init(driver);


    public void loginFunction(String userName, String password) {
        base.enterValueToElement(driver, userName_Textbox, userName);
        base.enterValueToElement(driver, password_Textbox, password);
        base.clickToElement(driver, login_Button);
        flag = loginSucessfull();
        assertTrue("Failed to login", flag);
    }

    public boolean loginSucessfull()
    {
        flag = base.isElementVisibled(driver, init.menu_btn);
        return flag;
    }
}
