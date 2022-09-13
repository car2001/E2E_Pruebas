package Helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class ElementSVG {

    private WebDriver driver;
    private JavascriptExecutor js;
    private Actions action;
    private BasicControl basicControl;

    public ElementSVG(WebDriver driver){
        this.driver = driver;
        this.js = (JavascriptExecutor) driver;
        this.basicControl = new BasicControl(driver);
        this.action = new Actions(driver);
    }

    public  void clickSVGElementCenter(String cssSelectorSVG) throws InterruptedException {
        String xmlview = basicControl.getXmlview();
        js.executeScript("let g = document.querySelector('"+cssSelectorSVG+"'); g.scrollIntoView();");
        WebElement task =  driver.findElement(By.cssSelector(cssSelectorSVG));
        action.moveToElement(task,0,0).click().build().perform();
        Thread.sleep(1000);
        js.executeScript("let y = document.getElementById('"+xmlview+"--detail-cont');y.scroll(0,0)");
    }

    public  void clickSVGElementRule(String cssSelectorSVG,int x,int y) throws InterruptedException {
        String xmlview = basicControl.getXmlview();
        js.executeScript("let g = document.querySelector('"+cssSelectorSVG+"'); g.scrollIntoView();");
        WebElement task =  driver.findElement(By.cssSelector(cssSelectorSVG));
        action.moveToElement(task,x,y).click().build().perform();
        Thread.sleep(1000);
        js.executeScript("let y = document.getElementById('"+xmlview+"--detail-cont');y.scroll(0,0)");
    }


}
