package Applications.ReleaseManager;


import Forms.ReleaseManager.FormsDeploymentPackage;
import Helpers.AccessBranch;
import Helpers.Asserts;
import Helpers.DynamicScroll;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

public class RM_DeploymentPackage {

    private WebDriver driver;
    private Actions action;
    private DynamicScroll searchScrollElement;
    private Asserts asserts;
    private AccessBranch accessBranch;
    private FormsDeploymentPackage formsDeploymentPackage;

    String componente = "Deployment Packages";

    public RM_DeploymentPackage(WebDriver driver){
        this.driver = driver;
        this.action = new Actions(driver);
        this.searchScrollElement = new DynamicScroll(driver);
        this.asserts = new Asserts(driver);
        this.accessBranch = new AccessBranch(driver);
        this.formsDeploymentPackage = new FormsDeploymentPackage(driver);
    }


    @Test
    public void createDeploymentPackage(String nameDP,String project,String release) throws InterruptedException {
        Thread.sleep(1000);
        WebElement btnOpen = driver.findElement(By.xpath("//span[text()='Open']"));
        Thread.sleep(1000);
        action.contextClick(btnOpen).build().perform();
        driver.findElement(By.xpath("//div[text()='New Deployment Package' or text()='Nuevo Paquete de Despliegue' ]")).click();
        Thread.sleep(1000);
        formsDeploymentPackage.createDeploymentPackage(nameDP,project,release);
        asserts.assertSaveDP();
    }


}
