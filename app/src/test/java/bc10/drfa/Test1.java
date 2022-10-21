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
import org.openqa.selenium.interactions.Actions;

import javax.swing.*;
import java.time.Duration;

public class Test1 {

    WebDriver driver;
    //locators
    By inputBlanck = By.xpath("//input[@id='searchbox']");
    By filterB = By.xpath("//input[@id='searchsubmit']");
    By botonAdd = By.xpath("//a[@id='add']");
    By home = By.xpath("//a[@class='fill']");
    By computerName = By.xpath("//a[normalize-space()='Computer name']");
    By introduced = By.xpath("//a[normalize-space()='Introduced']");
    By discontinued = By.xpath("//a[normalize-space()='Discontinued']");
    By company = By.xpath("//a[normalize-space()='Company']");


    @BeforeEach
    void setUp() {

        WebDriverManager.edgedriver().setup();
        driver = new EdgeDriver();
        driver.get("https://computer-database.gatling.io/");
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
    }

    @Test
    void validacionesCD(){

        // validacion reazise windows 800x600
        driver.manage().window().setSize(new Dimension(800,600));
        int width = driver.manage().window().getSize().width;
        int heigth = driver.manage().window().getSize().height;
        Assertions.assertEquals(800,width);
        Assertions.assertEquals(600,heigth);
        System.out.println("La nueva resolucion de pantalla es : " + driver.manage().window().getSize());

        // validacion boton filter by Name
        WebElement inputB = driver.findElement(inputBlanck);
        WebElement filterByName = driver.findElement(filterB);
        System.out.println("El boton esta habilitado? : " + filterByName.isEnabled());
        inputB.sendKeys("Acer");
        filterByName.click();



        //validar Boton  add a new Computer
        WebElement btnAddC = driver.findElement(botonAdd);
        System.out.println("El boton esta habilitado? : " + btnAddC.isEnabled());
        btnAddC.click();
        //return Home
        WebElement casa = driver.findElement(home);
        casa.click();

        Actions action = new Actions(driver);

        WebElement cname = driver.findElement(computerName);
        action.moveToElement(cname).click().perform();

        WebElement intro = driver.findElement(introduced);
        action.moveToElement(intro).click().perform();
        WebElement discont = driver.findElement(discontinued);
        action.moveToElement(discont).click().perform();
        WebElement cpany = driver.findElement(company);
        action.moveToElement(cpany).click().perform();

    }
}
