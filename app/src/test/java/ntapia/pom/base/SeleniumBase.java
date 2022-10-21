package ntapia.pom.base;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * Clase Base de selenium WebDriver, nos servira
 * para enmascarar la tecnología de Selenium - Wrapper
 * **/
public class SeleniumBase {
    // Atributo

    private WebDriver driver;

    //Constructor
    public SeleniumBase(WebDriver driver){
        this.driver = driver;
    }

    //Funciones Wrapper (Enmascarar)
    public void navegarAPagina(String url){
        driver.navigate().to(url);
    }
    public WebElement findElement(By locator){
        return driver.findElement(locator);
    }

    public List<WebElement> findElements (By locator){
        return driver.findElements(locator);
    }

    public String getText (By locator){
        return driver.findElement(locator).getText();
    }

    public void type(String inputText, By locator){
        driver.findElement(locator).sendKeys(inputText);
    }

    public void click(By locator){
        driver.findElement(locator).click();
    }

    public Boolean isDisplayed(By locator) {
        try {
            return driver.findElement(locator).isDisplayed();
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }
    public Boolean isSelected(By locator) {
        try {
            return driver.findElement(locator).isSelected();
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }

    public void cambiarTamanoDriver(int width, int heigth){
        driver.manage().window().setSize(new Dimension(width, heigth));
    }

    public int getWidth(){
        return driver.manage().window().getSize().width;
    }
    public int getHeigth(){
        return driver.manage().window().getSize().height;
    }

    public void escribir(String inputText, By locator){
        driver.findElement(locator).sendKeys(inputText);
    }
    public void clickear(By locator){
        driver.findElement(locator).click();
    }






}
