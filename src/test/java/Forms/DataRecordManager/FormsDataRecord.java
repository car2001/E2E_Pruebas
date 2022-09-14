package Forms.DataRecordManager;

import Forms.DataEntityManager.FormsDataEntity;
import Helpers.Asserts;
import Helpers.BasicControl;
import Helpers.FormsControl;
import Helpers.SelectListItem;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class FormsDataRecord {
    private WebDriver driver;
    private List<WebElement> listForm;
    private BasicControl basicControl;
    private Asserts asserts;
    private SelectListItem selectListItem;
    private WebDriverWait wait;

    private String newDataRecord = "//button[@title='Nuevo Registro de Datos' or @title='New Data Record']";

    public FormsDataRecord(WebDriver driver){
        this.driver = driver;
        this.basicControl = new BasicControl(driver);
        this.selectListItem = new SelectListItem(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(100));
        this.asserts = new Asserts(driver);
    }

    public void createDataRecord(String [][] data) throws InterruptedException {
        int filas = data.length;
        int col = data[0].length;

        for(int i =0 ; i < filas; i++){
            WebElement btnNewRecord = driver.findElement(By.xpath(newDataRecord));
            wait.until(ExpectedConditions.visibilityOf(btnNewRecord));
            driver.findElement(By.xpath(newDataRecord)).click();
            listForm = FormsControl.controlNew(driver,"Registro de Datos","New Data Record");
            WebElement btnSave = driver.findElement(By.xpath("//span[contains(@id,'--save-img')]"));
            wait.until(ExpectedConditions.visibilityOf(btnSave));
            for (int j =0 ; j < col; j++){
                listForm.get(j).click();
                listForm.get(j).sendKeys(data[i][j]);
            }
            basicControl.btnSave();
            Thread.sleep(1000);
            asserts.assertSave();
        }

    }

}
