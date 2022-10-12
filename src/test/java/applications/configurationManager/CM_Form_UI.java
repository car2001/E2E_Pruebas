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

    SelectBrowser browser;
    Asserts asserts;
    BasicControl basicControl;
    FormsFormUI formsFormUI;

    final String URL = "http://wedox.sytes.net/buplat_dev/";
    final String componente = "Form UI Configuration";
    final String newFormUI = "Form UI Selenium";
    final String editFormUI = "Form UI Selenium Editado";

    public CM_Form_UI(WebDriver driver){
        this.driver = driver;
        this.browser = new SelectBrowser(driver);
        this.asserts = new Asserts(driver);
        this.basicControl = new BasicControl(driver);
        this.formsFormUI = new FormsFormUI(driver);
    }


    @BeforeMethod
    public void setup() throws InterruptedException {
        basicControl.btn_More(componente);
    }

    @Parameters("formUI")
    @Test
    public void crear_FormUI(@Optional(newFormUI) String formUI){
        basicControl.btn_More(componente);
        formsFormUI.formCreateFormUI(formUI);
        asserts.assertSave();
    }


    @Parameters({"formUI","formUI_edit"})
    @Test
    public void editar_FormUI(@Optional(newFormUI) String formUI,@Optional(editFormUI) String formUI_edit) throws InterruptedException {
        basicControl.btn_More(componente);
        driver.findElement(By.xpath("//div[text()='"+formUI+"']")).click();
        formsFormUI.formEditFormUI(formUI_edit);
        asserts.assertSave();
    }

    @Parameters("delete_FormUI")
    @Test
    public void eliminar_FormUI(@Optional(editFormUI) String delete_FormUI){
        basicControl.btn_More(componente);
        FormsControl.controlDelete(driver,delete_FormUI);
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