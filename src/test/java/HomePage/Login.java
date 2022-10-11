package HomePage;

import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;

import java.time.Duration;



public class Login {

    private WebDriver driver;
    private String url;
    private String user;
    private String password;

    public Login(WebDriver driver) {
        this.driver = driver;
        //this.url = "https://cloud.buplat.com/IDO_SANDBOX/";
        //this.url = "http://wedox.sytes.net/buplat_rt_config/";
        this.url = "http://wedox.sytes.net/buplat_config/";
        
        this.user = "cpingo";
        this.password = "1234";
    }


    public void loginPage(){
        driver.get(url);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.findElement(By.xpath("//input[contains(@id,'--inputUserName-inner')]")).sendKeys(user);
        driver.findElement(By.xpath("//input[contains(@id,'--inputPassword-inner')]")).sendKeys(password);
        driver.findElement(By.xpath("//button[contains(@id,'--btnSubmit')]")).click();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        driver.get(getUrl()+"#/Applications");

    }

    public void loginPage(String url){
        driver.get(url);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.findElement(By.xpath("//input[contains(@id,'--inputUserName-inner')]")).sendKeys(user);
        driver.findElement(By.xpath("//input[contains(@id,'--inputPassword-inner')]")).sendKeys(password);
        driver.findElement(By.xpath("//button[contains(@id,'--btnSubmit')]")).click();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        driver.get(getUrl()+"#/Applications");
    }


    public void loginPage(String user,String password){
        driver.get(url);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.findElement(By.xpath("//input[contains(@id,'--inputUserName-inner')]")).sendKeys(user);
        driver.findElement(By.xpath("//input[contains(@id,'--inputPassword-inner')]")).sendKeys(password);
        driver.findElement(By.xpath("//button[contains(@id,'--btnSubmit')]")).click();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        driver.get(getUrl()+"#/Applications");
    }

    public String getUser() {
        return user;
    }

    public String getUrl(){return  url;}


}