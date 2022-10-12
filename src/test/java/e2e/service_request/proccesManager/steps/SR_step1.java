package e2e.service_request.proccesManager.steps;

import helpers.Asserts;
import helpers.BasicControl;
import helpers.ChargePopPup;
import helpers.FormsControl;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;


public class SR_step1 {

    private WebDriver driver;
    private WebDriverWait wait;
    private BasicControl basicControl;
    private Asserts asserts;
    private Actions action;
    private String editModel = "--btnEdit-img";
    private String openFile = "//span[contains(@id,'--btnOpenFile-img')]";
    private String saveModel = "--btnSave-inne";

    public SR_step1(WebDriver driver){
        this.driver = driver;
        this.basicControl = new BasicControl(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(100));
        this.asserts = new Asserts(driver);
        this.action = new Actions(driver);
    }

    public void step1Process() throws InterruptedException, IOException {
        //Ingrsamos al paso 1 Model Process
        FormsControl.controlTitle(driver,"Modelar Proceso","Model Process");
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
        String rutaExe , rutaDiagrama;
        rutaDiagrama = pathFile("resources\\ServiceRequest\\diagramSR.bpmn");
        rutaExe = pathFile("resources\\ServiceRequest\\cargardiagramaServiceRequest.exe");
        ProcessBuilder pb = new ProcessBuilder(rutaExe,rutaDiagrama);
        pb.start();
        Thread.sleep(6000);
        basicControl.btnSave(saveModel);
        ChargePopPup.PopPupGeneral(driver,wait);
        asserts.assertSaveDiagram();
    }

    private  String pathFile(String NOMBRE_ARCHIVO){
        final String pathAbsolute;
        Path rutaRelativa = Paths.get(NOMBRE_ARCHIVO);
        Path rutaAbsoluta = rutaRelativa.toAbsolutePath();
        File ca  = new File("cargardiagramaServiceRequest.exe");
        ca.getAbsolutePath();

        return  rutaAbsoluta.toString();
    }



}
