package Applications.ReleaseManager;

import Forms.ReleaseManager.FormsProject;
import Helpers.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class RM_Project {
    private WebDriver driver;

    BasicControl basicControl;
    Actions action;
    DynamicScroll searchScrollElement;
    Asserts asserts;
    FormsProject formsProject;
    AccessBranch accessBranch;
    WebDriverWait wait;
    JavascriptExecutor js;


    String componente = "Projects";
    String newProject = "Proyecto Selenium2";
    String editProject = "Proyecto Selenium Editado";
    int exist = -1;

    public RM_Project(WebDriver driver){
        this.driver = driver;
        this.asserts = new Asserts(driver);
        this.action = new Actions(driver);
        this.accessBranch = new AccessBranch(driver);
        this.searchScrollElement = new DynamicScroll(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(100));
        this.js = (JavascriptExecutor) driver;
        this.basicControl = new BasicControl(driver);
        this.formsProject = new FormsProject(driver);
    }


    @Test
    public void crearProyecto() throws InterruptedException {
        WebElement proyecto = driver.findElement(By.id("__xmlview4--mainTree-rows-row0-treeicon"));
        action.contextClick(proyecto).perform();
        driver.findElement(By.xpath("//div[normalize-space()='New " + componente + "']")).click();
        formsProject.createProject(driver, newProject);
        asserts.assertSave();
    }

    @Test(priority = 1)
    public void verifyRelease() {
        String stateRelease = estadoRelease(newProject);
        Assert.assertEquals(stateRelease, "Si hay Release");
    }

    @Test(priority = 2)
    public void editarProyecto(String editProject) throws InterruptedException {
        exist= searchScrollElement.elementSearch(newProject);
        if(exist !=-1){
            driver.findElement(By.xpath("//span[normalize-space()='"+newProject+"']")).click();
            formsProject.editProject(editProject);
            asserts.assertSave();
        }else{ Assert.assertEquals("No hay "+newProject, "Si hay Proyecto");}
    }


    @Test(priority = 3)
    public void eliminarProyecto(){
        metodoEliminarProyecto(editProject);
    }

    @Test(priority = 4)
    public void crearProyectoSinRelease(String projectWithoutRelease) throws InterruptedException {
        WebElement proyecto = driver.findElement(By.id("__xmlview4--mainTree-rows-row0-treeicon"));
        action.contextClick(proyecto).perform();
        driver.findElement(By.xpath("//div[normalize-space()='New "+componente+"']")).click();
        formsProject.createProjectWithoutRelease(projectWithoutRelease);
        asserts.assertSave();
        String stateRelease = estadoRelease(projectWithoutRelease);
        Assert.assertEquals(stateRelease,"No hay Release");
        metodoEliminarProyecto(projectWithoutRelease);
    }

    @AfterMethod
    public void tearDown(){
        if (driver != null){
            //driver.quit();
        }
    }

    public String estadoRelease(String proyecto){
        exist= searchScrollElement.elementSearch(proyecto);
        if(exist !=-1){
            accessBranch.clickBranches(exist);
            exist=searchScrollElement.elementSearch("Release");
            if(exist !=-1){
                 return "Si hay Release" ;
            }else{
                return "No hay Release" ;
            }
        }else{return "No hay Proyecto" ;}
    }

    public void metodoEliminarProyecto(String proyecto){
        exist= searchScrollElement.elementSearch(proyecto);
        if(exist !=-1){
            WebElement btnEditProject = driver.findElement(By.xpath("//span[normalize-space()='"+proyecto+"']"));
            String xpathMessage = "//span[@class='sapMText sapUiSelectable sapMTextMaxWidth sapMMsgBoxText']";
            FormsControl.controlDelete(driver,action,btnEditProject,componente);
            asserts.assertDelete(xpathMessage);
        }else{Assert.assertEquals("No hay "+proyecto, "Si hay Proyecto");}
    }
}
