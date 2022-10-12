package e2e.simple_process.releaseManager;

import applications.releaseManager.RM_ChangeContainer;
import applications.releaseManager.RM_Release;
import helpers.AccessBranch;
import helpers.BasicControl;
import helpers.DynamicScroll;
import org.openqa.selenium.WebDriver;

import java.time.LocalDate;

public class RM_SimpleProcess {

    private WebDriver driver;
    private BasicControl basicControl;
    private RM_Release release;
    private RM_ChangeContainer changeContainer;
    private DynamicScroll searchScrollElement;
    private AccessBranch accessBranch;
    private int pos;

    public RM_SimpleProcess(WebDriver driver){
        this.driver = driver;
        this.basicControl = new BasicControl(driver);
        this.release = new RM_Release(driver);
        this.changeContainer = new RM_ChangeContainer(driver);
        this.searchScrollElement = new DynamicScroll(driver);
        this.accessBranch = new AccessBranch(driver);
    }

    String date = LocalDate.now().toString(); //Fecha Actual
    //Nombres componentes
    private String nameproyecto = "Proyecto-Selenium" + " " + date ;
    private String nameRelease  = "Release-Selenium"  + " " + date;
    private String nameChangeContainer = "CC-Selenium" + " " + date;

    public void createRM_SimpleProcess(String user) throws InterruptedException {
        release.crearRelease(nameproyecto,nameRelease);
        pos = searchScrollElement.elementSearch("Projects");
        accessBranch.clickBranches(pos);
        accessBranch.clickBranches(searchScrollElement.elementSearch("Change Containers"));
        changeContainer.activarChangeContainerTabla(nameChangeContainer,nameproyecto,nameRelease,user);
    }
}
