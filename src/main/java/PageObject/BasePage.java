package PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class BasePage {
    public WebDriver driver;
    WebDriverWait explicitWait;
    Boolean flag;
    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    public void waitForElementVisible(WebDriver driver, By locator){
        explicitWait = new WebDriverWait(driver, 10);
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public boolean waitForElementInvisible(WebDriver driver, By locator){
        explicitWait = new WebDriverWait(driver, 10);
        flag = explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
        return flag;
    }

    public void waitForWebElementClickable(WebDriver driver, By locator){
         explicitWait = new WebDriverWait(driver, 10);
        explicitWait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    public void enterValueToElement(WebDriver driver, By locator, String value){
        waitForElementVisible(driver, locator);
        driver.findElement(locator).click();
        driver.findElement(locator).sendKeys(Keys.chord(Keys.CONTROL,"a", Keys.DELETE));
        driver.findElement(locator).clear();
        driver.findElement(locator).sendKeys(value);
    }

    public void clickToElement(WebDriver driver, By locator){
        waitForWebElementClickable(driver, locator);
        driver.findElement(locator).click();
    }

    public boolean isElementVisibled(WebDriver driver, By locator){
        waitForElementVisible(driver,locator);
        flag = driver.findElement(locator).isDisplayed();
        return flag;
    }

    public boolean isElementInvisibled(WebDriver driver, By locator){
        flag = false;
        List<WebElement> ele = driver.findElements(locator);
        if(ele.size()==0)
        {
            flag = true;
        }
        return flag;
    }

}
