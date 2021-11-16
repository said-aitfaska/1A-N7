

/**
  * Classe CreneauInvalideException Projet-long
  * @author		Saïd ait faska
  * @version    1.0
  */

public class CreneauInvalideException extends Error{
	
	
	/* Constructeur CreneauInvalideException
	 * 
	 */
	public CreneauInvalideException(String message) {
		super("Le creneaux est complet !");
	}
	
	public CreneauInvalideException(Throwable e) {
		super(e);
	}
	
	public CreneauInvalideException() {
		super();
	}

}