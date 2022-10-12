package forms.ConfigurationManager;

import helpers.BasicControl;
import helpers.FormsControl;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class FormsCounter {
    private  WebDriver driver;
    private  List<WebElement> listForm;
    private  BasicControl basicControl;

    public FormsCounter(WebDriver driver){
        this.driver = driver;
        basicControl = new BasicControl(driver);
    }

    public  void formCreateCounter(String Counter, String inicio , String incremento){
        basicControl.btnAdd();
        listForm = FormsControl.controlNew(driver,"Contador","Counter");
        listForm.get(0).click();
        listForm.get(0).sendKeys(Counter);
        listForm.get(1).click();
        listForm.get(1).sendKeys(Counter);
        listForm.get(2).click();
        listForm.get(2).sendKeys(Counter);
        listForm.get(3).click();
        listForm.get(3).sendKeys(inicio);
        listForm.get(4).click();
        listForm.get(4).sendKeys(incremento);
        basicControl.btnSave();
    }


}
