import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Observable;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;  // Import the IOException class to handle errors

import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files

/** Permet de définir la carte du CROUS.
 * On peut la modifier en ajoutant et supprimant des prooduits
 *
*/

public class Carte extends Observable implements Iterable<Produit> {
	
	/** Liste de Produits. */
	private List<Produit> carte;
	static String chemin_carte = "carte.txt";
	
	/** Constructeur de la commande. */
	public Carte() {
		this.carte = new ArrayList<Produit>(20);
		this.charger();
	}
	
	/** Ajouter un produit à la carte. */
	public void ajouterProduit(Produit produit) {
		Produit ajout = new Produit(produit);

		this.carte.add(ajout);
		this.setChanged();
		this.notifyObservers(ajout.getNom());
		this.setChanged();
		this.notifyObservers(ajout.getPrix());
	}

	/** Supprimer un produit de la carte. */
	public void suprimerProduit(Produit produit) {
		this.carte.remove(produit);
	}

	// renvoie une vue non modifiable de la liste spécifiée
	@Override
	public java.util.Iterator<Produit> iterator() {
		return Collections.unmodifiableList(this.carte).iterator(); 
	}
	/** Obtenir le produit de la carte à l'indice i. */
	void afficher()
	{
		for(Produit p : carte)
		p.afficher();	

	}
	void charger()
	{
			carte.clear();
	 		/*

			Charger la carte
			*/
			try{
				File fichier = new File(chemin_carte);
				Scanner lecteur = new Scanner(fichier);
				while(lecteur.hasNextLine())
				{
					String nom = lecteur.nextLine();
					int prix=  Integer.parseInt(lecteur.nextLine());
					String type=  lecteur.nextLine();	
					String chem_image = lecteur.nextLine();
					System.out.println(chem_image);
					//int prix = 0;
					Produit p = new Produit(nom,prix,type, chem_image);
					if(p == null)
						System.out.println("Nul");
					this.ajouterProduit(p);
					System.out.println("Nom : "+ nom + "\n prix : "+ prix + "\n type : "+type+ "\n chemin image : "+ chem_image);

				}
			}
			catch(FileNotFoundException e)
			{
				System.out.println("Erreur");
				}
	}
	void sauvegarder()
	{
		try{
		FileWriter fichier = new FileWriter(chemin_carte);
		
				for(Produit p : carte)
			{
				String nom = p.getNom();
				int prix = p.getPrix();
				String type= p.getType();
				fichier.write(nom+"\n");
				fichier.write(prix+"\n");
				//fichier.write(p.getN+"\n");
				System.out.println("Sauvegarde de :" + nom + "prix : "+ prix+ " euros" + " " + type);
			}
			fichier.close();
		}

		catch(IOException e)
		{

		}

		
	}
	public Produit get(int indice) {
		return carte.get(indice);
	}

}
