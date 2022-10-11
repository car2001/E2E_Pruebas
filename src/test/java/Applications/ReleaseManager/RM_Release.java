package Applications.ReleaseManager;


import Forms.ReleaseManager.FormsProject;
import Forms.ReleaseManager.FormsRelease;
import Helpers.AccessBranch;
import Helpers.Asserts;
import Helpers.DynamicScroll;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class RM_Release  {
    private WebDriver driver;

    Actions action;
    AccessBranch accessBranch;
    DynamicScroll searchScrollElement;
    Asserts asserts;
    FormsProject formsProject;
    FormsRelease formsRelease;

    String component = "Releases";
    int exist = -1;

    public RM_Release(WebDriver driver){
        this.driver = driver;
        this.action = new Actions(driver);
        this.asserts = new Asserts(driver);
        this.accessBranch = new AccessBranch(driver);
        this.searchScrollElement = new DynamicScroll(driver);
        this.formsProject = new FormsProject(driver);
        this.formsRelease = new FormsRelease(driver);
    }


    @Test
    public void crearRelease(String project,String nameRelease) throws InterruptedException {
        crearProyecto(project);
        exist = searchScrollElement.elementSearch(project);
        if(exist != -1){
            accessBranch.clickBranches(exist);
            exist= searchScrollElement.elementSearch(component);
            if(exist != -1){
                WebElement release = driver.findElement(By.xpath("//span[text()='"+component+"']"));
                action.contextClick(release).perform();
                driver.findElement(By.xpath("//div[text()='Nueva Liberación' or text()='New Release']")).click();
                formsRelease.createRelease(nameRelease);
                asserts.assertSave();
            }else{
                Assert.assertEquals("No hay Release","Si hay Release");
            }
        }else{
            Assert.assertEquals("No hay Proyecto","Si hay Proyecto");
        }
    }

    @Test
    public void editarRelease(String project,String nameRelease,String editRelease) throws InterruptedException {
        exist = searchScrollElement.elementSearch(project);
        if(exist != -1){
            accessBranch.clickBranches(exist);
            exist= searchScrollElement.elementSearch(component);
            if(exist != -1){
                accessBranch.clickBranches(exist);
                exist=searchScrollElement.elementSearch(nameRelease);
                if(exist !=-1){
                    driver.findElement(By.xpath("//span[normalize-space()='"+nameRelease+"']")).click();
                    formsRelease.editRelease(editRelease);
                    asserts.assertSave();
                    accessBranch.clickBranches(0);
                }else{
                    Assert.assertEquals("No hay " + nameRelease,"Si hay Release");
                }
            }else{
                Assert.assertEquals("No hay Release","Si hay Release");
            }
        }else{
            Assert.assertEquals("No hay Proyecto","Si hay Proyecto");
        }
    }

    @Test
    public void eliminarRelease(String project,String editRelease){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        exist = searchScrollElement.elementSearch(project);
        if(exist != -1){
            accessBranch.clickBranches(exist);
            exist= searchScrollElement.elementSearch(component);
            if(exist != -1){
                accessBranch.clickBranches(exist);
                exist=searchScrollElement.elementSearch(editRelease);
                if(exist !=-1){
                    WebElement release = driver.findElement(By.xpath("//span[normalize-space()='"+editRelease+"']"));
                    action.contextClick(release).perform();
                    driver.findElement(By.xpath("//div[normalize-space()='Delete "+component+"']")).click();
                    driver.findElement(By.xpath("//bdi[normalize-space()='Sí']")).click();
                    String xpathMessage = "//span[@class='sapMText sapUiSelectable sapMTextMaxWidth sapMMsgBoxText']";
                    driver.findElement(By.xpath("//bdi[normalize-space()='OK']")).click();
                    asserts.assertDelete(xpathMessage);
                    //-------espera que el pop-up de eliminar desaparezca
                    WebElement popUp = driver.findElement(By.id("sap-ui-blocklayer-popup"));
                    wait.until(ExpectedConditions.invisibilityOf(popUp));
                    //--------------------------
                    WebElement spanProject = driver.findElement(By.xpath("//span[normalize-space()='"+project+"']"));
                    action.contextClick(spanProject).perform();
                    driver.findElement(By.xpath("//div[normalize-space()='Delete Project']")).click();
                    driver.findElement(By.xpath("//bdi[normalize-space()='Sí'][last()]")).click();
                    driver.findElement(By.xpath("//bdi[normalize-space()='OK'][last()]")).click();
                }else{
                    Assert.assertEquals("No hay " + editRelease,"Si hay Release");
                }
            }else{
                Assert.assertEquals("No hay Release","Si hay Release");
            }
        }else{
            Assert.assertEquals("No hay Proyecto","Si hay Proyecto");
        }
    }

    @AfterMethod
    public void tearDown(){
        if(driver != null){
            driver.quit();
        }
    }

    public void crearProyecto(String proyecto) throws InterruptedException {
        WebElement despleProyecto = driver.findElement(By.xpath("//span[contains(@id,'--mainTree-rows-row0-treeicon')]"));
        action.contextClick(despleProyecto).perform();
        driver.findElement(By.xpath("//div[text()='New Project' or text()='Nuevo Proyecto']")).click();
        formsProject.createProject(driver, proyecto);
        asserts.assertSave();
    }


}
