package applications.releaseManager;


import forms.ReleaseManager.FormsDeploymentRequest;
import helpers.AccessBranch;
import helpers.Asserts;
import helpers.DynamicScroll;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

public class RM_DeploymentRequest {

    private WebDriver driver;
    private Actions action;
    private DynamicScroll searchScrollElement;
    private Asserts asserts;
    private AccessBranch accessBranch;
    private FormsDeploymentRequest formsDeploymentRequest;


    String componente = "Deployment Requests";

    public RM_DeploymentRequest(WebDriver driver){
        this.driver = driver;
        this.action = new Actions(driver);
        this.searchScrollElement = new DynamicScroll(driver);
        this.asserts = new Asserts(driver);
        this.formsDeploymentRequest = new FormsDeploymentRequest(driver);
    }


    @Test
    public void createDeploymentRequest(String nameDR, String project , String release) throws InterruptedException {
        WebElement btnOpen = driver.findElement(By.xpath("//span[text()='Open']"));
        action.contextClick(btnOpen).build().perform();
        driver.findElement(By.xpath("//div[text()='New Deployment Request' or text()='Nueva Solicitud de Despliegue']")).click();
        formsDeploymentRequest.createDeploymentRequest(nameDR, project, release);
        asserts.assertSaveDR();
    }




}
