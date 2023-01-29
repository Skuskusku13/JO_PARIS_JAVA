package Modele;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Controleur.Categorie;

public class ModeleCategorie {

private static Bdd uneBdd = new Bdd("localhost:8889", "jo_paris", "root", "root");

	
	// fonction de connexion à la bdd 
	
	public static void insertCategorie(Categorie uneCategorie) {
		String req = "INSERT INTO categorie VALUES(NULL, '" 
				+uneCategorie.getLibelle()+"');";
		
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
	

	public static ArrayList<Categorie> selectAllCategories() {
		
		String req = "SELECT * FROM categorie;";
		ArrayList<Categorie> lesCategories = new ArrayList<Categorie>();
		
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement();
			ResultSet desResultats = unStat.executeQuery(req);
			
			while (desResultats.next()) {
				Categorie uneCategorie = new Categorie(
						desResultats.getInt("idcategorie"),
						desResultats.getString("libelle")
						);
				lesCategories.add(uneCategorie);
			}
			unStat.close();
			uneBdd.seDeconnecter();
		} catch (SQLException e) {
			System.out.println("Erreur d'éxecution de : " + req);
		}
		
		return lesCategories;
		
	}
}
