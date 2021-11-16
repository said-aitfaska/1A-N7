

import javax.swing.*;
import java.awt.*;
/** Permet d'afficher les produits choisie.
 * On peut aussi choisir d'ajouter ou supprimer un produit 
	 * @author	Saïd ait faska
         * @version 	1.0
*/
public class ControleurPayer extends JPanel{
	public ControleurPayer(Client client, Panier panier) {
		super(new FlowLayout());
		
		 
		
		/** Valider le panier*/
		JButton bNumValider = new JButton("Valider le paiement");
		bNumValider.setBackground(Color.GREEN);
		add(bNumValider);
	
		bNumValider.addActionListener(ev ->
		client.setSolde(client.getSolde()-panier.getPrixTotal()));
		bNumValider.addActionListener(ev -> this.setVisible(false));	
		JLabel merci = new JLabel("commande validee !");		
		bNumValider.addActionListener(ev -> add(merci));
			//if(panier.getPrixTotal()> client.getSolde()) {
				//System.out.println(e.getMessage());
			
		
	}
}
