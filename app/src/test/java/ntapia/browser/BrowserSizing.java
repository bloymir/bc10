package ntapia.browser;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class BrowserSizing {
    WebDriver driver;

    @BeforeEach
    void setup(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://www.google.cl");
    }

    @Test
    void browserSize(){

        //Largo y Ancho de la ventana
        int width = driver.manage().window().getSize().getWidth();
        int heigth = driver.manage().window().getSize().getHeight();

        //Dimension
        Dimension size = driver.manage().window().getSize();
        int width1 = size.getWidth();
        int heigth1 = size.getHeight();

        //Cambiar el tamaño
        driver.manage().window().setSize(new Dimension(400, 400));
    }

    @Test
    void browserPosition() throws InterruptedException {
        int x = driver.manage().window().getPosition().x;
        int y = driver.manage().window().getPosition().y;

        Point pos = driver.manage().window().getPosition();
        int x1 = pos.x;
        int y1 = pos.y;
        System.out.println(x1);
        System.out.println(y1);

        driver.manage().window().setPosition(new Point(0,0));

        Thread.sleep(2000);

    }

    @AfterEach
    void close(){
        driver.quit();
    }
}
