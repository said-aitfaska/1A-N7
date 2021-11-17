import java.awt.Color;

/** Programme de Cercle.
  * @author Saïd AIT FASKA.
  * groupe	 A.
  */
public class Cercle implements Mesurable2D {
	    /** declaration des variables.
	    */
		private Point centre;
		/** Rayon du cercle.
		 */
		private double rayon;
		/** Couleur du cercle.
		 */
		private Color couleur;
		/** Declaration de PI.
		 */
		public static final double PI = Math.PI;
		/** Translater un cercle.
		 * @param  dx déplacement suivant  X
		 * @param  dy  éplacement suivant  Y
		 */

		public void translater(double dx, double dy) {
			this.centre.translater(dx, dy);
		}

		/** Obtenir le centre d'un cercle.
		 * @return centre centre du cercle
		 */
		public Point getCentre() {
			return new Point(centre.getX(),
					centre.getY());
		}

		/** Obtenir le rayon du cercle.
		 * @return rayon du cercle
		 */
		public double getRayon() {
			assert this.rayon > 0;
			return this.rayon;
		}

		/** Obtenir le diametre d'un cercle.
		 * @return diametre diametre du cercle
		 */
		public double getDiametre() {
			assert this.rayon > 0;
			return this.rayon * 2;
		}

		/** Verifier que le point est dans le cercle.
		 * @param p1 point a verifier.
		 * @return boolean
		 */
		public boolean contient(Point p1) {
			assert p1 != null;
			return (p1.distance(this.centre)
					<= this.rayon);
		}

		/** Obtenir le perimetre d'un cercle.
		 * @return rayon du cercle
		 */
		public double perimetre() {
			return this.rayon * 2 * PI;
		}

		/**Obtenir la couleur d'un cercle.
		 * @return couleur du cercle
		 */
		public Color getCouleur() {
			return this.couleur;
		}

		/** Changer la couleur du cercle.
		 * @param nouveauCouleur du cercle
		 */
		public void setCouleur(Color nouveauCouleur) {
			assert nouveauCouleur != null;
			this.couleur = nouveauCouleur;
		}

		/** Obtenir l'aire du cercle.
		 * @return aire du cercle
		 */
		public double aire() {
			return PI * Math.pow(this.rayon, 2);
		}

		/** Construir un cercle a partir.
		 * d'un point centre et un rayon
		 * @param centre centre du cercle
		 * @param rayon rayon de cercle
		 */
		public Cercle(Point centre, double rayon) {
			assert centre != null;
			assert rayon > 0;
			this.centre = new Point(centre.getX(), centre.getY());
			this.rayon = rayon;
			this.couleur = Color.blue;
		}

		/** Construir un cercle a partir  de deux points.
		 *  diamétralement opposés et de sa couleur
		 * @param p1 pointp1
		 * @param p2 pointp2
		 * @param nouvelleCouleur couleur de cercle
		 */
		public Cercle(Point p1, Point p2, Color nouvelleCouleur) {
			this(p1, p2);
			assert p1 != null;
			assert p2 != null;
			this.setCouleur(nouvelleCouleur);
		}

		/** construire un cercle à partir de deux points.
		 *  diamétralement opposés
		 * @param p1 point 1
		 * @param p2 point 2
		 */
		public 	Cercle(Point p1, Point p2) {
			assert p1 != null;
			assert p2 != null;
		    double cx = (p1.getX() + p2.getX()) / 2;
		    double cy = (p1.getY() + p2.getY()) / 2;
		    this.centre = new Point(cx, cy);
			assert p1.distance(p2) > 0;
			this.rayon = p1.distance(p2) / 2;
			this.couleur = Color.blue;
		}

		/** Créer un cercle à partir de deux points.
		 * @param p1 point 1
		 * @param p2 point 2
		 * @return cercle
		 */
		public static Cercle  creerCercle(Point p1, Point p2) {
			assert p2 != null;
			assert p1 != null;
			return  new Cercle(p1, p2.distance(p1));
		}

		/** Afficher un cercle sous forme.
		 * C rayon@(abssice ,ordonee)
		 * @return 	affichage d'un cercle
		 */
		public String toString() {
			return "C" + this.rayon + "@" + this.centre;
		}

		/** Changer le rayon du cercle.
		 * @param nouveauRayon a  donner au cercle
		 */
		public void setRayon(double nouveauRayon) {
			assert nouveauRayon > 0;
			this.rayon = nouveauRayon;
		}

		/** Changer le diametre du Cercle.
		 * @param nouveauDiametre a donner au cercle nouveau
		 */
		public void setDiametre(double nouveauDiametre) {
			assert nouveauDiametre > 0;
			this.rayon = nouveauDiametre / 2;
		}

		/** Afficher le cercle.
		 */
		public void afficherCercle() {
			System.out.print(this);
	    }
}
