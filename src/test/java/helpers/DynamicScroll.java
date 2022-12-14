package helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class DynamicScroll {
    private WebDriver driver;
    private JavascriptExecutor js ;
    private BasicControl basicControl;

    public DynamicScroll(WebDriver driver){
        this.driver = driver;
        this.js= (JavascriptExecutor) driver;
        this.basicControl = new BasicControl(driver);
    }

    public int elementSearch(String element) {
        int positionFound = -1;
        int existScroll;
        String xpathCompany = "//span[@class='sapMText sapUiSelectable sapMTextMaxWidth {Tree>class}' or @class='sapMText sapUiSelectable sapMTextBreakWord sapMTextMaxWidth {Tree>class}']";

        //Obtenemos la lista de Objetos
        List<WebElement>  elementTable = driver.findElements(By.xpath(xpathCompany));
        while(elementTable.contains("Loading...") ){
            elementTable = driver.findElements(By.xpath(xpathCompany));
        }

        //Creamos nuevo arreglo
        List<String> nameElement = new ArrayList<>();
        //Pasamos los nombres de los Elementos al nuevo array
        for(int i = 0; i<=elementTable.size()-1;i=i+1){
            if(elementTable.get(i).getText().equals("Loading...") == false){
                nameElement.add(elementTable.get(i).getText());
            }
        }

        existScroll = basicControl.displayScrollTree();


        if( existScroll != 0 ){
            try {
                String xmlview = basicControl.getXmlview();
                WebElement scrollBar = driver.findElement(By.id(xmlview+"--mainTree-vsb"));
                int scrollHeight,clientHeight;

                scrollHeight = js.executeScript("let int = arguments[0].scrollHeight; return(int)",scrollBar).hashCode();
                clientHeight = js.executeScript("let int = arguments[0].clientHeight; return(int)",scrollBar).hashCode();

                int numVeces,iterator;

                numVeces = scrollHeight/clientHeight; // Numero de veces para repetir el bucle
                iterator = -1;
                // Verificamos
                while (iterator<=numVeces+1){
                    if(nameElement.lastIndexOf(element) != -1){
                        positionFound = nameElement.lastIndexOf(element);
                        break;
                    }else{
                        iterator = iterator+1;
                        int multiplo = clientHeight*iterator ;
                        js.executeScript("arguments[0].scroll(0,'"+multiplo+"')",scrollBar);
                        elementTable = driver.findElements(By.xpath(xpathCompany));
                        nameElement.clear();
                        for(int i = 0; i<=elementTable.size()-1;i=i+1){
                            if(elementTable.get(i).getText().equals("Loading...") == false){
                                nameElement.add(elementTable.get(i).getText());
                            }
                        }
                    }
                }

            }catch (ArithmeticException e){
                System.out.println(e);
            }

        }else{
            if(nameElement.lastIndexOf(element)!= -1){
                positionFound = nameElement.lastIndexOf(element);
            }else {
                positionFound = -1;
            }
        }
        return positionFound;
    }

    public  String searchElementTable(String project,String state ,String release,String nameCC){
        WebElement scrollTable;
        Boolean displayedScroll = false;
        String xpos = "";

        List<WebElement> webElementList = driver.findElements(By.xpath("//tr[contains(@id,'--tblComponentList-rows-row')]"));
        List<String> textContentList = new ArrayList<>();

        //Pasamos los nombres de los Elementos al nuevo array
        for(int i = 0; i<= webElementList.size()-1;i=i+1){
            String textContent = js.executeScript("let text = arguments[0].textContent; return(text)",webElementList.get(i)).toString();
            textContentList.add(textContent);
        }



        try{
            scrollTable = driver.findElement(By.xpath("//div[contains(@id,'--tblComponentList-vsb')]"));
            displayedScroll = scrollTable.isDisplayed();
        }catch (Exception e){
            System.out.println("No se encontro scroll");
        }

        if(displayedScroll == true){
            scrollTable = driver.findElement(By.xpath("//div[contains(@id,'--tblComponentList-vsb')]"));
            int scrollHeight= js.executeScript("let scrollHeight = arguments[0].scrollHeight; return(scrollHeight)",scrollTable).hashCode();
            int clientHeight = js.executeScript("let clientHeight = arguments[0].clientHeight; return(clientHeight)",scrollTable).hashCode();

            int numVeces = scrollHeight/clientHeight;
            int iterator = 0;


            while (iterator<=numVeces+1){
                xpos = searchString(textContentList,project,state,release,nameCC);
                if(xpos != " "){
                    break;
                }else{
                    iterator = iterator+1;
                    js.executeScript("arguments[0].scroll(0,'"+clientHeight+"')",scrollTable);
                    webElementList = driver.findElements(By.xpath("//tr[contains(@id,'--tblComponentList-rows-row')]"));
                    textContentList.clear();
                    for(int i = 0; i<= webElementList.size()-1;i=i+1){
                        String textContent = js.executeScript("let text = arguments[0].textContent; return(text)",webElementList.get(i)).toString();
                        textContentList.add(textContent);
                    }
                }
            }
        } else{
            xpos = searchString(textContentList,project,state,release,nameCC);
        }

        return xpos;

    }

    public  String searchString (List<String> contentList,String project,String state ,String release,String nameCC){
        String xpos = " ";
        String content = " ";
        for(int i = 0; i<= contentList.size()-1;i=i+1){
            content = contentList.get(i);

            if(content.contains(state) && content.contains(project) && content.contains(release)){
                xpos = content.substring(0,content.indexOf(nameCC));
                break;
            }else{
                xpos = " ";
            }
        }
        return  xpos;
    }


    public int searchAttribute(String attributte){
        int xpos = -1;
        WebElement scroll;
        Boolean displayedScroll = false;
        List<WebElement> listElementAttribute = driver.findElements(By.xpath("//tr[contains(@id,'--TreeDMFB-rows-row')]"));
        List<String> listTextAttribute = new ArrayList<>();

        for (int i =0; i <= listElementAttribute.size()-1; i++){
            listTextAttribute.add(listElementAttribute.get(i).getText());
        }

        try{
            scroll = driver.findElement(By.xpath("//div[@class='sapUiTableVSb' and contains(@id,'--TreeDMFB-vsb')]"));
            displayedScroll = scroll.isDisplayed();
        }catch (Exception e){
            System.out.println("No se encontro scroll");
        }


        if(displayedScroll == true){
            scroll = driver.findElement(By.xpath("//div[@class='sapUiTableVSb' and contains(@id,'--TreeDMFB-vsb')]"));
            int scrollHeight= js.executeScript("let scrollHeight = arguments[0].scrollHeight; return(scrollHeight)",scroll).hashCode();
            int clientHeight = js.executeScript("let clientHeight = arguments[0].clientHeight; return(clientHeight)",scroll).hashCode();

            int numVeces = scrollHeight/clientHeight;
            int iterator = -1;

            while (iterator<=numVeces+1){

                if(listTextAttribute.contains(attributte)){
                    xpos = listTextAttribute.lastIndexOf(attributte);
                    listTextAttribute.clear();
                    break;
                }
                else{
                    iterator = iterator+1;
                    int multiplo = clientHeight*iterator ;
                    js.executeScript("arguments[0].scroll(0,'"+multiplo+"')",scroll);
                    listElementAttribute = driver.findElements(By.xpath("//tr[contains(@id,'--TreeDMFB-rows-row')]"));
                    listTextAttribute.clear();
                    for(int i = 0; i<= listElementAttribute.size()-1;i=i+1){
                        listTextAttribute.add(listElementAttribute.get(i).getText());
                    }
                }
            }

        }else{
            xpos = listTextAttribute.lastIndexOf(attributte);

        }

        return  xpos;
    }


    public int searchAttributeDataModel(String attribute){
        int xpos = -1;
        int existScroll;
        WebElement scroll;
        Boolean displayedScroll = false;

        List<WebElement> modelDataList = driver.findElements(By.xpath("//tr[contains(@id,'--treeDM-rows-row')]"));
        List<String> nameElement = new ArrayList<>();

        //Pasamos los nombres de los Elementos al nuevo array
        for ( WebElement modelData : modelDataList){
            nameElement.add(modelData.getText());
        }

        try{
            scroll = driver.findElement(By.xpath("//div[@class='sapUiTableVSb' and contains(@id,'--treeDM-vsb')]"));
            displayedScroll = scroll.isDisplayed();
        }catch (Exception e){
            System.out.println("No se encontro scroll");
        }

        if(displayedScroll == true){
            scroll = driver.findElement(By.xpath("//div[@class='sapUiTableVSb' and contains(@id,'--treeDM-vsb')]"));
            int scrollHeight= js.executeScript("let scrollHeight = arguments[0].scrollHeight; return(scrollHeight)",scroll).hashCode();
            int clientHeight = js.executeScript("let clientHeight = arguments[0].clientHeight; return(clientHeight)",scroll).hashCode();

            int numVeces = scrollHeight/clientHeight;
            int iterator = -1;

            while (iterator<=numVeces+1){

                if(nameElement.contains(attribute)){
                    xpos = nameElement.lastIndexOf(attribute);
                    nameElement.clear();
                    break;
                }
                else{
                    iterator = iterator+1;
                    int multiplo = clientHeight*iterator ;
                    js.executeScript("arguments[0].scroll(0,'"+multiplo+"')",scroll);
                    modelDataList = driver.findElements(By.xpath("//tr[contains(@id,'--treeDM-rows-row')]"));
                    nameElement.clear();
                    for(int i = 0; i<= modelDataList.size()-1;i=i+1){
                        nameElement.add(modelDataList.get(i).getText());
                    }
                }
            }

        }else{
            xpos = nameElement.lastIndexOf(attribute);
        }
        return xpos;
    }

}
