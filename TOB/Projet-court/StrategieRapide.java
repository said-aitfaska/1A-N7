package allumettes;

public class StrategieRapide implements Strategie {

	/** Prise du joueur dans la partie.
	 * @return Prise du joueur
	 * @param jeu jeu de la partie
	 * @param joueur joueur
	 */
	public int getPrise(Jeu jeu, Joueur joueur) {
		return Math.min(jeu.getNombreAllumettes(), Jeu.PRISE_MAX);
	}
}
