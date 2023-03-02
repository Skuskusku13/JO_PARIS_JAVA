package Controleur;

import java.util.ArrayList;

import Modele.ModeleCategorie;

public class C_Categorie {
	

	public static final String SelectWhereCategorie = null;

	public static void insertCategorie(Categorie uneCategorie) {
		ModeleCategorie.insertCategorie(uneCategorie);
	}
	
	public static ArrayList<Categorie> selectAllCategories() {
		return ModeleCategorie.selectAllCategories();
	}
	
}
