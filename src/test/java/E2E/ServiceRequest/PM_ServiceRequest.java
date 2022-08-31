package E2E.ServiceRequest;

import Applications.ProcessManager.PM_Hierarchies;
import org.openqa.selenium.WebDriver;

public class PM_ServiceRequest {
    private WebDriver driver;
    PM_Hierarchies hierarchies;

    public PM_ServiceRequest(WebDriver driver){
        this.driver = driver;
        this.hierarchies = new PM_Hierarchies(driver);
    }

    public void createPM_ServiceRequest() throws InterruptedException {
        hierarchies.crearHierarchies("Gestión Soporte");
    }

}
