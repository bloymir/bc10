package bc10.earaya.browser;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class InstallDriversTest {

    @Test
    void chromeSession(){
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.quit();
    }

    @Test
    void edgeSession(){
        WebDriverManager.edgedriver().setup();
        WebDriver driver = new EdgeDriver();
        driver.quit();
    }
    @Test
    void firefoxSession(){
        WebDriverManager.firefoxdriver().setup();
        WebDriver driver = new FirefoxDriver();
        driver.quit();

    }
    @Test
    public void ieSession() {
        WebDriverManager.iedriver().setup();
        WebDriver driver = new InternetExplorerDriver();
        driver.quit();
    }

}
