package allumettes;

public class Joueur {
	/** Nom du joueur.*/
	private String nom;

	/**Nom de la startégie adopté. */
	private Strategie strategie;

	/** Joueur de la partie et sa stratégie.
	 * @param nom
	 * @param strategie
	 */
	public Joueur(String nom, Strategie strategie) {
		this.nom = nom;
		this.strategie = strategie;
	}

	/** Avoir le nom du joueur.
	 * @return Nom nom du joueur
	 */
	public String getNom() {
		return this.nom;
	}

	/** Avoir la prise du joueur.
	 * @param jeu
	 * @return prise prise du joueur
	 * @throws CoupInvalideException
	 */
	public int getPrise(Jeu jeu) throws CoupInvalideException {
		assert (jeu != null);
		return this.strategie.getPrise(jeu, this);

	}

}
