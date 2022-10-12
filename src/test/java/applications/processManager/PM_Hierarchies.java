package applications.processManager;

import forms.ProcessManager.FormsHierarchie;
import helpers.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PM_Hierarchies {

    private WebDriver driver;

    Actions action;
    AccessBranch accessBranch;
    DynamicScroll searchScrollElement;
    Asserts asserts;

    String component = "Process Hierarchies";
    String nameLevel1 = "Jerarquia Selenium";

    FormsHierarchie formsHierarchie;

    public PM_Hierarchies(WebDriver driver){
        this.driver = driver;
        this.asserts = new Asserts(driver);
        this.action = new Actions(driver);
        this.accessBranch = new AccessBranch(driver);
        this.searchScrollElement = new DynamicScroll(driver);
        this.formsHierarchie = new FormsHierarchie(driver);
    }


    @Test
    public void crearHierarchies(String nameLevel) throws InterruptedException {
        accessBranch.clickBranches(1);
        WebElement hierarchies = driver.findElement(By.xpath("//span[text()='"+component+"']"));
        action.contextClick(hierarchies).perform();
        driver.findElement(By.xpath("//div[normalize-space()='New Level' or text()='Nuevo Nivel']")).click();
        Thread.sleep(1000);
        formsHierarchie.createNewHierarchie(nameLevel);
        asserts.assertSave();
    }

    @Test
    public void eliminarHierarchies(String nameLevel){
        int xpos = searchScrollElement.elementSearch(nameLevel);
        if(xpos != -1){
            WebElement hierarchie = driver.findElement(By.xpath("//span[text()='"+nameLevel+"']"));
            FormsControl.controlDelete(driver,action,hierarchie,"Level");
        }else{
            Assert.assertEquals("No se encontro la jerarquia","NO");
        }
    }


}
