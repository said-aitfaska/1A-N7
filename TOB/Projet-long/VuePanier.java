
import javax.swing.*;
import java.awt.*;
import java.util.Iterator;
/**
 * Classe VuePanier Projet-long
 * @author	   Saïd ait faska
 * @version    1.0
 */

public class VuePanier extends JTextArea {
	private int numero = 0;

	public VuePanier(final Carte carte, Panier panier, int nbLignes, int nbColonnes) {
		super(nbLignes, nbColonnes);
		this.setEditable(false);
		
		panier.addObserver(new java.util.Observer(){
			public void update(java.util.Observable o, Object arg) {
				mettreAJour(panier);
			}
		});
	}
				
				public void afficheTotal(Panier panier)
				{
					//System.out.println("Nb lignes : " + this.getLineCount()/3);
					this.append("Total : " + String.valueOf(panier.getPrixTotal())+ " euros\n\n" );
				}
		private void mettreAJour(Panier panier) {
			//this= new JTextArea();
			Iterator<Produit> it = panier.iterator();
			this.setText(null); // on nettoie tout le texte
			panier.AfficherPanier();
			String resultat=  "";	
			int numero = 0;
			while(it.hasNext())
			{
			resultat+=">> Nom : " + panier.get(numero).getNom() + "\n";
			resultat+=">> Prix : " + panier.get(numero).getPrix() + " Euro" +  "\n";
			resultat+="                    ------------   \n ";
			numero++;
			it.next();
			}
			resultat+="Total : " + String.valueOf(panier.getPrixTotal())+ " euros\n\n";

			//this.setText(String.valueOf(panier.getPrixTotal()));
			this.append(resultat);
			numero = numero +1;
			
		}
		
	public static void main(String [] args)
	{
		
	}

}
