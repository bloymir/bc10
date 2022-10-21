package bc10.capdbu;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
public class FuncionalTest {
    WebDriver driver;
    @FindBy(xpath="//*[@id='add']")
    WebElement btnAdd;
    @FindBy(xpath = "//input[@value='Create this computer']")
    WebElement btnCrear;
    @FindBy(xpath = "//*[@id='main']/form/fieldset/div[1]/div/span")
    WebElement labelC;
    @FindBy(id = "name")
    WebElement inputName;
    @FindBy(xpath = "//*[@class='alert-message warning']")
    WebElement alertSuccess;
    @BeforeEach
    void setUp() {
        WebDriverManager.edgedriver().setup();
        driver = new EdgeDriver();
        driver.get("https://computer-database.gatling.io/");
        driver.manage().window().maximize();
        PageFactory.initElements(driver,this);
    }

    @Test
    void addComputerAlert(){
        btnAdd.click();
        //Validamos la existencia y uso del btnAdd verificando la direccion del button con la del driver.
        Assertions.assertEquals("https://computer-database.gatling.io/computers/new",driver.getCurrentUrl());
        btnCrear.click();
        Assertions.assertEquals("Failed to refine type : Predicate isEmpty() did not fail.",labelC.getText());
    }

    @Test
    void addComputerSuccess(){
        btnAdd.click();
        inputName.clear();
        inputName.sendKeys("BC10_CAPDBU");
        btnCrear.click();
        Assertions.assertEquals("Done ! " + "Computer BC10_CAPDBU has been created", alertSuccess.getText());
    }

    @AfterEach
    void clean(){
        driver.close();
    }

}
