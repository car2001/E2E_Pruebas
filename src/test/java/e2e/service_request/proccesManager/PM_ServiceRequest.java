package e2e.service_request.proccesManager;

import applications.processManager.PM_Hierarchies;
import applications.processManager.PM_Process;
import e2e.service_request.proccesManager.steps.*;
import helpers.AccessBranch;
import helpers.BasicControl;
import helpers.ChargePopPup;
import helpers.DynamicScroll;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDate;

public class PM_ServiceRequest {
    private WebDriver driver;
    private BasicControl basicControl;
    private PM_Hierarchies hierarchies;
    private PM_Process process;
    private AccessBranch accessBranch;
    private DynamicScroll searchScrollElement;
    private SR_step1 step1;
    private SR_step2 step2;
    private SR_step3 step3;
    private SR_step4 step4;
    private SR_step5 step5;
    private SR_step8 step8;

    public PM_ServiceRequest(WebDriver driver){
        this.driver = driver;
        this.basicControl = new BasicControl(driver);
        this.hierarchies = new PM_Hierarchies(driver);
        this.process = new PM_Process(driver);
        this.searchScrollElement = new DynamicScroll(driver);
        this.accessBranch= new AccessBranch(driver);
        this.step1 = new SR_step1(driver);
        this.step2 = new SR_step2(driver);
        this.step3 = new SR_step3(driver);
        this.step4 = new SR_step4(driver);
        this.step5 = new SR_step5(driver);
        this.step8 = new SR_step8(driver);
    }

    String date = LocalDate.now().toString(); //Fecha Actual
    String nameINS =  "INS-SR" + " " + date ;
    private String nameSLA = "SLA-SR" + " " + date ;
    private String namePP = "PP-SR" + " " + date ;
    private String nameLevel = "Gestión Soporte " + date;
    private String nameProcess = "Service Request " + date;


    public void createPM_ServiceRequest() throws InterruptedException, AWTException, IOException {
        hierarchies.crearHierarchies(nameLevel);
        process.crearProceso(nameLevel,nameProcess,nameINS,nameSLA);
    }

    public void createSteps_ServiceRequest() throws IOException, InterruptedException, AWTException {
        searchProcess(nameProcess); //Service Request Selenium
        basicControl.openWizard();
        Thread.sleep(1500);
        step1.step1Process();
        Thread.sleep(1000);
        step2.step2Process();
        Thread.sleep(1000);
        step3.step3Process();
        Thread.sleep(1000);
        step4.step4Process(namePP);
        Thread.sleep(1000);
        step5.step5Process(nameSLA);
        Thread.sleep(1000);
        step8.step8Process();
    }


    private void searchProcess(String nameProcess){
        int xpos = searchScrollElement.elementSearch("Process Hierarchies");
        if (xpos != -1){
            accessBranch.clickBranches(xpos);
            xpos = searchScrollElement.elementSearch(nameLevel); //Gestión Soporte Selenium
            if(xpos != -1){
                accessBranch.clickBranches(xpos);
                xpos = searchScrollElement.elementSearch("Processes");
                if(xpos != -1){
                    accessBranch.clickBranches(xpos);
                    xpos = searchScrollElement.elementSearch(nameProcess);
                    if(xpos != -1){
                        WebElement process = driver.findElement(By.xpath("//span[text()='"+nameProcess+"']"));
                        process.click();
                        ChargePopPup.PopPupGeneral(driver,new WebDriverWait(driver, Duration.ofSeconds(100)));
                        //Ingreso al proceso
                    }else{
                        System.out.println("No hay proceso buscado");
                    }
                }else{
                    System.out.println("No hay Processes");
                }
            }else{
                System.out.println("No hay jerarquia");
            }
        }else{
            System.out.println("No hay Process Hierarchies");
        }
    }



}
