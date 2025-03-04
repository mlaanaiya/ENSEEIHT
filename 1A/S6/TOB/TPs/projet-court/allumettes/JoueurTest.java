package allumettes;

import org.junit.*;

import static org.junit.Assert.*;

/**
 * Classe de test de la classe Joueur
 *
 * @author Hamza MOUDDENE
 * @version 1.0
 */
public class JoueurTest {

    private Joueur j;

    @Before
    public void setUp() {
        this.j = new Joueur("naruto", new StrategieExperte());
    }

    @Test
    public void testGetters() {
        assertEquals(this.j.getNom(), "naruto");
        assertEquals(this.j.getStrategie().getNom(), "Experte");
    }

    @Test
    public void testSetters() {
        this.j.setNom("goku");
        this.j.setStrategie(new StrategieRapide());
        assertEquals(this.j.getNom(), "goku");
        assertEquals(this.j.getStrategie().getNom(), "Rapide");
    }

    /**
     * Méthode pricipale de la classe RobustesseJoueurTest
     *
     * @param args est la variable d'environnement qui est une table de caractères
     */
    public static void main(String[] args) {
        org.junit.runner.JUnitCore.main(JoueurTest.class.getName());
    }

}

