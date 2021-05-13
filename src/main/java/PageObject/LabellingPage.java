package PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import javax.annotation.Nullable;
import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

public class LabellingPage {
    //Locator Labelling Page----------------------------------------------
    public final By addEmp_btn = By.xpath("//aside[@id='m-drawer']//i[@class='v-icon v-icon--link material-icons theme--dark'][1]");
    public final By userName_txt = By.xpath("//input[@aria-label = 'User Name']");
    public final By company_cb = By.xpath("//label[text()='Company']/parent::div");
    public final By company_list = By.xpath("//*[@id='inspire']//div[@class='v-menu__content theme--light v-menu__content--fixed menuable__content__active']//div[@role='list']");
    public final By workingPlace_cb = By.xpath("//label[text()='Working Place']/parent::div");
    public final By workingPlace_list = By.xpath("//*[@id='inspire']//div[@class='v-menu__content theme--light v-menu__content--fixed menuable__content__active']//div[@role='list']");
    public final By status_cb = By.xpath("//label[text()='Active/Deactive']/parent::div");
    public final By status_list = By.xpath("//*[@id='inspire']//div[@class='v-menu__content theme--light v-menu__content--fixed menuable__content__active']//div[@role='list']");
    public final By search_btn = By.xpath("//*[@id='inspire']//div/nav/div/div/button");
    public final By employees_list = By.xpath("//*[@id='m-drawer']//div[@role='list']");
    public final By uploadImg_btn = By.xpath("//i[text()='image']/parent::div/parent::button");
    public final By chooseFile_btn = By.id("dropzone");
    //Locator New User Popup----------------------------------------------
    public final By newUser_popup = By.xpath("//div[@id='inspire']//div[@class='v-dialog__content v-dialog__content--active']//div[@class='v-card v-sheet theme--light']");
    public final By popup_userID_txt = By.xpath("//input[@aria-label = 'User Id *']");
    public final By popup_userName_txt = By.xpath("//input[@aria-label = 'User Name *']");
    public final By popup_voiceLabel_txt = By.xpath("//input[@aria-label='Voice Label']");
    public final By popup_generateVoice_btn = By.xpath("//i[text()='double_arrow']/parent::div");
    public final By popup_saveNewUser_btn = By.xpath("//div[text()='Save']/parent::button");
    public final By newUser_msg = By.xpath("//legend[text()='Login Infomation']");
    public final By closeNewUserMsg_btn = By.xpath("//i[text()='cancel_presentation']");
    public final By existingUserId_msg = By.xpath("//div[text()='User ID already exists!']");
    public final By uploading_msg = By.xpath("//div[@class='cus-card v-card v-sheet theme--light']//span[text()='Please wait']");
    public final By image_list = By.xpath("//div[@class='vue-drag-select layout row wrap']");


    boolean flag;
    WebDriver driver;
    String sheet,record,qty;
    String filePath = "src\\main\\resources\\data\\imgs\\";

    public LabellingPage(WebDriver driver)
    {
        this.driver = driver;
    }
    BasePage base = new BasePage(driver);

    ExcelReader excelReader;

    public void clickAddNewEmployeeButton()
    {
        base.clickToElement(driver,addEmp_btn);
        base.waitForElementVisible(driver,newUser_popup);
    }

    public void setTextUserIdPopup(String userId)
    {
        base.enterValueToElement(driver,popup_userID_txt,userId);
    }

    public void setTextUserNamePopup(String userName)
    {
        base.enterValueToElement(driver, popup_userName_txt,userName);
    }

    public void setTextVoiceLabel(String voiceLabel)
    {
        base.enterValueToElement(driver, popup_voiceLabel_txt,voiceLabel);
    }

    public void clickSaveNewEmployeePopup() {
        base.clickToElement(driver, popup_saveNewUser_btn);
        flag = base.isElementInvisibled(driver, existingUserId_msg);
        if (flag) {
            flag = base.isElementVisibled(driver, newUser_msg);
            if (flag) {
                base.clickToElement(driver, closeNewUserMsg_btn);
                flag = base.isElementInvisibled(driver, newUser_msg);
                if (!flag) {
                    Assert.assertTrue(flag, "Can't close message");
                }
            } else Assert.assertTrue(flag, "Can't save new folder");
        }else Assert.assertTrue(flag, "User ID already exists!");
    }

    public void selectCompany(String company)
    {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        base.clickToElement(driver,company_cb);
        WebElement comp_list = driver.findElement(company_list);
        List<WebElement> companies = comp_list.findElements(By.xpath("div"));
        int curentQty = companies.size();
        for(int i = curentQty;i<=curentQty;i++)
        {
            WebElement ele = comp_list.findElement(By.xpath("./div["+i+"]//div[@class='v-list__tile__title']"));
            js.executeScript("arguments[0].scrollIntoView();", ele);
            companies = comp_list.findElements(By.xpath("div"));
            if (companies.size() == curentQty) {
                ele = comp_list.findElement(By.xpath("./div[1]//div[@class='v-list__tile__title']"));
                js.executeScript("arguments[0].scrollIntoView();", ele);
                break;
            }else{
                curentQty = companies.size();
            }

        }

        for(int i = 1;i <=curentQty;i++)
        {
            WebElement e = comp_list.findElement(By.xpath("./div["+i+"]//div[@class='v-list__tile__title']"));
            js.executeScript("arguments[0].scrollIntoView();", e);
            if(e.getText().equals(company)){
                e.click();
                break;
            }
        }
    }

