package bc10.drfa;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Test2 {
    WebDriver driver;
    //localizadores
    By agregarC = By.xpath("//a[@id='add']");
    By createThis = By.xpath("//input[@value='Create this computer']");
    By errorSummit = By.xpath("//span[contains(text(),'Failed to refine type : Predicate isEmpty() did no')]");



    @BeforeEach
    void setUp(){
        WebDriverManager.edgedriver().setup();
        driver = new EdgeDriver();
        driver.get("https://computer-database.gatling.io/");
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(1500));
        WebElement addNewC = driver.findElement(agregarC);
        addNewC.click();
        driver.manage().window().maximize();
        Dimension dimesion = driver.manage().window().getSize();
        int width = dimesion.getWidth();
        int height = dimesion.getHeight();
        Assertions.assertEquals(1936,width);
        Assertions.assertEquals(1056,height);
        System.out.println("Width " + width);
        System.out.println("height " + height);
    }

    @Test
    void test2(){




        //validacion empty button
        WebElement cThisComputer = driver.findElement(createThis);
        cThisComputer.click();
        Assertions.assertEquals("Failed to refine type : Predicate isEmpty() did not fail.",driver.findElement(errorSummit).getText());

    }

    @AfterEach
    void closed(){
        driver.quit();
    }

}
