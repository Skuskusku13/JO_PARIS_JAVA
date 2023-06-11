package Controleur;

import java.util.ArrayList;

import Modele.ModeleTypesServices;

public class C_TypesServices {
	
	public static void insertTypeService(TypesServices unTypeService) {
		ModeleTypesServices.insertTypeService(unTypeService);
	}
	
	public static ArrayList<TypesServices> selectAllTypesServices() {
		return ModeleTypesServices.selectAllTypesServices();
	}
	
	public static void deleteTypeService(String libelle) {
		ModeleTypesServices.deleteTypeService(libelle);
	}
}
