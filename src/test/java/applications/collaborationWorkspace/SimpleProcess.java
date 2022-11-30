package applications.collaborationWorkspace;

import e2e.simple_process.configurationManager.CM_SimpleProcess;
import e2e.simple_process.processManager.PM_SimpleProcess;
import e2e.simple_process.releaseManager.RM_SimpleProcess;
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

public class SimpleProcess {
    private WebDriver driver;
    private String chosen_browser = "Chrome";

    private Login login;
    private LoginApplications loginApplications;
    private DynamicScroll searchScrollElement;
     private  SelectBrowser browser = new SelectBrowser(driver);
    private Actions action;
    private JavascriptExecutor js;
    private Asserts asserts;
    private BasicControl basicControl;
    private RM_SimpleProcess releaseManager;
    private CM_SimpleProcess configurationManager;
    private PM_SimpleProcess processManager;


    @BeforeTest
    public void setup(){
        browser.chooseBrowser(chosen_browser);
        driver = browser.getDriver();
        login = new Login(driver);
        loginApplications = new LoginApplications(driver);
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
        loginApplications.loginRM("Projects");
        //Creamos los componentes del Release Manager
        //releaseManager.createRM_SimpleProcess(login.getUser());
        //Salimos a la vista de las aplicaciones
        basicControl.logo();
        //Ingresamos al Configuration Manager
        loginApplications.loginCM();
        //configurationManager.createCM_SimpleProcess();
        //Salimos a la vista de las aplicaciones
        basicControl.logo();
        //Ingresamos al Process Manager
        loginApplications.loginPM();
        processManager.createPM_SimpleProcess();



    }


}
