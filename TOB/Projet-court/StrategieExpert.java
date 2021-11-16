package allumettes;


public class StrategieExpert implements Strategie {

	/** Prise du joueur dans la stratégie Expert.
	 * @return prise du joueur
	 * @param jeu jeu de la partie
	 * @param joueur joueur de la partie
	 */
	public int getPrise(Jeu jeu, Joueur joueur) {
		assert (jeu.getNombreAllumettes() > 0);
		if ((jeu.getNombreAllumettes() - 1) % (Jeu.PRISE_MAX + 1) == 0) {
			return 1;
		} else {
			return (jeu.getNombreAllumettes() - 1) % (Jeu.PRISE_MAX + 1);
		}
	}
}
