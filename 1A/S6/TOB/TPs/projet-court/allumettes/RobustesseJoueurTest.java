package allumettes;

import org.junit.*;

import static org.junit.Assert.*;

/**
 * Classe de test de la robustesse de la classe Joueur
 *
 * @author Hamza MOUDDENE
 * @version 1.0
 */
public class RobustesseJoueurTest {

    private Joueur j;

    @Before
    public void setUp() {
        j = new Joueur("naruto", new StrategieExperte());
    }

    @Test(expected = AssertionError.class)
    public void test1() {
        this.j = new Joueur("", new StrategieHumaine());
    }

    @Test(expected = AssertionError.class)
    public void test2() {
        this.j = new Joueur(null, new StrategieHumaine());
    }

    @Test(expected = AssertionError.class)
    public void test3() {
        this.j = new Joueur("lufi", null);
    }

    @Test(expected = AssertionError.class)
    public void test4() {
        this.j = new Joueur("", null);
    }

    @Test(expected = AssertionError.class)
    public void test5() {
        this.j = new Joueur(null, null);
    }

    @Test(expected = AssertionError.class)
    public void testSetNom1() {
        this.j.setNom(null);
    }

    @Test(expected = AssertionError.class)
    public void testSetNom2() {
        this.j.setNom("");
    }

    @Test(expected = AssertionError.class)
    public void testSetStrategie() {
        this.j.setStrategie(null);
    }

    /**
     * Méthode pricipale de la classe RobustesseJoueurTest
     *
     * @param args est la variable d'environnement qui est une table de caractères
     */
    public static void main(String[] args) {
        org.junit.runner.JUnitCore.main(RobustesseJoueurTest.class.getName());
    }

}
