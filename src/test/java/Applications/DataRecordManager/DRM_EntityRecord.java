package Applications.DataRecordManager;


import Forms.DataRecordManager.FormsDataRecord;
import Helpers.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;


public class DRM_EntityRecord {
    private WebDriver driver;
    private WebDriverWait wait;
    private JavascriptExecutor js;
    private BasicControl basicControl;
    private Actions action;
    private AccessBranch accessBranch;
    private DynamicScroll searchScrollElement;
    private Asserts asserts;
    private String componente = "Data Entities";
    private FormsDataRecord formsDataRecord;


    public DRM_EntityRecord(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver,Duration.ofSeconds(100));
        this.js = (JavascriptExecutor)driver;
        this.basicControl = new BasicControl(driver);
        this.action = new Actions(driver);
        this.accessBranch = new AccessBranch(driver);
        this.searchScrollElement = new DynamicScroll(driver);
        this.asserts = new Asserts(driver);
        this.formsDataRecord = new FormsDataRecord(driver);
    }

    @Test
    public void crearDataRecordGlobal(String dataName, String[][] attributeList,String typeEntity) throws InterruptedException {
        accessBranch.clickBranches(0);
        accessBranch.clickBranches(1);
        int xpos =searchScrollElement.elementSearch(typeEntity);
        accessBranch.clickBranches(xpos);
        crearDataRecord(dataName,attributeList);
    }

    @Test
    public void crearDataRecordProcess(String hierarchie,String process,String dataName, String[][] attributeList ,String typeEntity) throws InterruptedException {
        accessBranch.clickBranches(1);
        ChargePopPup.PopPupTree(driver,wait);
        int xpos =searchScrollElement.elementSearch(hierarchie);
        if(xpos != -1){
            accessBranch.clickBranches(xpos);
            xpos = searchScrollElement.elementSearch("Processes");
            if(xpos != -1){
                accessBranch.clickBranches(xpos);
                xpos = searchScrollElement.elementSearch(process);
                if(xpos != -1){
                    accessBranch.clickBranches(xpos);
                    accessBranch.clickBranches(xpos+1);
                    xpos =searchScrollElement.elementSearch(typeEntity);
                    accessBranch.clickBranches(xpos);
                    crearDataRecord(dataName,attributeList);
                }else{
                    System.out.println("Error");
                }
            }else{
                System.out.println("Error");
            }
        }else{
            System.out.println("Error");
        }

    }

    public void crearDataRecord(String dataName, String[][] attributeList) throws InterruptedException {
        scrollTop();
        searchScrollElement.elementSearch(dataName);
        driver.findElement(By.xpath("//span[text()='"+dataName+"']")).click();
        ChargePopPup.PopPupGeneral(driver,wait);
        formsDataRecord.createDataRecord(attributeList);
    }

    public void scrollTop(){
        WebElement scroll;
        int tamScroll = basicControl.displayScrollTree();
        String xmlview = basicControl.getXmlview();

        if(tamScroll != 0 ){
            scroll = driver.findElement(By.id(xmlview+"--mainTree-vsb"));
            js.executeScript("arguments[0].scroll(0,0);",scroll);
        }

    }

}
