package allumettes;

import org.junit.*;
import static org.junit.Assert.*;

public class StrategieRapideTest {

	private static final int NB_ALLUMETTES_INITIAL = 13;

	private Joueur Ordinateur;
	private Joueur joueurTest;
	private Jeu jeu;
	Arbitre arbitre;

	@Before
	public void setUp() {
		joueurTest = new Joueur("JoueurTest", new StrategieRapide());

		Ordinateur = new Joueur("Ordinateur", new StrategieExpert());

	}

	@Test
	public void Test1() throws CoupInvalideException {
		Jeu jeu = new JeuReel(NB_ALLUMETTES_INITIAL);
		System.out.println("-------- Verifier la prise de la startegie prise  -------");
		assertTrue(jeu.getNombreAllumettes() == NB_ALLUMETTES_INITIAL);
		jeu.retirer(3);
		AfficherPrise(joueurTest);
		AfficherPrise(Ordinateur);
		assertEquals(10, jeu.getNombreAllumettes());
		AfficherPrise(joueurTest);
		jeu.retirer(3);
		assertEquals(7, jeu.getNombreAllumettes());
		AfficherPrise(Ordinateur);
		jeu.retirer(3);
		assertEquals(4, jeu.getNombreAllumettes());
		AfficherPrise(joueurTest);
		jeu.retirer(3);
		assertEquals(1, jeu.getNombreAllumettes());
		AfficherPrise(Ordinateur);
		jeu.retirer(1);
		afficheGagnantPerdant(joueurTest, Ordinateur);
	}

	@Test
	public void TestErreurPrise() throws CoupInvalideException {
		jeu = new JeuReel(NB_ALLUMETTES_INITIAL);
		int PriseErreur = 4;
		System.out.println("-------- Verifier l'erreur de prise (CoupInvalideException) -------");
		try {
			jeu.retirer(PriseErreur);
		} catch (CoupInvalideException r) {
			System.out.println("   Vous avez pris " + PriseErreur + " allumettes ");
			System.out.println("        La prise est interdite !!");
		}
	}

	public void AfficherPrise(Joueur joueur) throws CoupInvalideException {
		jeu = new JeuReel(NB_ALLUMETTES_INITIAL);
		int nombrePrise = joueur.getPrise(jeu);
		if (nombrePrise > 1) {
			System.out.println(joueur.getNom() + " prend " + nombrePrise + " allumettes .");
		} else {
			System.out.println(joueur.getNom() + " prend " + nombrePrise + " allumette .");
		}

	}

	public void afficheGagnantPerdant(Joueur gagne, Joueur perd) {
		System.out.println(gagne.getNom() + " a gagné !");
		System.out.println(perd.getNom() + " a perdu !");
	}
}
