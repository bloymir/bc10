package bc10.shlc;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;


import java.time.Duration;

public class Test2 {
    WebDriver driver;
    //Localizadores
    By btnNewComputerLoc = By.xpath("//a[@id='add']");
    By btnCreateComputerLoc = By.xpath("//input[@value='Create this computer']");
    By mensajeErrorLoc = By.xpath("//span[contains(text(),'Failed to refine type : Predicate isEmpty() did no')]");

    @BeforeEach
    void setUp(){
        //Configuracion inicial
        WebDriverManager.edgedriver().setup();
        driver = new EdgeDriver();
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        driver.navigate().to("https://computer-database.gatling.io");
    }

    @Test
    void createUserfailed(){

        WebElement btnNewComputer = driver.findElement(btnNewComputerLoc);
        //Click en boton New Computer
        btnNewComputer.click();
        WebElement btnCreateComputer = driver.findElement(btnCreateComputerLoc);
        //Click boton Create computer
        btnCreateComputer.click();
        WebElement mensajeError = driver.findElement(mensajeErrorLoc);
        //Se valida el Texto error
        Assertions.assertEquals("Failed to refine type : Predicate isEmpty() did not fail.",mensajeError.getText());
    }

    @AfterEach
    void close(){
        //Se cierra el navegador
        driver.quit();
    }

}
