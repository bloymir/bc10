package bc10.rllg;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.Select;
public class Parte3 {
    WebDriver driver;

    By btnAddLocalizador = By.id("add");
    By nombreLocalizador = By.id("name");
    By introducirLocalizador = By.id("introduced");
    By descontinuadoLocalizador = By.id("discontinued");
    By companiaLocalizador = By.id("company");
    By btnCreateComputerLocalizador = By.xpath("//input[@class='btn primary']");
    By confirmacionLocalizador = By.xpath("//strong[normalize-space()='Done !']");
    @BeforeEach
    void setUp() {
        WebDriverManager.edgedriver().setup();
        driver = new EdgeDriver();
        driver.navigate().to("https://computer-database.gatling.io/computers");
        driver.manage().deleteAllCookies();
    }
    @Test
    public void test2_2(){
        WebElement btnAdd = driver.findElement(btnAddLocalizador);
        btnAdd.click();
        driver.manage().window().maximize();
        WebElement nombre= driver.findElement(nombreLocalizador);
        nombre.sendKeys("BC10_RL");
        WebElement introducir = driver.findElement(introducirLocalizador);
        introducir.sendKeys("1988-01-10");
        WebElement descontinuado = driver.findElement(descontinuadoLocalizador);
        descontinuado.sendKeys("2022-10-18");
        WebElement compania = driver.findElement(companiaLocalizador);
        Select company = new Select(compania);
        company.selectByVisibleText("Nintendo");
        WebElement btnCreateComputer = driver.findElement(btnCreateComputerLocalizador);
        btnCreateComputer.click();
        WebElement confirmacion = driver.findElement(confirmacionLocalizador);
        Assertions.assertEquals("Done !",confirmacion.getText());
    }
    @AfterEach
    void close(){
        driver.close();
    }
}
