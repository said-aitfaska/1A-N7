import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
/** C'est la classe principale.
 * Elle fait le lien entre les différentes IHM
 * et dirife l'utilisateur. 
	 * @author	Bonrepaux Rémi
*/
public class ClickAndCollect {
	public static void main(String[] args) {
		Commandes commandes = new Commandes();
		Carte carte = new Carte();
		Planning planning = new Planning();
//		Panier panier = new Panier();
	 //   Client client1 = new Client("bob", panier, 15);
		//new ModelePayer(panier, client1);
		new LoginCarte(carte, commandes, planning);
		
		//new ModeleCreerClient(client1,commandes).setLocation(1200,600);
//		new ModeleCommandes(commandes, client1).setLocation(1200,0);
		
		
	//new ModeleCreerProduit(produit,carte).setLocation(0, 600);
	//new ModeleCarte(carte, panier).setLocation(400, -0);
	//new ModelePanier(carte, panier, commandes,client1).setLocation(800, 0);
		
		
	}
}
