package Modele;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Controleur.TypesServices;

public class ModeleTypesServices {

	private static Bdd uneBdd = new Bdd("localhost:8889", "jo_paris", "root", "root");

	
	public static void insertTypeService(TypesServices unTypeService) {
		String req = "INSERT INTO Typeservice VALUES(NULL, '"
				+unTypeService.getLibelle()+"');";
		
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
	
	public static ArrayList<TypesServices> selectAllTypesServices() {

		String req = "SELECT * FROM Typeservice;";
		ArrayList<TypesServices> lesTypesServices = new ArrayList<TypesServices>();

		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement();
			ResultSet desResultats = unStat.executeQuery(req);

			while (desResultats.next()) {
				TypesServices unTypeService = new TypesServices(
						desResultats.getInt("idtypeservices"),
						desResultats.getString("libelle")
						);
				lesTypesServices.add(unTypeService);
			}
			unStat.close();
			uneBdd.seDeconnecter();
		} catch (SQLException e) {
			System.out.println("Erreur d'éxecution de : " + req);
		}

		return lesTypesServices;

	}
	
	public static void deleteTypeService(String libelle) {
		String req = "DELETE FROM Typeservice WHERE libelle='"
	+ libelle + "';";
		
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

