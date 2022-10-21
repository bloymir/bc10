package ntapia.pom.base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.time.Duration;

public class TestBase {

    //Atributos
    public WebDriver driver;

    @BeforeAll
    public static void initTesting(){
        WebDriverManager.firefoxdriver().setup();
    }

    @BeforeEach
    public void setUp(){
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(1500));
    }

    @AfterEach
    public void close(){
        if(driver != null ){
            driver.close();
        }
    }
}
