import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Observable;

import javax.swing.JOptionPane;

/** Permet de définir un créneau.
 * C'est  une liste de client, qui vaut 20 au maximum. 
 * Sinon cela signifie que le creneau est complet 
	 * @author	Bonrepaux Rémi
*/
public class Creneau extends Observable implements Iterable<Client> {
	/** Liste des creneaux */
	private List<Client> creneau;
	
	/**
	 * Constructeur crneaux.
	 */
	public Creneau(String etat, int numero,int nb) {
		creneau = new ArrayList<Client>(5);
		this.numero = numero;
		this.etat = etat;
		this.nombreClient = nb;
	}
	public Creneau(Creneau creneau) {
		//Creneau creneau = new ArrayList<Client>(5);
		this.numero = creneau.numero;
		this.etat = creneau.etat;
		this.nombreClient = creneau.nombreClient;
	}
	private String etat;
	public String getEtat() { 
		return this.etat;
	}
	public void setEtat(String etat) { 
		this.etat = etat;
	}

	private int nombreClient;
	
	public Client get(int indice) {
		return creneau.get(indice);
	}
		
	public int getNombreClient() {
		return this.nombreClient;
	}	
	public void setNombreClient(int nb) {
		this.nombreClient = nb;
	}
	/** Ajouter un client au creneau. */
	public void ajouterClient() {
		//Client client = new Client(client);
		if (this.nombreClient < 2) {
			this.nombreClient = this.nombreClient + 1 ;
			this.etat = "Disponible";	
		}
		else if (this.nombreClient == 2){
			//nombreClient = 0;
			this.nombreClient = this.nombreClient + 1 ;
			this.etat = "Complet";
			//this.setChanged();
			//this.notifyObservers(creneau);
		}
		else {
			JOptionPane.showMessageDialog(null,
				    "Le creneau est complet  !",
				    "Creneau error",
				    JOptionPane.ERROR_MESSAGE);
			//System.out.println("Le creneau est complet !");
			this.etat = "Complet";
		}
		
	}
	private int numero;
	public void setNumero(int num) {
		this.numero = num;
}
	public int getNumero() {
		return this.numero;
}
	
	@Override
	public Iterator<Client> iterator() {
		return Collections.unmodifiableList(this.creneau).iterator();
	}
	
}
