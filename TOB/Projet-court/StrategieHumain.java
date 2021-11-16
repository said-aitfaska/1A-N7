package allumettes;

import java.util.Scanner;

public class StrategieHumain implements Strategie {
	/** Scanner.java pour lire la ligne de commande.
	 */
	private static Scanner scan = new Scanner(System.in);

	/** Avoir la prise du joueur.
	 * @return prise du joueur
	 * @param jeu jeu de la partie
	 * @param joueur joeuur de la partie
	 * @throws CoupInvalideException
	 */
	public int getPrise(Jeu jeu, Joueur joueur)
			throws CoupInvalideException {
		int priseJoueur = 1;
		boolean reussi = false;
		do {
			try {

				System.out.print(joueur.getNom() + ","
				+ " combien d'allumettes ? ");
				String argument = scan.nextLine();
				if (argument.equals("triche")) {
					System.out.println("[" + "Une allumette"
				+ "en moins, plus que "
				+ (jeu.getNombreAllumettes() - 1) + "."
				+ "Chut!" + "]");

					jeu.retirer(1);

					continue;
				}
				priseJoueur = Integer.parseInt(argument);
				reussi = true;
			} catch (NumberFormatException e) {
				System.out.println("Vous devez"
			+ "donner un entier. ");

			}
		} while (!reussi);

		return priseJoueur;
	}

}
