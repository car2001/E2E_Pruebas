package Forms.ProcessManager;

import Helpers.BasicControl;
import Helpers.FormsControl;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

public class FormsProcess {
    private WebDriver driver;
    private Actions action;
    private List<WebElement> listForm;
    private BasicControl basicControl;
    private JavascriptExecutor js;
    private String isAutomated;
    private String addSecurityProfile;
    private String groupPermisos;
    private String selectType;
    private String valueA;

    public FormsProcess(WebDriver driver){
        this.driver = driver;
        this.basicControl = new BasicControl(driver);
        this.js = (JavascriptExecutor) driver;
        this.action = new Actions(driver);
        this.isAutomated = "//div[contains(@id,'--isAutomated-handle')]";
        this.addSecurityProfile = "//span[contains(@id,'--addSecurityProfile-inner')]";
        this.groupPermisos = "//span[ contains(@id,'__select') and @class='sapMSltArrow']";
        this.selectType = "//span[contains(@id,'--typeSelect-') and @class='sapMSltArrow' ]";
        this.valueA = "//span[contains(@id,'--valueSelect-') and @class='sapMSltArrow']";
    }

    public void crearProceso(String process , Actions action, String INS, String SLA){
        listForm = FormsControl.controlNew(driver,"proceso","Process");
        listForm.get(2).click();
        listForm.get(2).sendKeys(process);
        listForm.get(3).click();
        listForm.get(3).sendKeys(process);
        listForm.get(4).click();
        listForm.get(4).sendKeys(process);
        driver.findElement(By.xpath(isAutomated)).click();

        securityProfile();

        // INS
        if(js.executeScript("let INS = document.getElementById('__xmlview4--itfInstanceNumbering-icon');return INS.clientHeight").hashCode() == 0){
            driver.findElement(By.id("__xmlview4--itbAutomationProcess--header-overflow-text")).click();
            driver.findElement(By.xpath("//li[@title = 'Numeración de instancia']")).click();
        }else{
            driver.findElement(By.id("__xmlview4--itfInstanceNumbering-icon")).click();
        }
        driver.findElement(By.id("__xmlview4--instanceNumbering-inner")).click();
        driver.findElement(By.id("__xmlview4--instanceNumbering-inner")).sendKeys(INS);
        //SLA
        if(js.executeScript("let SLA = document.getElementById('__xmlview4--itfSLAConfiguration-icon');return SLA.clientHeight").hashCode() == 0){
            driver.findElement(By.id("__xmlview4--itbAutomationProcess--header-overflow-text")).click();
            driver.findElement(By.xpath("//li[@title = 'SLA']")).click();
        }else{
            driver.findElement(By.id("__xmlview4--itfSLAConfiguration-icon")).click();
        }
        driver.findElement(By.id("__xmlview4--selectSLAPROProperty-inner")).click();
        driver.findElement(By.id("__xmlview4--selectSLAPROProperty-inner")).sendKeys(SLA);
        driver.findElement(By.id("__xmlview4--selectSLAPROProperty-inner")).sendKeys(Keys.TAB);
        driver.findElement(By.id("__xmlview4--numberOfDaysPRO-inner")).click();
        driver.findElement(By.id("__xmlview4--numberOfDaysPRO-inner")).sendKeys("5");
        basicControl.btnSave();
    }

    public void securityProfile(){
        //SECURITY PROFILE
        WebElement btnSecurity =  driver.findElement(By.xpath(addSecurityProfile));
        action.doubleClick(btnSecurity).perform();
        //Permisos
        List<WebElement> permisos = driver.findElements(By.xpath(groupPermisos));
        permisos.get(0).click();
        driver.findElement(By.xpath("//li[contains(@id,'__select') and contains(@class,'sapMSelectListItemBase sapMSelectListItem sapMSelectListItemBaseHoverable') and text()='Instance Creator']")).click();
        permisos.get(1).click();
        driver.findElements(By.xpath("//li[contains(@id,'__select') and contains(@class,'sapMSelectListItemBase sapMSelectListItem sapMSelectListItemBaseHoverable') and text()='Administrator']")).get(1).click();
        //Tipo
        List<WebElement> tipo = driver.findElements(By.xpath(selectType));
        tipo.get(0).click();
        String role = "//li[contains(@id,'--typeSelect-') and contains(@class,'sapMSelectListItemBase sapMSelectListItem sapMSelectListItemBaseHoverable') and text()='Role']";
        driver.findElement(By.xpath(role)).click();
        tipo.get(1).click();
        driver.findElements(By.xpath(role)).get(1).click();
        //Value
        List<WebElement> value = driver.findElements(By.xpath(valueA));
        value.get(0).click();
        String superadmin = "//li[contains(@id,'--valueSelect-') and contains(@class,'sapMSelectListItemBase sapMSelectListItem sapMSelectListItemBaseHoverable') and text()='Superadmin']";
        driver.findElement(By.xpath(superadmin)).click();
        value.get(1).click();
        driver.findElements(By.xpath(superadmin)).get(1).click();
    }
}
