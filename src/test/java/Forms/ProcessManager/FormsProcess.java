package Forms.ProcessManager;

import Helpers.BasicControl;
import Helpers.FormsControl;
import Helpers.SelectListItem;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import java.util.List;

public class FormsProcess {

    private WebDriver driver;
    private List<WebElement> listForm;
    private SelectListItem selectListItem;
    private Actions action;
    private BasicControl basicControl;
    private String isAutomated;
    private String addSecurityProfile;
    private String groupPermisos;
    private String selectType;
    private String valueA;
    //INS
    private String arrowINS;
    //SLA
    private String arrowSLA;
    private String slaDays;

    public FormsProcess(WebDriver driver){
        this.driver = driver;
        this.basicControl = new BasicControl(driver);
        this.action = new Actions(driver);
        this.selectListItem = new SelectListItem(driver);
        this.isAutomated = "//div[contains(@id,'--isAutomated-handle')]";
        //security profile
        this.addSecurityProfile = "//span[contains(@id,'--addSecurityProfile-inner')]";
        this.groupPermisos = "//span[ contains(@id,'__select') and @class='sapMSltArrow']";
        this.selectType = "//span[contains(@id,'--typeSelect-') and @class='sapMSltArrow' ]";
        this.valueA = "//span[contains(@id,'--valueSelect-') and @class='sapMSltArrow']";
        //INS
        this.arrowINS = "//div[contains(@id,'xmlview')]//span[contains(@id,'--instanceNumbering-arrow')][not(contains(@class,'sapUiInvisibleText'))]";
        //SLA
        this.arrowSLA = "//div[contains(@id,'xmlview')]//span[contains(@id,'--selectSLAPROProperty-arrow')][not(contains(@class,'sapUiInvisibleText'))]";
        this.slaDays = "//input[contains(@id,'--numberOfDaysPRO-inner')]";
    }

    public void createProcess(String process,String INS, String SLA) throws InterruptedException {
        listForm = FormsControl.controlNew(driver,"Proceso","Process");
        listForm.get(0).click();
        listForm.get(0).sendKeys(process);
        listForm.get(1).click();
        listForm.get(1).sendKeys(process);
        listForm.get(2).click();
        listForm.get(2).sendKeys(process);
        driver.findElement(By.xpath(isAutomated)).click();

        securityProfile();

        // INS
        clickProcessConfigurationIcons("Instance Creation");
        driver.findElement(By.xpath(arrowINS)).click();
        selectListItem.SelectItemDiv(INS);
        //SLA
        clickProcessConfigurationIcons("SLA");
        driver.findElement(By.xpath(arrowSLA)).click();
        selectListItem.SelectItemDiv(SLA);
        driver.findElement(By.xpath(slaDays)).click();
        driver.findElement(By.xpath(slaDays)).sendKeys("5");
        basicControl.btnSave();
    }

    private void securityProfile() throws InterruptedException {
        //SECURITY PROFILE
        WebElement btnSecurity =  driver.findElement(By.xpath(addSecurityProfile));
        action.doubleClick(btnSecurity).perform();
        //Permisos
        List<WebElement> permisos = driver.findElements(By.xpath(groupPermisos));
        List<WebElement> tipo = driver.findElements(By.xpath(selectType));
        List<WebElement> value = driver.findElements(By.xpath(valueA));
        //Instance Creator
        permisos.get(0).click();
        selectListItem.SelectItemLi("Instance Creator");
        tipo.get(0).click();
        selectListItem.SelectItemLi("Role");
        value.get(0).click();
        selectListItem.SelectItemLi("Superadmin");
        Thread.sleep(500);
        //Instance Administrator
        permisos.get(1).click();
        selectListItem.SelectItemLi("Administrator");
        tipo.get(1).click();
        selectListItem.SelectItemLi("Role");
        //Value
        value.get(1).click();
        selectListItem.SelectItemLi("Superadmin");
    }

    private void clickProcessConfigurationIcons(String processConfiguration){
        int iconProcess;

        switch (processConfiguration)
        {
            case "Security Profile":
                iconProcess = driver.findElement(By.xpath("//span[contains(@id,'--itfSecurityProfile-icon')]")).getRect().getHeight();
                checkIcons(iconProcess,"--itfSecurityProfile-");
                break;
            case "Automation Features":
                iconProcess = driver.findElement(By.xpath("//span[contains(@id,'--itfAutomation-icon')]")).getRect().getHeight();
                checkIcons(iconProcess,"--itfAutomation-");
                break;
            case "Form UI":
                iconProcess = driver.findElement(By.xpath("//span[contains(@id,'--itfFormOptions-icon')]")).getRect().getHeight();
                checkIcons(iconProcess,"--itfFormOptions-");
                break;
            case "Instance Creation":
                iconProcess = driver.findElement(By.xpath("//span[contains(@id,'--itfInstanceNumbering-icon')]")).getRect().getHeight();
                checkIcons(iconProcess,"--itfInstanceNumbering-");
                break;
            case "SLA":
                iconProcess = driver.findElement(By.xpath("//span[contains(@id,'--itfSLAConfiguration-icon')]")).getRect().getHeight();
                checkIcons(iconProcess,"--itfSLAConfiguration-");
                break;
            case "Notification":
                iconProcess = driver.findElement(By.xpath("//span[contains(@id,'--itNotificationProfile-icon')]")).getRect().getHeight();
                checkIcons(iconProcess,"--itNotificationProfile-");
                break;
            case "Risk Management":
                iconProcess = driver.findElement(By.xpath("//span[contains(@id,'--idRiskProfileProfile-icon')]")).getRect().getHeight();
                checkIcons(iconProcess,"--idRiskProfileProfile-");
                break;
            case "Project Management":
                iconProcess = driver.findElement(By.xpath("//span[contains(@id,'--idProjectManagement-icon')]")).getRect().getHeight();
                checkIcons(iconProcess,"--idProjectManagement-");
                break;
            case "Indexed Fields":
                iconProcess = driver.findElement(By.xpath("//span[contains(@id,'--idIndexedManagement-icon')]")).getRect().getHeight();
                checkIcons(iconProcess,"--idIndexedManagement-");
                break;
        }
    }

    private void checkIcons(int tamIcons,String xpath){
        String moreIcons = "//div[contains(@id,'--itbAutomationProcess--header-overflow-text')]";
        if(tamIcons == 0){
            driver.findElement(By.xpath(moreIcons)).click();
            driver.findElement(By.xpath("//li[contains(@id,'"+xpath+"')]")).click();
        }else{
            driver.findElement(By.xpath("//span[contains(@id,'"+xpath+"icon')]")).click();
        }
    }

}
