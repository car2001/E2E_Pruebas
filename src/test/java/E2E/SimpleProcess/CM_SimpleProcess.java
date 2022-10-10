package E2E.SimpleProcess;

import Applications.ConfigurationManager.*;
import Helpers.BasicControl;
import org.openqa.selenium.WebDriver;

public class CM_SimpleProcess {
    private WebDriver driver;
    private BasicControl basicControl;
    private CM_Counter Counter;
    private CM_INS INS;
    private CM_SLA SLA;
    private CM_Form_UI formUI;
    private CM_Performer_Profile performer;
    private CM_Notification_Profile notification;
    private CM_Risk_Profile riskProfile;

    public CM_SimpleProcess(WebDriver driver){
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

    public void createCM_SimpleProcess() throws InterruptedException {
        Counter.crearCounter("Counter Selenium","2","10");
        INS.crearINS("INS Selenium","-","SELENIUM","Counter Selenium");
        SLA.crear_SLA("SLA Selenium");
        formUI.crear_FormUI("Form UI Selenium");
        performer.crearPerformerProfile("Performer Selenium");
        notification.crearNotification("Notification Profile Selenium");
        riskProfile.crearRiskProfile("Risk Profile Selenium");
    }


}
