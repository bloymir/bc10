package bc10.rllg;

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

public class Parte1 {
    WebDriver driver;
    By btnFiltrarLocalizador = By.id("searchsubmit");
    By btnAddLocalizador = By.id("add");
    By colComputerNameLocalizador = By.xpath("//a[text()='Computer name']");
    By colIntroducedLocalizador = By.xpath("//a[text()='Introduced']");
    By colDiscontinuedLocalizador = By.xpath("//a[text()='Discontinued']");
    By colCompanyLocalizador = By.xpath("//a[text()='Company']");


    @BeforeEach
    void setUp() {
        WebDriverManager.edgedriver().setup();
        driver = new EdgeDriver();
        driver.navigate().to("https://computer-database.gatling.io/computers");
        driver.manage().deleteAllCookies();
    }
    @Test
    public void test1(){
        driver.manage().window().setSize(new Dimension(800,600));
        Dimension dimension = driver.manage().window().getSize();
        Assertions.assertEquals(800,dimension.getWidth());
        Assertions.assertEquals(600,dimension.getHeight());
        WebElement btnFilter = driver.findElement(btnFiltrarLocalizador);
        Assertions.assertEquals("Filter by name",btnFilter.getAccessibleName());
        WebElement btnAddCompu = driver.findElement(btnAddLocalizador);
        Assertions.assertEquals("Add a new computer",btnAddCompu.getAccessibleName());
        WebElement colComputerName = driver.findElement(colComputerNameLocalizador);
        Assertions.assertEquals("Computer name",colComputerName.getText());
        WebElement colIntroduced = driver.findElement(colIntroducedLocalizador);
        Assertions.assertEquals("Introduced",colIntroduced.getText());
        WebElement colDiscontinued = driver.findElement(colDiscontinuedLocalizador);
        Assertions.assertEquals("Discontinued",colDiscontinued.getText());
        WebElement colCompany = driver.findElement(colCompanyLocalizador);
        Assertions.assertEquals("Company",colCompany.getText());
    }
    @AfterEach
    void close(){
        driver.close();
    }
}