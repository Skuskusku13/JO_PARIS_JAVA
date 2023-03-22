package Controleur;

public class TypeService {
	private int idTypeService;
	private String libelle;
	
	
	public TypeService(int idTypeService, String libelle) {
		this.idTypeService = idTypeService;
		this.libelle = libelle;
	}
	
	public TypeService(String libelle) {
		this.libelle = libelle;
	}
	
	public TypeService() {
		this.idTypeService = 0;
		this.libelle = "";
	}

	public int getIdTypeService() {
		return idTypeService;
	}

	public void setIdTypeService(int idTypeService) {
		this.idTypeService = idTypeService;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	
	

}
