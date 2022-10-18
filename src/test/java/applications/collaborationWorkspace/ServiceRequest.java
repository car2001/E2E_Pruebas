package applications.collaborationWorkspace;


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

public class ServiceRequest {
    private WebDriver driver;
    private String chosen_browser = "Chrome";

    private Login login;
    private DynamicScroll searchScrollElement;
    private SelectBrowser browser = new SelectBrowser(driver);
    private Actions action;
    private JavascriptExecutor js;
    private Asserts asserts;
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
        action = new Actions(driver);
        asserts = new Asserts(driver);
        js = (JavascriptExecutor) driver;
        basicControl = new BasicControl(driver);
        searchScrollElement = new DynamicScroll(driver);
        releaseManager = new RM_ServiceRequest(driver);
        configurationManager = new CM_ServiceRequest(driver);
        processManager = new PM_ServiceRequest(driver);
        entityManager = new DEM_ServiceRequest(driver);
        recordManager = new DRM_ServiceRequest(driver);
    }



    @Test
    public void runProcessServiceRequest() throws InterruptedException, AWTException, IOException {
        //Iniciamos Sesión
        login.loginPage();
        //Ingresamos al Release Manager
        LoginApplications.loginRM(driver,"Projects");
        //Creamos los componentes del Release Manager
        //releaseManager.createRM_ServiceRequest(login.getUser());
        //Salimos a la vista de las aplicaciones
        basicControl.logo();
        //Ingresamos al Configuration Manager
        LoginApplications.loginCM(driver);
        //Creaamos componente CM
        //configurationManager.createCM_ServiceRequest();
        basicControl.logo();
        //Ingresamos al Process Manager
        LoginApplications.loginPM(driver);
        //processManager.createPM_ServiceRequest();
        basicControl.logo();
        //Ingresamos al DEMA
        LoginApplications.loginDataEntity(driver);
        //entityManager.createDEM_ServiceRequest();
        basicControl.logo();
        //Ingresamos al Data record
        LoginApplications.loginDataRecord(driver);
        //recordManager.createDRM_ServiceRequest();
        basicControl.logo();
        //Ingresamos al PM
        LoginApplications.loginPM(driver);
        processManager.createSteps_ServiceRequest();
    }

    @Test
    public void deleteunProcessServiceRequest() throws InterruptedException {
        login.loginPage();
        LoginApplications.loginRM(driver,"Change Container");
        basicControl.logo();
        LoginApplications.loginCM(driver);
        configurationManager.deleteCM_ServiceRequest();
        basicControl.logo();
        LoginApplications.loginPM(driver);
        basicControl.logo();
    }



}
