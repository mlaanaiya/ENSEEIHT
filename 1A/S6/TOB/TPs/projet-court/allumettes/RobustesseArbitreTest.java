package allumettes;

import org.junit.*;

import static org.junit.Assert.*;

/**
 * Classe de test de la robustesse de la classe Arbitre
 *
 * @author Hamza MOUDDENE
 * @version 1.0
 */
public class RobustesseArbitreTest {

    private Arbitre a;

    @Before
    public void setUp() {
        this.a = new Arbitre(new Joueur("naruto", new StrategieRapide()), new Joueur("sasuke", new StrategieNaive()));
    }

    @Test(expected = AssertionError.class)
    public void test1() {
        this.a = new Arbitre(null, new Joueur("sakura", new StrategieLente()));
    }

    @Test(expected = AssertionError.class)
    public void test2() {
        this.a = new Arbitre(new Joueur("kakashei", new StrategieTricheur()), null);
    }

    @Test(expected = AssertionError.class)
    public void test3() {
        this.a = new Arbitre(null, null);
    }

    @Test(expected = AssertionError.class)
    public void testGetJoueurCourant1() {
        this.a.getJoueurCourant(0);
    }

    @Test(expected = AssertionError.class)
    public void testGetJoueurCourant2() {
        this.a.getJoueurCourant(-5);
    }

    @Test(expected = AssertionError.class)
    public void testestFinie1() {
        this.a.estFinie(-5, null);
    }

    @Test(expected = AssertionError.class)
    public void testEstFinie2() {
        this.a.estFinie(1, null);
    }

    @Test(expected = AssertionError.class)
    public void testEstFinie1() {
        this.a.estFinie(-2, new Game(13));
    }

    @Test(expected = AssertionError.class)
    public void testArbitrer1() {
        this.a.arbitrer(null, null, true);
    }

    @Test(expected = AssertionError.class)
    public void testArbitrer2() {
        this.a.arbitrer(new Game(13), null, false);
    }

    @Test(expected = AssertionError.class)
    public void testArbitrer() {
        this.a.arbitrer(null, new Proxy(new Game(13)), false);
    }

    /**
     * Méthode pricipale de la classe RobustesseArbitreTest
     *
     * @param args est la variable d'environnement qui est une table de caractères
     */
    public static void main(String[] args) {
        org.junit.runner.JUnitCore.main(RobustesseArbitreTest.class.getName());
    }

}