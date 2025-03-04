import java.awt.Color;
import org.junit.*;
import static org.junit.Assert.*;

/**
  * Classe de tests complémentaires de la classe Cercle
  *
  * @author	Hamza MOUDDENE
  */
public class ComplementsCercleTest {
	
	// précision pour les comparaisons réelle
	public final static double EPSILON = 0.001;
	
	/**
	* Attributs de la classe ComplementCercleTest
	*/
        private Point A, B, C;		// Les poins de tests
	private Cercle C1, C2, C3;	// Les cercles de tests
	
	/**
	 * Initialiser les attributs de la classe ComplementCercleTest (les données
	 * de test) qui seront utilisés au cours des tests
	 */
	@Before public void setUp() {
		// Création des points
                A = new Point(1, 2);
                B = new Point(2, 1);
                C = new Point(4, 1);

                // Construire des cercles
                C1 = new Cercle(A, 33.0);
                C2 = new Cercle(B, C);
                C3 = new Cercle(C, A, Color.red);
	}

    	/**
	 * Une méthode de tests d'un scénario
	 */
    	@Test public void testerScenario() {
		// Vérifier que C1 est bien créé
		assertEquals(C1.getCentre().getX(), 1, EPSILON);
		assertEquals(C1.getCentre().getY(), 2, EPSILON);
		assertEquals(C1.getRayon(), 33.0, EPSILON);
		assertEquals(C1.getCouleur(), Color.blue);
		
		// Vérifier que C1 contient les points B et C
		assertTrue(C1.contient(C));
		assertTrue(C1.contient(B));

		// Changer le rayon de C1
		C1.setRayon(0.1);

		// Vérifier que le rayon de C1 a été changé
		assertEquals(C1.getRayon(), 0.1, EPSILON);

		// Vérifier que C1 ne contient pas les points B et C
                assertFalse(C1.contient(C));
                assertFalse(C1.contient(B));

		// Changer le rayon de C1
                C1.setRayon(100);

		// Vérifier que le rayon de C1 a été changé
                assertEquals(C1.getRayon(), 100, EPSILON);

		// Vérifier que C1 contient les points B et C
                assertTrue(C1.contient(C));
                assertTrue(C1.contient(B));

		// Translater C1 de 1000 suivant X et 1000 suivant Y
		C1.translater(1000, 1000);
		
		// Vérifier que C1 a été bien bien translaté
                assertEquals(C1.getCentre().getX(), 1001, EPSILON);
                assertEquals(C1.getCentre().getY(), 1002, EPSILON);

		// Vérifier que C1 ne contient pas les points B et C
                assertFalse(C1.contient(C));
                assertFalse(C1.contient(B));

		// Vérifier l'aire et le perimetre
		assertEquals(C1.perimetre(), 628.318, EPSILON);
                assertEquals(C1.aire(), 31415.926, EPSILON);
	}

    	/**
	* Méthode pricipale de la classe ComplementCercleTest
	*
	* @param args est la variable d'environnement qui est une table de caractères
	*/
	public static void main(String[] args) {
		org.junit.runner.JUnitCore.main("ComplementsCercleTest");
	}

}
