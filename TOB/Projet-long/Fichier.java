import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Fichier{


	public static Carte ouvrir(String chemin)
	{
		Carte carte = new Carte();
      try{
			File fichier = new File(chemin);
			Scanner reader = new Scanner(fichier);
			while(reader.hasNextLine())
			{
				String nom = reader.nextLine();
				boolean dispo;
		/*		if(reader.nextLine() == "true")
					dispo = true;
				else 
					dispo =false;*/
				String type = reader.nextLine();
			}

			}
			catch(FileNotFoundException e)
			{
				System.out.println("Erreur dans le chargement du fichier");
			}
			return carte;
	}
	public static void main(String []args)
	{
		Carte c = ouvrir("Fichier.java");
	}
}
