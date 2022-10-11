package Applications.CollaborationWorkspace;


import E2E.ServiceRequest.*;
import Helpers.Asserts;
import Helpers.BasicControl;
import Helpers.DynamicScroll;
import Helpers.SelectBrowser;
import HomePage.Login;
import HomePage.LoginApplications;
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

    Login login;
    DynamicScroll searchScrollElement;
    SelectBrowser browser = new SelectBrowser(driver);
    Actions action;
    JavascriptExecutor js;
    Asserts asserts;
    BasicControl basicControl;
    RM_ServiceRequest releaseManager;
    CM_ServiceRequest configurationManager;
    PM_ServiceRequest processManager;
    DEM_ServiceRequest entityManager;
    DRM_ServiceRequest recordManager;


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
        //configurationManager.createCM_ServiceRequest();
        basicControl.logo();
        LoginApplications.loginPM(driver);
        //processManager.createPM_ServiceRequest();
        basicControl.logo();
        LoginApplications.loginDataEntity(driver);
        //entityManager.createDEM_ServiceRequest();
        basicControl.logo();
        LoginApplications.loginDataRecord(driver);
        //recordManager.createDRM_ServiceRequest();
        basicControl.logo();
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
