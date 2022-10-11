package E2E.ServiceRequest;

import Helpers.BasicControl;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ServiceRequest_Step4 {
    private WebDriver driver;
    private WebDriverWait wait;
    private BasicControl basicControl;
    private JavascriptExecutor js;

    public ServiceRequest_Step4(){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.basicControl = new BasicControl(driver);
    }




}
