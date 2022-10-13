package ntapia;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class BrowserActionTest {
    WebDriver driver;

    @BeforeEach
    void setup(){
        WebDriverManager.chromedriver().setup();
    }

    @Test
    void browserActionsTest(){
        String titulo;
        driver = new ChromeDriver();

        //Abrir pagina web
        driver.get("https://www.google.com");

        titulo = driver.getTitle();
        System.out.println("El titulo de la url es: "+titulo);

        //abrir url 2
        driver.navigate().to("https://www.selenium.dev");
        titulo = driver.getTitle();
        System.out.println("El titulo de la url es: "+titulo);

        //Back volver atras
        driver.navigate().back();
        titulo = driver.getTitle();
        System.out.println("El titulo de la url es: "+titulo);

        //Forward
        driver.navigate().forward();
        titulo = driver.getTitle();
        System.out.println("El titulo de la url es: "+titulo);

        //refresh
        driver.navigate().refresh();
    }

    @AfterEach
    void close(){
        driver.quit();
    }

}
