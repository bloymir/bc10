package ntapia.pom.pages;

import ntapia.pom.base.SeleniumBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class GatlingHomePage extends SeleniumBase {

    //Constructor Selenium Base
    public GatlingHomePage(WebDriver driver) {
        super(driver);
    }

    //Localizadores
    String urlHome = "https://computer-database.gatling.io/computers";
    By btnAddLoc = By.id("add");
    By btnCreateComputerLoc = By.xpath("//input[@value='Create this computer']");
    By errorMsjLoc = By.xpath("//span[@class='help-inline']");
    By inputNameLoc = By.id("name");
    By msjDoneLoc = By.xpath("//div[@class='alert-message warning']");
    By computerNameLoc = By.xpath("//th[@class='col-name header headerSortUp']");
    By introducedLoc = By.xpath("//th[@class='col-introduced header ']");
    By discontinuedLoc = By.xpath("//th[@class='col-discontinued header ']");
    By companyLoc = By.xpath("//th[@class='col-company header ']");
    By btnFilterNameLoc = By.id("searchsubmit");


    //Funciones o acciones que podemos desarrollar en la pagina

    public void irAHomePage(){
        navegarAPagina(urlHome);
    }

    public void validarResize(int width, int heigth){
        cambiarTamanoDriver(width, heigth);
    }

    public void validarDespliegueDeWebElement(){
        WebElement btnFilterName = findElement(btnFilterNameLoc);

    }

}
