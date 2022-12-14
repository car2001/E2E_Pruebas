package applications.configurationManager;


import forms.ConfigurationManager.FormsSLA;
import helpers.Asserts;
import helpers.BasicControl;
import helpers.FormsControl;
import home_page.Login;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

public class CM_SLA {
    private WebDriver driver;
    final String URL = "http://wedox.sytes.net/buplat_dev/";

    final String componente = "SLA Definitions";
    final String newSLA = "SLA Selenium";
    final String editSLA = "SLA Edit Selenium";


    Login login;
    Asserts asserts;
    BasicControl basicControl;
    FormsSLA formsSLA;

    public CM_SLA(WebDriver driver){
        this.driver = driver;
        this.formsSLA = new FormsSLA(driver);
        this.asserts = new Asserts(driver);
        this.basicControl = new BasicControl(driver);
        this.formsSLA = new FormsSLA(driver);
    }

    @BeforeMethod
    public void setUp() {
        basicControl.btn_More(componente);
    }

    @Parameters("SLA")
    @Test()
    public void crear_SLA(@Optional(newSLA) String SLA) throws InterruptedException {
        basicControl.btn_More(componente);
        formsSLA.formCreateSLA(driver, SLA);
        asserts.assertSave();
    }

    @Parameters({"SLA","SLA_edit"})
    @Test(priority = 2)
    public void editar_SLA(@Optional(newSLA) String SLA, @Optional(editSLA) String SLA_edit) throws InterruptedException{
        basicControl.btn_More(componente);
        driver.findElement(By.xpath("//div[text()='"+SLA+"']")).click();
        formsSLA.formEditSLA(driver,SLA_edit);
        asserts.assertSave();
    }


    @Parameters("delete_SLA")
    @Test(priority = 6)
    public void eliminar_SLA(@Optional(editSLA) String delete_SLA){
        basicControl.btn_More(componente);
        FormsControl.controlDelete(driver,delete_SLA);
        String xpathMessage = "//span[@class='sapMText sapUiSelectable sapMTextMaxWidth sapMMsgBoxText']";
        asserts.assertDelete(xpathMessage);
    }

    @AfterMethod
    public void tearDown(){
        if (driver != null){
            driver.quit();
        }
    }
}