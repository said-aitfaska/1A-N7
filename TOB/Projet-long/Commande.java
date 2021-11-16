class Commande
{

	float prix;
	String image;
	String nom;
	Menu menu;
	public void setPrix(float Prix)
	{
		this.prix = Prix;
	}
	public void setImage(String chemin)
	{
		this.image = chemin;
	}
	public void setMenu(Menu menu)
	{
		this.menu = menu;
	}

	/*
		@return prix
	*/
	public float getPrix()
	{
		return prix;
	}

	/*
		@return le chemin de l'image
	*/
	public String getImage(){
		return image;
	}
	public String getNom(){
		return nom;
	}
	public Menu getMenu()
	{
		return menu;
	}

	public void supprimerCommande(Commande c)
	{
	}
	public void annulerCommande(Commande c)
	{
	}

}
