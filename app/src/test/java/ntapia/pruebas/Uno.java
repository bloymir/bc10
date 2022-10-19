package ntapia.pruebas;

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


import java.time.Duration;

public class Uno {
    WebDriver driver;

    By btnAddLoc = By.id("add");
    By btnCreateComputerLoc = By.xpath("//input[@value='Create this computer']");
    By btnFilterNameLoc = By.id("searchsubmit");
    By errorMsgLoc = By.xpath("//span[@class='help-inline']");
    By inputNameLoc = By.id("name");
    By msjDoneLoc = By.xpath("//div[@class='alert-message warning']");


    //Tabla
    By computerNameLoc = By.xpath("//th[@class='col-name header headerSortUp']");
    By introducedLoc = By.xpath("//th[@class='col-introduced header ']");
    By discontinuedLoc = By.xpath("//th[@class='col-discontinued header ']");
    By companyLoc = By.xpath("//th[@class='col-company header ']");


    @BeforeEach
    void setup(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        driver.manage().window().maximize();
        driver.get("https://computer-database.gatling.io/computers");
    }


    @Test
    void etstUno(){
        driver.manage().window().setSize(new Dimension(800, 600));

        WebElement btnFilterName = driver.findElement(btnFilterNameLoc);
        WebElement btnAdd = driver.findElement(btnAddLoc);
        WebElement computerName = driver.findElement(computerNameLoc);
        WebElement introduced = driver.findElement(introducedLoc);
        WebElement discontinues = driver.findElement(discontinuedLoc);
        WebElement company = driver.findElement(companyLoc);

        boolean columnas = (computerName.isDisplayed() && introduced.isDisplayed() && discontinues.isDisplayed() && company.isDisplayed());
        boolean filtarName = (btnFilterName.isDisplayed());
        boolean newComputer = (btnAdd.isDisplayed());
        Assertions.assertEquals(true, columnas);
        Assertions.assertEquals(true, filtarName);
        Assertions.assertEquals(true, newComputer);
    }

    @Test
    void funcionalTest(){
        WebElement btnAdd = driver.findElement(btnAddLoc);
        btnAdd.click();
        WebElement btnCreateComputer = driver.findElement(btnCreateComputerLoc);
        btnCreateComputer.click();
        WebElement msjError = driver.findElement(errorMsgLoc);
        Assertions.assertEquals("Failed to refine type : Predicate isEmpty() did not fail.", msjError.getText());
    }

    @Test
    void pruebaPorsia(){
        WebElement btnAdd = driver.findElement(btnAddLoc);
        btnAdd.click();
        WebElement inputName = driver.findElement(inputNameLoc);
        inputName.sendKeys("BC10FLNT");
        WebElement btnCreateComputer = driver.findElement(btnCreateComputerLoc);
        btnCreateComputer.click();

        WebElement msjDone = driver.findElement(msjDoneLoc);
        Assertions.assertEquals("Done ! Computer BC10FLNT has been created", msjDone.getText());
    }

    @AfterEach
    void close(){
        driver.quit();
    }
}
