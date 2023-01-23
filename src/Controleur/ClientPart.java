package Controleur;

public class ClientPart extends User {

	private String prenom;

	public ClientPart(int iduser, String nom, String email, String mdp, String tel, String role, String prenom) {
		super(iduser, nom, email, mdp, tel, role);
		this.prenom = prenom;

	}

	public ClientPart(String nom, String email, String mdp, String tel, String role, String prenom) {
		super(nom, email, mdp, tel, role);
		this.prenom = prenom;
	}
	

	public ClientPart() {
		super();
		this.prenom = "";
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	
	
}
