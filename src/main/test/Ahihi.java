import PageObject.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Ahihi {
    public WebDriver driver;
    LoginPage loginPage;
    Init init;
    ExcelReader excelReader;
    LabellingPage labelPage;
    List<String> data;
    int qty;
    @BeforeClass
    public void beforeClass(){
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "//src//libs//chromedriver2.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("http://10.0.0.90:8081/login");
    }
    @AfterClass
    public void afterClass(){
        driver.quit();
    }


    @Test
    public void Step00_Init() throws IOException {
        loginPage = new LoginPage(driver);
        init = new Init(driver);
        labelPage = new LabellingPage(driver);
        excelReader = new ExcelReader();
        excelReader.startWorkingExcel("config");
        data = excelReader.getData();
        qty = Integer.parseInt(data.get(2).toString());

    }
    @Test
    public void Step01_Login() {
        loginPage.loginFunction("nguyenlh", "Nguyen.le!1");
    }
    @Test
    public void Step02_OpenLabellingPage(){
        init.navigateToLabelling();
    }

    @Test()
    public void Step03_CreateMultipleFolder() throws InterruptedException, AWTException {
        for (int i = 1; i <= qty; i++) {
            labelPage.clickAddNewEmployeeButton();
            String temp = data.get(0).toString();
            temp = temp + "" + i;
            labelPage.setTextUserIdPopup(temp);
            temp = data.get(1).toString();
            temp = temp + "" + i;
            labelPage.setTextUserNamePopup(temp);
            labelPage.clickSaveNewEmployeePopup();
            labelPage.searchAndOpenEmployeeFolder(temp, null, null, null);
            labelPage.uploadImage(temp);
        }
    }

}
