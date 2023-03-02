package Controleur;

public class Admin extends User {

	public Admin() {
		super();
	}

	public Admin(int iduser, String nom, String email, String mdp, String tel, String role) {
		super(iduser, nom, email, mdp, tel, role);
	}

	public Admin(String nom, String email, String mdp, String tel, String role) {
		super(nom, email, mdp, tel, role);
	}
	

}
