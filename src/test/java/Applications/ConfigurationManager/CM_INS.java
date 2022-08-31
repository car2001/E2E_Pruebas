package Applications.ConfigurationManager;


import Forms.ConfigurationManager.FormsINS;
import Helpers.Asserts;
import Helpers.BasicControl;
import Helpers.FormsControl;
import Helpers.SelectBrowser;
import HomePage.Login;
import HomePage.LoginApplications;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

public class CM_INS {

    private WebDriver driver;

    SelectBrowser browser;
    Asserts asserts;
    BasicControl basicControl;
    FormsINS formsINS;

    final String componente = "Instance Numbering Schema";
    final String newINS = "INS Selenium";
    final String separator = "-";
    final String fixedValue = "SELENIUM";
    final String Counter = "Counter Selenium";

    public CM_INS(WebDriver driver){
        this.driver = driver;
        this.asserts = new Asserts(driver);
        this.basicControl = new BasicControl(driver);
        this.formsINS = new FormsINS(driver);
    }



    @Parameters({"INS","separador","valorFijo","counter"})
    @Test
    public void crearINS(@Optional(newINS) String INS, @Optional(separator) String separador , @Optional(fixedValue) String valorFijo , @Optional(Counter) String counter){
        basicControl.btn_More(componente);
        formsINS.formCreateINS(INS,separador,valorFijo,counter);
        asserts.assertSave();
    }

    @Parameters("INS")
    @Test
    public void eliminarINS(@Optional(newINS) String INS){
        basicControl.btn_More(componente);
        FormsControl.controlDelete(driver,INS);
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
