package Applications.CollaborationWorkspace;

import E2E.ServiceRequest.*;
import E2E.SimpleProcess.CM_SimpleProcess;
import E2E.SimpleProcess.PM_SimpleProcess;
import E2E.SimpleProcess.RM_SimpleProcess;
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
    RM_SimpleProcess releaseManager;
    CM_SimpleProcess configurationManager;
    PM_SimpleProcess processManager;


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
        releaseManager = new RM_SimpleProcess(driver);
        configurationManager = new CM_SimpleProcess(driver);
        processManager = new PM_SimpleProcess(driver);
    }


    @Test
    public void runProcessSimpleProcess() throws InterruptedException, AWTException, IOException {
        //Iniciamos Sesión
        login.loginPage();
        //Ingresamos al Release Manager
        LoginApplications.loginRM(driver,"Projects");
        //Creamos los componentes del Release Manager
        //releaseManager.createRM_SimpleProcess(login.getUser());
        //Salimos a la vista de las aplicaciones
        basicControl.logo();
        //Ingresamos al Configuration Manager
        LoginApplications.loginCM(driver);
        //configurationManager.createCM_SimpleProcess();
        //Salimos a la vista de las aplicaciones
        basicControl.logo();
        //Ingresamos al Process Manager
        LoginApplications.loginPM(driver);
        processManager.createPM_SimpleProcess();



    }


}
