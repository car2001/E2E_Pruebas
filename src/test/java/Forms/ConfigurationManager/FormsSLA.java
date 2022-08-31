package Forms.ConfigurationManager;

import Helpers.BasicControl;
import Helpers.FormsControl;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.time.Duration;
import java.util.List;

public class FormsSLA {
    private WebDriver driver;
    private List<WebElement> listForm;
    private BasicControl basicControl;
    private String slaType;

    public FormsSLA(WebDriver driver){
        this.driver = driver;
        this.basicControl = new BasicControl(driver);
        this.slaType = "//span[contains(@id,'--slaType-arrow')]";
    }

    public void formCreateSLA(WebDriver driver, String SLA) throws InterruptedException {
        basicControl.btnAdd();
        listForm = FormsControl.controlNew(driver,"SLA","SLA");
        listForm.get(2).click();
        listForm.get(2).sendKeys(SLA);
        listForm.get(3).click();
        listForm.get(3).sendKeys(SLA);
        listForm.get(4).click();
        listForm.get(4).sendKeys("Es un "+ SLA);
        //Tipo de SLA
        driver.findElement(By.xpath(slaType)).click();
        driver.findElement(By.xpath("//ul[contains(@id,'--slaType-popup-lis')]//li//div[@class='sapMSLITitleOnly'][text()='Fixed Value']")).click();
        driver.findElement(By.xpath("//div[contains(@id,'--useNumberDays-switch')]")).click();
        basicControl.btnSave();
    }

    public void formEditSLA(WebDriver driver, String SLA) throws InterruptedException {
        listForm = FormsControl.controlEdit(driver,"SLA","SLA");
        listForm.get(2).click();
        listForm.get(2).clear();
        listForm.get(2).sendKeys(SLA);
        listForm.get(3).click();
        listForm.get(3).clear();
        listForm.get(3).sendKeys(SLA);
        listForm.get(4).click();
        listForm.get(4).clear();
        listForm.get(4).sendKeys("Descripción "+ SLA);
        driver.findElement(By.xpath(slaType)).click();
        driver.findElement(By.xpath("//ul[contains(@id,'--slaType-popup-lis')]//li//div[@class='sapMSLITitleOnly'][text()='Depends on a Condition']")).click();
        basicControl.btnSave();
    }


}
