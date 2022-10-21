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
import org.openqa.selenium.support.ui.Select;

import java.time.Duration;

public class Test3 {

    WebDriver driver;

    By btnAddNewComputerloc = By.xpath("//a[@id='add']");
    By searchboxComputerNameloc = By.xpath("//input[@id='name']");
    By searchboxIntroducedloc = By.xpath("//input[@id='introduced']");
    By searchboxDiscontinuedloc = By.xpath("//input[@id='discontinued']");
    By selectCompanyloc = By.xpath("//select[@id='company']");
    By msjeloc = By.xpath("//div[@class='alert-message warning']");
    By btnCreateThisComputerloc = By.xpath("//input[@value='Create this computer']");


    @BeforeEach
    void setUp() {

        WebDriverManager.edgedriver().setup();
        driver = new EdgeDriver();
        driver.get("https://computer-database.gatling.io");
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
    }

    @Test
    void Test3(){

        WebElement btnAddNewComputer = driver.findElement(btnAddNewComputerloc);
        btnAddNewComputer.click();
        driver.manage().window().maximize();

        WebElement searchboxCompuerName = driver.findElement(searchboxComputerNameloc);
        searchboxCompuerName.sendKeys("BC10_JJMN");

        WebElement searchboxIntroduced = driver.findElement(searchboxIntroducedloc);
        searchboxIntroduced.sendKeys("2022-10-23");

        WebElement searchboxDiscontinued = driver.findElement(searchboxDiscontinuedloc);
        searchboxDiscontinued.sendKeys("2022-10-24");

        WebElement selectCompany = driver.findElement(selectCompanyloc);
        selectCompany.click();

        Select company = new Select(selectCompany);
        company.selectByIndex(42);


        WebElement btnCreateNewComputer = driver.findElement(btnCreateThisComputerloc);
        btnCreateNewComputer.click();

        WebElement msje = driver.findElement(msjeloc);


        Assertions.assertEquals("Done ! Computer BC10_JJMN has been created",msje.getText());

    }
    @AfterEach
    void quit(){driver.quit();}
}
