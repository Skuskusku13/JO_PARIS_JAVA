package Controleur;

import java.util.ArrayList;

import Modele.ModeleAdmin;

public class C_Admin {
	
	public static void insertAdmin(Admin unAdmin) {
		ModeleAdmin.insertAdmin(unAdmin);
	}
	
	public static ArrayList<Admin> selectAllAdmin() {
		return ModeleAdmin.selectAllAdmin();
	}

}