    public void selectWorkingPlace(String workingPlace) {
        Actions actions = new Actions(driver);
        List<String> workPlaces = Arrays.asList(workingPlace.split(";"));
        base.clickToElement(driver, workingPlace_cb);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement wplaceList = driver.findElement(workingPlace_list);
        List<WebElement> workPlace_cb = wplaceList.findElements(By.xpath("./div//i"));
        int i=1;
        for (WebElement combobox : workPlace_cb) {
            if (workingPlace.equals("all")) {
                js.executeScript("arguments[0].scrollIntoView();", combobox);
                flag = combobox.isSelected();
                if (!flag) {
                    WebElement selectAll_cb = wplaceList.findElement(By.xpath("./div[1]//i"));
                    actions.moveToElement(selectAll_cb).click().perform();
                    break;
                }
            } else {
                js.executeScript("arguments[0].scrollIntoView();", combobox);
                WebElement workPlace_name = wplaceList.findElement(By.xpath("./div[" + i + "]//div[@class='v-list__tile__title']"));
                js.executeScript("arguments[0].scrollIntoView();", combobox);
                for (int j = 0; j < workPlaces.size(); j++) {
                    flag = workPlace_name.getText().equals(workPlaces.get(j));
                    if (flag) {
                        flag = combobox.isSelected();
                        if (!flag) {
                            actions.moveToElement(combobox).click().perform();
                        }
                    } else {
                        flag = combobox.isSelected();
                        if (flag) {
                            actions.moveToElement(combobox).click().perform();
                        }
                    }
                }
            }
            i++;
        }
    }

    public void selectStatus(String status)
    {
        Actions actions = new Actions(driver);
        base.clickToElement(driver,status_cb);
        WebElement statusList = driver.findElement(status_list);
        List<WebElement> statusOption = statusList.findElements(By.xpath(".//div[@class='v-list__tile__title']"));
        for (WebElement option:statusOption)
        {
            if(status.toLowerCase().equals(option.getText().toString().toLowerCase(Locale.ROOT)))
            {
                actions.moveToElement(option).click().perform();
                break;
            }
        }
    }

    public void searchUser(String userName, @Nullable String company, @Nullable String workingPlace, @Nullable String active ){
        base.enterValueToElement(driver,userName_txt,userName);
        if(company!= null)
        {
            selectCompany(company);
        }
        if(workingPlace!= null)
        {
            selectWorkingPlace(workingPlace);
        }
        if(active!= null)
        {
            selectStatus(active);
        }
        Actions actions = new Actions(driver);
        WebElement searchButton = driver.findElement(search_btn);
        actions.moveToElement(searchButton).click().perform();
    }

    public void searchAndOpenEmployeeFolder(String userName, @Nullable String company, @Nullable String workingPlace, @Nullable String active)
    {
        searchUser(userName,company,workingPlace,active);
        WebElement employeeList = driver.findElement(employees_list);
        List<WebElement> employeeFolder = employeeList.findElements(By.xpath(".//div[@class='v-list__tile__title m-name']"));
        for(WebElement folder: employeeFolder){
            if (folder.getText().toLowerCase(Locale.ROOT).toString().equals(userName.toLowerCase(Locale.ROOT)))
            {
                folder.click();
                break;
            }
        }
    }

    public void uploadImage(String userName) throws InterruptedException, AWTException {
        base.clickToElement(driver,uploadImg_btn);
        base.clickToElement(driver,chooseFile_btn);
        Thread.sleep(5000);
        //Settext in window popup---------------------------
        String temp = System.getProperty("user.dir") + "\\" +filePath+userName+".png";
        StringSelection ss = new StringSelection(temp);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss,null);
        Robot rb = new Robot();
        // press Contol+V for pasting
        rb.keyPress(KeyEvent.VK_CONTROL);
        rb.keyPress(KeyEvent.VK_V);
        // release Contol+V for pasting
        rb.keyRelease(KeyEvent.VK_CONTROL);
        rb.keyRelease(KeyEvent.VK_V);
        // press ENTER
        rb.keyPress(KeyEvent.VK_ENTER);
        // release ENTER
        rb.keyRelease(KeyEvent.VK_ENTER);
        base.waitForElementVisible(driver,uploading_msg);
        base.waitForElementInvisible(driver,uploading_msg);
        base.isElementInvisibled(driver,uploading_msg);
        WebElement imgList = driver.findElement(image_list);
        List<WebElement> imgs = imgList.findElements(By.xpath("./div"));
        if(imgs.size()>0){
            flag = true;
        }
        Assert.assertTrue(flag,"Image is uploaded unsuccessfully");
    }
}
