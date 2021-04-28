package test;

import PageObject.BasePage;
import PageObject.LoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Ahihi {
    WebDriver driver;
    LoginPage loginPage;

    @BeforeClass
    public void beforeClass() {
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "//src//libs//chromedriver2.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.get("http://10.0.0.90:8081/login");
        loginPage = new LoginPage(driver);
    }

    @Test
    public void TC_01() {
        loginPage.loginFunction("luannguyen", "0914458615");
    }

    @AfterClass
    public void afterClass(){
        driver.quit();
    }
}
