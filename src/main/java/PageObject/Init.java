package PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.List;
import java.util.Locale;

public class Init{
    //Locator ----------------------------------------------
    public final By menu_btn = By.xpath("//div[@class='menu-display']/button");
    public final By page_option = By.xpath("//div[@id='app']//div[@class='v-list theme--light']/div[2]");

    public WebDriver driver;

    public Init(WebDriver driver)
    {
        this.driver = driver;
    }
    BasePage base = new BasePage(driver);

    public void navigateToPage(String pageName)
    {
        Actions action = new Actions(driver);
        base.clickToElement(driver,menu_btn);
        WebElement pageList = driver.findElement(page_option);
        List<WebElement> pageOptions = pageList.findElements(By.tagName("div"));
        int i=1;
        for(WebElement page : pageOptions){
            WebElement title = pageList.findElement(By.xpath("./div["+i+"]//div[@class='v-list__tile__title']"));
            String temp = title.getText().toString();
            if(title.getText().toString().toLowerCase(Locale.ROOT).equals(pageName.toLowerCase(Locale.ROOT)))
            {
                WebElement option = pageList.findElement(By.xpath("./div["+i+"]"));
                option.click();
                break;
            }
            i++;
        }
    }

    public void navigateToLabelling()
    {
        navigateToPage("Labelling");
    }

}
