package forms.DataEntityManager;

import helpers.BasicControl;
import helpers.ChargePopPup;
import helpers.FormsControl;
import helpers.SelectListItem;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
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
    private String addAtributte = "//button[@title='Agregar' or @title='Add']";
    private ChargePopPup chargePopPup;

    public FormsDataEntity(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(100));
        this.basicControl = new BasicControl(driver);
        this.selectListItem = new SelectListItem(driver);
        this.chargePopPup = new ChargePopPup(driver);
    }

    public void createDataEntity(String dataEntity,Map<String,String> attributeList,String typeEntity) throws InterruptedException {
        listForm = FormsControl.controlNew(driver,"Entidad de Datos","Data Entity");
        listForm.get(0).click();
        listForm.get(0).sendKeys(dataEntity);
        listForm.get(1).click();
        listForm.get(1).sendKeys(dataEntity);
        listForm.get(2).click();
        listForm.get(2).sendKeys(dataEntity);
        Thread.sleep(1000);

        driver.findElement(By.xpath("//span[text()='Configuración' or text()='Configuration']")).click();

        driver.findElement(By.xpath(arrowTypeEntity)).click();
        selectListItem.SelectItemLi(typeEntity);


        int x = 0;
        for (Map.Entry<String,String> attribute : attributeList.entrySet()) {
            driver.findElement(By.xpath(addAtributte)).click();
            driver.findElements(By.xpath(inputTbl)).get(x).click();
            driver.findElements(By.xpath(inputTbl)).get(x).sendKeys(attribute.getKey());
            driver.findElements(By.xpath(arrowTbl)).get(x).click();
            selectListItem.SelectItemLi(attribute.getValue());
            x++;
        }

        driver.findElement(By.xpath("//span[text()='Perfil de Seguridad' or text()='Security Profile']")).click();

        basicControl.btnSave();
        chargePopPup.PopPupGeneral();
    }


}
