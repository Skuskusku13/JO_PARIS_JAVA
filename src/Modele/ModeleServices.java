package Modele;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

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
	
	public static void deleteService(String libelle, String tel) {
		String req = "DELETE FROM Service WHERE libelle='"+libelle+"' AND tel='"+tel+"';";
		
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
	
	public static ArrayList<Services> selectAllServices() {

		String req = "SELECT * FROM Service;";
		ArrayList<Services> lesServices = new ArrayList<Services>();

		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement();
			ResultSet desResultats = unStat.executeQuery(req);

			while (desResultats.next()) {
				Services unService = new Services(
						desResultats.getInt("idservice"),
						desResultats.getString("libelle"),
						desResultats.getString("adresse"),
						desResultats.getFloat("prix"),
						desResultats.getString("tel"),
						desResultats.getString("email"),
						desResultats.getString("image"),
						desResultats.getInt("idtypeservices")
						);
				lesServices.add(unService);
			}
			unStat.close();
			uneBdd.seDeconnecter();
		} catch (SQLException e) {
			System.out.println("Erreur d'éxecution de : " + req);
		}

		return lesServices;

	}

}
