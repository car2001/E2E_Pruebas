package Forms.ConfigurationManager;

import Helpers.BasicControl;
import Helpers.FormsControl;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


import java.util.List;

public class FormsFormUI {
    private WebDriver driver;
    private List<WebElement> listForm;
    private BasicControl basicControl;
    private String comment;
    private String attachment;
    private String instructions;

    public FormsFormUI(WebDriver driver){
        this.driver = driver;
        this.basicControl = new BasicControl(driver);
        this.comment = "//div[contains(@id,'--comments-switch')]";
        this.attachment = "//div[contains(@id,'--attachments-switch')]";
        this.instructions = "//div[contains(@id,'--instructions-switch')]";
    }

    public void formCreateFormUI(String UI){
        basicControl = new BasicControl(driver);
        basicControl.btnAdd();
        listForm = FormsControl.controlNew(driver,"UI","UI");
        listForm.get(2).click();
        listForm.get(2).sendKeys(UI);
        listForm.get(3).click();
        listForm.get(3).sendKeys(UI);
        listForm.get(4).click();
        listForm.get(4).sendKeys(UI);
        driver.findElement(By.xpath(comment)).click();
        driver.findElement(By.xpath(attachment)).click();
        driver.findElement(By.xpath(instructions)).click();
        basicControl.btnSave();
    }

    public void formEditFormUI(String UI) throws InterruptedException {
        basicControl = new BasicControl(driver);
        listForm = FormsControl.controlEdit(driver,"UI","UI");
        listForm.get(2).click();
        listForm.get(2).clear();
        listForm.get(2).sendKeys(UI);
        listForm.get(3).click();
        listForm.get(3).clear();
        listForm.get(3).sendKeys(UI);
        listForm.get(4).click();
        listForm.get(4).clear();
        listForm.get(4).sendKeys(UI);
        basicControl.btnSave();
    }



}
