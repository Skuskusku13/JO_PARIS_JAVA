package Controleur;

public class Services {
	
	private int idservice, idtypeservice;
	private String libelle, adresse, tel, email, image;
	private float prix;
	
	public Services(int idservice, String libelle, String adresse, float prix, String tel, String email, String image, int idtypeservice) {
		this.idservice = idservice;
		this.libelle = libelle;
		this.adresse = adresse;
		this.prix = prix;
		this.tel = tel;
		this.email = email;
		this.image = image;
		this.idtypeservice = idtypeservice;
	}
	
	public Services(String libelle, String adresse, float prix, String tel, String email, String image, int idtypeservice) {
		this.libelle = libelle;
		this.adresse = adresse;
		this.prix = prix;
		this.tel = tel;
		this.email = email;
		this.image = image;
		this.idtypeservice = idtypeservice;
	}

	public Services(TypesServices unTypeService) {
		this.idservice = 0;
		this.libelle = "";
		this.adresse = "";
		this.prix = 0;
		this.tel = "";
		this.email = "";
		this.image = "";
		this.idtypeservice = unTypeService.getIdTypeService();
	}

	public int getIdservice() {
		return idservice;
	}

	public void setIdservice(int idservice) {
		this.idservice = idservice;
	}

	public int getIdtypeservice() {
		return idtypeservice;
	}

	public void setIdtypeservice(int idtypeservice) {
		this.idtypeservice = idtypeservice;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public float getPrix() {
		return prix;
	}

	public void setPrix(float prix) {
		this.prix = prix;
	}

	
}

