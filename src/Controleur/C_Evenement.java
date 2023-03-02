package Controleur;

import java.util.ArrayList;

import Modele.ModeleEvenement;

public class C_Evenement {

	public static void insertEvenement(Evenement unEvenement) {
		
		ModeleEvenement.insertEvenement(unEvenement);
		
	}
	
	public static ArrayList<Evenement> selectAllEvenements() {
		return ModeleEvenement.selectAllEvenements();
	}
}
