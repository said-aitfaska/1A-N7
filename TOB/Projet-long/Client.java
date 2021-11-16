import java.util.Observable;

import javax.swing.JOptionPane;

/** Permet de définir un client.
 * On peut obtenir son nom, son identifiant,
 * son panier, son solde. 
	 * @author	Bonrepaux Rémi
*/

public class Client extends Observable {
	public Client() {
	}
	public Client(String nom, Panier panier) {
		this.nom = nom;
		this.panier = panier;
		
	}
	public Client(String nom, Panier panier, int creneau) {
		this.nom = nom;
		this.panier = panier;
		this.creneau = creneau;
	}
	public Client(Client client) {
		this.nom = client.nom;
		this.panier = client.panier;
		this.solde = client.solde;
	}

	public Client(String nom, int solde) {
		this.nom = nom;
		this.solde = solde;
	}
	/** Le nom du client. */
	private String nom;
	
	/** Le montant disponible pour payer. */
	private int solde = 10;
	
	/** Le numéro du créneau de l'étudiant. */
	private int creneau;
	
	/** Le panier de l'étudiant. */
	private Panier panier;
	
	/** Obtenir le nom de l'étudiant. */
	public String getNom() {
		return this.nom;
	}
	/** Définir le nom de l'étudiant. */
	public void setNom(String nom) {
		this.nom = nom;
	}

	/** Obtenir le solde de l'étudiant. */
	public int getSolde() {
		return this.solde;
	}
	/** Définir le solde de l'étudiant. */
	public void setSolde(int solde) { 
		if(solde < 0) {
			//System.out.println("Votre sold n'est pas Suffisant !\n");
			//JOptionPane.showMessageDialog(null, "Votre Solde est insuffisant !");
			JOptionPane.showMessageDialog(null,
				    "Votre solde est insuffisent !",
				    "Sold error",
				    JOptionPane.ERROR_MESSAGE);
		}
		else {
		this.solde = solde;
		this.setChanged();
		this.notifyObservers(solde);
		}
	}
	/** Définir le creneau de l'étudiant. */
	public void setCreneau(int num) {
		this.creneau = num;
	}
	/** Obtenir le créneau de l'étudiant. */
	public int getCreneau() {
		return this.creneau;
	}

	/** Obtenir le panier de l'étudiant. */
	public Panier getPanier() {
		return this.panier;
	}

	/** Ajouter le panier au client. */
	public void ajouterPanier(Panier panier) {
		this.panier = panier;
	}
}
