package ntapia.pom.pages;

import ntapia.pom.base.SeleniumBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class GoogleHomePage extends SeleniumBase {

    public GoogleHomePage(WebDriver driver) {
        super(driver);
    }

    //Atributos - Localizadores
    String url = "https://www.google.com";
    By barraBusquedaLocator = By.name("q");
    By btnBuscarGoogleLocator = By.name("btnK");
    By btnVoyaTenerSuerteLocator = By.name("btnI");


    //Funciones o metodos de la pagina

    public void irAlHome(){
        navegarAPagina(url);
    }

    public void buscarBarraBtnGoogle(String texto){
        escribir(texto, barraBusquedaLocator);
        clickear(btnBuscarGoogleLocator);
    }

    public void buscarConVoyATenerSuerte(String texto){
        escribir(texto, barraBusquedaLocator);
        clickear(btnVoyaTenerSuerteLocator);
    }
}
