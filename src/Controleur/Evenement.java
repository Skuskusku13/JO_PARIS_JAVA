package Controleur;

public class Evenement {

	private int idevenement;
	private String type;
	private String dateEvent;
	private String nomEvenement;
	private String description;
	private String adresse;
	private String horraireD;
	private String horraireF;
	private int capacite;
	private int idcategorie;
	
	public Evenement(int idevenement, String type, String dateEvent, String nomEvenement, String description,
			String adresse, String horraireD, String horraireF, int capacite, int idcategorie) {
		this.idevenement = idevenement;
		this.type = type;
		this.dateEvent = dateEvent;
		this.nomEvenement = nomEvenement;
		this.description = description;
		this.adresse = adresse;
		this.horraireD = horraireD;
		this.horraireF = horraireF;
		this.capacite = capacite;
		this.idcategorie = idcategorie;
	}

	public Evenement(String type, String dateEvent, String nomEvenement, String description, String adresse,
			String horraireD, String horraireF, int capacite, int idcategorie) {
		this.type = type;
		this.dateEvent = dateEvent;
		this.nomEvenement = nomEvenement;
		this.description = description;
		this.adresse = adresse;
		this.horraireD = horraireD;
		this.horraireF = horraireF;
		this.capacite = capacite;
		this.idcategorie = idcategorie;
	}
	
	public Evenement(Categorie uneCategorie) {
		this.idevenement = 0;
		this.type = "";
		this.dateEvent = "";
		this.nomEvenement = "";
		this.description = "";
		this.adresse = "";
//		this.horraireD = "";
//		this.horraireF = "";
		this.capacite = 0;
		this.idcategorie = uneCategorie.getIdcategorie();
	}

	public int getIdevenement() {
		return idevenement;
	}

	public void setIdevenement(int idevenement) {
		this.idevenement = idevenement;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDateEvent() {
		return dateEvent;
	}

	public void setDateEvent(String dateEvent) {
		this.dateEvent = dateEvent;
	}

	public String getNomEvenement() {
		return nomEvenement;
	}

	public void setNomEvenement(String nomEvenement) {
		this.nomEvenement = nomEvenement;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getHorraireD() {
		return horraireD;
	}

	public void setHorraireD(String horraireD) {
		this.horraireD = horraireD;
	}

	public String getHorraireF() {
		return horraireF;
	}

	public void setHorraireF(String horraireF) {
		this.horraireF = horraireF;
	}

	public int getCapacite() {
		return capacite;
	}

	public void setCapacite(int capacite) {
		this.capacite = capacite;
	}

	public int getIdcategorie() {
		return idcategorie;
	}

	public void setIdcategorie(int idcategorie) {
		this.idcategorie = idcategorie;
	}
	
}
