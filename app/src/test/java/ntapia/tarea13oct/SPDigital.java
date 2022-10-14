package ntapia.tarea13oct;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import javax.swing.*;
import java.time.Duration;

public class SPDigital {
    WebDriver driver;
    By barraBusquedaLocalizador = By.className("Fractal-SearchBar--searchbar");
    By btnBuscarLocalizador = By.className("Fractal-SearchBar--button");
    By linkQuienesSomosLocalizador = By.linkText("Quiénes somos");
    By linkPagosLocalizador = By.xpath("//*[@id=\"gatsby-focus-wrapper\"]/div/div[4]/section[15]/div/div/span[3]/span[1]/a");
    By menuGaminLocalizador = By.xpath("/html/body/div[1]/div[1]/div/div[3]/div/a[1]/div/span");
    By armadoPcLocalizador = By.xpath("//*[@id=\"gatsby-focus-wrapper\"]/div[3]/div[2]/div[1]/div/div/div[3]/ul/li[1]/a");


    @BeforeEach
    void setUp(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://www.spdigital.cl/");
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3)); //espere al menos 3 segundos por cada elemento que quiero referenciar
    }

    @Test
    void buscar() throws InterruptedException {
        WebElement barraBusqueda = driver.findElement(barraBusquedaLocalizador);
        barraBusqueda.clear();
        barraBusqueda.sendKeys("lupa");
        WebElement btnBuscar = driver.findElement(btnBuscarLocalizador);
        btnBuscar.submit();
        Thread.sleep(10000);
        Assertions.assertEquals("Lupa | SP Digital", driver.getTitle());
        driver.navigate().back();
        driver.navigate().back();
        Assertions.assertEquals("SP Digital | Expertos en tecnología", driver.getTitle());
    }

    @Test
    void navegarQuienesSomos() throws InterruptedException {
        WebElement linkQuinesSomos = driver.findElement(linkQuienesSomosLocalizador);
        linkQuinesSomos.click();
        Thread.sleep(1000);
        Assertions.assertEquals("https://www.spdigital.cl/about/", driver.getCurrentUrl());
        driver.navigate().back();
        Assertions.assertEquals("SP Digital | Expertos en tecnología", driver.getTitle());
    }

    @Test
    void navegarPagos() throws InterruptedException {
        WebElement linkPagos = driver.findElement(linkPagosLocalizador);
        linkPagos.click();
        Thread.sleep(1000);
        Assertions.assertEquals("https://www.spdigital.cl/faq/payments/", driver.getCurrentUrl());
        driver.navigate().back();
        Assertions.assertEquals("SP Digital | Expertos en tecnología", driver.getTitle());
    }

    @Test
    void armadoGaming(){
        Actions act = new Actions(driver);
        WebElement gaminMenu = driver.findElement(menuGaminLocalizador);

        act.moveToElement(gaminMenu);
        WebElement armadoLink = driver.findElement(armadoPcLocalizador);
        armadoLink.click();
    }


    @AfterEach
    void clean(){
        driver.close();
        driver.quit();
    }
}
