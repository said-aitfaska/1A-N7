
import java.util.List;
import java.util.Observable;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
/** Permet de définir les commandes des clients.

* On peut le modifier, obtenir son prix total
@author Yassinne Kouchida
* et l'aficher.
*/

public class Commandes  extends Observable implements Iterable<Client> {

	static String chemin_commandes = "commandes.txt";

	/** Liste de clients. */
	private List<Client> commandes;
	
	
	/** Constructeur de la commande. */
	public Commandes() {
		this.commandes = new ArrayList<Client>(20);
		this.charger();
	}
	
	/** Ajouter la commande d'un client. */
	public void ajouterCommande(Client client) {
		Client nouveau = new Client(client);
		this.commandes.add(client);
	 
		this.setChanged();
		this.notifyObservers(client);
	}

	/** Supprimer un produit du panier. */
	public void suprimerProduit(Client client) {
		this.commandes.remove(client);
	}

	
	/** Obtenir le produit du panier à l'indice i. */
	public Client get(int indice) {
		return commandes.get(indice);
	}

	@Override
	public Iterator<Client> iterator() {
		return Collections.unmodifiableList(this.commandes).iterator();
	}
	
	void charger()
	{
	 		/*
			Charger la carte
			*/
			try{
				File fichier = new File(chemin_commandes);
				Scanner lecteur = new Scanner(fichier);
				while(lecteur.hasNextLine())
				{
					String nom = lecteur.nextLine();
					int creneau=  Integer.parseInt(lecteur.nextLine());
					//Panier panier = lecteur.next();
					
					Client clien = new Client(nom,creneau);
					if(clien == null)
						System.out.println("Nul");
					this.ajouterCommande(clien);
					System.out.println("Nom : "+ nom + "\n creneau : "+ creneau + "\n type : ");

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
		FileWriter fichier = new FileWriter(chemin_commandes);
		
				for(Client client : commandes)
			{
				String nom = client.getNom();
				int creneau = client.getCreneau();
				
				fichier.write(nom+"\n");
				fichier.write(creneau+"\n");
				//fichier.write(p.getN+"\n");
				System.out.println("Sauvegarde de :" + nom + "creneau : "+ creneau);
			}
			fichier.close();
		}catch(IOException e)
		{

		}

		
	}

}
