package Modele;

import java.sql.SQLException;
import java.sql.Statement;

import Controleur.ClientPart;

public class ModeleClientPart extends ModeleUser {
	
	private static Bdd uneBdd = new Bdd("localhost:8889", "jo_paris", "root", "root");

	
	public static void insertClientPart(ClientPart unClientPart) {
		String req = "CALL insertClientPar('"
				+unClientPart.getNom()+"', '"
				+unClientPart.getEmail()+"', '"
				+unClientPart.getMdp()+"', '"
				+unClientPart.getTel()+"', '"
				+unClientPart.getRole()+"', '"
				+unClientPart.getPrenom()+"');";
		
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement();
			unStat.execute(req);
			unStat.close();
			uneBdd.seDeconnecter();
		} catch(SQLException exp) {
			System.out.println("Erreur d'execution de : " + req);
		}
	}
}
