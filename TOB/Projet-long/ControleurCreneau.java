

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
/** Permet d'afficher les creneaux
 * Le client peut choisir un creneau disponible 
	 * @author	Bonrepaux Rémi
*/
public class ControleurCreneau extends  JPanel{
	
	public ControleurCreneau(Planning planning,Carte carte, Panier panier,Commandes commandes, Client client) {
		super(new FlowLayout());
				
		
		/** Rentrer le numéro du créneau*/
		JLabel numeroCreneaux = new JLabel("Numéro du créneau:");
		JSpinner spinnerNumCreneau = new JSpinner();
		JSpinner.NumberEditor spinnerEditor = new JSpinner.NumberEditor(spinnerNumCreneau);
		spinnerNumCreneau.setEditor(spinnerEditor);
		JButton bNumValider = new JButton("Valider");
		bNumValider.setBackground(Color.GREEN);
		add(numeroCreneaux);
		add(spinnerNumCreneau);
		add(bNumValider);
		JButton bNumconf = new JButton("Confirmer");
		bNumconf.setBackground(Color.GREEN);
		bNumconf.addActionListener(ev -> this.setVisible(false));
		add(bNumconf);
		
		try {
			bNumValider.addActionListener(ev ->
			planning.get((Integer)spinnerEditor.getModel().getNumber() - 1).ajouterClient());
			//communiquer le numéro du créneau
			bNumValider.addActionListener(ev ->
			planning.get((Integer)spinnerEditor.getModel().getNumber() - 1).setNumero((Integer)spinnerEditor.getModel().getNumber() - 1));
			bNumValider.addActionListener(ev ->
			System.out.println("Nombre de clients pour ce créneau: "+ planning.get((Integer)spinnerEditor.getModel().getNumber() - 1).getNombreClient()));
			
		bNumValider.addActionListener(ev ->
		client.setCreneau((Integer)spinnerEditor.getModel().getNumber()));
		
		bNumconf.addActionListener(ev ->
		planning.sauvegarder());
	new ModelePanier(carte, panier,commandes, client).setLocation(800, 0);
		bNumconf.addActionListener(ev ->
		new ModelePanier(carte, panier,commandes, client).setLocation(800, 0));
		bNumconf.addActionListener(ev ->
		new ModeleCarte(carte, panier).setLocation(400, -0));
		}catch (CreneauInvalideException e) {
				System.out.println("Créneau complet, il faut modifier le planning ");
		}
		
		
	}

}
