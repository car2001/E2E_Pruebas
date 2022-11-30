package e2e.simple_process.processManager.steps;

import helpers.ChargePopPup;
import helpers.FormsControl;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SP_step8 {

    private WebDriver driver;
    private WebDriverWait wait;
    private ChargePopPup chargePopPup;
    private Actions action;
    private String step8 = "//span[contains(@id,'-count') and @class='sapMITBCount' and text()='8']";

    public SP_step8(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.chargePopPup = new ChargePopPup(driver);
        this.action = new Actions(driver);
    }

    public void step8Process(){
        //Ingreso al paso 8
        driver.findElement(By.xpath(step8)).click();
        chargePopPup.PopPupDetail();
        FormsControl.controlTitle(driver,"Desplegar","Deploy");


        driver.findElement(By.xpath("//bdi[text()='Validar' or text()='Validate']")).click();
        chargePopPup.PopPupGeneral();
        WebElement verticalbar = driver.findElement(By.xpath("//div[@title='Ajustar el tamaño entre el panel 1 y el panel 2' or @title='Resize between pane 1 and pane 2']"));// este el original
        action.doubleClick(verticalbar).build().perform();
    }
}
