package Forms.ProcessManager;


import Helpers.BasicControl;
import Helpers.FormsControl;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


import java.util.List;

public class FormsHierarchie {
    private WebDriver driver;
    private List<WebElement> listForm;
    private BasicControl basicControl;

    public FormsHierarchie(WebDriver driver){
        this.driver = driver;
        this.basicControl = new BasicControl(driver);
    }
    public void createNewHierarchie(String hierarchie){
        listForm = FormsControl.controlNew(driver,"Nivel","Level");
        listForm.get(0).click();
        listForm.get(0).sendKeys(hierarchie);
        listForm.get(1).click();
        listForm.get(1).sendKeys(hierarchie);
        listForm.get(2).click();
        listForm.get(2).sendKeys(hierarchie);
        basicControl.btnSave();
    }


}
