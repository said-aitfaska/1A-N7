
import javax.swing.*;
import java.awt.*;

/** Permet de définir un client.
 * On peut définir son nom, son solde, son panier
  
	 * @author	Bonrepaux Rémi
*/

public class ControleurCreerClient extends JPanel{
	public ControleurCreerClient(Client client, Commandes commandes) {
		super(new FlowLayout());

		/** Rentrer le nom du produit*/
		JLabel NomClient = new JLabel("Votre nom:");
		JTextField texteNomClient = new JTextField(10);
		JButton bNomValider = new JButton("Valider");
		
		add(NomClient);
		add(texteNomClient); 
		add(bNomValider);
				
		bNomValider.addActionListener(ev ->
		client.setNom(texteNomClient.getText()));
		
		/** Rentrer le prix du produit*/
		JLabel solde = new JLabel("Votre solde:");
		JSpinner spinnerSolde = new JSpinner();
		JSpinner.NumberEditor spinnerEditor = new JSpinner.NumberEditor(spinnerSolde);
		spinnerSolde.setEditor(spinnerEditor);
		JButton bSolde = new JButton("Valider");

		add(solde);
		add(spinnerSolde);
		add(bSolde);

		// DÃ©finir les contrÃ´leurs
		bSolde.addActionListener(ev ->
				client.setSolde((Integer)spinnerEditor.getModel().getNumber()));
		
		/** Ajouter le produite à la carte*/
		JButton bajoutClient= new JButton("Terminer");
		add(bajoutClient);

		// DÃ©finir les contrÃ´leurs
		bajoutClient.addActionListener(ev ->
		commandes.ajouterCommande(client));
		
		/** à supprimer juste pour voir si ca marche*/
			
		bNomValider.addActionListener(ev -> 
		System.out.println("nom client: "+texteNomClient.getText()));
		bSolde.addActionListener(ev ->
		System.out.println("Solde client: "+(Integer)spinnerEditor.getModel().getNumber()));
	}
}