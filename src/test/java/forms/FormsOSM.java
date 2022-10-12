package forms;

import helpers.FormsControl;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class FormsOSM {

    private static List<WebElement> listForm;
    private static String save = "__xmlview4--save-img";
    private static String edit = "__xmlview4--edit-img";
    private static  String num = "4";

    private static WebDriverWait wait;

    //Company

    public static void formCreateCompany(WebDriver driver,String company) {
        FormsControl.controlNew(driver,"Empresa","");
        listForm = driver.findElements(By.className("sapMInputBaseInner"));
        listForm.get(0).sendKeys(company);
        listForm.get(1).sendKeys(company);
        listForm.get(2).sendKeys("Compañia Creada en Selenium");
        listForm.get(3).sendKeys("123456");
        driver.findElement(By.id(save)).click();
    }

    public static void formEditCompany(WebDriver driver, String company) throws InterruptedException {
        listForm = FormsControl.controlEdit(driver,"Empresa","");
        listForm.get(0).clear();
        listForm.get(0).sendKeys(company);
        listForm.get(1).clear();
        listForm.get(1).sendKeys(company);
        listForm.get(2).clear();
        listForm.get(2).sendKeys("Compañia Editada en Selenium");
        listForm.get(3).clear();
        listForm.get(3).sendKeys("123456");
        driver.findElement(By.id(save)).click();

    }

    //Organizational Unit
    public static void formCreateOrganization(WebDriver driver, String organización) throws InterruptedException{
        Thread.sleep(1000);
        listForm = driver.findElements(By.className("sapMInputBaseInner"));
        listForm.get(0).sendKeys(organización);
        listForm.get(1).sendKeys(organización);
        listForm.get(2).sendKeys("Creado por " + organización);
        driver.findElement(By.id(save)).click();
    }

    public static void formEditOrganization(WebDriver driver, String organización) throws InterruptedException{
        Thread.sleep(500);
        driver.findElement(By.id(edit)).click();
        Thread.sleep(300);
        listForm = driver.findElements(By.className("sapMInputBaseInner"));
        listForm.get(0).clear();
        listForm.get(0).sendKeys(organización);
        listForm.get(1).clear();
        listForm.get(1).sendKeys(organización);
        listForm.get(2).clear();
        listForm.get(2).sendKeys(organización);
        driver.findElement(By.id(save)).click();
    }


    // LOCATION
    public static void formCreateLocation(WebDriver driver, String location) throws InterruptedException {
        Thread.sleep(2000);
        listForm = driver.findElements(By.className("sapMInputBaseInner"));
        listForm.get(0).sendKeys(location);
        listForm.get(1).sendKeys(location);
        listForm.get(2).sendKeys("Location es" + location);
        driver.findElement(By.id(save)).click();
    }

    public static void formEditLocation(WebDriver driver, String location) throws InterruptedException {
        Thread.sleep(500);
        //Editando Formulario
        driver.findElement(By.id(edit)).click();
        Thread.sleep(500);
        listForm = driver.findElements(By.className("sapMInputBaseInner"));
        listForm.get(0).clear();
        listForm.get(0).sendKeys(location);
        listForm.get(1).clear();
        listForm.get(1).sendKeys(location);
        listForm.get(2).clear();
        listForm.get(2).sendKeys("Location es" + location);
        driver.findElement(By.id(save)).click();
    }


    //POSITION
    public static void formCreatePosition(WebDriver driver, String position) throws InterruptedException {
        Thread.sleep(500);
        //Llenando Formulario
        listForm = driver.findElements(By.className("sapMInputBaseInner"));
        listForm.get(0).sendKeys(position);
        listForm.get(1).sendKeys(position);
        listForm.get(2).sendKeys("Posicion es " + position);
        driver.findElement(By.id(save)).click();
    }

    public static void formEditPosition(WebDriver driver, String position) throws InterruptedException {
        Thread.sleep(500);
        driver.findElement(By.id(edit)).click();
        Thread.sleep(300);
        listForm = driver.findElements(By.className("sapMInputBaseInner"));
        listForm.get(0).clear();
        listForm.get(0).sendKeys(position);
        listForm.get(1).clear();
        listForm.get(1).sendKeys(position);
        listForm.get(2).clear();
        listForm.get(2).sendKeys("Posicion es " + position);
        driver.findElement(By.id(save)).click();
    }

}
