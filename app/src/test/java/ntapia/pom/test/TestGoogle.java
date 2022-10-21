package ntapia.pom.test;

import ntapia.pom.base.TestBase;
import ntapia.pom.pages.GoogleHomePage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestGoogle extends TestBase {

    GoogleHomePage ghp;
    @Test
    void busquedaNormal(){
        ghp = new GoogleHomePage(super.driver);
        ghp.irAlHome();
        ghp.buscarBarraBtnGoogle("Tsoft");
        Assertions.assertTrue(true);
    }

    @Test
    void busquedaSuerte(){
        ghp = new GoogleHomePage(super.driver);
        ghp.irAlHome();
        ghp.buscarConVoyATenerSuerte("Tsoft");
        Assertions.assertTrue(true);
    }
}
