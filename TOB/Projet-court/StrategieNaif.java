package allumettes;

import java.util.Random;

public class StrategieNaif implements Strategie {

	/** Avoir la prise du joueur.
	 * @return Prise du joueur
	 * @param jeu jeu de la partie
	 * @param joueur joueur
	 */
	public int getPrise(Jeu jeu, Joueur joueur) {
		Random rand = new Random();
		return rand.nextInt(Math.min(
				jeu.getNombreAllumettes(), Jeu.PRISE_MAX)) + 1;
	}
}
