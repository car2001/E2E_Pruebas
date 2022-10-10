package Applications.ConfigurationManager;

import Forms.ConfigurationManager.FormsCounter;
import Helpers.Asserts;
import Helpers.BasicControl;
import Helpers.FormsControl;
import Helpers.SelectBrowser;
import HomePage.Login;
import HomePage.LoginApplications;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

public class CM_Counter {

    private WebDriver driver;
    final String URL = "http://wedox.sytes.net/buplat_config/";

    Asserts asserts;
    BasicControl basicControl;
    FormsCounter formsCounter;

    final String componente = "Counters";
    final String newCounter = "Counter Selenium";
    final String start = "100";
    final String increment = "1";

    public CM_Counter(WebDriver driver){
        this.driver = driver;
        this.asserts = new Asserts(driver);
        this.basicControl = new BasicControl(driver);
        this.formsCounter = new FormsCounter(driver);
    }

    @BeforeMethod
    public void setup() throws InterruptedException {
        basicControl.btn_More(componente);
    }

    @Parameters({"counter","inicio","incremento"})
    @Test
    public void crearCounter (@Optional(newCounter) String counter, @Optional(start) String inicio, @Optional(increment) String aumento){
        basicControl.btn_More(componente);
        formsCounter.formCreateCounter(counter,inicio,aumento);
        asserts.assertSave();
    }

    @Parameters("delete_counter")
    @Test
    public void eliminarCounter(@Optional(newCounter) String delete_counter){
        basicControl.btn_More(componente);
        FormsControl.controlDelete(driver,delete_counter);
        String xpathMessage = "//span[@class='sapMText sapUiSelectable sapMTextMaxWidth sapMMsgBoxText']";
        asserts.assertDelete(xpathMessage);
    }

}
