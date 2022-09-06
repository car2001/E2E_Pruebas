package Forms.DataRecordManager;

import Forms.DataEntityManager.FormsDataEntity;
import Helpers.Asserts;
import Helpers.BasicControl;
import Helpers.FormsControl;
import Helpers.SelectListItem;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class FormsDataRecord {
    private WebDriver driver;
    private List<WebElement> listForm;
    private BasicControl basicControl;
    private Asserts asserts;
    private SelectListItem selectListItem;

    private String newDataRecord = "//button[@title='Nuevo registro de datos' or @title='New Data Record']";

    public FormsDataRecord(WebDriver driver){
        this.driver = driver;
        this.basicControl = new BasicControl(driver);
        this.selectListItem = new SelectListItem(driver);
        this.asserts = new Asserts(driver);
    }

    public void createDataRecord(String [][] data){
        //driver.findElement(By.xpath(newDataRecord)).click();
        int filas = data.length;
        int col = data[0].length;
        for(int i =0 ; i < filas; i++){
            driver.findElement(By.xpath(newDataRecord)).click();
            listForm = FormsControl.controlNew(driver,"Nuevo registro de datos","New Data Record");
            for (int j =0 ; j < col; j++){
                listForm.get(j).click();
                listForm.get(j).sendKeys(data[i][j]);
            }
            basicControl.btnSave();
            asserts.assertSave();
        }

    }

}
