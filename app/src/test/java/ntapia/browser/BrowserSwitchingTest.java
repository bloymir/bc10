package ntapia.browser;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;

public class BrowserSwitchingTest {
    WebDriver driver;

    @BeforeEach
    void setup(){
        WebDriverManager.chromedriver().setup();
    }

    @Test
    void browserSwitchingTest(){
        driver = new ChromeDriver();

        driver.navigate().to("https://www.google.com.ar");
        //numero de ventana del driver
        String originalWindow = driver.getWindowHandle();

        //Crear nueva ventana y cambiarse a ella
        driver.switchTo().newWindow(WindowType.WINDOW);
        String newWindow = driver.getWindowHandle();
        driver.navigate().to("https://www.google.cl");

        //cambiar a window 1
        driver.switchTo().window(originalWindow);
        driver.switchTo().newWindow(WindowType.TAB);
        driver.navigate().to("https://www.selenium.dev");

        //cambiar a window 2
        driver.switchTo().window(newWindow);
        driver.switchTo().newWindow(WindowType.TAB);
        driver.navigate().to("https://www.selenium.dev");
    }

    @AfterEach
    void close(){
        driver.quit();
    }
}
