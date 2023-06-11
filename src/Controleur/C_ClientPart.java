package Controleur;

import java.util.ArrayList;

import Modele.ModeleClientPart;

public class C_ClientPart {

	public static void insertClientPart(ClientPart unClientPart) {
		
		ModeleClientPart.insertClientPart(unClientPart);
	}
	
	public static void deleteClientPar(int idclient) {
		
		ModeleClientPart.deleteClientPart(idclient);
	}
	
	public static int selectIdPart(String nom, String prenom, String email, String tel) {
		
		return ModeleClientPart.selectIdPart(nom, prenom, email, tel);
	}
	
	public static ArrayList<ClientPart>selectAllClientsPart() {
		return ModeleClientPart.selectAllClientPart();
	}

}
