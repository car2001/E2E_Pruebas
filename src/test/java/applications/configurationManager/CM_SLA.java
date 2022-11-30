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
    private final String componente = "SLA Definitions";
    private Asserts asserts;
    private BasicControl basicControl;
    private FormsSLA formsSLA;

    public CM_SLA(WebDriver driver){
        this.driver = driver;
        this.formsSLA = new FormsSLA(driver);
        this.asserts = new Asserts(driver);
        this.basicControl = new BasicControl(driver);
        this.formsSLA = new FormsSLA(driver);
    }


    public void crear_SLA(String SLA) throws InterruptedException {
        basicControl.btn_More(componente);
        formsSLA.formCreateSLA(driver, SLA);
        asserts.assertSave();
    }


    public void editar_SLA(String SLA, String SLA_edit) throws InterruptedException{
        basicControl.btn_More(componente);
        String xmlview = basicControl.getXmlview();
        driver.findElement(By.xpath("//div[@id='"+xmlview+"--listObject']//div[text()='"+SLA+"']")).click();
        formsSLA.formEditSLA(driver,SLA_edit);
        asserts.assertSave();
    }


    @Test
    public void eliminar_SLA(String delete_SLA){
        basicControl.btn_More(componente);
        FormsControl.controlDelete(driver,delete_SLA);
        String xpathMessage = "//span[@class='sapMText sapUiSelectable sapMTextMaxWidth sapMMsgBoxText']";
        asserts.assertDelete(xpathMessage);
    }

}