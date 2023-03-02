package Controleur;

import java.util.ArrayList;

import Modele.ModeleClientPart;

public class C_ClientPart {

	public static void insertClientPart(ClientPart unClientPart) {
		
		ModeleClientPart.insertClientPart(unClientPart);
	}
	
	public static ArrayList<ClientPart>selectAllClientsPart() {
		return ModeleClientPart.selectAllClientPart();
	}

}
