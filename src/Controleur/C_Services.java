package Controleur;

import java.util.ArrayList;

import Modele.ModeleServices;

public class C_Services {

	public static void insertService(Services unService) {
		ModeleServices.insertService(unService);
	}
	
	public static void deleteService(String libelle, String tel) {
		ModeleServices.deleteService(libelle, tel);
	}
	
	public static ArrayList<Services> selectAllServices() {
		return ModeleServices.selectAllServices();
	}
}
