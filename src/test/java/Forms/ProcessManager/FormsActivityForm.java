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
import java.util.Collection;
import java.util.List;

public class FormsActivityForm {
    private WebDriver driver;
    private List<WebElement> listForm;
    private BasicControl basicControl;
    private Actions action;
    private JavascriptExecutor js;
    private WebDriverWait wait;
    private String more = "//div[contains(@id,'--itbMainFB--header-overflow-text')]";
    private String container = "//span[text()='Contenedores' or text()='Containers'][@class='sapMText sapMTextNoWrap sapMITBText sapMITBBadgeHolder']";
    private String dataModel = "//span[text()='Modelo de Datos' or text()='Data Model'][@class='sapMText sapMTextNoWrap sapMITBText sapMITBBadgeHolder']";
    private String dataModelList = "//span[contains(@id,'--TreeDMFB-rows-row0-treeicon')]";
    private String containerList = "//span[contains(@id,'--idContainerList-rows-row0-treeicon')]";


    public FormsActivityForm(WebDriver driver){
        this.driver = driver;
        this.basicControl = new BasicControl(driver);
        this.action = new Actions(driver);
        this.js = (JavascriptExecutor) driver;
        this.wait = new WebDriverWait(driver,Duration.ofSeconds(100));
    }

    public void createNewActivityForm(String nameAf){
        listForm = FormsControl.controlNew(driver,"Formulario de Actividad","New Activity Form");
        listForm.get(0).click();
        listForm.get(0).sendKeys(nameAf);
        listForm.get(1).click();
        listForm.get(1).sendKeys(nameAf);
        listForm.get(2).click();
        listForm.get(2).sendKeys(nameAf);
        basicControl.btnSave();
    }

    public void panelActivityForm(int tamModel) throws InterruptedException, AWTException {
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

    public void panelActivityForm() throws InterruptedException, AWTException {
        driver.findElement(By.xpath(dataModelList)).click(); //Hacemos click en los atributos
        Thread.sleep(500);
        clickMore("Container"); // En el popup de container
        Thread.sleep(500);
        driver.findElement(By.xpath(containerList)).click(); // Listamos los container
        Thread.sleep(500);

        ArrayList<String> titlePanels = new ArrayList<>();
        titlePanels.add("Aprobación de la Solución");
        titlePanels.add("Detalle de la Atención 3");
        titlePanels.add("Detalle de la Atención 2");
        titlePanels.add("Detalle de la Atención 1");
        titlePanels.add("Aprobación de la Solicitud");
        titlePanels.add("Información de la Solicitud de Servicio");

        for(int i = 0; i <= titlePanels.size() -1 ; i++ ){
            putPanel(titlePanels.get(i));

        }


        clickMore("Data Model"); // En el popup de Data Model
        Thread.sleep(500);


/*        from = driver.findElement(By.xpath("//td[contains(@id,'--TreeDMFB-rows-row')]//span[text()='Impact']"));
        to = driver.findElements(By.xpath("//div[@aria-roledescription='Lista de elementos'  and contains(@id,'__container')]")).get(0);
        moveBox(from,to,js);
        Thread.sleep(1000);
        String idTitlePanel = driver.findElement(By.xpath("//h2[text()='Detalle de la Atención 3'][@class='sapMPanelHdr']")).getAttribute("id");
        js.executeScript("let title = document.getElementById('"+idTitlePanel+"');title.scrollIntoView(false);");
        Thread.sleep(1000);
        from = driver.findElement(By.xpath("//td[contains(@id,'--TreeDMFB-rows-row')]//span[text()='NoUsersolicitante']"));
        moveBox(from,to,js);
        Thread.sleep(1000);
        from = driver.findElement(By.xpath("//td[contains(@id,'--TreeDMFB-rows-row')]//span[text()='NousarAsignee']"));
        moveBox(from,to,js);
        Thread.sleep(1000);
        from = driver.findElement(By.xpath("//td[contains(@id,'--TreeDMFB-rows-row')]//span[text()='RequestDatail']"));
        moveBox(from,to,js);

        Thread.sleep(1000);
        driver.findElement(By.xpath("//td[contains(@id,'--TreeDMFB-rows-row')]//span[text()='EstimatedTime']")).click();
        from = driver.findElement(By.xpath("//td[contains(@id,'--TreeDMFB-rows-row')]//span[text()='EstimatedTime']"));
        moveBox(from,to,js);*/

        //div[@aria-roledescription='Lista de elementos'  and contains(@id,'__container')]
/*        driver.findElement(By.id("__xmlview4--btnSaveFB-img")).click();
        WebElement popupCarga = driver.findElement(By.cssSelector("#sapUiBusyIndicator.sapUiUserSelectable"));
        wait.until(ExpectedConditions.visibilityOf(popupCarga));
        wait.until(ExpectedConditions.invisibilityOf(popupCarga));*/

    }

    private void clickMore(String element) throws InterruptedException {
        Thread.sleep(500);
        WebElement more = driver.findElement(By.id("" + basicControl.getXmlview() + "--itbMainFB--header-overflow"));
        action.click(more).build().perform();
        Thread.sleep(500);
        more.click();
        if(element.equals("Container")){
            WebElement btncontainer = driver.findElement(By.xpath(container));
            action.click(btncontainer).build().perform();
            Thread.sleep(1000);
        }else{
            WebElement btnDataModel = driver.findElement(By.xpath(dataModel));
            action.click(btnDataModel).build().perform();
            Thread.sleep(1000);
        }
    }

    private void putPanel(String titlePanel) throws InterruptedException, AWTException {
        WebElement from = driver.findElement(By.xpath("//td[contains(@id,'--idContainerList-rows-row')]//span[text()='Panel']"));
        WebElement to = driver.findElement(By.xpath("//div[contains(@id,'idGridBuilder') and @aria-roledescription='Lista de elementos']"));
        moveBox(from,to,js);
        driver.findElement(By.xpath("//h2[text()='Panel'][@class='sapMPanelHdr']")).click();
        Thread.sleep(1000);
        List<WebElement> listForms = basicControl.inputForms();
        listForms.get(0).clear();
        listForms.get(0).click();
        listForms.get(0).sendKeys(titlePanel);
        listForms.get(1).clear();
        listForms.get(1).click();
        listForms.get(1).sendKeys(titlePanel);
        driver.findElement(By.xpath("//span[contains(@id,'--idCloseAtt-img')]")).click();
        Thread.sleep(500);
        driver.findElement(By.xpath("//h2[text()='"+titlePanel+"'][@class='sapMPanelHdr']")).click();
        driver.findElement(By.xpath("//span[contains(@id,'--idCloseAtt-img')]")).click();
        Thread.sleep(500);
    }

    private void putAttribute() throws InterruptedException, AWTException {
        WebElement from = driver.findElement(By.xpath("//td[contains(@id,'--TreeDMFB-rows-row')]//span[text()='Impact']"));
        WebElement to = driver.findElements(By.xpath("//div[@aria-roledescription='Lista de elementos'  and contains(@id,'__container')]")).get(0);
        moveBox(from,to,js);
        Thread.sleep(1000);
        String idTitlePanel = driver.findElement(By.xpath("//h2[text()='Detalle de la Atención 3'][@class='sapMPanelHdr']")).getAttribute("id");
        js.executeScript("let title = document.getElementById('"+idTitlePanel+"');title.scrollIntoView(false);");
        Thread.sleep(1000);
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
        //robot.mousePress(InputEvent.BUTTON1_MASK);
        //robot.mouseRelease(InputEvent.BUTTON1_MASK);
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
