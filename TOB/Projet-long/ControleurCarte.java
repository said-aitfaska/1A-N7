
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
/** Permet d'afficher les produits sur la carte..
 * On peut aussi choisir d'ajouter ousupprimer un produit 
	 * @author	Bonrepaux Rémi
*/
public class ControleurCarte extends JPanel{
	
	public ControleurCarte(Carte carte, Panier panier) {
		super(new FlowLayout());
	
		/** Rentrer le prix du produit*/
		JLabel numeroProduit = new JLabel("Numéro du produit:");
		JSpinner spinnerNumProduit = new JSpinner();
		JSpinner.NumberEditor spinnerEditor = new JSpinner.NumberEditor(spinnerNumProduit);
		spinnerNumProduit.setEditor(spinnerEditor);
		JButton bNumValider = new JButton("Valider");
		JButton sauvegarder = new JButton("Sauvegarder");
		bNumValider.setBackground(Color.GREEN);
		add(numeroProduit);
		add(spinnerNumProduit);
		add(bNumValider);
		add(sauvegarder);
		
		// DÃ©finir les contrÃ´leurs
		//carte.afficher();	
		bNumValider.addActionListener(ev ->
		panier.ajouterProduit(carte.get((Integer)spinnerEditor.getModel().getNumber() - 1)));
		sauvegarder.addActionListener(ev -> carte.sauvegarder());	
		//panier.ajouterProduit(copieCarte.get((Integer)spinnerEditor.getModel().getNumber())));	
		
	}

}
