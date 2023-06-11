package Controleur;

import java.util.ArrayList;

import Modele.ModeleEvenement;

public class C_Evenement {

	public static void insertEvenement(Evenement unEvenement) {
		
		ModeleEvenement.insertEvenement(unEvenement);
		
	}
	
	public static void deleteEvent(String type, String nomEvent, String horraireD, String horraireF) {
		
		ModeleEvenement.deleteEvent(type, nomEvent, horraireD, horraireF);
		
	}
	
	public static ArrayList<Evenement> selectAllEvenements() {
		return ModeleEvenement.selectAllEvenements();
	}
}
