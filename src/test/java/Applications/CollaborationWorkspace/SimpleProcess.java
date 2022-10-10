package Applications.CollaborationWorkspace;

import E2E.ServiceRequest.CM_ServiceRequest;
import E2E.ServiceRequest.DEM_ServiceRequest;
import E2E.ServiceRequest.DRM_ServiceRequest;
import E2E.ServiceRequest.PM_ServiceRequest;
import E2E.SimpleProcess.CM_SimpleProcess;
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

public class SimpleProcess {
    private WebDriver driver;
    private String chosen_browser = "Chrome";

    Login login;
    DynamicScroll searchScrollElement;
    SelectBrowser browser = new SelectBrowser(driver);
    Actions action;
    JavascriptExecutor js;
    Asserts asserts;
    BasicControl basicControl;
    CM_SimpleProcess configurationManager;

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
        configurationManager = new CM_SimpleProcess(driver);
    }


    @Test
    public void runProcessServiceRequest() throws InterruptedException, AWTException, IOException {
        login.loginPage();
        basicControl.logo();
        LoginApplications.loginRM(driver,"Change Container");
        basicControl.logo();
        LoginApplications.loginCM(driver);
        configurationManager.createCM_SimpleProcess();
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

    }


}