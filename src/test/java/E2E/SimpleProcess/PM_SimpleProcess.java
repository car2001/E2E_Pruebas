package E2E.SimpleProcess;

import Applications.ProcessManager.PM_Hierarchies;
import Applications.ProcessManager.PM_Process;
import Helpers.AccessBranch;
import Helpers.BasicControl;
import Helpers.DynamicScroll;
import org.openqa.selenium.WebDriver;

import java.awt.*;
import java.io.IOException;
import java.time.LocalDate;

public class PM_SimpleProcess {

    private WebDriver driver;
    private BasicControl basicControl;
    private PM_Hierarchies hierarchies;
    private PM_Process process;
    private AccessBranch accessBranch;
    private DynamicScroll searchScrollElement;
    private SimpleProcess_Step1 step1;
    private SimpleProcess_Step2 step2;
    private SimpleProcess_Step3 step3;
    private SimpleProcess_Step4 step4;
    private SimpleProcess_Step5 step5;
    private SimpleProcess_Step8 step8;

    public PM_SimpleProcess(WebDriver driver){
        this.driver = driver;
        this.basicControl = new BasicControl(driver);
        this.hierarchies = new PM_Hierarchies(driver);
        this.process = new PM_Process(driver);
        this.accessBranch = new AccessBranch(driver);
        this.searchScrollElement = new DynamicScroll(driver);
        this.step1 = new SimpleProcess_Step1(driver);
        this.step2 = new SimpleProcess_Step2(driver);
        this.step3 = new SimpleProcess_Step3(driver);
        this.step4 = new SimpleProcess_Step4(driver);
        this.step5 = new SimpleProcess_Step5(driver);
        this.step8 = new SimpleProcess_Step8(driver);
    }

    String date = LocalDate.now().toString(); //Fecha Actual
    // Nombres de los componentes
    String nameLevel = "Jerarquia Selenium" + " " + date;
    String nameProcess = "Proceso Selenium" + " " + date;
    String nameINS = "INS Selenium" + " " + date ;
    String nameSLA = "SLA Selenium" + " " + date ;

    public void createPM_SimpleProcess() throws InterruptedException, AWTException, IOException {
        hierarchies.crearHierarchies(nameLevel);
        process.crearProceso(nameLevel,nameProcess,nameINS,nameSLA);
        basicControl.openWizard();
        step1.step1Process();
        step2.step2Process();
        step3.step3Process();
        step4.step4Process();
        step5.step5Process();
        step8.step8Process();
    }


}
