package applications.configurationManager;


import forms.ConfigurationManager.FormsPerformerProfile;
import helpers.Asserts;
import helpers.BasicControl;
import helpers.FormsControl;
import helpers.SelectBrowser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

public class CM_Performer_Profile {
    private WebDriver driver;
    private SelectBrowser browser;
    private Asserts asserts;
    private BasicControl basicControl;
    private FormsPerformerProfile formsPerformerProfile;

    final String componente = "Performer Profiles";


    public CM_Performer_Profile(WebDriver driver){
        this.driver = driver;
        this.browser = new SelectBrowser(driver);
        this.asserts = new Asserts(driver);
        this.basicControl = new BasicControl(driver);
        this.formsPerformerProfile = new FormsPerformerProfile(driver);
    }


    @Test
    public void crearPerformerProfile(String PP){
        basicControl.btn_More(componente);
        formsPerformerProfile.formCreatePerformer(PP);
        asserts.assertSave();
    }


    @Test
    public void editarPerformerProfile(String PP, String PP_edit ) throws InterruptedException {
        basicControl.btn_More(componente);
        String xmlview = basicControl.getXmlview();
        driver.findElement(By.xpath("//div[@id='"+xmlview+"--listObject']//div[text()='"+PP+"']")).click();
        formsPerformerProfile.formEditPerformer(PP_edit);
        asserts.assertSave();
    }


    @Test
    public void eliminarPerformerProfile(String delete_PP){
        basicControl.btn_More(componente);
        FormsControl.controlDelete(driver,delete_PP);
        String xpathMessage = "//span[@class='sapMText sapUiSelectable sapMTextMaxWidth sapMMsgBoxText']";
        asserts.assertDelete(xpathMessage);
    }

}