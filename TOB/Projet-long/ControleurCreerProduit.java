
import javax.swing.*;
import java.awt.*;

/** Permet de construire un produit.
 * On peut définir son nom, son prix,
 * sa disponibilité est vraie par défault 
*/

import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle error
import java.util.Scanner; // Import the Scanner class to read text files

import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;  //



public class ControleurCreerProduit extends JPanel{
	String chemin_carte = "carte.txt";	
	public void enregistre_carte(String nom, int prix, String type)
	{
		try{
		 FileWriter fichier = new FileWriter(chemin_carte);
		  fichier.write(nom+"\n");
          fichier.write(prix+"\n");
           fichier.write(type);
			  fichier.close();
			  System.out.println("sauvegarde...");
		}
		catch(IOException e)
		{
			}
	}
	public ControleurCreerProduit(Produit produit, Carte carte, Planning planning) {
		super(new FlowLayout());
		
		JButton bNumconf = new JButton("Renouveler le planning");
		bNumconf.setBackground(Color.GREEN);
		add(bNumconf);
		bNumconf.addActionListener(ev ->
		planning.raz());
		/** Rentrer le nom du produit*/
		JLabel NomProduit = new JLabel("Nom du produit:");
		JTextField texteNomProduit = new JTextField(10);

		JButton bValider = new JButton("Sauvegarder");
			
		add(NomProduit);
		add(texteNomProduit); 
		add(bValider);
		bValider.setBackground(Color.GREEN);
				
		bValider.addActionListener(ev ->
		produit.setNom(texteNomProduit.getText()));
		//bValider.addActionListener(ev-> produit.setPrix(texte_c));	
		/** Rentrer le prix du produit*/
		JLabel prixProduit = new JLabel("Prix du produit:");
		JSpinner spinnerPrixProduit = new JSpinner();
		JSpinner.NumberEditor spinnerEditor = new JSpinner.NumberEditor(spinnerPrixProduit);
		spinnerPrixProduit.setEditor(spinnerEditor);
		JButton bPrixValider = new JButton("Valider");


		JLabel cheminImage = new JLabel("Chemin de l'image du produit");
		JTextField texte_cheminImage  = new JTextField(10);
		add(texte_cheminImage);
		add(cheminImage);
		add(prixProduit);
		add(spinnerPrixProduit);
		//add(bPrixValider);
		bPrixValider.setBackground(Color.GREEN);
	
				/** Ajouter le produite à la carte*/
		JButton bajoutProduit = new JButton("Ajouter le produit à la carte");
		add(bajoutProduit);
		int prix = (Integer)spinnerEditor.getModel().getNumber();
		String type = "";
		String nom = texteNomProduit.getText();
		boolean Disponibilite= true;

		String Imagepath = "images/"+ nom+".jpg";
		Produit p = new Produit(prix,type,nom,Imagepath,Disponibilite);
		bValider.addActionListener(ev-> p.setNom(texteNomProduit.getText()));
		bValider.addActionListener(ev ->p.setPrix((Integer)spinnerEditor.getModel().getNumber()));
		bValider.addActionListener(ev-> p.updateChemin());
	
	//	bValider.addActionListener(ev->p.afficher());
		// DÃ©finir les contrÃ´leurs
		bajoutProduit.addActionListener(ev->enregistre_carte(nom, prix,type));
		bajoutProduit.addActionListener(ev ->
		carte.ajouterProduit(p));
	 	bajoutProduit.addActionListener(ev->carte.afficher());	
		
		bajoutProduit.addActionListener(ev-> carte.sauvegarder());
		bajoutProduit.addActionListener(ev -> carte.charger());	
		/** à supprimer juste pour voir si ca marche*/
			
		bValider.addActionListener(ev -> 
		System.out.println("nom produit: "+texteNomProduit.getText()));
		bPrixValider.addActionListener(ev ->
		System.out.println("Prix produit: "+(Integer)spinnerEditor.getModel().getNumber()));
	}
}
