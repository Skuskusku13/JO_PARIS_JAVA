package Controleur;

import java.util.ArrayList;

import Modele.ModeleUser;

public class C_User {

	
	public static User selectWhereUser(String email, String mdp) {
		
		// on controle les données 
		
		return ModeleUser.selectWhereUser(email, mdp);
		
	}
	
	public static ArrayList<User> selectAllUser() {
		
		// on controle les données 
		
		return ModeleUser.selectAllUser();
		
	}
}
