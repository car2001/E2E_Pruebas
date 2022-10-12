package forms.ProcessManager;

import helpers.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.awt.*;
import java.awt.event.InputEvent;
import java.time.Duration;
import java.util.*;
import java.util.List;

public class FormsActivityForm {
    private WebDriver driver;
    private List<WebElement> listForm;
    private BasicControl basicControl;
    private SelectListItem selectListItem;
    private Actions action;
    private DynamicScroll searchElement;
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
        this.selectListItem = new SelectListItem(driver);
        this.searchElement = new DynamicScroll(driver);
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
        driver.findElement(By.xpath(dataModelList)).click();
        Thread.sleep(2000);
        WebElement to = driver.findElement(By.xpath("//div[(@aria-roledescription='Lista de elementos' or @aria-roledescription = 'Item List')]"));

        for(int i=tamModel; i > 0 ; i -- ){
            WebElement from = driver.findElement(By.xpath("//td[contains(@id,'--TreeDMFB-rows-row"+i+"-col')]"));
            moveBox(from,to,js);
        }


        Thread.sleep(1000);
        basicControl.btnSave("--btnSaveFB-img");
        ChargePopPup.PopPupGeneral(driver,wait);

    }

    public void panelActivityForm() throws InterruptedException, AWTException {
        driver.findElement(By.xpath(dataModelList)).click(); //Hacemos click en los atributos
        Thread.sleep(500);
        clickMore("Container"); // En el popup de container
        Thread.sleep(500);
        driver.findElement(By.xpath(containerList)).click(); // Listamos los container
        Thread.sleep(500);

        String[] titlePanels = {"Aprobación de la Solución", "Detalle de la Atención 3", "Detalle de la Atención 2",
                "Detalle de la Atención 1", "Aprobación de la Solicitud", "Información de la Solicitud de Servicio"};


        Map<String, String> aprobacionSolucion = new LinkedHashMap<>();
        aprobacionSolucion.put("¿El Cliente Autorizó el Cierre de la Solicitud?", "RequestClosureAuthorization");
        aprobacionSolucion.put("¿Hay Observaciones?", "DecisionDescription");
        aprobacionSolucion.put("Comentario de la Observación", "Text Area");


        Map<String, String> detalleAtención3 = new LinkedHashMap<>();
        detalleAtención3.put("¿Se Requiere 3° Ejecutor?", " ");
        detalleAtención3.put("Ejecutor 3", "Full Name");
        detalleAtención3.put("Tiempo Estimado 3 (Horas)", " ");
        detalleAtención3.put("Detalle de la Atención Realizada 3", "Text Area");

        Map<String, String> detalleAtención2 = new LinkedHashMap<>();
        detalleAtención2.put("¿Se Requiere 2° Ejecutor?", " ");
        detalleAtención2.put("Ejecutor 2", "Full Name");
        detalleAtención2.put("Tiempo Estimado 2 (Horas)", " ");
        detalleAtención2.put("Detalle de la Atención Realizada 2", "Text Area");

        Map<String, String> detalleAtención1 = new LinkedHashMap<>();
        detalleAtención1.put("Asignado", "Full Name");
        detalleAtención1.put("Tiempo Estimado (Horas)", " ");
        detalleAtención1.put("Detalle de la Solución", "Text Area");

        Map<String, String> aprobacionSolicitud = new LinkedHashMap<>();
        aprobacionSolicitud.put("¿Requiere Aprobación?", "DecisionDescription");
        aprobacionSolicitud.put("Aprobador", "Full Name");
        aprobacionSolicitud.put("¿Aprueba Solicitud?", "DecisionDescription");
        aprobacionSolicitud.put("Comentario de la Aprobación", "Text Area");

        Map<String, String> informacionSolicitud = new LinkedHashMap<>();
        informacionSolicitud.put("Cliente", "NombreComercial");
        informacionSolicitud.put("Reportado por", "FullName");
        informacionSolicitud.put("Resumen de la Solicitud", " ");
        informacionSolicitud.put("Detalle de la Solicitud", "Text Area");
        informacionSolicitud.put("Referencia Externa", " ");
        informacionSolicitud.put("Prioridad", "PriorityName");
        informacionSolicitud.put("Categoría", "RequestCategoryName");
        informacionSolicitud.put("Motivo de la Solicitud", "RequestReasonName");
        informacionSolicitud.put("Posible Fecha de Entrega", " ");
        informacionSolicitud.put("Tiempo Total Estimado (horas)", " ");
        informacionSolicitud.put("Tabla Selenium", "Table");

        List<Map<String, String>> listAttribute = new ArrayList<>();

        listAttribute.add(aprobacionSolucion);
        listAttribute.add(detalleAtención3);
        listAttribute.add(detalleAtención2);
        listAttribute.add(detalleAtención1);
        listAttribute.add(aprobacionSolicitud);
        listAttribute.add(informacionSolicitud);

        for (int i = 0; i <= titlePanels.length - 1; i++) {
            //Colocar el panel en el diseñador de formularios.
            putPanel(titlePanels[i]);
            clickMore("Data Model"); // Click en el popup de Data Model
            Thread.sleep(500);

            for (Map.Entry<String, String> entry : listAttribute.get(i).entrySet()) {
                putAttribute(titlePanels[i], entry.getKey(), entry.getValue());
                Thread.sleep(800);
            }

            clickMore("Container"); // Click En el popup de container
            Thread.sleep(500);
        }

        basicControl.btnSave("--btnSaveFB-img");
        ChargePopPup.PopPupGeneral(driver,wait);
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
        WebElement to = driver.findElement(By.xpath("//div[(@aria-roledescription='Lista de elementos' or @aria-roledescription = 'Item List')]"));
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


    private void putAttribute(String titlePanel,String attribute,String typeAttribute) throws InterruptedException, AWTException {
        searchAttribute(attribute);
        WebElement from = driver.findElement(By.xpath("//td[contains(@id,'--TreeDMFB-rows-row')]//span[text()='"+attribute+"']"));
        WebElement to = driver.findElements(By.xpath("//div[(@aria-roledescription='Lista de elementos' or @aria-roledescription = 'Item List')  and contains(@id,'__container')]")).get(0);
        from.click();
        moveBox(from,to,js);
        Thread.sleep(1000);
        String idTitlePanel = driver.findElement(By.xpath("//h2[text()='"+titlePanel+"'][@class='sapMPanelHdr']")).getAttribute("id");
        js.executeScript("let title = document.getElementById('"+idTitlePanel+"');title.scrollIntoView(false);");
        Thread.sleep(1000);
        String idPanel = to.getAttribute("id");
        if(typeAttribute.equals("Table")){
            driver.findElement(By.xpath("//div[@id = '"+idPanel+"']//span[text()='"+attribute+"']")).click(); // Hacemos click en el atributo en el diseñador de formulario
        }else{
            driver.findElement(By.xpath("//div[@id = '"+idPanel+"']//bdi[text()='"+attribute+"']")).click(); // Hacemos click en el atributo en el diseñador de formulario
        }
        Thread.sleep(800);
        switch (typeAttribute) {
            case " " :
                break;
            case "Text Area" :
                driver.findElement(By.xpath("//span[contains(@id,'Control-internalBtn-img')]")).click();
                driver.findElement(By.xpath("//div[@class='sapUiMnuItmTxt' and text()='Text Area']")).click();
                break;
            case "Table" :
                //Primera columna
                driver.findElement(By.xpath("//span[text()='"+attribute+"' and contains(@id,'__title')]/parent::div/parent::div/button[@title='Agregar' or @title='Add']")).click();
                driver.findElement(By.xpath("//span[@class='sapMLabelTextWrapper']//bdi[text()='column']")).click();
                List<WebElement> inputs = basicControl.inputForms();
                inputs.get(0).click();
                inputs.get(0).clear();
                inputs.get(0).sendKeys("ID");
                inputs.get(1).click();
                inputs.get(1).clear();
                inputs.get(1).sendKeys("ID");
                driver.findElement(By.xpath("//span[contains(@id,'--dataPropertyFormBuilder-vhi')]")).click();
                Thread.sleep(1500);
                driver.findElement(By.xpath("//span[contains(@id,'--treeDM-rows-row0-treeicon')]")).click();
                driver.findElement(By.xpath("//span[text()='ID : String']")).click();
                Thread.sleep(1500);
                //Segunda Columna
                driver.findElement(By.xpath("//span[text()='"+attribute+"' and contains(@id,'__title')]/parent::div/parent::div/button[@title='Agregar' or @title='Add']")).click();
                driver.findElement(By.xpath("//span[@class='sapMLabelTextWrapper']//bdi[text()='column']")).click();
                inputs = basicControl.inputForms();
                inputs.get(0).click();
                inputs.get(0).clear();
                inputs.get(0).sendKeys("Descripción");
                inputs.get(1).click();
                inputs.get(1).clear();
                inputs.get(1).sendKeys("Descripción");
                driver.findElement(By.xpath("//span[contains(@id,'--dataPropertyFormBuilder-vhi')]")).click();
                Thread.sleep(1500);
                driver.findElement(By.xpath("//span[contains(@id,'--treeDM-rows-row0-treeicon')]")).click();
                driver.findElement(By.xpath("//span[text()='Descripción : String']")).click();
                Thread.sleep(1500);
                break;

            default:
                driver.findElement(By.xpath("//span[contains(@id,'fieldDataPropertyFormBuilder-arrow')]")).click();
                selectListItem.SelectItemLi(typeAttribute);
                break;

        }
        js.executeScript("let title = document.getElementById('"+idTitlePanel+"');title.scrollIntoView(false);");
        Thread.sleep(1000);
    }

    private void searchAttribute(String attribute){
        int pos = searchElement.searchAttribute(attribute);
        System.out.println(pos);
        if(pos != -1){
            WebElement atributo = driver.findElement(By.xpath("//td[contains(@id,'--TreeDMFB-rows-row')]//span[text()='"+attribute+"']"));
            atributo.click();
        }else{
            Assert.assertEquals("No hay atributo","Si hay atributos");
        }
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