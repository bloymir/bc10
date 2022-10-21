package bc10.earaya.webelements;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Waits {
    WebDriver driver;

    //Localizadores
    By link1Loc = By.xpath("//a[text()='Example 1: Element on page that is hidden']");
    By btnStartLoc = By.xpath("//button[normalize-space()='Start']");
    By HelloAsincLoc = By.xpath("//h4[normalize-space()='Hello World!']");


    @BeforeEach
    void setUp(){
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
        // waits :: implicit
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(1500));
    }

    @Test
    void waits(){
        driver.navigate().to("https://the-internet.herokuapp.com/dynamic_loading");
        WebElement link1 = driver.findElement(link1Loc);
        link1.click();
        WebElement btnStart = driver.findElement(btnStartLoc);
        btnStart.click();
        //WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
        //espera explicita de un webelement

        //fluent wait
        Wait<WebDriver> fluentwait = new FluentWait<WebDriver>(driver)
                                    .withTimeout(Duration.ofSeconds(10))
                                    .pollingEvery(Duration.ofMillis(100))
                                    .ignoring(NoSuchElementException.class);


        fluentwait.until(ExpectedConditions.elementToBeClickable(HelloAsincLoc));

        Assertions.assertEquals("Hello World!",driver.findElement(HelloAsincLoc).getText());

    }

    @AfterEach
    void clean(){
        driver.close();
    }



}
