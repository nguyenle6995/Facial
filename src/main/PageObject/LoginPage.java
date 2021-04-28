package PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage extends BasePage {
    WebDriver driver;
    WebDriverWait explicitWait;

    public final String userName_Textbox = "//input[@aria-label='User Name']";
    public final String password_Textbox = "//input[@aria-label='Password']";
    public final String login_Button = "//div[@class='v-card__text']//button";

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void loginFunction(String userName, String password) {
        enterValueToElement(driver, userName_Textbox, userName);
        enterValueToElement(driver, password_Textbox, password);

        clickToElement(driver, login_Button);
    }
}
