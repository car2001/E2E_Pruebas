package applications.configurationManager;


import forms.ConfigurationManager.FormsRiskProfile;
import helpers.Asserts;
import helpers.BasicControl;
import helpers.FormsControl;
import helpers.SelectBrowser;
import home_page.Login;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

public class CM_Risk_Profile {
    private WebDriver driver;
    private Asserts asserts;
    private BasicControl basicControl;
    private FormsRiskProfile formsRiskProfile;

    private final String componente = "Risk Profiles";

    public CM_Risk_Profile(WebDriver driver){
        this.driver = driver;
        this.asserts = new Asserts(driver);
        this.basicControl = new BasicControl(driver);
        this.formsRiskProfile = new FormsRiskProfile(driver);
    }

    @Test
    public void crearRiskProfile(String riskProfile){
        basicControl.btn_More(componente);
        formsRiskProfile.formCreateRisk(riskProfile);
        asserts.assertSave();
    }

    @Test
    public void editRiskProfile(String riskProfile,String edit_RiskProfile) throws InterruptedException {
        basicControl.btn_More(componente);
        String xmlview = basicControl.getXmlview();
        driver.findElement(By.xpath("//div[@id='"+xmlview+"--listObject']//div[text()='"+riskProfile+"']")).click();
        formsRiskProfile.formEditRisk(edit_RiskProfile);
        asserts.assertSave();
    }

    @Test
    public void eliminar_RP(String delete_RP){
        basicControl.btn_More(componente);
        FormsControl.controlDelete(driver,delete_RP);
        String xpathMessage = "//span[@class='sapMText sapUiSelectable sapMTextMaxWidth sapMMsgBoxText']";
        asserts.assertDelete(xpathMessage);
    }

}