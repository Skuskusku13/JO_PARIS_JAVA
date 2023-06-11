package Controleur;

public class Inscription {
	private String dateInscription, commentaire, statut;
	private int idinscription, idevenement, iduser;
	
	public Inscription(int idinscription, String dateinscription, String commentaire, String statut, int idevenement, int iduser) {
		this.idinscription = idinscription;
		this.dateInscription = dateinscription;
		this.commentaire = commentaire;
		this.statut = statut;
		this.idevenement = idevenement;
		this.iduser = iduser;
	}
	
	public Inscription(String dateinscription, String commentaire, String statut, int idevenement, int iduser) {
		this.dateInscription = dateinscription;
		this.commentaire = commentaire;
		this.statut = statut;
		this.idevenement = idevenement;
		this.iduser = iduser;
	}

	public Inscription(Evenement unEvenement, User unUser) {
		this.dateInscription = "";
		this.commentaire = "";
		this.statut = "";
		this.idevenement = unEvenement.getIdevenement();
		this.iduser = unUser.getIduser();
	}

	public String getDateInscription() {
		return dateInscription;
	}

	public void setDateInscription(String dateInscription) {
		this.dateInscription = dateInscription;
	}

	public String getCommentaire() {
		return commentaire;
	}

	public void setCommentaire(String commentaire) {
		this.commentaire = commentaire;
	}

	public String getStatut() {
		return statut;
	}

	public void setStatut(String statut) {
		this.statut = statut;
	}

	public int getIdinscription() {
		return idinscription;
	}

	public void setIdinscription(int idinscription) {
		this.idinscription = idinscription;
	}

	public int getIdevenement() {
		return idevenement;
	}

	public void setIdevenement(int idevenement) {
		this.idevenement = idevenement;
	}

	public int getIduser() {
		return iduser;
	}

	public void setIduser(int iduser) {
		this.iduser = iduser;
	}
	
	
}
