package Forms.ProcessManager;

import Helpers.BasicControl;
import Helpers.FormsControl;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.awt.event.InputEvent;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class FormsActivityForm {
    private WebDriver driver;
    private List<WebElement> listForm;
    private BasicControl basicControl;
    private WebDriverWait wait;

    public FormsActivityForm(WebDriver driver){
        this.driver = driver;
        this.basicControl = new BasicControl(driver);
        this.wait = new WebDriverWait(driver,Duration.ofSeconds(100));
    }

    public void createNewActivityForm(String nameAf){
        listForm = FormsControl.controlNew(driver,"formulario de actividad","Activity Form");
        listForm.get(0).click();
        listForm.get(0).sendKeys(nameAf);
        listForm.get(1).click();
        listForm.get(1).sendKeys(nameAf);
        listForm.get(2).click();
        listForm.get(2).sendKeys(nameAf);
        basicControl.btnSave();
    }

    public void panelActivityForm(WebDriver driver, Actions actions , int tamModel, JavascriptExecutor js ) throws InterruptedException, AWTException {
        wait = new WebDriverWait(driver, Duration.ofSeconds(100));
        driver.findElement(By.xpath("//span[contains(@id,'--TreeDMFB-rows-row0-treeicon')]")).click();
        Thread.sleep(2000);
        WebElement to = driver.findElement(By.xpath("//div[contains(@id,'idGridBuilder') and @aria-roledescription='Lista de elementos']"));

        for(int i=tamModel; i > 0 ; i -- ){
            WebElement from = driver.findElement(By.xpath("//td[contains(@id,'--TreeDMFB-rows-row"+i+"-col')]"));
            moveBox(from,to,js);
        }
        Thread.sleep(1000);
        driver.findElement(By.id("__xmlview4--btnSaveFB-img")).click();
        WebElement popupCarga = driver.findElement(By.cssSelector("#sapUiBusyIndicator.sapUiUserSelectable"));
        wait.until(ExpectedConditions.visibilityOf(popupCarga));
        wait.until(ExpectedConditions.invisibilityOf(popupCarga));

    }

    private void moveBox(WebElement from , WebElement to,JavascriptExecutor js) throws AWTException, InterruptedException {
        List<Integer> desplazamiento = calculoPixeles(from, to, js);
        int supBrowser = calculoPantalla(js);
        int x = from.getLocation().getX();
        int y = from.getLocation().getY() + supBrowser;
        System.out.println(x + " " + y);
        System.out.println(desplazamiento.get(0));
        System.out.println(desplazamiento.get(1));
        System.out.println(supBrowser);

        Robot robot = new Robot();
        robot.mouseMove(x+3,y+3);
        robot.mousePress(InputEvent.BUTTON1_MASK);
        Thread.sleep(1000);
        robot.mouseMove(x+desplazamiento.get(0)+10,y+3);
        Thread.sleep(1000);
        robot.mouseMove(x+desplazamiento.get(0)+10,y-desplazamiento.get(1));
        Thread.sleep(1000);
        robot.mouseRelease(InputEvent.BUTTON1_MASK);
        robot.mousePress(InputEvent.BUTTON1_MASK);
        robot.mouseRelease(InputEvent.BUTTON1_MASK);
    }

    private List<Integer> calculoPixeles(WebElement from , WebElement to,JavascriptExecutor js){
        List<Integer> posiciones = new ArrayList<>();
        //Calculo Pantalla
        int aumentY = calculoPantalla(js);
        //Posicion de Panel
        int x1 = to.getLocation().getX();
        int y1 = to.getLocation().getY()+aumentY;
        //Posicion de la caja
        int x2 = from.getLocation().getX();
        int y2 = from.getLocation().getY()+aumentY;
        //Desplazamiento
        int desX = x1-x2;
        int desy = y2-y1;
        posiciones.add(desX);
        posiciones.add(desy);
        return posiciones;
    }

    private int calculoPantalla(JavascriptExecutor js){
        int fijo;
        int tamWindow = js.executeScript("return(window.innerHeight);").hashCode();
        int tamScreen = js.executeScript("return(screen.height);").hashCode();

        fijo = tamScreen -tamWindow;

        return fijo -40;
    }
}
