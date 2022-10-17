package bc10.earaya.browser;

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
    void setUp(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://www.google.cl");
        driver.manage().window().maximize();
        driver.manage().window().minimize();
        driver.manage().window().fullscreen();
        driver.manage().window().setSize(new Dimension(800,600));
    }

    @Test
    void browserSize(){
        //largo y ancho de la ventana o browser
        int width = driver.manage().window().getSize().width;
        int height = driver.manage().window().getSize().getHeight();

        //Dimension
        Dimension dimension = driver.manage().window().getSize();
        int witdh2 = dimension.getWidth();
        int height2 = dimension.getHeight();

        //Cambiar el tamaño en tiempo de ejecucion
        driver.manage().window().setSize(new Dimension(600,400));


    }

    @Test
    void browserPosition(){
        int x = driver.manage().window().getPosition().getX();
        int y = driver.manage().window().getPosition().getY();

        //Point
        Point posicion = driver.manage().window().getPosition();
        int x1 = posicion.x;
        int y1 = posicion.y;

        // modificar la posicion de la ventana - browser - driver, en tiempo de ejecucion
        driver.manage().window().setPosition(new Point(0,0));

    }
    @AfterEach
    void close(){
        driver.quit();
    }



}
