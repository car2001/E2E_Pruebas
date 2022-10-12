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
    }

    private String namePP = "PP-SR";
    private String nameSLA = "SLA-SR";


    public void createPM_ServiceRequest() throws InterruptedException, AWTException, IOException {
        hierarchies.crearHierarchies("Gestión Soporte Selenium");
        process.crearProceso("Gestión Soporte Selenium","Service Request Selenium","INS-SR","SLA-SR");
    }

    public void createSteps_ServiceRequest() throws IOException, InterruptedException, AWTException {
        searchProcess("Service Request Selenium");
        basicControl.openWizard();
        Thread.sleep(5000);
        //step1.step1Process();
        //step2.step2Process();
        //step3.step3Process();
        //step4.step4Process(namePP);
        step5.step5Process(nameSLA);
    }


    private void searchProcess(String nameProcess){
        int xpos = searchScrollElement.elementSearch("Process Hierarchies");
        if (xpos != -1){
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            accessBranch.clickBranches(xpos);
            xpos = searchScrollElement.elementSearch("Gestión Soporte Selenium");
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
