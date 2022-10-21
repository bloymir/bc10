package bc10.drfa;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

import java.time.Duration;

public class Test3 {
    WebDriver driver;
    //localizadores
    By agregarC = By.xpath("//a[@id='add']");
    By createThis = By.xpath("//input[@value='Create this computer']");
    By computerName = By.xpath("//input[@id='name']");
    By doneEsclamasion = By.xpath("//div[@class='alert-message warning']");


    @BeforeEach
    void setUp(){
        WebDriverManager.edgedriver().setup();
        driver = new EdgeDriver();
        driver.get("https://computer-database.gatling.io/");
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(1500));

        //validacion Ingresar a add a new computer y maximizar la pantalla
        WebElement addNewC = driver.findElement(agregarC);
        addNewC.click();
        driver.manage().window().maximize();
        Dimension dimesion = driver.manage().window().getSize();
        int width = dimesion.getWidth();
        int height = dimesion.getHeight();
        Assertions.assertEquals(1936,width);
        Assertions.assertEquals(1056,height);
    }

    @Test
    void crearComputador(){
        // validacion de mandar el texto al imput computer name
        WebElement nameC = driver.findElement(computerName);
        nameC.sendKeys("BC10_DF");
        // apreta el boton crear este computador
        WebElement crearNuevo = driver.findElement(createThis);
        crearNuevo.click();
        Assertions.assertEquals("Done ! Computer BC10_DF has been created", driver.findElement(doneEsclamasion).getText());
    }
}
