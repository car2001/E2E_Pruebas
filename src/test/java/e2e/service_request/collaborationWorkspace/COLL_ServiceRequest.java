package e2e.service_request.collaborationWorkspace;

import helpers.BasicControl;
import org.openqa.selenium.WebDriver;

public class COLL_ServiceRequest {
    private WebDriver driver;
    private BasicControl basicControl;

    public COLL_ServiceRequest(WebDriver driver){
        this.driver =  driver;
        this.basicControl = new BasicControl(driver);
    }


}
