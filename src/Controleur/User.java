package Controleur;

public class User {

	protected int iduser;
	protected String nom;
	protected String email;
	protected String mdp;
	protected String tel;
	protected String role;
	
	public User(int iduser, String nom, String email, String mdp, String tel, String role) {
		this.iduser = iduser;
		this.nom = nom;
		this.email = email;
		this.mdp = mdp;
		this.tel = tel;
		this.role = role;
	}
	
	
	public User(String nom, String email, String mdp, String tel, String role) {
		this.iduser = 0;
		this.nom = nom;
		this.email = email;
		this.mdp = mdp;
		this.tel = tel;
		this.role = role;
	}
	
	
	public User() {
		this.iduser = 0;
		this.nom = "";
		this.email = "";
		this.mdp = "";
		this.tel = "";
		this.role = "";
	}


	public int getIduser() {
		return iduser;
	}


	public void setIduser(int iduser) {
		this.iduser = iduser;
	}


	public String getNom() {
		return nom;
	}


	public void setNom(String nom) {
		this.nom = nom;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getMdp() {
		return mdp;
	}


	public void setMdp(String mdp) {
		this.mdp = mdp;
	}


	public String getTel() {
		return tel;
	}


	public void setTel(String tel) {
		this.tel = tel;
	}


	public String getRole() {
		return role;
	}


	public void setRole(String role) {
		this.role = role;
	}




	
	
}
