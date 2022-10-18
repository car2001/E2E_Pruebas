package forms.ProcessManager;

import helpers.BasicControl;
import helpers.ChargePopPup;
import helpers.DynamicScroll;
import helpers.SelectListItem;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.swing.plaf.DimensionUIResource;
import java.time.Duration;
import java.util.List;

public class FormsRule {
    private WebDriver driver;
    private List<WebElement> listForm;
    private BasicControl basicControl;
    private SelectListItem selectListItem;
    private Actions action;
    private DynamicScroll searchElement;
    private JavascriptExecutor js;
    private WebDriverWait wait;
    private String inputRuleName = "//input[contains(@id,'--ruleName-inner')]";
    private String inputRuleDisplayName = "//input[contains(@id,'--ruleDisplayName-inner')]";
    private String btnAddRule = "//button[(@aria-label='Agregar' or @aria-label='Add') and @class='sapMBtnBase sapMBtn sapMBarChild']";
    private String arrowOperand = "//span[(@aria-label='Mostrar ayuda para entradas' or @aria-label='Show Value Help') and contains(@id,'-vhi')]";
    private String saveRule = "//bdi[text()='Guardar' or text()='Save']";


    public FormsRule(WebDriver driver) {
        this.driver = driver;
        this.basicControl = new BasicControl(driver);
        this.selectListItem = new SelectListItem(driver);
        this.action = new Actions(driver);
        this.searchElement = new DynamicScroll(driver);
        this.js = (JavascriptExecutor) driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }


    public void createRule(String nameRule,String operand,String operator) throws InterruptedException {
        driver.findElement(By.xpath(inputRuleName)).click();
        driver.findElement(By.xpath(inputRuleName)).sendKeys(nameRule);
        driver.findElement(By.xpath(inputRuleDisplayName)).click();
        driver.findElement(By.xpath(inputRuleDisplayName)).sendKeys(nameRule);
        driver.findElement(By.xpath(btnAddRule)).click();

        List<WebElement> arrow = driver.findElements(By.cssSelector(".sapMSltArrow"));
        arrow.get(0).click();
        selectListItem.SelectItemLi("Data Model Attribute");

        driver.findElement(By.xpath(arrowOperand)).click();
        ChargePopPup.PopPupGeneral(driver,wait);
        driver.findElement(By.xpath("//span[contains(@id,'--treeDM-rows-row0') and (@title='Expandir nodos' or @title='Expand Node')]")).click();

        if(searchElement.searchAttributeDataModel(operand) != -1){
            driver.findElement(By.xpath("//span[text()='"+operand+"']")).click();
            Thread.sleep(800);

            arrow = driver.findElements(By.cssSelector(".sapMSltArrow"));
            arrow.get(1).click();
            Thread.sleep(800);
            selectListItem.SelectItemLi(operator);
            Thread.sleep(1000);
            driver.findElement(By.xpath(saveRule)).click();

            ChargePopPup.PopPupGeneral(driver,wait);
            basicControl.btnDecline();
            Thread.sleep(800);
        }

    }


    public void createRule(String nameRule,String operand,String operator,String operandType , String value ) throws InterruptedException {
        driver.findElement(By.xpath(inputRuleName)).click();
        driver.findElement(By.xpath(inputRuleName)).sendKeys(nameRule);
        driver.findElement(By.xpath(inputRuleDisplayName)).click();
        driver.findElement(By.xpath(inputRuleDisplayName)).sendKeys(nameRule);
        driver.findElement(By.xpath(btnAddRule)).click();

        List<WebElement> arrow = driver.findElements(By.cssSelector(".sapMSltArrow"));
        arrow.get(0).click();
        selectListItem.SelectItemLi("Data Model Attribute");

        driver.findElement(By.xpath(arrowOperand)).click();
        ChargePopPup.PopPupGeneral(driver,wait);
        driver.findElement(By.xpath("//span[contains(@id,'--treeDM-rows-row0') and (@title='Expandir nodos' or @title='Expand Node')]")).click();
        Thread.sleep(500);

        if(searchElement.searchAttributeDataModel(operand) != -1){
            driver.findElement(By.xpath("//span[text()='"+operand+"']")).click();
            Thread.sleep(800);

            arrow = driver.findElements(By.cssSelector(".sapMSltArrow"));
            arrow.get(1).click();
            Thread.sleep(800);
            selectListItem.SelectItemLi(operator);
            Thread.sleep(1000);

            arrow = driver.findElements(By.cssSelector(".sapMSltArrow"));
            arrow.get(2).click();
            selectListItem.SelectItemLi(operandType);

            Thread.sleep(800);
            driver.findElement(By.xpath("//div[contains(@id,'--hbFVROpeRE')]//span[@aria-label='Opciones de selección' or @aria-label='Select Options']")).click();
            selectListItem.SelectItemDiv(value);

            driver.findElement(By.xpath(saveRule)).click();
            ChargePopPup.PopPupGeneral(driver,wait);
            basicControl.btnDecline();
            Thread.sleep(1200);
        }


    }


}
