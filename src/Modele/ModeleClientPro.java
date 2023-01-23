package Modele;

import java.sql.SQLException;
import java.sql.Statement;

import Controleur.ClientPro;

public class ModeleClientPro extends ModeleUser {

private static Bdd uneBdd = new Bdd("localhost:8889", "jo_paris", "root", "root");

	
	public static void insertClientPro(ClientPro unClientPro) {
		String req = "CALL insertClientPro('"
				+unClientPro.getNom()+"', '"
				+unClientPro.getEmail()+"', '"
				+unClientPro.getMdp()+"', '"
				+unClientPro.getTel()+"', '"
				+unClientPro.getRole()+"', '"
				+unClientPro.getNum_siret()+"', '"
				+unClientPro.getAdresse()+"');";
		
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
