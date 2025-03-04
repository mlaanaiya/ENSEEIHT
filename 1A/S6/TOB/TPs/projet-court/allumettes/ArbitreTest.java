package allumettes;

import org.junit.*;

import static org.junit.Assert.*;

/**
 * Classe de test de la classe Arbitre
 *
 * @author Hamza MOUDDENE
 * @version 1.0
 */
public class ArbitreTest {

    private Arbitre a;

    @Before
    public void setUp() {
        this.a = new Arbitre(new Joueur("naruto", new StrategieExperte()), new Joueur("neji", new StrategieHumaine()));
    }

    @Test
    public void testGetters() {
        assertTrue(this.a.getJ1().isEquals(new Joueur("naruto", new StrategieExperte())));
        assertTrue(this.a.getJ2().isEquals(new Joueur("neji", new StrategieHumaine())));
    }

    @Test
    public void testSetters() {
        this.a.setJ1(new Joueur("Shuji", new StrategieLente()));
        assertTrue(this.a.getJ1().isEquals(new Joueur("Shuji", new StrategieLente())));
        assertTrue(this.a.getJ2().isEquals(new Joueur("neji", new StrategieHumaine())));

        this.a.setJ2(new Joueur("sanji", new StrategieRapide()));
        assertTrue(this.a.getJ1().isEquals(new Joueur("Shuji", new StrategieLente())));
        assertTrue(this.a.getJ2().isEquals(new Joueur("sanji", new StrategieRapide())));
    }

    @Test
    public void testGetJoueurCourant() {
        assertTrue(this.a.getJoueurCourant(1).isEquals(new Joueur("naruto", new StrategieExperte())));
        assertTrue(this.a.getJoueurCourant(2).isEquals(new Joueur("neji", new StrategieHumaine())));
        this.a.setJ1(new Joueur("Shuji", new StrategieLente()));
        this.a.setJ2(new Joueur("sanji", new StrategieRapide()));
        assertTrue(this.a.getJoueurCourant(1).isEquals(new Joueur("Shuji", new StrategieLente())));
        assertTrue(this.a.getJoueurCourant(2).isEquals(new Joueur("sanji", new StrategieRapide())));
    }

    @Test
    public void testEstFinie() {
        assertFalse(this.a.estFinie(3, new Game(3)));
        assertTrue(this.a.estFinie(15, new Game(0)));
    }

    @Test
    public void testGetString() {
        assertEquals(this.a.getString(3), " allumettes.");
        assertEquals(this.a.getString(1), " allumette.");
    }

    /**
     * Méthode pricipale de la classe RobustesseJoueurTest
     *
     * @param args est la variable d'environnement qui est une table de caractères
     */
    public static void main(String[] args) {
        org.junit.runner.JUnitCore.main(ArbitreTest.class.getName());
    }

}

