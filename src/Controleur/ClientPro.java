package Controleur;

public class ClientPro extends User {
	
	private String num_siret;
	private String adresse;
	
	

	public ClientPro(int iduser, String nom, String email, String mdp, String tel, String role, String num_siret, String adresse) {
		super(iduser, nom, email, mdp, tel, role);
		this.num_siret = num_siret;
		this.adresse = adresse;
	}
	
	public ClientPro(String nom, String email, String mdp, String tel, String role, String num_siret, String adresse) {
		super(nom, email, mdp, tel, role);
		this.num_siret = num_siret;
		this.adresse = adresse;
	}
	
	public ClientPro() {
		super();
		this.num_siret = "";
		this.adresse = "";
	}
	
	public String getNum_siret() {
		return num_siret;
	}
	public void setNum_siret(String num_siret) {
		this.num_siret = num_siret;
	}
	public String getAdresse() {
		return adresse;
	}
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	
	
	
}
