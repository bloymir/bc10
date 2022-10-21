package bc10.jjmn;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;

import java.time.Duration;

public class Test2 {

    WebDriver driver;
    By btnAddNewComputerloc = By.xpath("//a[@id='add']");
    By btnCreateThisComputerloc = By.xpath("//input[@value='Create this computer']");
    By mensajeloc = By.xpath("//span[contains(text(),'Failed to refine type : Predicate isEmpty() did no')]");


    @BeforeEach
    void setUp() {

        WebDriverManager.edgedriver().setup();
        driver = new EdgeDriver();
        driver.get("https://computer-database.gatling.io");
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
    }

    @Test
    void test2(){

        WebElement btnAddNewComputer = driver.findElement(btnAddNewComputerloc);
        btnAddNewComputer.click();

        driver.manage().window().maximize();

        WebElement btnCreateNewComputer = driver.findElement(btnCreateThisComputerloc);
        btnCreateNewComputer.click();

        WebElement mensaje = driver.findElement(mensajeloc);

        Assertions.assertEquals("Failed to refine type : Predicate isEmpty() did not fail.",mensaje.getText());


    }
    @AfterEach
    void quit(){driver.quit();}
}
