package allumettes;

public class StrategieTricheur implements Strategie {

	/** Avoir la prise du joueur.
	 * @return prise joueur
	 * @param jeu jeu de la partie
	 * @param joueur de la partie
	 * @throws CoupINvalideException
	 */
	public int getPrise(Jeu jeu, Joueur joueur)
			throws CoupInvalideException {

		System.out.println("\n [Je triche...]");
		assert (jeu.getNombreAllumettes() > 0);
		// try {
		while (jeu.getNombreAllumettes()
				>= 2 * Jeu.PRISE_MAX - 1) {
			jeu.retirer(Jeu.PRISE_MAX);
		}
		jeu.retirer(jeu.getNombreAllumettes() - 2);
		System.out.println("[" + "Allumettes restantes :"
		+ jeu.getNombreAllumettes() + "]");
		return 1;
	}
}
