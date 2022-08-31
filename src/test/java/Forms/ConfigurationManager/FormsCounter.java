package Forms.ConfigurationManager;

import Helpers.BasicControl;
import Helpers.FormsControl;
import org.openqa.selenium.By;
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
        listForm = FormsControl.controlNew(driver,"contador","Counter");
        listForm.get(2).click();
        listForm.get(2).sendKeys(Counter);
        listForm.get(3).click();
        listForm.get(3).sendKeys(Counter);
        listForm.get(4).click();
        listForm.get(4).sendKeys(Counter);
        listForm.get(6).click();
        listForm.get(6).sendKeys(inicio);
        listForm.get(7).click();
        listForm.get(7).sendKeys(incremento);
        basicControl.btnSave();
    }


}
