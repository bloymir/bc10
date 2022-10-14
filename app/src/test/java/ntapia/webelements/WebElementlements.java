package ntapia.webelements;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class WebElementlements {
    WebDriver driver;
    //Localizadores
    By barraBusquedaLocalizador = By.name("q");
    By btnBuscarGoogleLocalizador = By.name("btnK");


    @BeforeEach
    void setUp(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://www.google.com");
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3)); //espere al menos 3 segundos por cada elemento que quiero referenciar
    }

    @Test
    void busquedaGoogle() throws InterruptedException {

        //Capturamos el elemento
        WebElement barraBusqueda = driver.findElement(barraBusquedaLocalizador);
        barraBusqueda.clear(); //Limpiar Barra
        barraBusqueda.sendKeys("Tsoft"); //Ingresar al buscar
        //barraBusqueda.sendKeys(Keys.ESCAPE);
        Thread.sleep(1000);

        WebElement btnBucarGoogle = driver.findElement(btnBuscarGoogleLocalizador);
        btnBucarGoogle.click();

        Assertions.assertEquals("Tsoft - Buscar con Google", driver.getTitle());
    }

    @AfterEach
    void clean(){
        driver.close();
    }
}
