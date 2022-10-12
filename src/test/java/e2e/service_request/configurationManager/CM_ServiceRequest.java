package e2e.service_request.configurationManager;

import applications.configurationManager.*;
import helpers.BasicControl;
import org.openqa.selenium.WebDriver;

import java.time.LocalDate;

public class CM_ServiceRequest {
    private WebDriver driver;
    private BasicControl basicControl;
    private CM_Counter Counter;
    private CM_INS INS;
    private CM_SLA SLA;
    private CM_Form_UI formUI;
    private CM_Performer_Profile performer;
    private CM_Notification_Profile notification;
    private CM_Risk_Profile riskProfile;

    public CM_ServiceRequest(WebDriver driver){
        this.driver =  driver;
        this.basicControl = new BasicControl(driver);
        this.Counter = new CM_Counter(driver);
        this.INS = new CM_INS(driver);
        this.SLA = new CM_SLA(driver);
        this.formUI = new CM_Form_UI(driver);
        this.performer = new CM_Performer_Profile(driver);
        this.notification = new CM_Notification_Profile(driver);
        this.riskProfile = new CM_Risk_Profile(driver);
    }

    String date = LocalDate.now().toString(); //Fecha Actual
    // Nombres de los componentes
    String nameCounter = "Counter-SR" + " " + date ;
    String nameINS =  "INS-SR" + " " + date ;
    String nameSLA = "SLA-SR" + " " + date ;
    String nameFormUI = "FU-SR" + " " + date ;
    String namePP = "PP-SR" + " " + date ;
    String nameNP = "NP-SR" + " " + date ;
    String nameRP = "RP-SR" + " " + date ;

    public void createCM_ServiceRequest() throws InterruptedException {
        Counter.crearCounter(nameCounter,"1","10");
        INS.crearINS(nameINS,"-","SR",nameCounter);
        SLA.crear_SLA(nameSLA);
        formUI.crear_FormUI(nameFormUI);
        performer.crearPerformerProfile(namePP);
        notification.crearNotification(nameNP);
        riskProfile.crearRiskProfile(nameRP);
    }

    public void deleteCM_ServiceRequest(){
        INS.eliminarINS(nameINS);
        Counter.eliminarCounter(nameCounter);
        SLA.eliminar_SLA(nameSLA);
        formUI.eliminar_FormUI(nameFormUI);
        performer.eliminarPerformerProfile(namePP);
        notification.eliminarNotification(nameNP);
        riskProfile.eliminar_RP(nameRP);
    }

}

