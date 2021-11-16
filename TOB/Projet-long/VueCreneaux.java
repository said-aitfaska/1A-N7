

import javax.swing.*;
import java.awt.*;
/** Permet d'afficher les créneaux disponibles.
 * On peut aussi choisir le créneau souhaité 
	 * @author	Bonrepaux Rémi
*/

public class VueCreneaux extends JTextArea {
	private int numero;
		
	public VueCreneaux(final Planning planning,Client client, int nbLignes, int nbColonnes) {
		super(nbLignes,nbColonnes);
		
				
		//for (Produit produit : carte) {
		//	this.append("Nom du produit: "+ produit.getNom() + " Prix: " + produit.getPrix() + " Euros. \n");

		//}
		for (numero=0;numero<8;numero++) {
			planning.get(numero).addObserver(new java.util.Observer(){
				public void update(java.util.Observable o , Object arg ) {
				mettreAJour(arg,planning,client);
				}
				});
		}
		
	}
		//carte.addObserver( (ce,prixProduit) -> this.append("Prix du produit: " + prixProduit + "\n"));
		
		private void mettreAJour(Object arg, Planning planning,Client client) {
			//this.setText(planning.get(numero).getEtat());
			 
			//setVisible(false);
			//new ModelCreneaux(planning,client);
	}
	public static void main(String [] args)
	{

	}
	private void majNumero(int leNumero) { 
		//try {
			this.numero ++;
			
		//}catch(CreneauInvalideException e) {
		//	if(leNumero > 5) {
		//		System.out.println(" impossible de choisir ce crenaux ! " );
		//	}
		//}
	}
}