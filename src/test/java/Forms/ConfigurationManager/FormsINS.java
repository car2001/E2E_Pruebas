package Forms.ConfigurationManager;

import Helpers.BasicControl;
import Helpers.FormsControl;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class FormsINS {
    private WebDriver driver;
    private List<WebElement> listForm;
    private BasicControl basicControl;
    private String addOptions;
    private String selectOptions;

    public FormsINS(WebDriver driver){
        this.driver = driver;
        basicControl = new BasicControl(driver);
        this.addOptions = "//span[contains(@id,'--addItem-img')]";
        this.selectOptions = "//span[@class='sapUiIcon sapUiIconMirrorInRTL sapUiIconPointer sapMInputBaseIcon' and (@aria-label ='Opciones de selección' or @aria-label='Select Options')]";
    }

    public void formCreateINS(String INS , String separador , String fixedValue, String counter){
        basicControl.btnAdd();
        listForm = FormsControl.controlNew(driver,"Nuevo esquema de numeración de instancias","Instance Numbering Schema");
        listForm.get(0).click();
        listForm.get(0).sendKeys(INS);
        listForm.get(1).click();
        listForm.get(1).sendKeys(INS);
        listForm.get(2).click();
        listForm.get(2).sendKeys(INS);
        listForm.get(4).click();
        listForm.get(4).sendKeys(separador);
        //Agregamos Component List
        driver.findElement(By.xpath(addOptions)).click();
        driver.findElement(By.xpath(addOptions)).click();
        //Seleccionamos Opciones
        List<WebElement> cboComponentes =  driver.findElements(By.xpath(selectOptions));
        cboComponentes.get(0).click();
        driver.findElement(By.xpath("//div[text()='Fixed Value' and @class ='sapMSLITitleOnly']")).click();
        cboComponentes.get(1).click();
        driver.findElements(By.xpath("//div[text()='Counter' and @class ='sapMSLITitleOnly']")).get(1).click();
        listForm = basicControl.inputForms();
        listForm.get(6).click();
        listForm.get(6).sendKeys(fixedValue);
        driver.findElements(By.xpath(selectOptions)).get(2).click();
        driver.findElement(By.xpath("//div[text()='"+counter+"']")).click();
        basicControl.btnSave();
    }
}
