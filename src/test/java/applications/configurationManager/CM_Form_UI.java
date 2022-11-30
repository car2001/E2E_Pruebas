package applications.configurationManager;


import forms.ConfigurationManager.FormsFormUI;
import helpers.Asserts;
import helpers.BasicControl;
import helpers.FormsControl;
import helpers.SelectBrowser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;


public class CM_Form_UI {
    private WebDriver driver;

    private SelectBrowser browser;
    private Asserts asserts;
    private BasicControl basicControl;
    private FormsFormUI formsFormUI;
    private final String componente = "Form UI Configuration";

    public CM_Form_UI(WebDriver driver){
        this.driver = driver;
        this.browser = new SelectBrowser(driver);
        this.asserts = new Asserts(driver);
        this.basicControl = new BasicControl(driver);
        this.formsFormUI = new FormsFormUI(driver);

    }


    public void crear_FormUI(String formUI){
        basicControl.btn_More(componente);
        formsFormUI.formCreateFormUI(formUI);
        asserts.assertSave();
    }


    public void editar_FormUI(String formUI,String formUI_edit) throws InterruptedException {
        basicControl.btn_More(componente);
        String xmlview = basicControl.getXmlview();
        driver.findElement(By.xpath("//div[@id='"+xmlview+"--listObject']//div[text()='"+formUI+"']")).click();
        formsFormUI.formEditFormUI(formUI_edit);
        asserts.assertSave();
    }


    public void eliminar_FormUI(String delete_FormUI){
        basicControl.btn_More(componente);
        FormsControl.controlDelete(driver,delete_FormUI);
        String xpathMessage = "//span[@class='sapMText sapUiSelectable sapMTextMaxWidth sapMMsgBoxText']";
        asserts.assertDelete(xpathMessage);
    }

}