package bc10.ndno;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class TestFuncionalEj {

    WebDriver driver;
    By errorCreate = By.xpath("//*[@id=\"main\"]/form/fieldset/div[1]/div/span");
    By AlertText = By.xpath("//*[@id=\"main\"]/div[1]");



    @BeforeEach
    void setUp (){
        WebDriverManager.edgedriver().setup();
        driver = new EdgeDriver();
        driver.get("https://computer-database.gatling.io/computers");
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
    }

    @Test
    void BtnAddNewComputer() throws IOException {
        driver.navigate().to("https://computer-database.gatling.io/computers/new");
        WebElement btnCreateComputer = driver.findElement(By.xpath("/html/body/section/form/div/input"));
        btnCreateComputer.click();
        Assertions.assertEquals("Failed to refine type : Predicate isEmpty() did not fail.",driver.findElement(errorCreate).getText());
        WebElement inputName = driver.findElement(By.id("name"));
        WebElement inputintroduced = driver.findElement(By.id("introduced"));
        WebElement inputdiscontinued = driver.findElement(By.id("discontinued"));

        //SCEENSHOT PARA COMPROBAR SI LA VENTANA ESTA MAXIMIZADA
        File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile,new File("./TestFuncional.png"));
    }
    @Test
    void CreateComputer() {
        driver.navigate().to("https://computer-database.gatling.io/computers/new");
        WebElement inputName = driver.findElement(By.id("name"));
        inputName.sendKeys("BC10_NN");
        WebElement btnCreateComputer = driver.findElement(By.xpath("/html/body/section/form/div/input"));
        btnCreateComputer.click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
        wait.until(ExpectedConditions.elementToBeClickable(AlertText));
        Assertions.assertEquals("Done ! Computer BC10_NN has been created",driver.findElement(AlertText).getText());
    }
    @AfterEach
    void close (){
        driver.close();
    }
}