package allumettes;

public class Arbitre {

	/** joueur 1 de la partie. */
	private Joueur j1;

	/** joueur 2 de la partie. */
	private Joueur j2;

	/** mode de jeu. */
	private boolean confiant;

	/** Arbitre constructeur initialisation.
	 * @param j1
	 * @param j2
	 */
	public Arbitre(Joueur j1, Joueur j2) {
		assert (j1 != null);
		assert (j2 != null);
		this.j1 = j1;
		this.j2 = j2;
	}

	/** Determiner si le mode de jeu est confiant.
	 * @param j1
	 * @param j2
	 * @param confiant
	 */
	public Arbitre(Joueur j1, Joueur j2, boolean confiant) {
		this(j1, j2);
		this.confiant = confiant;
	}

	/** Donner la prise du joueur.
	 * @param jeu
	 * @param tour
	 * @return nombre de prise
	 * @throws CoupInvalideException
	 */
	public int getPrise(Jeu jeu, Joueur tour) throws CoupInvalideException {
		int nombrePrise;
		if (confiant) {
			nombrePrise = tour.getPrise(jeu);
		} else {
			nombrePrise = tour.getPrise(new JeuProcoration(jeu));
		}
		return nombrePrise;
	}

	/** Afficher la prise d'un joueur.
	 * @param joueur
	 * @param nombrePrise
	 */
	public void afficherPrise(Joueur joueur, int nombrePrise) {
		if (nombrePrise > 1) {
			System.out.println(joueur.getNom()
					+ "  prend " + nombrePrise + " allumettes. ");
		} else if (nombrePrise == 1 | nombrePrise == 0 | nombrePrise == -1) {
			System.out.println(joueur.getNom()
					+ "  prend " + nombrePrise + " allumette. ");
		} else { // Le joueur prend qu'ne seule allumete
			System.out.println(joueur.getNom()
					+ "  prend " + nombrePrise + " allumettes. ");
		}
		}

	/**
	 * Verifier si la partie est finie.
	 * @param jeu
	 * @return boolean de la fin partie
	 */
	public boolean partieFini(Jeu jeu) {
		if (jeu.getNombreAllumettes() > 0) {
			return false;
			}
		return true;
		}

	/**
	 * Savoir le joueur gagnat et perdant.
	 * @return joueur joueur impaire qui commence
	 * @param tour tour de la partie
	 */
	public Joueur getJoueurCourant(int tour) {
		if (tour % 2 == 1) { // La partie commence par le joueur j1
			return j1;
		} else {
			return j2;
		}
	}

	/**
	 * Retourner le joueur qui gagne, perd la partie.
	 * @param joueur1   joueur j1
	 * @param joueur2   joueur j2
	 * @param jeu  partie joué
	 * @param tour tour du joueur
	 */
	public void gagnatPerdant(Joueur joueur1, Joueur joueur2, Jeu jeu, int tour) {
		assert (joueur1 != null);
		assert (joueur2 != null);
		if (jeu.getNombreAllumettes() == 0) {
			System.out.println(getJoueurCourant(tour + 1).getNom()
					+ " perd !");
			System.out.println(getJoueurCourant(tour).getNom()
					+ " gagne !");
			}
		}

	/**
	 * Arbitrer la partie entre de joueurs.
	 * @param jeu jeu de la partie
	 */
	public void arbitrer(Jeu jeu) {
		int tour2 = 1;
		String impossible = "Impossible ! Nombre d'allumettes invalide: ";
		while (jeu.getNombreAllumettes() > 0) {
			System.out.println("Nombre d'allumettes"
		+ " restantes : "
		+ jeu.getNombreAllumettes());
			int prise = 1;
			try {
				if (confiant) {
					prise = this.getJoueurCourant(tour2)
							.getPrise(jeu);
				} else {
					prise = this.getJoueurCourant(tour2)
							.getPrise(
							new JeuProcoration(jeu));
				}
				afficherPrise(getJoueurCourant(tour2), prise);
				jeu.retirer(prise);
				tour2 = tour2 + 1;
				System.out.println();
			} catch (CoupInvalideException e) {
				if (e.getNombreAllumettes()
						> jeu.getNombreAllumettes()) {
					System.out.println(impossible
				//		+ " Nombre"
				//+ " d'allumettes" + " invalide: "
						+ e.getNombreAllumettes()
				+ "(>" + jeu.getNombreAllumettes() + ")");
				} else if (e.getNombreAllumettes() < 1) {
					System.out.println(impossible
				//+ " d'allumettes "+ "invalide : "
							+ e.getNombreAllumettes()
				+ "(<" + 1 + ")");
				} else if (e.getNombreAllumettes()
						> Jeu.PRISE_MAX) {
					System.out.println(impossible
				//+ " d'allumettes"+ " invalide : "
							+ e.getNombreAllumettes()
							+ "(>" + Jeu.PRISE_MAX + ")");
				}
			} catch (OperationInterditeException f) {
				System.out.println("Abandon de la partie car "
			+ getJoueurCourant(tour2).getNom() + " triche !");
				break;
			}
		}
		gagnatPerdant(j1, j2, jeu, tour2);
	}
}
