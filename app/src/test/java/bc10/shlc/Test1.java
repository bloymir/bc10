package bc10.shlc;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;


import java.time.Duration;
import java.util.List;

public class Test1 {
    WebDriver driver;
    //Localizadores
    By btnFilterNameLoc = By.xpath("//input[@id='searchsubmit']");
    By btnNewComputerLoc = By.xpath("//a[@id='add']");
    By tablaLoc = By.xpath("//table[@class='computers zebra-striped']");


    @BeforeEach
    void setUp(){
        //Configuracion inicial
        WebDriverManager.edgedriver().setup();
        driver = new EdgeDriver();
        driver.manage().deleteAllCookies();
        driver.manage().window().setSize(new Dimension(800, 600));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        //Navegamos hacia el link
        driver.navigate().to("https://computer-database.gatling.io");
        //Se obtiene las dimenciones de la pantalla
        Dimension dimension = driver.manage().window().getSize();
        int wight = dimension.getWidth();
        int height = dimension.getHeight();
        //Se muestra la dimencion de pantalla
        System.out.println("La resolucion de pantalla es: "+wight+" x "+ height);
    }

    @Test
    void userInterfaceFilterName(){
        WebElement btnFilterName = driver.findElement(btnFilterNameLoc);
        //Verificamos si el boton esta desplegado
        boolean btnFilter  =  btnFilterName.isDisplayed();
        //Se valida que el boton esta desplegado y se validado por texto
        Assertions.assertEquals("Filter by name",btnFilterName.getAccessibleName());
        Assertions.assertTrue(btnFilter);
        //Se imprime resultado
        System.out.println("Boton Filter by Name esta funcionando de manera correta: " +btnFilter);
    }

    @Test
    void userInterfaceNewComputer(){
        WebElement btnNewComputer = driver.findElement(btnNewComputerLoc);
        //Verificamos si el boton esta desplegado
        boolean btnNewComp  =  btnNewComputer.isEnabled();
        //Se valida que el boton esta desplegado y validado por texto
        Assertions.assertEquals("Add a new computer",btnNewComputer.getAccessibleName());
        Assertions.assertTrue(btnNewComp);
        //Se imprime resultado
        System.out.println("Boton Filter by Name esta funcionando de manera correta: " +btnNewComp);
    }

    @Test
    void userInterfaceTable() {
        //Se obtiene la WebTable
        List<WebElement> webTables = driver.findElements(tablaLoc);
        WebElement tabla = webTables.get(0);
        List<WebElement> columnas = tabla.findElement(By.tagName("thead")).findElements(By.tagName("th"));
        //Se recorre la lista y verifica los nombres de la columna
        for (int i = 0; i <columnas.size() ; i++) {
            String nombre = columnas.get(i).findElement(By.xpath("//th["+(i+1)+"]")).getText();
            System.out.print(nombre+ " - ");
            Assertions.assertEquals(nombre, columnas.get(i).getText());
        }

    }

    @AfterEach
    void close(){
        //Se cierra el navegador
        driver.quit();
    }


}
