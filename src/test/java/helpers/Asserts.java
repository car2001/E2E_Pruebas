package helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class Asserts {
    private WebDriver driver ;
    private JavascriptExecutor js;
    private BasicControl basicControl;

    public Asserts(WebDriver driver) {
        this.driver = driver;
        this.js= (JavascriptExecutor) driver;
        this.basicControl = new BasicControl(driver);
    }

    public void assertSave(){
        String message = driver.findElement(By.className("sapMMsgStripMessage")).getAttribute("textContent");
        if(message.contains("The")){
            Assert.assertEquals(message, "The Operation has been Completed Successfully."+ "\n" );
        }else{
            Assert.assertEquals(message, "La operación se ha completado con éxito."+ "\n" );
        }
        basicControl.btn_MsgStrigMessage();
    }



    public void assertSaveDiagram(){
        String message = driver.findElement(By.className("sapMMsgStripMessage")).getAttribute("textContent");
        if(message.contains("The")){
            Assert.assertEquals(message,"The diagram was successfully saved");
        }else{
            Assert.assertEquals(message, "El diagrama se guardó con éxito.");
        }
        basicControl.btn_MsgStrigMessage();
    }

    public void assertSaveDP(){
        String message = driver.findElement(By.className("sapMMsgStripMessage")).getAttribute("textContent");
        Assert.assertEquals(message.substring(0,54), "El paquete de instalación ha sido creado con el nombre");
        basicControl.btn_MsgStrigMessage();
    }

    public void assertSaveDR(){
        String message = driver.findElement(By.className("sapMMsgStripMessage")).getAttribute("textContent");
        Assert.assertEquals(message.substring(0,56), "La solicitud de instalación ha sido creado con el nombre");
        basicControl.btn_MsgStrigMessage();
    }

    public void assertSaveModelData(){
        String message = driver.findElement(By.xpath("//div[contains(@id,'--messageSection')]//div[@class='sapMMsgStripMessage']//span")).getAttribute("textContent");
        if(message.contains("The")){
            Assert.assertEquals(message,"The Data Model attributes were successfully saved");
        }else{
            Assert.assertEquals(message, "Los atributos del modelo de datos se guardaron con éxito");
        }
        basicControl.btn_MsgStrigMessage();
    }

    public void assertDependecies(){
        String message = driver.findElement(By.xpath("//span[contains(@id,'--dependenciesTableTitle-inner')]")).getText();
        if(message.contains("Dependencies")){
            Assert.assertEquals(message,"Dependencies List");
        }else {
            Assert.assertEquals(message,"Lista de dependencias");
        }
    }

    public void assertDelete(String xpathMessage ){
        String message = driver.findElement(By.xpath(xpathMessage)).getText();
        if(message.contains("The")){
            Assert.assertEquals(message, "The Operation has been Completed Successfully.");
        }else{
            Assert.assertEquals(message, "La operación se ha completado con éxito.");
        }
    }

    public void assertDoubleCheck(String expected,String esperado){
        String message = driver.findElement(By.className("sapMMsgStripMessage")).getAttribute("textContent");
        String idioma = basicControl.getLanguage();
        if(idioma.equals("en")){
            Assert.assertEquals(message,expected);
        }else{
            Assert.assertEquals(message,esperado);
        }
        basicControl.btn_MsgStrigMessage();
        basicControl.btnCancel();
    }

}