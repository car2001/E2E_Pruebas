package Forms.DataEntityManager;

import Helpers.BasicControl;
import Helpers.ChargePopPup;
import Helpers.FormsControl;
import Helpers.SelectListItem;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FormsDataEntity {

    private WebDriver driver;
    private WebDriverWait wait;
    private List<WebElement> listForm;
    private BasicControl basicControl;
    private SelectListItem selectListItem;
    private String arrowTypeEntity = "//div[contains(@id,'xmlview')]//span[contains(@id,'idSelectEntityType-arrow')]";
    private String arrowTbl = "//table[contains(@id,'--idTableAttribute-listUl')]//span[contains(@class,'sapMSltArrow')]";
    private String inputTbl = "//table[contains(@id,'--idTableAttribute-listUl')]//div[not(contains(@id,'idDisplayNameGrid'))]//input[contains(@class,'sapMInputBaseInner')]";
    private String addAtributte = "//button[@title='Añadir' or @title='Add']";

    public FormsDataEntity(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(100));
        this.basicControl = new BasicControl(driver);
        this.selectListItem = new SelectListItem(driver);
    }

    public void createDataEntity(String dataEntity,Map<String,String> attributeList,String typeEntity) throws InterruptedException {
        listForm = FormsControl.controlNew(driver,"entidad de datos","Data Entity");
        listForm.get(0).click();
        listForm.get(0).sendKeys(dataEntity);
        listForm.get(1).click();
        listForm.get(1).sendKeys(dataEntity);
        listForm.get(2).click();
        listForm.get(2).sendKeys(dataEntity);
        driver.findElement(By.xpath(arrowTypeEntity)).click();
        selectListItem.SelectItemLi(typeEntity);
        driver.findElements(By.xpath("//div[@class='sapMSwtHandle']")).get(1).click();

        int x = 0;
        for (Map.Entry<String,String> attribute : attributeList.entrySet()) {
            driver.findElement(By.xpath(addAtributte)).click();
            driver.findElements(By.xpath(inputTbl)).get(x).click();
            driver.findElements(By.xpath(inputTbl)).get(x).sendKeys(attribute.getKey());
            driver.findElements(By.xpath(arrowTbl)).get(x).click();
            selectListItem.SelectItemLi(attribute.getValue());
            x++;
        }
        basicControl.btnSave();
        ChargePopPup.PopPupGeneral(driver,wait);
    }


}
