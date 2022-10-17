package bc10.earaya.webelements;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.time.Duration;
import java.util.List;

public class WebElementsAvanzados {

    WebDriver driver;

    @FindBy(id = "dropdown")
    WebElement dropdown;

    @FindBy(id = "ui-id-3")
    WebElement btnEnable;

    @FindBy(id = "ui-id-4")
    WebElement btnDownloads;

    @FindBy(id = "ui-id-5")
    WebElement btnPDF;

    @FindBy(xpath = "//input[1]")
    WebElement checkbox1;

    @FindBy(xpath = "//input[2]")
    WebElement checkbox2;


    @BeforeEach
    void setUp(){
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        PageFactory.initElements(driver,this);
    }

    @Test
    public void dropdown(){
        driver.navigate().to("https://the-internet.herokuapp.com/dropdown");

        //Clase soporte para trabajar con los elementos <select>
        Select manejoDropDown = new Select(dropdown);

        //fuincionalidades de Select
        manejoDropDown.selectByValue("1");
        manejoDropDown.selectByValue("2");
        manejoDropDown.selectByValue("");

        manejoDropDown.selectByVisibleText("Option 1");
        manejoDropDown.selectByVisibleText("Option 2");

    }

    @Test
    public void dropDownDinamico(){
        driver.navigate().to("https://the-internet.herokuapp.com/jqueryui/menu");
        btnEnable.click();
        btnDownloads.click();
        Assertions.assertEquals("PDF",btnPDF.getText());
    }

    @Test
    public void checkbox(){
        driver.get("https://the-internet.herokuapp.com/checkboxes");
        checkbox1.click();
        boolean estaClickeado = checkbox1.isSelected();
        checkbox2.click();
        estaClickeado = checkbox2.isSelected();
    }

    @Test
    public void iframes(){
        driver.navigate().to("https://the-internet.herokuapp.com/iframe");

        //obtener todos los webElement que conbtengan el tag iframe
        List<WebElement> iframes = driver.findElements(By.tagName("iframe"));

        //cambiar a iframe
        driver.switchTo().frame(iframes.get(0));

        //ya cambiados al iframe, obtendremos objetos de etsa pagina
        WebElement areaEscritura = driver.findElement(By.id("tinymce"));
        areaEscritura.clear();
        areaEscritura.sendKeys("Hola saludos BC10 :)");

    }

    @Test
    public void webTables(){
        // ejercicio tabla 1 : ordenar la tabla por deuda de mayor a menor
        driver.navigate().to("https://the-internet.herokuapp.com/tables");

        //lista de webtables en la pagina
        List<WebElement> webTables = driver.findElements(By.tagName("table"));

        WebElement tabla1 = webTables.get(0);
        //1. cuantas filas y columnas tiene?
        List<WebElement> filas = tabla1.findElement(By.tagName("tbody")).findElements(By.tagName("tr"));
        System.out.println(filas.size());
        List<WebElement> columnas = tabla1.findElement(By.tagName("thead")).findElements(By.tagName("th"));
        System.out.println(columnas.size());

        //obtener el webElement Due y presionarlo 2 veces para ordenar la tabla
        if(columnas.get(3).getText().contains("Due")){
            columnas.get(3).click();
            columnas.get(3).click();
        }

        // obtener el nombre, apellido y valor de deuda de la primera fila
        String nombre = filas.get(0).findElement(By.xpath("//td[2]")).getText();
        String apellido = filas.get(0).findElement(By.xpath("//td[1]")).getText();
        String deuda = filas.get(0).findElement(By.xpath("//td[4]")).getText();

        System.out.println("El usuario con mayor deuda actual es: "+nombre+" "+apellido+" "+deuda);

    }



    @AfterEach
    void clean(){
        driver.close();
    }


}
