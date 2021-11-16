import javax.swing.*;
import java.awt.*;
/** Permet d'afficher l'interface de paiement.
 * 
	 * @author	Bonrepaux Rémi
*/

public class VuePayer extends JTextArea {
	
	public VuePayer(Panier panier, Client client, int nbLignes, int nbColonnes) {
		super(nbLignes,nbColonnes);
	 
	}
}