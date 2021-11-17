import org.junit.*;

import static org.junit.Assert.*;

import java.awt.Color;

/** Programme de test de la classe Cercle.
 * @author	Saïd AIT FASKA
 * Groupe A
 */
public class CercleTest {
	
	public static void main(String[] args) {
		org.junit.runner.JUnitCore.main("CercleTest");
	}
	
	// précision pour les comparaisons réelle
	public final static double EPSILON = 0.001;

	// Les points du sujet
	private Point A, C, D, E;

	// Les cercles du sujet
	private Cercle C1, C2, C3;
	
	@Before public void setUp() {
		// Construire les points
		A = new Point(1, 2);
		C = new Point(4, 1);
		D = new Point(8, 1);
		E = new Point(8, 4);

		// Construire les cercles
		C1 = new Cercle(A, D);
		C2 = new Cercle(D, C, Color.RED);
		//C1.setCouleur(Color.yellow);
	    C3 = Cercle.creerCercle(D, E);
	}
	
	/** Vérifier si deux points ont mêmes coordonnées.
	  * @param p1 le premier point
	  * @param p2 le deuxième point
	  */
	static void memesCoordonnees(String message, Point p1, Point p2) {
		assertEquals(message + " (x)", p1.getX(), p2.getX(), EPSILON);
		assertEquals(message + " (y)", p1.getY(), p2.getY(), EPSILON);
	}

	/**Verifier que le cercle est construit a partir de deux points oposeés et d'une couleur. 
	 */
	@Test public void testerE12() {
			memesCoordonnees("E12 : Centre de C1 incorrect", new Point(4.5, 1.5), C1.getCentre());
			assertEquals("E12 : Rayon de C1 incorrect",
					3.535, C1.getRayon(), EPSILON);
			assertEquals(Color.blue, C1.getCouleur());
		}
     
	/**construire un cercle à partir de deux points diamétralement opposés et de sa couleur.
	 * 
	 */	
	@Test public void testerE13() {
		    memesCoordonnees("E13 : Centre de C2 incorrect", new Point(6, 1), C2.getCentre());
		    assertEquals("E13 : Rayon de C2 incorrect",
				2.000, C2.getRayon(), EPSILON);
		    assertEquals(Color.red, C2.getCouleur());
	}
	
	/** créer un cercle à partir de deux points.
	 *  le premier correspond au centre du cercle et le deuxième est un point du cercle
	 */
	@Test public void testerE14() {
			memesCoordonnees("E14 : Centre de C3 incorrect", D, C3.getCentre());
			assertEquals("E14 : Rayon de C3 incorrect",
				3.000, C3.getRayon(), EPSILON);
			assertEquals(Color.blue, C3.getCouleur());
		}
}