package Controleur;

import Modele.ModeleUser;

public class C_User {

	
	public static User selectWhereUser(String email, String mdp) {
		
		// on controle les données 
		
		return ModeleUser.selectWhereUser(email, mdp);
		
	}
}
