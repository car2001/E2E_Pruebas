package E2E.ServiceRequest;

import Applications.ConfigurationManager.*;
import Helpers.BasicControl;
import org.openqa.selenium.WebDriver;

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

    public void createCM_ServiceRequest() throws InterruptedException {
        Counter.crearCounter("Counter-SR","2","10");
        INS.crearINS("INS-SR","-","SR","Counter-SR");
        SLA.crear_SLA("SLA-SR");
        formUI.crear_FormUI("FU-SR");
        performer.crearPerformerProfile("PP-SR");
        notification.crearNotification("NP-SR");
        riskProfile.crearRiskProfile("RP-SR");
    }

    public void deleteCM_ServiceRequest(){
        INS.eliminarINS("INS-SR");
        Counter.eliminarCounter("Counter-SR");
        SLA.eliminar_SLA("SLA-SR");
        formUI.eliminar_FormUI("FU-SR");
        performer.eliminarPerformerProfile("PP-SR");
        notification.eliminarNotification("NP-SR");
        riskProfile.eliminar_RP("RP-SR");
    }

}

