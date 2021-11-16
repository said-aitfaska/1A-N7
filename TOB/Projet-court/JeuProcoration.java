package allumettes;

public class JeuProcoration implements Jeu {

	/** Jeu de la partie.*/
	private Jeu jeu;

	/** Jeu procoration.
	 * @param jeu
	 */
	public JeuProcoration(Jeu jeu) {
		assert jeu != null;
		this.jeu = jeu;
	}

	@Override
	public int getNombreAllumettes() {
		return this.jeu.getNombreAllumettes();
	}

	@Override
	public void retirer(int nbPrises) throws OperationInterditeException {
		throw new OperationInterditeException("erreur de retirer");

	}

}
