package allumettes;

public interface Strategie {

	/** Avoir al prise du joueur.
	 * @param jeu
	 * @param joueur
	 * @return prise joueur
	 * @throws CoupInvalideException
	 */
	int getPrise(Jeu jeu, Joueur joueur) throws CoupInvalideException;

}
