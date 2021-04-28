package PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class BasePage {
    WebDriver driver;
    WebDriverWait explicitWait;

    public void waitForElementVisible(WebDriver driver, String locator){
        explicitWait = new WebDriverWait(driver, 10);
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator)));
    }

    public void waitForWebElementClickable(WebDriver driver, String locator){
         explicitWait = new WebDriverWait(driver, 10);
        explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath(locator)));
    }

    public void enterValueToElement(WebDriver driver, String locator, String value){
        waitForElementVisible(driver, locator);
        driver.findElement(By.xpath(locator)).sendKeys(value);
    }

    public void clickToElement(WebDriver driver, String locator){
        waitForWebElementClickable(driver, locator);
        driver.findElement(By.xpath(locator)).click();
    }
}
