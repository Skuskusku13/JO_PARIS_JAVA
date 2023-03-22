package Modele;

import java.sql.SQLException;
import java.sql.Statement;

import Controleur.Services;

public class ModeleServices {
	
private static Bdd uneBdd = new Bdd("localhost:8889", "jo_paris", "root", "root");

	
	public static void insertService(Services unService) {
		String req = "INSERT INTO Service VALUES(NULL, '"
				+unService.getLibelle()+"', '"
				+unService.getAdresse()+"', '"
				+unService.getPrix()+"', '"
				+unService.getTel()+"', '"
				+unService.getEmail()+"', '"
				+unService.getImage()+"', '"
				+unService.getIdtypeservice()+"');";
		
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
