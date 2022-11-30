package applications.dataEntityManager;

import forms.DataEntityManager.FormsDataEntity;
import helpers.AccessBranch;
import helpers.Asserts;
import helpers.ChargePopPup;
import helpers.DynamicScroll;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Map;

public class DEM_Entity {

    private WebDriver driver;
    private Actions action;
    private AccessBranch accessBranch;
    private DynamicScroll searchScrollElement;
    private Asserts asserts;
    private FormsDataEntity formsDataEntity;
    private String componente = "Data Entities";
    private WebDriverWait wait;


    public DEM_Entity(WebDriver driver){
        this.driver = driver;
        this.action = new Actions(driver);
        this.accessBranch = new AccessBranch(driver);
        this.searchScrollElement = new DynamicScroll(driver);
        this.asserts = new Asserts(driver);
        this.formsDataEntity = new FormsDataEntity(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(100));
    }

    @Test
    public void crearDataEntityGlobal(String dataName,Map<String,String> attributeList,String typeEntity) throws InterruptedException {
        accessBranch.clickBranches(0);
        crearDataEntity(dataName,attributeList,typeEntity);
    }

    @Test
    public void crearDataEntityProcess(String hierarchie, String nameProcess,String dataName,Map<String,String> attributeList,String typeEntity) throws InterruptedException {
        accessBranch.clickBranches(1);
        int xpos = searchScrollElement.elementSearch(hierarchie);
        if(xpos != -1){
            accessBranch.clickBranches(xpos);
            xpos = searchScrollElement.elementSearch("Processes");
            if (xpos != -1){
                accessBranch.clickBranches(xpos);
                xpos = searchScrollElement.elementSearch(nameProcess);
                if(xpos != -1){
                    accessBranch.clickBranches(xpos);
                    xpos = searchScrollElement.elementSearch("Data Entities");
                    if(xpos != -1){
                        crearDataEntity(dataName,attributeList,typeEntity);
                    }else{
                        Assert.assertEquals("No hay data Entities","Si hay data Entities");
                    }
                }else{
                    Assert.assertEquals("No hay proceso","Si hay proceso");
                }
            }else {
                Assert.assertEquals("No hay process","Si hay process");
            }
        }else {
            Assert.assertEquals("No hay jerarquia","Si hay jerarquia");
        }
    }

    public void crearDataEntity(String dataName,Map<String,String> attributeList,String typeEntity) throws InterruptedException {
        WebElement entity = driver.findElement(By.xpath("//span[text()='"+componente+"']"));
        action.contextClick(entity).perform();
        driver.findElement(By.xpath("//div[normalize-space()='New Entity Manager' or text()='Nuevo Gestor de Entidad']")).click();
        Thread.sleep(1000);
        formsDataEntity.createDataEntity(dataName,attributeList,typeEntity);
        asserts.assertSave();
    }

}
