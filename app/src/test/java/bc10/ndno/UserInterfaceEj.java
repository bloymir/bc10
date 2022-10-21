package bc10.ndno;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class UserInterfaceEj {
    WebDriver driver;

    By FilterbyName = By.xpath("//*[@id=\"searchsubmit\"] ");
    By SearchName = By.xpath("//*[@id=\"searchbox\"] ");

    By AddNewcomputer = By.xpath("//*[@id=\"add\"] ");


    @BeforeEach
    void setUp(){
        WebDriverManager.edgedriver().setup();
        driver = new EdgeDriver();
        driver.manage().deleteAllCookies();
        //driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        driver.manage().window().setSize(new Dimension(800,600));
    }
    //TestFuncional:

    @Test

    void CrearComputadorVacio() throws IOException {

        //CASO FILTER BY NAME

        driver.navigate().to("https://computer-database.gatling.io");
        WebElement searchbox =driver.findElement(SearchName);
        searchbox.clear();
        searchbox.sendKeys("ARRA");

        WebElement btnFilterByName = driver.findElement(FilterbyName);
        btnFilterByName.click();

        // Foto para comprobar la resolución
        File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile,new File("./FilterByname.png"));

        // CASO "ADD NEW COMPUTER"
        WebElement btnAddNewcomputer = driver.findElement(AddNewcomputer);
        btnAddNewcomputer.click();

        File scrFile2 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile2,new File("./AddNewcomputer.png"));


    }
    @AfterEach
    void close (){
        driver.close();
    }
    }