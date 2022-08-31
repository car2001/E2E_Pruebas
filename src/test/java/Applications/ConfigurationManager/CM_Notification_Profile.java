package Applications.ConfigurationManager;


import Forms.ConfigurationManager.FormsNotificationProfile;
import Helpers.Asserts;
import Helpers.BasicControl;
import Helpers.FormsControl;
import Helpers.SelectBrowser;
import HomePage.Login;
import HomePage.LoginApplications;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;



public class CM_Notification_Profile {
    private WebDriver driver;

    SelectBrowser browser = new SelectBrowser(driver);
    Asserts asserts;
    BasicControl basicControl;
    FormsNotificationProfile formsNotificationProfile;

    final String componente = "Notification Profile";
    final String newNotification = "Notification Profile Selenium";
    final String editNotification = "Notification Profile Selenium Editado";

    public CM_Notification_Profile(WebDriver driver){
        this.driver = driver;
        this.browser = new SelectBrowser(driver);
        this.asserts = new Asserts(driver);
        this.basicControl  = new BasicControl(driver);
        this.formsNotificationProfile = new FormsNotificationProfile(driver);

    }

    @BeforeMethod
    public void setUp(){
        basicControl.btn_More(componente);
    }

    @Parameters("notificationProfile")
    @Test
    public void crearNotification(@Optional(newNotification)String notificationProfile){
        basicControl.btn_More(componente);
        formsNotificationProfile.formCreateNotification(driver,notificationProfile);
        asserts.assertSave();
    }


    @Parameters({"notificationProfile","editNotificationProfile"})
    @Test
    public void editarNotification(@Optional(newNotification)String notificationProfile,@Optional(editNotification) String editNotificationProfile) throws InterruptedException {
        basicControl.btn_More(componente);
        driver.findElement(By.xpath("//div[text()='"+notificationProfile+"']")).click();
        formsNotificationProfile.formEditNotification(driver,editNotificationProfile);
        asserts.assertSave();
    }


    @Parameters("delete_NP")
    @Test
    public void eliminarNotification(@Optional(editNotification)String delete_NP){
        basicControl.btn_More(componente);
        FormsControl.controlDelete(driver,delete_NP);
        String xpathMessage = "//span[@class='sapMText sapUiSelectable sapMTextMaxWidth sapMMsgBoxText']";
        asserts.assertDelete(xpathMessage);
    }

    @AfterMethod
    public void tearDown(){
        if(driver != null){
            driver.quit();
        }
    }
}