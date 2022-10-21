package bc10.capdbu;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
public class userInterfaceTest {
    WebDriver driver;
    @FindBy(xpath="//*[@id='searchsubmit']")
    WebElement btnFBN;
    @FindBy(xpath="//*[@id='add']")
    WebElement btnAdd;
    @FindBy(xpath = "//*[@id=\"main\"]/form/fieldset/div[1]/label")
    WebElement divComputerName;
    @FindBy(xpath="//*[@id=\"main\"]/form/fieldset/div[2]/label")
    WebElement divIntroduced;
    @FindBy(xpath = "//*[@id=\"main\"]/form/fieldset/div[3]/label")
    WebElement divDiscontinued;
    @FindBy(xpath = "//*[@id='main']/form/fieldset/div[4]/label")
    WebElement divCompany;
    @BeforeEach
    void setUp() {
        WebDriverManager.edgedriver().setup();
        driver = new EdgeDriver();
        driver.get("https://computer-database.gatling.io/");
        driver.manage().window().maximize();
        driver.manage().window().setSize(new Dimension(800, 600));
        PageFactory.initElements(driver,this);
    }

    @Test
    void userInterface(){
        btnFBN.click();
        Assertions.assertEquals("",btnFBN.getText());
        btnAdd.click();
        //Validamos la existencia y uso del btnAdd verificando la direccion del button con la del driver.
        Assertions.assertEquals("https://computer-database.gatling.io/computers/new",driver.getCurrentUrl());
        Assertions.assertEquals("Computer name", divComputerName.getText());
        Assertions.assertEquals("Introduced", divIntroduced.getText());
        Assertions.assertEquals("Discontinued",divDiscontinued.getText());
        Assertions.assertEquals("Company", divCompany.getText());
    }

    @AfterEach
    void clean(){
        driver.close();
    }

}
