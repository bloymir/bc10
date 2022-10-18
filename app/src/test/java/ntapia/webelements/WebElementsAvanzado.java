package ntapia.webelements;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.time.Duration;
import java.util.List;

public class WebElementsAvanzado {
    WebDriver driver;

    @FindBy(id = "dropdown")
    WebElement dropdown;

    /*@FindBy(id = "ui-id-3")
    WebElement btnEnable;*/
    By btnEnableLocalizador = By.id("ui-id-3");
    By btnDownloadLocalizador = By.id("ui-id-4");
    By btnPdfLocalizador = By.id("ui-id-5");

    /*@FindBy(id = "ui-id-4")
    WebElement btnDownloads;*/

    /*@FindBy(id = "ui-id-5")
    WebElement btnPdf;*/

    @FindBy(xpath = "//input[1]")
    WebElement checkbox1;

    @FindBy(xpath = "//input[2]")
    WebElement checkbox2;

    @BeforeEach
    void setUp(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        //PageFactory.initElements(driver, this);
    }
    @AfterEach
    void clean(){
        driver.close();
    }

    @Test
    public void dropdown(){
        driver.navigate().to("https://the-internet.herokuapp.com/dropdown");
        Select manejoDropwdown = new Select(dropdown);

        manejoDropwdown.selectByValue("1");
        manejoDropwdown.selectByValue("2");
        manejoDropwdown.selectByValue("");

        manejoDropwdown.selectByValue("Option 1");
    }

    @Test
    public void dropDownDinamico() throws InterruptedException {
        driver.navigate().to("https://the-internet.herokuapp.com/jqueryui/menu#");
        //
        WebElement btnEnable = driver.findElement(btnEnableLocalizador);
        btnEnable.click();
        WebElement btnDownloads = driver.findElement(btnDownloadLocalizador);
        btnDownloads.click();
        //String resultado = btnPdf.getText();
        WebElement btnPdf = driver.findElement(btnPdfLocalizador);
        Thread.sleep(5000);
        Assertions.assertEquals("PDF", btnPdf.getText());

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

        //Obtener todos los webelement que contengan tag iframes

        List<WebElement> iframes = driver.findElements(By.tagName("iframe"));

        //Cambiar a iframe
        driver.switchTo().frame(iframes.get(0));

        //Ya cambiados al iframes obtendremos nuevo objetos de esta pagina
        WebElement areaEscritura = driver.findElement(By.id("tinymce"));
        areaEscritura.clear();
        areaEscritura.sendKeys("Wena los K como andan");
    }

    @Test
    public void webTable(){
        driver.navigate().to("https://the-internet.herokuapp.com/tables");

        //Ordenar tabla 1 por deuda de mayor a menor

        //Traer lista de tablas en la pagina
        List<WebElement> tables = driver.findElements(By.tagName("table"));

        WebElement tabla1 = tables.get(0);

        //Cuantas filas y columnas tiene
        List<WebElement> filas = tabla1.findElement(By.tagName("tbody")).findElements(By.tagName("tr"));
        List<WebElement> titulosColumnas = tabla1.findElement(By.tagName("thead")).findElements(By.tagName("th"));

        System.out.println(filas.size());

        //Obtener el WebElement DUE y presionarlo 2 veces para ordenar la tabla
        if(titulosColumnas.get(3).getText().contains("Due")){
            titulosColumnas.get(3).click();
            titulosColumnas.get(3).click();
        }

        //Obtener el nombre, apellido y deuda de la primera persona de la tabla
        String name = filas.get(0).findElement(By.xpath("//td[2]")).getText();
        String lastName = filas.get(0).findElement(By.xpath("//td[1]")).getText();
        String deuda = filas.get(0).findElement(By.xpath("//td[4]")).getText();

        System.out.println("El usuario con mayor deuda es: "+name+ " "+lastName+" "+deuda);

    }

    @Test
    public void tableTwo(){
        driver.get("https://the-internet.herokuapp.com/tables");

        //Traer Lista de tablas
        List<WebElement> tables = driver.findElements(By.tagName("table"));
        WebElement tabla2 = tables.get(1);

        //Traer filas y columnas
        List<WebElement> filas = tabla2.findElement(By.tagName("tbody")).findElements(By.tagName("tr"));
        List<WebElement> titulosColumnas = tabla2.findElement(By.tagName("thead")).findElements(By.tagName("th"));

        if(titulosColumnas.get(1).getText().contains("First Name")){
            titulosColumnas.get(1).click();
        }
       /* String name = "";
        String lastName = "";
        String deuda = "";

        for (int i = 0; i < 4; i++) {
            System.out.println(i);
            name = filas.get(i).findElement(By.xpath("//td[2]")).getText();
            lastName = filas.get(i).findElement(By.xpath("//td[1]")).getText();
            deuda = filas.get(i).findElement(By.xpath("//td[4]")).getText();

            System.out.println(name+" "+lastName+" "+deuda);

        }*/
        //table[2]/tbody/tr[1]/td[2]

        String nombre = filas.get(0).findElement(By.xpath("//table[2]/tbody/tr[1]/td[2]")).getText();
        String apellido = filas.get(0).findElement(By.xpath("//table[2]/tbody/tr[1]/td[1]")).getText();
        String deuda = filas.get(0).findElement(By.xpath("//table[2]/tbody/tr[1]/td[4]")).getText();
        System.out.println("El usuario : "+nombre+" "+apellido+" "+deuda);

        String nombre2 = filas.get(1).findElement(By.xpath("//table[2]/tbody/tr[2]/td[2]")).getText();
        String apellido2 = filas.get(1).findElement(By.xpath("//table[2]/tbody/tr[2]/td[1]")).getText();
        String deuda2 = filas.get(1).findElement(By.xpath("//table[2]/tbody/tr[2]/td[4]")).getText();
        System.out.println("El usuario : "+nombre2+" "+apellido2+" "+deuda2);

        String nombre3 = filas.get(2).findElement(By.xpath("//table[2]/tbody/tr[3]/td[2]")).getText();
        String apellido3 = filas.get(2).findElement(By.xpath("//table[2]/tbody/tr[3]/td[1]")).getText();
        String deuda3 = filas.get(2).findElement(By.xpath("//table[2]/tbody/tr[3]/td[4]")).getText();
        System.out.println("El usuario : "+nombre3+" "+apellido3+" "+deuda3);

        String nombre4 = filas.get(3).findElement(By.xpath("//table[2]/tbody/tr[4]/td[2]")).getText();
        String apellido4 = filas.get(3).findElement(By.xpath("//table[2]/tbody/tr[4]/td[1]")).getText();
        String deuda4 = filas.get(3).findElement(By.xpath("//table[2]/tbody/tr[4]/td[4]")).getText();
        System.out.println("El Usuario : "+nombre4+" "+apellido4+" "+deuda4);

        
    }
}
