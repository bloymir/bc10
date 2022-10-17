package bc10.earaya.webelements;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;

public class WebElements {
    WebDriver driver;

    @FindBy(name = "q")
    WebElement barraBusqueda;

    @FindBy(name = "btnK")
    WebElement btnBuscarConGoogle;

    @FindBy(css = ".andes-modal-dialog > iframe:nth-child(1)")
    WebElement iframe;

    By iframeLocalizador = By.cssSelector(".andes-modal-dialog > iframe:nth-child(1)");



    @BeforeEach
    void setUp(){
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        PageFactory.initElements(driver,this);
    }

    @Test
    void busquedaGoogle() throws InterruptedException {

        driver.get("https://www.google.com");
        //Con WebElement capturamos el elemento, a traves de sus atributos
        barraBusqueda.clear();
        barraBusqueda.sendKeys("Tsoft");
        barraBusqueda.sendKeys(Keys.ESCAPE);
        Thread.sleep(500);
        btnBuscarConGoogle.click();
        Assertions.assertEquals("Tsoft - Buscar con Google",driver.getTitle());
    }
    @Test
    void busquedaMercadoLibre() throws InterruptedException {

        driver.get("https://www.mercadolibre.com.ar/");
        driver.findElement(By.xpath("(//li[@class='nav-menu-item']/a)[1]")).click();
        driver.switchTo().frame(driver.findElement(iframeLocalizador));
        Thread.sleep(1500);
        driver.findElement(By.xpath("/html/body/main/div/div[2]/form/div/div/div/div[1]/label/div/input")).sendKeys("5300");
        driver.findElement(By.xpath("/html/body/main/div/div[2]/form/div/div/div/div[1]/label/div/div/button/span")).click();

    }


    @AfterEach
    void clean(){
        driver.close();
    }
}
