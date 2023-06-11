package Controleur;

import java.util.ArrayList;

import Modele.ModeleClientPro;

public class C_ClientPro {

	public static void insertClientPro(ClientPro unClientPro) {
		
		ModeleClientPro.insertClientPro(unClientPro);
	}
	
	public static int selectIdPro(String nom, String email, String tel, String siret) {
		
		return ModeleClientPro.selectIdPro(nom, email, tel, siret);
	}
	
	public static void deleteClientPro(int iduser) {
		
		ModeleClientPro.deleteClientPro(iduser);
	}
	
	public static ArrayList<ClientPro> selectAllClientPro() {
		return ModeleClientPro.selectAllClientPro();
	}
}
