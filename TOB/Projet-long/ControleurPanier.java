
import javax.swing.*;
import java.awt.*;
/** Permet d'afficher les produits choisie.
 * On peut aussi choisir d'ajouter ou supprimer un produit 
	 * @author	Saïd ait faska
         * @version 	1.0
*/
public class ControleurPanier extends JPanel{
	public ControleurPanier(Panier panier, Commandes commandes, Client client) {
		super(new FlowLayout());
		
		/**Bouton pour afficher le prix*/
		//JButton //PrixTotal = new JButton("Prix Total : ");
		//JTextField textePrix = new JTextField(10);
		//add(//PrixTotal);
		//add(textePrix);
		
		//PrixTotal.setBackground(Color.YELLOW);
		//add(spinnerNumProduit);
		JPanel centre = new JPanel(new FlowLayout());
		this.add(centre, FlowLayout.LEFT);
		//centre.add(PrixTotal);
		//PrixTotal.setHorizontalAlignment(JLabel.LEFT);
		//centre.add(PrixTotal);
		/*PrixTotal.addActionListener(ev ->
		textePrix.setText("  " + panier.getPrixTotal() + " Euros"));*/
		
		/** Valider le panier*/
		JButton bNumValider = new JButton("Valider le panier");
		bNumValider.setBackground(Color.GREEN);
		add(bNumValider);
		
		// DÃ©finir les contrÃ´leurs
	
		bNumValider.addActionListener(ev ->
		client.ajouterPanier(panier));
		
		bNumValider.addActionListener(ev ->
		commandes.ajouterCommande(client));
		
		bNumValider.addActionListener(ev ->
		commandes.sauvegarder());
	}
}
