package E2E.ServiceRequest;

import Helpers.Asserts;
import Helpers.BasicControl;
import Helpers.ChargePopPup;
import Helpers.FormsControl;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.time.Duration;


public class ServiceRequest_Step1 {

    private WebDriver driver;
    private WebDriverWait wait;
    private BasicControl basicControl;
    private Asserts asserts;
    private Actions action;
    private String editModel = "--btnEdit-img";
    private String openFile = "//span[contains(@id,'__xmlview6--btnOpenFile-img')]";
    private String saveModel = "--btnSave-inne";

    public ServiceRequest_Step1(WebDriver driver){
        this.driver = driver;
        this.basicControl = new BasicControl(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(100));
        this.asserts = new Asserts(driver);
        this.action = new Actions(driver);
    }

    public void step1Process() throws InterruptedException, IOException {
        //Ingrsamos al paso 1 Model Process
        FormsControl.controlTitle(driver,"Modelar proceso","Model Process");
        //Editamos el paso 1
        basicControl.btnEdit(editModel);
        //Obtenemos xmlview
        String xmlview = basicControl.getXmlview();
        //Exportamos el diagrama
        WebElement rect = driver.findElement(By.cssSelector("#"+xmlview+"--js-canvas > div > div > svg > g > g.layer-base > g > g.djs-element.djs-shape.highlight-gray > rect.djs-hit.djs-hit-click-stroke"));
        action.moveToElement(rect,0,rect.getRect().getHeight()/2).click().perform();
        driver.findElement(By.xpath("//div[@data-action='delete']")).click();
        driver.findElement(By.xpath("//bdi[text()='Sí' or text()='Yes']")).click();
        driver.findElement(By.xpath(openFile)).click();
        Runtime.getRuntime().exec("D:\\Pruebas_Selenium\\E2E_Test\\E2E_Pruebas\\resources\\cargararchivo.exe");
        Thread.sleep(5000);
        basicControl.btnSave(saveModel);
        ChargePopPup.PopPupGeneral(driver,wait);
        asserts.assertSaveDiagram();
    }

}
