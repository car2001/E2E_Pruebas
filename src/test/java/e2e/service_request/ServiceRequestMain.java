package e2e.service_request;

import e2e.service_request.configurationManager.CM_ServiceRequest;
import e2e.service_request.entityManager.DEM_ServiceRequest;
import e2e.service_request.proccesManager.PM_ServiceRequest;
import e2e.service_request.recordManager.DRM_ServiceRequest;
import e2e.service_request.releaseManager.RM_ServiceRequest;
import helpers.Asserts;
import helpers.BasicControl;
import helpers.DynamicScroll;
import helpers.SelectBrowser;
import home_page.Login;
import home_page.LoginApplications;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.IOException;

public class ServiceRequestMain {

    private WebDriver driver;
    private String chosen_browser = "Chrome";
    private Login login;
    private LoginApplications loginApplications;
    private SelectBrowser browser = new SelectBrowser(driver);
    private BasicControl basicControl;
    private RM_ServiceRequest releaseManager;
    private CM_ServiceRequest configurationManager;
    private PM_ServiceRequest processManager;
    private DEM_ServiceRequest entityManager;
    private DRM_ServiceRequest recordManager;


    @BeforeTest
    public void setup(){
        browser.chooseBrowser(chosen_browser);
        driver = browser.getDriver();
        login = new Login(driver);
        basicControl = new BasicControl(driver);
        releaseManager = new RM_ServiceRequest(driver);
        configurationManager = new CM_ServiceRequest(driver);
        processManager = new PM_ServiceRequest(driver);
        entityManager = new DEM_ServiceRequest(driver);
        recordManager = new DRM_ServiceRequest(driver);
        loginApplications = new LoginApplications(driver);
    }



    @Test
    public void runProcessServiceRequest() throws InterruptedException, AWTException, IOException {
        //Iniciamos Sesión
        login.loginPage();
        //Ingresamos al Release Manager
        loginApplications.loginRM("Projects");
        //Creamos los componentes del Release Manager
        releaseManager.createRM_ServiceRequest(login.getUser());
        //Salimos a la vista de las aplicaciones
        basicControl.logo();
        //Ingresamos al Configuration Manager
        loginApplications.loginCM();
        //Creaamos componente CM
        configurationManager.createCM_ServiceRequest();
        basicControl.logo();
        //Ingresamos al Process Manager
        loginApplications.loginPM();
        processManager.createPM_ServiceRequest();
        basicControl.logo();
        //Ingresamos al DEMA
        loginApplications.loginDataEntity();
        entityManager.createDEM_ServiceRequest();
        basicControl.logo();
        //Ingresamos al Data record
        loginApplications.loginDataRecord();
        recordManager.createDRM_ServiceRequest();
        basicControl.logo();
        //Ingresamos al PM
        loginApplications.loginPM();
        processManager.createSteps_ServiceRequest();
    }


}
