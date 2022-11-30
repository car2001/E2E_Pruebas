package helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ChargePopPup {

    private WebDriver driver;
    private WebDriverWait wait;

    public ChargePopPup(WebDriver driver){
        this.driver =driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(100));
    }

    public void PopPupGeneral() {
        try {
            WebElement popupCarga = driver.findElement(By.xpath("//div[@id='sapUiBusyIndicator' and @class='sapUiUserSelectable']"));
            wait.until(ExpectedConditions.visibilityOf(popupCarga));
            wait.until(ExpectedConditions.invisibilityOf(popupCarga));
            Thread.sleep(900);
            String carga = driver.findElement(By.id("sap-ui-blocklayer-popup")).getAttribute("style");
            while(carga.contains("visible;")){
                carga = driver.findElement(By.id("sap-ui-blocklayer-popup")).getAttribute("style");
            }
            Thread.sleep(1000);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    public void PopPupMain(){

        try{
            WebElement popupCarga = driver.findElement(By.xpath("//div[@class='sapUiBlockLayer  sapUiLocalBusyIndicator sapUiLocalBusyIndicatorSizeMedium sapUiLocalBusyIndicatorFade' and contains(@id,'--resSplitMain-busyIndicator')]"));
            wait.until(ExpectedConditions.visibilityOf(popupCarga));
            wait.until(ExpectedConditions.invisibilityOf(popupCarga));
            Thread.sleep(1000);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }

    }

    public void PopPupDetail(){
        try{
            WebElement popupCarga = driver.findElement(By.xpath("//div[contains(@id,'--detail-busyIndicator')]"));
            wait.until(ExpectedConditions.visibilityOf(popupCarga));
            wait.until(ExpectedConditions.invisibilityOf(popupCarga));
            Thread.sleep(1000);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public  void PopPupTree(){
        try{
            WebElement popupCarga = driver.findElement(By.xpath("//div[contains(@id,'--mainTree-busyIndicator')]"));
            wait.until(ExpectedConditions.visibilityOf(popupCarga));
            wait.until(ExpectedConditions.invisibilityOf(popupCarga));
            Thread.sleep(1000);
        }catch (Exception e){

        }
    }


}
