package allumettes;

/**
 * Lance une partie des 13 allumettes en fonction des arguments fournis sur la
 * ligne de commande.
 * @author Xavier Crégut
 * @version $Revision: 1.5 $
 */
public class Jouer {

	private static final int NOMBRE_ALLUMETTES = 13;

	/**
	 * Lancer une partie.En argument sont donnés les deux joueurs sous la forme
	 * nom@stratégie.
	 * @param args la description des deux joueurs
	 * @throws CoupInvalideException
	 */
	public static void main(String[] args) throws CoupInvalideException {
		Arbitre arbitre;
		Joueur j1;
		Joueur j2;
		Jeu jeu;
		int i = 0;
		boolean confiant = false;
		Strategie strategieTest1 = new StrategieNaif();
		Strategie strategieTest2 = new StrategieRapide();
		try {
			verifierNombreArguments(args);
			if (!args[0].equals("-confiant")) {
				// initialiser la varibale jeu
				jeu = new JeuReel(NOMBRE_ALLUMETTES);

			} else {

				// initialiser la varibale jeu
				jeu = new JeuReel(NOMBRE_ALLUMETTES);
				i = 1;
				confiant = true;
			}
			String[] tab = null;
			String[] tab1 = null;
			tab = tabSplit(args, i);
			tab1 = tabSplit(args, i + 1);
			strategieTest1 = setStrategie(tab);
			//j1 = new Joueur(tab[0], strategieTest1);
			strategieTest2 = setStrategie(tab1);
			//j2 = new Joueur(tab1[0], strategieTest2);
			j1 = new Joueur(tab[0], strategieTest1);
			j2 = new Joueur(tab1[0], strategieTest2);
			arbitre = new Arbitre(j1, j2, confiant);
			arbitre.arbitrer(jeu);
		} catch (ConfigurationException | IllegalArgumentException
				| IndexOutOfBoundsException e) {
			System.out.println();
			System.out.println("Erreur : " + e.getMessage());
			afficherUsage();
			System.exit(1);
		}
	}


	public static Strategie setStrategie(String[] tab) {
		Strategie strategieTest;
		String nomstrategie = tab[1];
		switch (nomstrategie) {
		case "rapide" :
			strategieTest = new StrategieRapide();
			break;
		case "naif" :
			strategieTest = new StrategieNaif();
			break;
		case "humain" :
			strategieTest = new StrategieHumain();
			break;
		case "expert" :
			strategieTest = new StrategieExpert();
			break;
		case "tricheur" :
			strategieTest = new StrategieTricheur();
			break;
		default :
			throw new ConfigurationException("Le  nom de la strategie choisie"
		+ "n'est pas bon");
		}
		return strategieTest;
	}

	public static String[] tabSplit(String[] args, int i) {
		String[] tableau;
		tableau = args[i].split("@");
		return tableau;
	}

	private static void verifierNombreArguments(String[] args) {
		final int nbJoueurs = 2;
		if (args.length < nbJoueurs) {
			throw new ConfigurationException("Trop peu d'arguments"
		+ " : " + args.length);
		}
		if (args.length > nbJoueurs + 1) {
			throw new ConfigurationException("Trop d'arguments"
		+ " : " + args.length);
		}
	}

	/** Afficher des indications sur la manière d'exécuter cette classe. */
	public static void afficherUsage() {
		System.out.println("\n" + "Usage :" + "\n\t"
	+ "java allumettes.Jouer joueur1 joueur2" + "\n\t\t"
				+ "joueur est de la forme nom@stratégie"
	+ "\n\t\t"
				+ "strategie = naif | rapide | expert | humain | tricheur"
	+ "\n" + "\n\t" + "Exemple :" + "\n\t"
				+ "	java allumettes.Jouer Xavier@humain "
	+ "Ordinateur@naif" + "\n");
	}

}
