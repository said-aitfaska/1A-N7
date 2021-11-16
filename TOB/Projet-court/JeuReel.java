package allumettes;

public class JeuReel implements Jeu {

	/** Nombre d'allumettes initials .*/
	private int nombreAllumettes;


	/**Constructeur jeuReel.
	 * @param nombreallumettes
	 */
	public JeuReel(int nombreallumettes) {
		this.nombreAllumettes = nombreallumettes;
	}

	@Override
	public int getNombreAllumettes() {
		return nombreAllumettes;
	}

	@Override
	public void retirer(int nbPrises)
			throws CoupInvalideException {
		// TODO Auto-generated method stub
		if (nbPrises > 0 && nbPrises <= Jeu.PRISE_MAX
				&& nbPrises <= nombreAllumettes) {
			nombreAllumettes -= nbPrises;
		} else {
			throw new CoupInvalideException(nbPrises, "Pas possible");

		}

	}

}
