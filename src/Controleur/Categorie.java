package Controleur;

public class Categorie {
	
	private int idcategorie;
	private String libelle;
	
	public Categorie(int idcategorie, String libelle) {
		this.idcategorie = idcategorie;
		this.libelle = libelle;
	}
	
	public Categorie(String libelle) {
		this.libelle = libelle;
	}
	
	public Categorie() {
		this.idcategorie = 0;
		this.libelle = "";
	}
	

	public int getIdcategorie() {
		return idcategorie;
	}

	public void setIdcategorie(int idcategorie) {
		this.idcategorie = idcategorie;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	
	
}

