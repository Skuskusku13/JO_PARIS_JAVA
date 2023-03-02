package Controleur;

import java.util.ArrayList;

import Modele.ModeleClientPro;

public class C_ClientPro {

	public static void insertClientPro(ClientPro unClientPro) {
		
		ModeleClientPro.insertClientPro(unClientPro);
	}
	
	public static ArrayList<ClientPro> selectAllClientPro() {
		return ModeleClientPro.selectAllClientPro();
	}
}
