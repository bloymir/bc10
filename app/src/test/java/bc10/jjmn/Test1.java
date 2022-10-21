package bc10.jjmn;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;

import java.time.Duration;

public class Test1 {
    WebDriver driver;

    By btnFilterNameloc = By.xpath("//input[@id='searchsubmit']");
    By btnAddNewComputerloc = By.xpath("//a[@id='add']");
    By btnHomeloc = By.xpath("//a[normalize-space()='Computer database']");
    By btnComputerNameloc = By.xpath("//a[normalize-space()='Computer database']");
    By btnDiscontinuedloc = By.xpath("//a[normalize-space()='Discontinued']");
    By btnCompanyloc = By.xpath("//a[normalize-space()='Company']");

    @BeforeEach
    void setUp() {

        WebDriverManager.edgedriver().setup();
        driver = new EdgeDriver();
        driver.get("https://computer-database.gatling.io");
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));

    }

    @Test
    void Test1(){

        driver.manage().window().setSize(new Dimension(800,600));

        Dimension dimension = driver.manage().window().getSize();

        int widht = dimension.getWidth();
        int heigth = dimension.getHeight();

        // valida las dimensiones
        Assertions.assertEquals(800,widht);
        Assertions.assertEquals(600,heigth);


        WebElement btnFilterName = driver.findElement(btnFilterNameloc);

        // valida que no se pueda entrar a menos que se haya escrito algo
        String palabra = driver.findElement(By.xpath("//input[@id='searchbox']")).getAttribute("placeholder");
        Assertions.assertEquals("Filter by computer name...",palabra);
        btnFilterName.click();
        //Boton nuevo computador
        WebElement btnAddNewComputer = driver.findElement(btnAddNewComputerloc);
        btnAddNewComputer.click();
        //Entra a add computer y valida que esta correcta la entrada con el titulo de la pagina
        Assertions.assertEquals("Computers database",driver.getTitle());

        //Regresa a la home
        WebElement btnHome = driver.findElement(btnHomeloc);
        btnHome.click();
        // validacion de que nos envia a algun destino.
        //Computer name
        WebElement btnComputerName = driver.findElement(btnComputerNameloc);
        btnComputerName.click();
        Assertions.assertEquals("Computers database",driver.getTitle());
        //Discontinued
        WebElement btnDiscontinued = driver.findElement(btnDiscontinuedloc);
        btnDiscontinued.click();
        Assertions.assertEquals("Computers database",driver.getTitle());
        //Company
        WebElement btnCompany = driver.findElement(btnCompanyloc);
        btnCompany.click();
        Assertions.assertEquals("Computers database",driver.getTitle());

    }

    @AfterEach
    void quit(){driver.quit();}
}
